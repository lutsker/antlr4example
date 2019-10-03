package org.vital.examples.SimpleParser;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CLIAppTest extends TestCase {
    private static final String EOL = System.getProperty("line.separator");
    private ByteArrayOutputStream testOutputStream;
    private PrintStream stdOutStream;
    private CLIApp app;

    @Before
    public void setUp() {
        testOutputStream = new ByteArrayOutputStream();
        stdOutStream = System.out;
        System.setOut(new PrintStream(testOutputStream));
        app = new CLIApp();
    }

    @After
    public void tearDown() {
        System.setOut(stdOutStream);
    }

    @Test
    public void testPrintUsageIfInsufficientCommandLineArgumentsSupplied() {
        app.run();
        assertEquals(String.format("Usage: simpleparse <file>%n", EOL), testOutputStream.toString());
    }

    @Test
    public void testPrintUsageIfMoreThanOneCommandLineArgumentSupplied() {
        app.run("one", "two");
        assertEquals(String.format("Usage: simpleparse <file>%n", EOL), testOutputStream.toString());
    }

    @Test
    public void testPrintErrorAndExitsIfSpecifiedFileDoesNotExist() {
        app.run("hello.tx");
        assertEquals(String.format("[Error]: The specified input file does not exist.%n", EOL), testOutputStream.toString());
    }
}
