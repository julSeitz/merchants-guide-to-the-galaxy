package org.seitz.MerchantsGuideToTheGalaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    InputHandler handler;

    @BeforeEach
    void setUp() {
        handler = new InputHandler();
    }

    //--START OF TESTS FOR addIntergalacticNumeral(), getRomanNumeralFromIntergalacticNumeral() AND updateIntergalacticNumeral()

    @Test
    void shouldBeAbleToStoreAndRetrieveNumeral() {
        handler.addIntergalacticNumeral("gek", "IV");
        assertEquals("IV", handler.getRomanNumeralFromIntergalacticNumeral("gek"));
    }

    @Test
    void shouldBeAbleToUpdateAndRetrieveNumeral() throws Exception {
        handler.addIntergalacticNumeral("gek", "IX");
        handler.updateIntergalacticNumeral("gek", "IX");
        assertEquals("IX", handler.getRomanNumeralFromIntergalacticNumeral("gek"));
    }

    @Test
    void shouldThrowExceptionWhenUnknownNumeralIsUpdated() throws Exception {
        assertThrows(Exception.class,
                () -> handler.updateIntergalacticNumeral("gek", "IX"));
    }

    //--END OF TESTS FOR addIntergalacticNumeral(), getRomanNumeralFromIntergalacticNumeral() AND updateIntergalacticNumeral()

    //-----------------------------------START OF TESTS FOR isNumeralKnown()--------------------------------------------

    @Test
    void numeralShouldNotBeKnown() {
        assertFalse(handler.isNumeralKnown("gek"));
    }

    @Test
    void numeralShouldBeKnown() {
        handler.addIntergalacticNumeral("gek", "IV");
        assertTrue(handler.isNumeralKnown("gek"));
    }

    //-------------------------------------END OF TESTS FOR isNumeralKnown()--------------------------------------------

    //--------------------START OF TESTS FOR addMineralValue(), getMineralValue() AND updateMineralValue()--------------

    @Test
    void shouldBeAbleToStoreAndRetrieveMineral() {
        handler.addMineralValue("Silver", 82.1);
        assertEquals(82.1, handler.getMineralValue("Silver"));
    }

    @Test
    void shouldBeAbleToUpdateAndRetrieveMineral() throws Exception {
        handler.addMineralValue("Silver", 82.1);
        handler.updateMineralValue("Silver", 79.3);
        assertEquals(79.3, handler.getMineralValue("Silver"));
    }

    @Test
    void shouldThrowExceptionWhenUnknownMineralIsUpdated() throws Exception {
        assertThrows(Exception.class,
                () -> handler.updateMineralValue("Silver", 87.4));
    }

    @Test
    void mineralShouldNotBeKnown() {
        assertFalse(handler.isMineralKnown("Silver"));
    }

    @Test
    void mineralShouldBeKnown() {
        handler.addMineralValue("Silver", 98.2);
        assertTrue(handler.isMineralKnown("Silver"));
    }

    //----------------------END OF TESTS FOR addMineralValue(), getMineralValue() AND updateMineralValue()--------------

    //-------------------------------START OF TESTS FOR calculateValueOfOneUnit()---------------------------------------

    // Testing if division works for even results

    @Test
    void shouldEqualFive() {
        assertEquals(5.0, handler.calculateValueOfOneUnit(20, 100.0));
    }

    // Testing of division works for odd results

    @Test
    void shouldEqualTwoPointFive() {
        assertEquals(2.5, handler.calculateValueOfOneUnit(6, 15.0));
    }

    // Testing of division works for odd inputs

    @Test
    void shouldEqualFourPointThreeFive() {
        assertEquals(4.35, handler.calculateValueOfOneUnit(4, 17.4));
    }

    //-------------------------------END OF TESTS FOR calculateValueOfOneUnit()-----------------------------------------

    //-------------------------------START OF TESTS FOR getValueOfXUnits()----------------------------------------------

    @Test
    void shouldEqualSixHundredSeventyFivePointSevenFour() {
        handler.addMineralValue("Silver", 29.38);
        assertEquals(675.74, handler.getValueOfXUnits(23, "Silver"));
    }

    //-------------------------------END OF TESTS FOR getValueOfXUnits()------------------------------------------------

    //-------------------------------START OF TESTS FOR isInputKeyWord()------------------------------------------------

    @TestFactory
    Stream<DynamicTest> keywordsShouldBeRecognized() {
        InputHandler secondHandler = new InputHandler();
        List<String> inputs = Arrays.asList("how", "many", "much", "is", "Credits");

        return inputs.stream().map(
                input -> DynamicTest.dynamicTest("Checking if " + input + " is a reserved word",
                        () -> assertTrue(secondHandler.isInputKeyWord(input))
                ));
    }

    @TestFactory
    Stream<DynamicTest> wordsShouldNotBeRecognized() {
        InputHandler secondHandler = new InputHandler();
        List<String> inputs = Arrays.asList("glek", "6578", "Silver", "X");

        return inputs.stream().map(
                input -> DynamicTest.dynamicTest("Checking if " + input + " is a reserved word",
                        () -> assertFalse(secondHandler.isInputKeyWord(input))
                ));
    }



}