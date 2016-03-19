package it.grimi.modularserver.test;

import it.grimi.modularserver.core.HServerBase;
import java.io.IOException;

public class HServerMain
{

    public static void main(String[] args) throws NumberFormatException, IOException
    {
        HServerBase hsb = new HServerBase(60001);
        hsb.addModule("it.grimi.modularserver.test.AwesomeThings");
        hsb.start();
    }
}
