package org.seitz.MerchantsGuideToTheGalaxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IOHandlerTest {

    private final ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    IOHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new IOHandler();
        System.setOut(new PrintStream(consoleOut));
    }

    @AfterEach
    public void cleanUp() {
        System.setOut(originalOut);
    }

    //--------------------------------START OF TESTS FOR outputAnswerToQuery()------------------------------------------

    @Test
    void shouldPrintEmptyLineWhenNumeralIsSet() {
        InputParser parser = new InputParser();
        handler.outputAnswerToQuery("glob is I", parser);
        assertEquals("", consoleOut.toString());
    }

    @Test
    void shouldPrintEmptyLineWhenMineralValueIsSet() {
        InputParser parser = new InputParser();
        parser.parseQuery("glob is I");
        handler.outputAnswerToQuery("glob glob Silver is 34 Credits", parser);
        assertEquals("", consoleOut.toString());
    }

    @Test
    void shouldPrintNumeralValue() {
        InputParser parser = new InputParser();
        parser.parseQuery("glob is I");
        parser.parseQuery("pish is X");
        parser.parseQuery("tegj is L");
        handler.outputAnswerToQuery("how much is pish tegj glob glob ?", parser);
        assertEquals("pish tegj glob glob is 42", consoleOut.toString());
    }

    @Test
    void shouldPrintValueOfMineral() {
        InputParser parser = new InputParser();
        parser.parseQuery("glob is I");
        parser.parseQuery("prok is V");
        parser.parseQuery("glob prok Gold is 57800 Credits");
        assertEquals("how many Credits is glob prok Gold ?", consoleOut.toString());
    }
}
