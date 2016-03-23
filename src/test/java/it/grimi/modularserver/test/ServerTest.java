package it.grimi.modularserver.test;

import it.grimi.modularserver.core.ModularServer;
import java.io.IOException;

public class ServerTest
{

    public static void main(String[] args) throws NumberFormatException, IOException
    {
        ModularServer server = new ModularServer();
        server.addModule(new AwesomeThings());
        server.build();
        server.start();
    }
}
