package org.seitz.MerchantsGuideToTheGalaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
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
                        () -> {
                            assertFalse(secondCalculator.isValidNumeral(numeral.repeat(4)));
                        }));
    }

    @TestFactory
    Stream<DynamicTest> shouldBeAbleToRepeatSymbolThreeTimes() {
        RomanNumeralCalculator secondCalculator = new RomanNumeralCalculator();
        List<String> inputNumerals = Arrays.asList("I", "X", "C", "M");

        return inputNumerals.stream().map(
                numeral -> DynamicTest.dynamicTest(numeral + " can not be repeated 4 times",
                        () -> assertTrue(secondCalculator.isValidNumeral(numeral.repeat(3)))));
    }

    //-----------------------------------END OF TESTS FOR isValidNumeral()----------------------------------------------
}
