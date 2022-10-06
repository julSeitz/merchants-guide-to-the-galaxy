package org.seitz.MerchantsGuideToTheGalaxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class IOHandlerTest {

    private final ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    InputParser parser;

    IOHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new IOHandler();
        parser = new InputParser();
        System.setOut(new PrintStream(consoleOut));
    }

    @AfterEach
    public void cleanUp() {
        handler = null;
        parser =  null;
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    //--------------------------------START OF TESTS FOR outputAnswerToQuery()------------------------------------------

    @Test
    void shouldPrintEmptyLineWhenNumeralIsSet() {
        handler.outputAnswerToQuery("glob is I", parser);
        assertEquals("", consoleOut.toString());
    }

    @Test
    void shouldPrintEmptyLineWhenMineralValueIsSet() {
        parser.parseQuery("glob is I");
        handler.outputAnswerToQuery("glob glob Silver is 34 Credits", parser);
        assertEquals("", consoleOut.toString());
    }

    @Test
    void shouldPrintNumeralValue() {
        parser.parseQuery("glob is I");
        parser.parseQuery("pish is X");
        parser.parseQuery("tegj is L");
        handler.outputAnswerToQuery("how much is pish tegj glob glob ?", parser);
        assertEquals("> pish tegj glob glob is 42\n", consoleOut.toString());
    }

    @Test
    void shouldPrintValueOfMineral() {
        parser.parseQuery("glob is I");
        parser.parseQuery("prok is V");
        parser.parseQuery("glob prok Gold is 57800 Credits");
        handler.outputAnswerToQuery("how many Credits is glob prok Gold ?", parser);
        assertEquals("> glob prok Gold is 57800 Credits\n", consoleOut.toString());
    }

    //----------------------------------END OF TESTS FOR outputAnswerToQuery()------------------------------------------

    //---------------------------------------START OF TESTS FOR readAndWrite()------------------------------------------

    @Test
    void shouldReturnFalseWhenUserInputIsNotExit() {
        ByteArrayInputStream in = new ByteArrayInputStream("something".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);
        assertFalse(handler.readAndWrite(scanner, parser));
    }
}
