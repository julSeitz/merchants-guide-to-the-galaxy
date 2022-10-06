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
}
