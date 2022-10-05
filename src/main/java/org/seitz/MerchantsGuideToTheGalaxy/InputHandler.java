package org.seitz.MerchantsGuideToTheGalaxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.intergalacticRomanDictionary.put(intergalacticNumeral, romanNumeral);
    }

    /**
     * Updates the corresponding roman numeral, of a known intergalactic numeral
     *
     * @param intergalacticNumeral  the intergalactic numeral given
     * @param romanNumeral          the new corresponding roman numeral
     */
    public void updateIntergalacticNumeral(String intergalacticNumeral, String romanNumeral) throws Exception {
        if (!this.intergalacticRomanDictionary.containsKey(intergalacticNumeral)) {
            throw new Exception("Numeral " + intergalacticNumeral + " not known");
        }
        this.intergalacticRomanDictionary.put(intergalacticNumeral, romanNumeral);
    }

    /**
     * Adds the Credit value of one unit of the given mineral
     *
     * @param mineral   the given mineral
     * @param value     the Credit value of one unit
     */
    public void addMineralValue(String mineral, Double value) {
        this.mineralValueList.put(mineral, value);
    }


    /**
     * Updates the Credit value of one unit of a given mineral
     *
     * @param mineral   the given mineral
     * @param value     the given Credit value of one unit
     */
    public void updateMineralValue(String mineral, Double value) throws Exception{
        if (!this.mineralValueList.containsKey(mineral)) {
            throw new Exception("Mineral " + mineral + " not known");
        }
        this.mineralValueList.put(mineral, value);
    }

    /**
     * Returns the equivalent roman numeral, to a given intergalactic numeral
     *
     * @param intergalacticNumeral  the intergalactic numeral given
     * @return                      the equivalent roman numeral
     */
    public String getRomanNumeralFromIntergalacticNumeral(String intergalacticNumeral) throws Exception {
        if (!this.intergalacticRomanDictionary.containsKey(intergalacticNumeral)) {
            throw new Exception("Numeral " + intergalacticNumeral + " is unknown");
        }

        return this.intergalacticRomanDictionary.get(intergalacticNumeral);
    }

    /**
     * Returns the value of X units of a given mineral
     *
     * @param x             the number of units
     * @param mineralName   the name of the mineral
     * @return              the value of x units of the given mineral
     */
    public double getValueOfXUnits(int x, String mineralName) throws Exception{
        return x * this.getMineralValue(mineralName);
    }

    /**
     * Returns the value of one unit, from the value of x units
     *
     * @param givenAmount           number of units
     * @param valueOfGivenAmount    value of x units
     * @return                      value of one unit
     */
    public double calculateValueOfOneUnit(int givenAmount, Double valueOfGivenAmount) {
        return valueOfGivenAmount / givenAmount;
    }

    /**
     * Return the value of one unit of the given mineral
     *
     * @param mineralName   the name of the given mineral
     * @return              the value of one unit of the given mineral
     */
    public double getMineralValue(String mineralName) throws Exception {
        if (!this.mineralValueList.containsKey(mineralName)) {
            throw new Exception("Mineral " + mineralName + " is unknown");
        }
        return this.mineralValueList.get(mineralName);
    }

    /**
     * Returns boolean, answering if input is keyword
     *
     * @param input the input to check
     * @return      answer if collision with keyword
     */
    public boolean isInputKeyWord(String input) {

        Pattern pattern = Pattern.compile("how|many|much|Credits|is");
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    /**
     * Returns type of user query
     * type "1": sets numeral
     * type "2": sets mineral credit value
     * type "3": asks for numeral value
     * type "4": asks for mineral price
     * type "0": invalid query
     *
     * @param inputQuery    user input
     * @return              the type if the user input
     */
    public String determineQueryType(String inputQuery) {
        Pattern pattern = Pattern.compile("^[a-z]+ is [IVXLCDM]$");
        Matcher matcher = pattern.matcher(inputQuery);
        boolean matchFound = matcher.find();

        if (matchFound) {
            return "1";
        }



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
