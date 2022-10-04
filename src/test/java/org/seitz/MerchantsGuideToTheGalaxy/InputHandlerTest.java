package org.seitz.MerchantsGuideToTheGalaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void shouldNotBeKnown() {
        assertFalse(handler.isNumeralKnown("gek"));
    }

    @Test
    void shouldBeKnown() {
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
}