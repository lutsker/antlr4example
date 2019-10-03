package org.vital.examples.SimpleParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;

public class SimParser {

    private String fileName;

    public SimParser(String fileName) {
        this.fileName = fileName;
    }

    public void execute() {
        /////////////////////////////////
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ANTLRInputStream inputStream = new ANTLRInputStream(fileInputStream);
            SimpleLexer simpleLexer = new SimpleLexer(inputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(simpleLexer);
            SimpleParser simpleParser = new SimpleParser(commonTokenStream);

            //ParseTree tree = simpleParser.simple();
            SimpleParser.SimpleContext tree = simpleParser.simple(); // parse "simple" see grammar
            TestListener extractor = new TestListener();

            ParseTreeWalker.DEFAULT.walk(extractor, tree);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
