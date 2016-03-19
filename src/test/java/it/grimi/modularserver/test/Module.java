package it.grimi.modularserver.test;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.grimi.modularserver.core.MUtilities;

public class Module extends MUtilities implements HttpHandler
{

    /**
     *
     * @param exch
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exch) throws IOException
    {
        String response = "<h1>SUKA</h1>";
        exch.sendResponseHeaders(200, response.length());
        OutputStream os = exch.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}