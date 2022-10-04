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

    //-----------------------------------END OF TESTS FOR isValidNumeral()----------------------------------------------
}
