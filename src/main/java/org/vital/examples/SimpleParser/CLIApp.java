package org.vital.examples.SimpleParser;


import java.io.File;

public class CLIApp {
    private boolean commandLineArgumentsAreNotValid(String... args) {
        if (args.length != 1)
            return true;
        else
            return false;
    }

    private void printUsage() {
        System.out.println("Usage: simpleparse <file>");
    }

    public int run(String... args) {
        if (commandLineArgumentsAreNotValid(args)) {
            printUsage();
            return 0;
        }
        File inputFile = new File(args[0]);
        if (inputFile.exists()) {
            SimParser simParser = new SimParser(args[0]);
            simParser.execute();
            return 0;
        } else {
            System.out.println("[Error]: The specified input file does not exist.");
            return 1;
        }
    }
}
