/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.grimi.modularserver.core;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.HttpHandler;

/**
 *
 * @author agrimandi
 */
public class ModularServer
{

    private Server server = null;
    private Executor executor = null;

    private List<String> classHandlers = new LinkedList<>();
    private List<HttpHandler> handlers = new LinkedList<>();

    /**
     *
     */
    public ModularServer()
    {
        this.server = new Server(59997);
    }

    /**
     *
     * @param port
     */
    public ModularServer(int port)
    {
        this.server = new Server(port);
    }

    /**
     *
     * @param port
     * @param ex
     */
    public ModularServer(int port, Executor ex)
    {
        this.server = new Server(port);
        this.executor = ex;
    }

    /**
     *
     * @param ex
     */
    public ModularServer(Executor ex)
    {
        this.executor = ex;
    }

    /**
     *
     * @param module
     */
    public void addModule(String module)
    {
        this.classHandlers.add(module);
    }

    /**
     *
     * @param module
     */
    public void addModule(HttpHandler module)
    {
        this.handlers.add(module);
    }

    /**
     *
     * @return
     */
    public List<String> getClassHandlers()
    {
        return classHandlers;
    }

    /**
     *
     * @param classHandlers
     */
    public void setClassHandlers(List<String> classHandlers)
    {
        this.classHandlers = classHandlers;
    }

    /**
     *
     * @param module
     */
    public void removeModule(String module)
    {
        this.classHandlers.remove(module);
    }

    /**
     *
     * @param index
     */
    public void removeModule(int index)
    {
        this.classHandlers.remove(index);
    }

    /**
     *
     * @return
     */
    public List<HttpHandler> getHandlers()
    {
        return this.handlers;
    }

    /**
     *
     * @param handlers
     */
    public void setHandlers(List<HttpHandler> handlers)
    {
        this.handlers = handlers;
    }

    /**
     *
     * @param handler
     */
    public void removeHandlerModule(HttpHandler handler)
    {
        this.handlers.remove(handler);
    }

    /**
     *
     * @param index
     */
    public void removeHandlerModule(int index)
    {
        this.handlers.remove(index);
    }

    /**
     *
     */
    public void buildClassHandlers()
    {
        this.buildClassHandlers(this.classHandlers);
    }

    /**
     *
     */
    public void buildHandlers()
    {
        this.buildHandlers(this.handlers);
    }

    /**
     *
     * @param modules
     */
    public void buildClassHandlers(List<String> modules)
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

            System.out.println(loadedModules + " class modules loaded");

        } catch (NumberFormatException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param modules
     */
    public void buildHandlers(List<HttpHandler> modules)
    {
        int loadedModules = 0;

        for (HttpHandler module : modules) {
            this.server.addContext("/" + module.getClass().getSimpleName(), module);
            loadedModules++;
        }

        System.out.println(loadedModules + " modules loaded");
    }

    /**
     *
     * @param modules
     * @param handlers
     */
    public void build(List<String> modules, List<HttpHandler> handlers)
    {
        this.buildClassHandlers(modules);
        this.buildHandlers(handlers);
    }

    /**
     *
     */
    public void build()
    {
        this.buildClassHandlers();
        this.buildHandlers();
    }

    /**
     *
     */
    public void start()
    {
        this.server.addExecutor(this.executor);
        this.server.hsStart();
    }
}
