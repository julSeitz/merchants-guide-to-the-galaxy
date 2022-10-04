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

    //--START OF TESTS FOR addIntergalacticNumeral(), getRomanNumeralFromIntergalacticNumeral AND updateIntergalacticNumeral()

    @Test
    void shouldBeAbleToStoreAndRetrieve() {
        handler.addIntergalacticNumeral("gek", "IV");
        assertEquals("IV", handler.getRomanNumeralFromIntergalacticNumeral("gek"));
    }

    @Test
    void shouldBeAbleToUpdateAndRetrieve() throws Exception {
        handler.addIntergalacticNumeral("gek", "IX");
        handler.updateIntergalacticNumeral("gek", "IX");
        assertEquals("IX", handler.getRomanNumeralFromIntergalacticNumeral("gek"));
    }

    @Test
    void shouldThrowExceptionWhenUnknownNumeralIsUpdated() throws Exception {
        assertThrows(Exception.class,
                () -> handler.updateIntergalacticNumeral("gek", "IX"));
    }

    //--END OF TESTS FOR addIntergalacticNumeral(), getRomanNumeralFromIntergalacticNumeral AND updateIntergalacticNumeral()

    //-----------------------------------START OF TESTS FOR isNumeralKnown()--------------------------------------------

    @Test
    void shouldNotBeKnown() {
        assertFalse(handler.isNumeralKnown("gek"));
    }


}
