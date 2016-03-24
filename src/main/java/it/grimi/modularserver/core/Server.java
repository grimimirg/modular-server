package it.grimi.modularserver.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server
{

    private HttpServer server = null;

    public Server(int socket)
    {
        try {
            System.out.println("Server is running on port " + socket);
            this.server = HttpServer.create(new InetSocketAddress(socket), 0);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addContext(String path, HttpHandler handler)
    {
        System.out.println(path + " added");
        this.server.createContext(path, handler);
    }

    public void addExecutor(Executor ex)
    {
        this.server.setExecutor(ex);
    }

    public void start()
    {
        this.server.start();
    }

}
