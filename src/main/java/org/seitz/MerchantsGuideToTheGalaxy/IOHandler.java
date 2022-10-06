package org.seitz.MerchantsGuideToTheGalaxy;

/**
 * This class provides tools to interact with the user
 */
public class IOHandler {

    /**
     * The parser to process user input
     */
    private InputParser parser;

    /**
     * The output displayed at program start
     */
    private final String firstOutput;

    /**
     * Constructor to set parser and firstOutput
     */
    public IOHandler() {
        this.parser = new InputParser();
        this.firstOutput = "Type EXIT to quit:";
    }

    /**
     * Retrieves user input
     *
     * @return  the user input
     */
    private String getQuery() {
        // TODO: implement
        return "";
    }

    /**
     * Provides user interface and displays appropriate answer to user input
     */
    public void readAndWrite() {
        // TODO: implement
    }
}
