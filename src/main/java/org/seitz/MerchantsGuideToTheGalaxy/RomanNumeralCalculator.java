package org.seitz.MerchantsGuideToTheGalaxy;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides tools to check if a roman numeral is valid and to calculate it's value
 * as an arabic decimal numeral.
 */
public class RomanNumeralCalculator {

    /**
     * An internal dictionary translating between roman and arabic numerals
     */
    private Map<String, Integer> romanNumerals = new HashMap<>();

    /**
     * This constructor sets the values for each roman numeral
     */
    public RomanNumeralCalculator() {
        this.romanNumerals.put("I", 1);
        this.romanNumerals.put("V", 5);
        this.romanNumerals.put("X", 10);
        this.romanNumerals.put("L", 50);
        this.romanNumerals.put("C", 100);
        this.romanNumerals.put("D", 500);
        this.romanNumerals.put("M", 1000);
    }

    /**
     * Returns a boolean value, answering if the given roman numeral complies
     * with the outlined restrictions.
     *
     * @param numeral   roman numeral
     * @return          answer if the given numeral is valid
     */
    public boolean isValidNumeral(String numeral) {

        // check for illegal characters

        Pattern pattern = Pattern.compile("[^IVXLCDM]");
        Matcher matcher = pattern.matcher(numeral);
        boolean matchFound = matcher.find();

        if (matchFound) {
            return false;
        }

        // check for 3 times "I", "X", "C" or "M" rule

        pattern = Pattern.compile("[I]{4,}|[X]{3}[MCDLV]*[X]|[C]{3}[MDLVI]*[C]|[M]{3}[VXDIL]*[M]");
        matcher = pattern.matcher(numeral);
        matchFound = matcher.find();

        if (matchFound) {
            return false;
        }

        // check for subtraction restrictions ("I" can only be subtracted from "V" and "X", etc.)

        pattern = Pattern.compile("[I]+[MDCL]|[X]+[MD]|[V]+[XLCDM]|[L]+[CDM]|[D]+[M]");
        matcher = pattern.matcher(numeral);
        matchFound = matcher.find();

        if (matchFound) {
            return false;
        }

        // check if "D", "L" and "V" can never be repeated

        pattern = Pattern.compile("[D]+.*[D]+|[L]+.*[L]+|[V]+.*[V]+");
        matcher = pattern.matcher(numeral);
        matchFound = matcher.find();

        if (matchFound) {
            return false;
        }

        // check for only allowing one subtraction per symbol

        pattern = Pattern.compile("[I]{2,}[MCDLXV]+|[IV]{2,}[MDLCX]+|[IVX]{2,}[MDLC]+|[IVXL]{2,}[MDC]+|[IVXLC]{2,}[MD]+|[IVXLCD]{2,}[M]+");
        matcher = pattern.matcher(numeral);
        matchFound = matcher.find();

        return !matchFound;
    }

    /**
     * Returns the value of the given roman numeral as an arabic decimal numeral
     *
     * @param numeral   roman numeral
     * @return          the value of the given roman numeral as an arabic decimal numeral
     */
    private int calculateValue(String numeral) {
        // TODO: implement
        return 0;
    }

    /**
     * Returns the value of the given roman numeral as an arabic decimal numeral, but checks
     * if roman numeral is valid.
     * Throws exception when numeral is not valid.
     *
     * @param numeral   roman numeral
     * @return          the value of the given roman numeral as an arabic decimal numeral
     */
    public int getNumeralValue(String numeral) {
        // TODO: implement
        return 0;
    }
}
