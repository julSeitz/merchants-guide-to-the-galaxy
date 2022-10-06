package org.seitz.MerchantsGuideToTheGalaxy;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides tools to evaluate and save user input regarding minerals and intergalactic numerals
 */
public class InputParser {

    /**
     * Calculator to handle roman numerals
     */
    private RomanNumeralCalculator calculator;

    /**
     * Constructor which creates new internal calculator for roman numerals
     */
    public InputParser() {
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
     * Adds the Credit value of one unit of the given mineral
     *
     * @param mineral   the given mineral
     * @param value     the Credit value of one unit
     */
    public void setMineralValue(String mineral, Double value) {
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

        Pattern pattern = Pattern.compile("^how$|^many$|^much$|^Credits$|^is$");
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

        pattern = Pattern.compile("^(?:[a-z]+ )+[A-Z][a-z]+ is [1-9]\\d* Credits$");
        matcher = pattern.matcher(inputQuery);
        matchFound = matcher.find();

        if (matchFound) {
            return "2";
        }

        pattern = Pattern.compile("^how much is (?:[a-z]+ )+\\?$");
        matcher = pattern.matcher(inputQuery);
        matchFound = matcher.find();

        if (matchFound) {
            return "3";
        }

        pattern = Pattern.compile("^how many Credits is (?:[a-z]+ )+[A-Z]+[a-z]+ \\?$");
        matcher = pattern.matcher(inputQuery);
        matchFound = matcher.find();

        if (matchFound) {
            return "4";
        }

        return "0";
    }

    /**
     * Returns a list of arguments extracted from the query
     *
     * @param inputQuery    the user input
     * @param queryType     the type of user input
     * @return              the arguments provided by user
     */
    public List<String> extractDataFromQuery(String inputQuery, String queryType) {

        switch (queryType) {
            case "1":
                Pattern pattern = Pattern.compile("^([a-z]+) is ([IVXLCDM])$");
                Matcher matcher = pattern.matcher(inputQuery);
                if (matcher.find()) {
                    return Arrays.asList(matcher.group(1), matcher.group(2));
                }
                break;
            case "2":
                pattern = Pattern.compile("^((?:[a-z]+ )+)([A-Z][a-z]+) is ([1-9]\\d*) Credits$");
                matcher = pattern.matcher(inputQuery);
                if (matcher.find()) {
                    return Arrays.asList(matcher.group(1).trim(), matcher.group(2), matcher.group(3));
                }
                break;
            case "3":
                pattern = Pattern.compile("^how much is ((?:[a-z]+ )+)\\?$");
                matcher = pattern.matcher(inputQuery);
                if (matcher.find()) {
                    return List.of(matcher.group(1).trim());
                }
                break;
            case "4":
                pattern = Pattern.compile("^how many Credits is ((?:[a-z]+ )+)([A-Z]+[a-z]+) \\?$");
                matcher = pattern.matcher(inputQuery);
                if (matcher.find()) {
                    return Arrays.asList(matcher.group(1).trim(), matcher.group(2));
                }
                break;
        }
        return new ArrayList<>();
    }

    /**
     * Returns multi symbol roman numeral from multi symbol intergalactic numeral
     *
     * @param intergalacticNumeral  the multi symbol intergalactic numeral
     * @return                      the multi symbol roman numeral
     * @throws Exception            throws exception in case symbol is not known or illegal
     */
    private String translateIntergalacticNumeral(String intergalacticNumeral) throws Exception{
        // split intergalactic numeral in individual numerals
        String[] intergalacticNumerals = intergalacticNumeral.split(" ");
        StringBuilder romanNumeral = new StringBuilder();
        for (String numeral : intergalacticNumerals) {
            // check if individual numeral is illegal
            if (this.isInputKeyWord(numeral)) {
                throw new Exception("Numeral contains reserved keyword");
            }
            romanNumeral.append(this.getRomanNumeralFromIntergalacticNumeral(numeral));
        }
        return romanNumeral.toString();
    }

    /**
     * Parsed user query and returns answer if applicable
     *
     * @param queryInput    the input query
     * @return              answer to query, is "" when answer is required
     */
    public String parseQuery(String queryInput) {
        String queryType = this.determineQueryType(queryInput);
        List<String> parameters = this.extractDataFromQuery(queryInput, queryType);

        switch (queryType) {
            case "1": {
                String intergalacticNumeral = parameters.get(0);
                String romanNumeral = parameters.get(1);

                // add new intergalactic numeral to dictionary
                if (this.isInputKeyWord(intergalacticNumeral)) {
                    return "I have no idea what you are talking about";
                }
                this.addIntergalacticNumeral(intergalacticNumeral, romanNumeral);
                return "";
            }
            case "2": {
                String mineralName = parameters.get(1);
                String intergalacticNumeral = parameters.get(0);
                String numberOfUnits = parameters.get(2);

                String romanNumeral;

                // translate intergalactic numeral to roman numeral
                try {
                    romanNumeral = this.translateIntergalacticNumeral(intergalacticNumeral);
                } catch (Exception e) {
                    return "I have no idea what you are talking about";
                }

                // get integer value number of units from roman numeral
                int numeralValue = this.calculator.getNumeralValue(romanNumeral);

                // calculate value of one unit of the mineral
                double valueOfOneUnitOfMineral = this.calculateValueOfOneUnit(numeralValue, Double.parseDouble(numberOfUnits));

                // check if name of mineral is illegal
                if (this.isInputKeyWord(mineralName)) {
                    return "I have no idea what you are talking about";
                }
                // add value of one unit of the given mineral to price list
                this.setMineralValue(mineralName, valueOfOneUnitOfMineral);

                return "";
            }
            case "3": {
                String intergalacticNumeral = parameters.get(0);
                String romanNumeral;

                // translate intergalactic numeral to roman numeral
                try {
                    romanNumeral = this.translateIntergalacticNumeral(intergalacticNumeral);
                } catch (Exception e) {
                    return "I have no idea what you are talking about";
                }
                int numeralValue = this.calculator.getNumeralValue(romanNumeral);
                return parameters.get(0) + " is " + numeralValue;
            }
            case "4":
                String intergalacticNumeral = parameters.get(0);
                String mineralName = parameters.get(1);

                String romanNumeral;

                // translate intergalactic numeral to roman numeral
                try {
                    romanNumeral = this.translateIntergalacticNumeral(intergalacticNumeral);
                } catch (Exception e) {
                    return "I have no idea what you are talking about";
                }
                int numeralValue = this.calculator.getNumeralValue(romanNumeral);

                // check if mineral name is illegal
                if (this.isInputKeyWord(mineralName)) {
                    return "I have no idea what you are talking about";
                }

                double calculatedPrice;
                // get price of X units of the given mineral, where X is the value of the intergalactic numeral
                try {
                    calculatedPrice = this.getValueOfXUnits(numeralValue, mineralName);
                }  catch (Exception e) {
                    return "I have no idea what you are talking about";
                }


                StringBuilder answer = new StringBuilder(intergalacticNumeral + " " + mineralName + " is ");

                // build answer with price as converted integer, instead of converted double, when price is a whole number
                if (calculatedPrice % 1 == 0) {
                    answer.append((int) calculatedPrice);
                } else {
                    answer.append(calculatedPrice);
                }
                return answer.append(" Credits").toString();
        }
        return "I have no idea what you are talking about";
    }
}
