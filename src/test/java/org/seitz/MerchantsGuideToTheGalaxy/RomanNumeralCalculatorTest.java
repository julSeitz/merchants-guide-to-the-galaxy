package org.seitz.MerchantsGuideToTheGalaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralCalculatorTest {

    RomanNumeralCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new RomanNumeralCalculator();
    }

    //-----------------------------------START OF TESTS FOR isValidNumeral()--------------------------------------------

    // Testing if "I", "X", "C" or "M" can only be repeated up to 3 times in succession

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToRepeatSymbolFourTimes() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputNumerals = Arrays.asList("I", "X", "C", "M");

        return inputNumerals.stream().map(
                numeral -> DynamicTest.dynamicTest(numeral + " can not be repeated 4 times",
                        () -> assertFalse(secondCalculator.isValidNumeral(numeral.repeat(4)))));
    }

    @TestFactory
    Stream<DynamicTest> shouldBeAbleToRepeatSymbolThreeTimes() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputNumerals = Arrays.asList("I", "X", "C", "M");

        return inputNumerals.stream().map(
                numeral -> DynamicTest.dynamicTest(numeral + " can be repeated 3 times",
                        () -> assertTrue(secondCalculator.isValidNumeral(numeral.repeat(3)))));
    }

    /*
        Testing if "I", "X", "C" or "M" can be repeated a fourth time, if the fourth occurrence is preceded by a smaller
        value
     */

    @Test
    void shouldBeValidToRepeatXFourTimesWithSubtraction() {
        assertTrue(calculator.isValidNumeral("XXXIX"));
    }

    @Test
    void shouldBeValidToRepeatCFourTimesWithSubtraction() {
        assertTrue(calculator.isValidNumeral("CCCXC"));
    }

    @Test
    void shouldBeValidToRepeatMFourTimesWithSubtraction() {
        assertTrue(calculator.isValidNumeral("MMMCM"));
    }

    // Testing if "D", "L" and "V" can not be repeated

    @Test
    void shouldNotBeAbleToRepeatD() {
        assertFalse(calculator.isValidNumeral("DCD"));
    }

    @Test
    void shouldNotBeAbleToRepeatL() {
        assertFalse(calculator.isValidNumeral("LL"));
    }

    @Test
    void shouldNotBeAbleToRepeatV() {
        assertFalse(calculator.isValidNumeral("VIV"));
    }

    // Testing if "I" can only be subtracted from "X" and "V"

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToSubtractIFromAnythingButXAndV() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputNumerals = Arrays.asList("L", "C", "D", "M");
        String subtractor = "I";

        return inputNumerals.stream().map(
                numeral -> DynamicTest.dynamicTest(subtractor + " can not be subtracted from " + numeral,
                        () -> assertFalse(secondCalculator.isValidNumeral(subtractor + numeral))
                ));
    }

    @Test
    void shouldBeAbleToSubtractIFromX() {
        assertTrue(calculator.isValidNumeral("IX"));
    }

    @Test
    void shouldBeAbleToSubtractIFromV() {
        assertTrue(calculator.isValidNumeral("IV"));
    }

    // Testing if "X" can only be subtracted from "L" and "C"

    @Test
    void shouldNotBeAbleToSubtractXFromD() {
        assertFalse(calculator.isValidNumeral("XD"));
    }

    @Test
    void shouldNotBeAbleToSubtractXFromM() {
        assertFalse(calculator.isValidNumeral("XM"));
    }

    @Test
    void shouldBeAbleToSubtractXFromL() {
        assertTrue(calculator.isValidNumeral("XL"));
    }

    @Test
    void shouldBeAbleToSubtractXFromC() {
        assertTrue(calculator.isValidNumeral("XC"));
    }

    // Testing if "V", "L" and "D" can never be subtracted

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToSubtractV() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputNumerals = Arrays.asList("X", "L", "C", "D", "M");
        String subtractor = "V";

        return inputNumerals.stream().map(
                numeral -> DynamicTest.dynamicTest(subtractor + " can not be subtracted from " + numeral,
                        () -> assertFalse(secondCalculator.isValidNumeral(subtractor + numeral))
                ));
    }

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToSubtractL() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputNumerals = Arrays.asList("C", "D", "M");
        String subtractor = "L";

        return inputNumerals.stream().map(
                numeral -> DynamicTest.dynamicTest(subtractor + " can not be subtracted from " + numeral,
                        () -> assertFalse(secondCalculator.isValidNumeral(subtractor + numeral))
                ));
    }

    @Test
    void shouldNotBeAbleToSubtractD() {
        assertFalse(calculator.isValidNumeral("DM"));
    }

    // Testing if only one small-value symbol can be subtracted from any large-value symbol

    @Test
    void shouldNotBeAbleToDoMultipleSubtractions() {
        assertFalse(calculator.isValidNumeral("IIX"));
    }

    @Test
    void shouldNotBeAbleToSubtractTheResultOfASubtraction() {
        assertFalse(calculator.isValidNumeral("VIX"));
    }

    // Testing if numeral can consist of illegal characters

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToEnterALowerCaseLetter() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputSymbols = Arrays.asList("abcdefghijklmnopqrstuvwxyz".split(""));

        return inputSymbols.stream().map(
                symbol -> DynamicTest.dynamicTest(symbol + " contains an illegal character",
                        () -> assertFalse(secondCalculator.isValidNumeral(symbol))
                ));
    }

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToEnterTheseUpperCaseLetters() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputSymbols = Arrays.asList("ABEFGHJKNOPQRSTUWYZ".split(""));

        return inputSymbols.stream().map(
                symbol -> DynamicTest.dynamicTest(symbol + " contains an illegal character",
                        () -> assertFalse(secondCalculator.isValidNumeral(symbol))
                ));
    }

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToEnterNumber() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputSymbols = Arrays.asList("0123456789".split(""));

        return inputSymbols.stream().map(
                symbol -> DynamicTest.dynamicTest(symbol + " contains an illegal character",
                        () -> assertFalse(secondCalculator.isValidNumeral(symbol))
                ));
    }

    @TestFactory
    Stream<DynamicTest> shouldNotBeAbleToEnterSpecialCharacters() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputSymbols = Arrays.asList(" \"!#$%&'()*+,-./:;<=>?@[]^_`\\{|}~".split(""));

        return inputSymbols.stream().map(
                symbol -> DynamicTest.dynamicTest(symbol + " contains an illegal character",
                        () -> assertFalse(secondCalculator.isValidNumeral(symbol))
                ));
    }

    //-----------------------------------END OF TESTS FOR isValidNumeral()----------------------------------------------

    //----------------------------------START OF TESTS FOR getNumeralValue()--------------------------------------------

    // Testing if each numeral is implemented correctly

    @TestFactory
    Stream<DynamicTest> isEachSymbolCorrect() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputNumerals = Arrays.asList("I", "V", "X", "L", "C", "D", "M");
        List<Integer> outputNumbers = Arrays.asList(1, 5, 10, 50, 100, 500, 1000);

        return inputNumerals.stream().map(
                numeral -> DynamicTest.dynamicTest(numeral + " equals " + outputNumbers.get(inputNumerals.indexOf(numeral)),
                        () -> assertEquals(outputNumbers.get(inputNumerals.indexOf(numeral)), secondCalculator.getNumeralValue(numeral))
                ));
    }

    // Testing if addition works correctly

    @Test
    void shouldEqualOnethousandsixhundredsixtysix() throws Exception {
        assertEquals(1666, calculator.getNumeralValue("MDCLXVI"));
    }

    // Testing if subtraction works at the beginning and the end of a numeral, for numerals of even length

    @Test
    void shouldEqualFour() throws IllegalArgumentException {
        assertEquals(4, calculator.getNumeralValue("IV"));
    }


    //-----------------------------------END OF TESTS FOR getNumeralValue()----------------------------------------------


}
