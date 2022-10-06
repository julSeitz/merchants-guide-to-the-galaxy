package org.seitz.MerchantsGuideToTheGalaxy;

import java.util.Scanner;

/**
 * This class provides tools to interact with the user
 */
public class IOHandler {

    /**
     * The parser to process user input
     */
    private InputParser parser;

    /**
     * The output
     */
    private String preamble;

    /**
     * Constructor to set parser and firstOutput
     */
    public IOHandler() {
        this.parser = new InputParser();
        this.preamble = "Type EXIT to quit:";
    }

    /**
     * Answers query by writing to console
     *
     * @param query user query
     */
    public void outputAnswerToQuery(String query) {
        // TODO: implement
    }

    /**
     * Provides user interface
     *
     * @param scanner   scanner to get user input
     * @return          answer if user wants to continue
     */
    public boolean readAndWrite(Scanner scanner) {
        // TODO: implement
        return true;
    }
}
