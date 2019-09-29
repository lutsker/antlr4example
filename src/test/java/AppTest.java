package org.vital.examples.SimpleParser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    private SimpleErrorListener errorListener;
    private SimpleLexer simpleLexer;

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    private SimpleParser setup(String input) {
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        this.simpleLexer = new SimpleLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(this.simpleLexer);
        SimpleParser simpleParser = new SimpleParser(commonTokenStream);

        StringWriter writer = new StringWriter();
        this.errorListener = new SimpleErrorListener(writer);

        simpleLexer.removeErrorListeners();
        simpleParser.removeErrorListeners();
        simpleParser.addErrorListener(this.errorListener);

        return simpleParser;
    }


    public void testLexerEvaluatesEmptyHOSTS() {
        SimpleParser parser = setup("HOSTS {}");
        SimpleParser.SimpleContext context = parser.simple();
        TokenStream recognized_tokens = parser.getTokenStream();

        assertEquals(SimpleLexer.KWHOSTS, recognized_tokens.get(0).getType());
        assertEquals(SimpleLexer.BLOCKOPEN, recognized_tokens.get(1).getType());
        assertEquals(SimpleLexer.BLOCKCLOSE, recognized_tokens.get(2).getType());
    }

}
