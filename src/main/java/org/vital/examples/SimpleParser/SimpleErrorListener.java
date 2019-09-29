package org.vital.examples.SimpleParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.*;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SimpleErrorListener extends BaseErrorListener {

    private StringWriter _stream;
    private String _symbol;

    public SimpleErrorListener(StringWriter stream) {
        this._stream = stream;
    }

    public String get_symbol() {
        return _symbol;
    }

    public StringWriter get_stream() {
        return _stream;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);

        this._stream.write(msg);
        this._stream.write(System.getProperty("line.separator"));

        if(offendingSymbol.getClass().getName() == "org.antlr.v4.runtime.CommonToken") {
            CommonToken token = (CommonToken) offendingSymbol;
            this._symbol = token.getText();
        }
    }
}
