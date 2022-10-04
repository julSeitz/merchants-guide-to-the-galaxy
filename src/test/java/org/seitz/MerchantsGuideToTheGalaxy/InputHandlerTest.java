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

    //-------------START OF TESTS FOR addIntergalacticNumeral() AND getRomanNumeralFromIntergalacticNumeral-------------

    @Test
    void shouldBeAbleToStoreAndRetrieve() {
        handler.addIntergalacticNumeral("gek", "IV");
        assertEquals("IV", handler.getRomanNumeralFromIntergalacticNumeral("gek"));
    }
}
