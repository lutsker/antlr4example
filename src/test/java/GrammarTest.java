package org.vital.examples.SimpleParser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GrammarTest extends TestCase {

    private SimpleErrorListener errorListener;
    private SimpleLexer simpleLexer;

    public GrammarTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(GrammarTest.class);
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
        assertEquals(SimpleLexer.BO, recognized_tokens.get(1).getType());
        assertEquals(SimpleLexer.BC, recognized_tokens.get(2).getType());
    }

    public void testLexerRecognizesString() {
        SimpleParser parser = setup("HOSTS { HOST name_a1 = { val = \"../../myfolder/myfile.com\"; } } " );
        SimpleParser.HostdefContext context = parser.hostdef();
        TokenStream recognized_tokens = parser.getTokenStream();
        assertEquals(SimpleLexer.STRING, recognized_tokens.get(8).getType() );
    }

    public void testLexerInterpretsEscapedQuotesAsString() {
        SimpleParser parser = setup("HOSTS { HOST name_a1 = { val = \"../../\"m\"yfolder/myfile.com\"; } } " );
        SimpleParser.HostdefContext context = parser.hostdef();
        TokenStream recognized_tokens = parser.getTokenStream();
        assertEquals(SimpleLexer.STRING, recognized_tokens.get(8).getType() );
    }

    public void testNumberOfHostsIs2() {
        SimpleParser parser = setup("HOSTS { HOST node = {} HOST node = {}}");
        SimpleParser.HostdefContext context = parser.hostdef();
        assertEquals(2, context.KWHOST().size());
    }
}
