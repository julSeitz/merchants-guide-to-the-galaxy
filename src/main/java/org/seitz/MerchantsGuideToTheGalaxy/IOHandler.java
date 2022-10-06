package org.seitz.MerchantsGuideToTheGalaxy;

import java.util.Scanner;

/**
 * This class provides tools to interact with the user
 */
public class IOHandler {

    /**
     * The output
     */
    private String preamble;

    /**
     * Constructor to set parser and firstOutput
     */
    public IOHandler() {
        this.preamble = "Type EXIT to quit:";
    }

    /**
     * Answers query by writing to console
     *
     * @param query user query
     */
    public void outputAnswerToQuery(String query, InputParser parser) {
        // TODO: implement
    }

    /**
     * Provides user interface
     *
     * @param scanner   scanner to get user input
     * @return          answer if user wants to continue
     */
    public boolean readAndWrite(Scanner scanner, InputParser parser) {
        // TODO: implement
        return true;
    }
}
