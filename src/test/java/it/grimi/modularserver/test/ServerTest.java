package it.grimi.modularserver.test;

import it.grimi.modularserver.core.ModularServer;
import java.io.IOException;

public class ServerTest
{

    public static void main(String[] args) throws NumberFormatException, IOException
    {
        ModularServer hsb = new ModularServer(60001);
        hsb.addModule("it.grimi.modularserver.test.AwesomeThings");
        hsb.start();
    }
}
