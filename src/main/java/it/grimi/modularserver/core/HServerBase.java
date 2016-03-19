package it.grimi.modularserver.core;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.HttpHandler;

public class HServerBase
{

    private HServer server = null;
    private Executor executor = null;

    private List<String> modules = new LinkedList<>();

    public HServerBase()
    {
        this.server = new HServer(80);
    }

    public HServerBase(int port)
    {
        this.server = new HServer(port);
    }

    public HServerBase(int port, Executor ex)
    {
        this.server = new HServer(port);
        this.executor = ex;
    }

    public HServerBase(Executor ex)
    {
        this.executor = ex;
    }

    public void addModule(String module)
    {
        this.modules.add(module);
    }

    public List<String> getModules()
    {
        return modules;
    }

    public void setModules(List<String> modules)
    {
        this.modules = modules;
    }

    public void removeModule(String module)
    {
        this.modules.remove(module);
    }

    public void removeModule(int index)
    {
        this.modules.remove(index);
    }

    public void start()
    {
        this.start(this.modules);
    }

    public void start(List<String> modules)
    {
        try {
            int loadedModules = 0;

            for (String module : modules) {
                System.out.println("Loading " + module);
                Class<HttpHandler> customModule = null;

                try {
                    customModule = (Class<HttpHandler>) Class.forName(module);
                } catch (ClassNotFoundException e) {
                    System.out.println("Unable to load " + module);
                }

                if (customModule != null) {
                    this.server.addContext("/" + customModule.getSimpleName(), (HttpHandler) customModule.newInstance());
                    loadedModules++;
                } else {
                    System.out.println("Module " + module + " will be ignored.");
                }
            }

            System.out.println(loadedModules + " modules loaded");

            this.server.addExecutor(this.executor);
            this.server.hsStart();

        } catch (NumberFormatException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
