package org.seitz.MerchantsGuideToTheGalaxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides tools to evaluate and save user input regarding minerals and intergalactic numerals
 */
public class InputHandler {

    /**
     * Calculator to handle roman numerals
     */
    private RomanNumeralCalculator calculator;

    /**
     * Constructor which creates new internal calculator for roman numerals
     */
    public InputHandler() {
        this.calculator = new RomanNumeralCalculator();
    }

    /**
     * A dictionary to save the corresponding roman numeral to an intergalactic numeral
     */
    private Map<String, String> intergalacticRomanDictionary = new HashMap<>();

    /**
     * A list to save the Credit value of one unit of a given mineral
     */
    private Map<String, Double> mineralValueList = new HashMap<>();

    /**
     * Adds a previously unknown intergalactic numeral to the dictionary, with it's corresponding roman numeral
     *
     * @param intergalacticNumeral  the intergalactic numeral given
     * @param romanNumeral          the corresponding roman numeral
     */
    public void addIntergalacticNumeral(String intergalacticNumeral, String romanNumeral) {

    }

    /**
     * Returns boolean, answering if given numeral already has corresponding roman numeral
     *
     * @param numeral   the given intergalactic numeral
     * @return          answer if numeral is known
     */
    public boolean isNumeralKnown(String numeral) {
        return false;
    }

    /**
     * Updates the corresponding roman numeral, of a known intergalactic numeral
     *
     * @param intergalacticNumeral  the intergalactic numeral given
     * @param romanNumeral          the new corresponding roman numeral
     */
    public void updateIntergalacticNumeral(String intergalacticNumeral, String romanNumeral) {

    }

    /**
     * Adds the Credit value of one unit of the given mineral
     *
     * @param mineral   the given mineral
     * @param value     the Credit value of one unit
     */
    public void addMineralValue(String mineral, Double value) {

    }


    /**
     * Updates the Credit value of one unit of a given mineral
     *
     * @param mineral   the given mineral
     * @param value     the given Credit value of one unit
     */
    public void updateMineralValue(String mineral, Double value) {

    }

    /**
     * Returns boolean, answering if given mineral already has a corresponding Credit value
     *
     * @param mineral   the given mineral name
     * @return          answer if mineral already has a corresponding Credit value
     */
    public boolean isMineralKnown(String mineral) {
        return false;
    }

    /**
     * Returns the equivalent roman numeral, to a given intergalactic numeral
     *
     * @param intergalacticNumeral  the intergalactic numeral given
     * @return                      the equivalent roman numeral
     */
    public String getRomanNumeralFromIntergalacticNumeral(String intergalacticNumeral) {
        return "";
    }

    /**
     * Returns the value of X units of a given mineral
     *
     * @param x             the number of units
     * @param mineralName   the name of the mineral
     * @return              the value of x units of the given mineral
     */
    public double getValueOfXUnits(int x, String mineralName) {
        return 0;
    }

    /**
     * Returns the value of one unit, from the value of x units
     *
     * @param givenAmount           number of units
     * @param valueOfGivenAmount    value of x units
     * @return                      value of one unit
     */
    public double calculateValueOfOneUnit(int givenAmount, Double valueOfGivenAmount) {
        return 0;
    }

    /**
     * Return the value of one unit of the given mineral
     *
     * @param mineralName   the name of the given mineral
     * @return              the value of one unit of the given mineral
     */
    public double getMineralValue(String mineralName) {
        return 0;
    }

    /**
     * Returns boolean, answering if input is keyword
     *
     * @param input the input to check
     * @return      answer if collision with keyword
     */
    public boolean isInputKeyWord(String input) {
        return false;
    }

    /**
     * Returns type of user query
     *
     * @param inputQuery    user input
     * @return              the type if the user input
     */
    private String determineQueryType(String inputQuery) {
        return "";
    }

    /**
     * Returns a list of arguments extracted from the query
     *
     * @param inputQuery    the user input
     * @param queryType     the type of user input
     * @return              the arguments provided by user
     */
    public List<String> extractDataFromQuery(String inputQuery, String queryType) {
        return new ArrayList<>();
    }

}
