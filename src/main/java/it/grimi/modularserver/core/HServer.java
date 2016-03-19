package it.grimi.modularserver.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HServer
{

    private HttpServer hserver = null;

    public HServer(int socket)
    {
        try {
            System.out.println("Starting...");
            this.hserver = HttpServer.create(new InetSocketAddress(socket), 0);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addContext(String path, HttpHandler handler)
    {
        this.hserver.createContext(path, handler);
    }

    public void addExecutor(Executor ex)
    {
        this.hserver.setExecutor(ex);
    }

    public void hsStart()
    {
        this.hserver.start();
    }

}
