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
     * The output
     */
    private final String preamble;

    /**
     * Constructor to set parser and firstOutput
     */
    public IOHandler() {
        this.parser = new InputParser();
        this.preamble = "Type EXIT to quit:";
    }

    /**
     * Retrieves user input
     *
     * @return  the user input
     */
    public String getQuery() {
        // TODO: implement
        return "";
    }

    /**
     * Answers query by writing to console
     *
     * @param query user query
     */
    public void outPutAnswerToQuery(String query) {
        // TODO: implement
    }

    /**
     * Sets up user interface for new input
     *
     */
    public void setUpUserInterface() {
        // TODO: implement
    }

    /**
     * Provides user interface
     *
     * @return  answer if user wants to continue
     */
    public boolean readAndWrite() {
        // TODO: implement
        return false;
    }
}
