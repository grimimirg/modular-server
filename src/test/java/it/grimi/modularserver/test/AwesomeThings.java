package it.grimi.modularserver.test;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.grimi.modularserver.core.ModuleUtilities;

public class AwesomeThings extends ModuleUtilities implements HttpHandler
{

    /**
     *
     * @param exch
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exch) throws IOException
    {
        String response = "<h1>SUKAAAA!!!</h1>";
        exch.sendResponseHeaders(200, response.length());
        OutputStream os = exch.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
