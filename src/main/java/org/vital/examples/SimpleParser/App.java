package org.vital.examples.SimpleParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class App {
    public static void main(String[] args) {

        try {
            FileInputStream fileInputStream = new FileInputStream(args[0]);
            ANTLRInputStream inputStream = new ANTLRInputStream(fileInputStream);
            SimpleLexer simpleLexer = new SimpleLexer(inputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(simpleLexer);
            SimpleParser simpleParser = new SimpleParser(commonTokenStream);

            //ParseTree tree = simpleParser.simple();
            SimpleParser.SimpleContext tree = simpleParser.simple(); // parse "simple" see grammar
            TestListener extractor = new TestListener();

            ParseTreeWalker.DEFAULT.walk(extractor, tree);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
