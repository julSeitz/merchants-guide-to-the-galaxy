package org.seitz.MerchantsGuideToTheGalaxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    InputParser parser;

    @BeforeEach
    void setUp() {
        parser = new InputParser();
    }

    //----------START OF TESTS FOR addIntergalacticNumeral() AND getRomanNumeralFromIntergalacticNumeral()--------------

    @Test
    void shouldBeAbleToStoreAndRetrieveNumeral() throws  Exception {
        parser.addIntergalacticNumeral("gek", "IV");
        assertEquals("IV", parser.getRomanNumeralFromIntergalacticNumeral("gek"));
    }

    @Test
    void shouldThrowExceptionWhenUnknownNumeralIsRequested() {
        assertThrows(Exception.class,
                () -> parser.getRomanNumeralFromIntergalacticNumeral("gek"));
    }

    //------------END OF TESTS FOR addIntergalacticNumeral() AND getRomanNumeralFromIntergalacticNumeral()--------------

    //--------------------------START OF TESTS FOR setMineralValue() AND getMineralValue()------------------------------

    @Test
    void shouldBeAbleToStoreAndRetrieveMineral() throws Exception{
        parser.setMineralValue("Silver", 82.1);
        assertEquals(82.1, parser.getMineralValue("Silver"));
    }

    @Test
    void shouldThrowExceptionWhenUnknownMineralIsRequested() {
        assertThrows(Exception.class,
                () -> parser.getMineralValue("Silver"));
    }

    //-----------------------------END OF TESTS FOR setMineralValue() AND getMineralValue()-----------------------------

    //-------------------------------START OF TESTS FOR calculateValueOfOneUnit()---------------------------------------

    // Testing if division works for even results

    @Test
    void shouldEqualFive() {
        assertEquals(5.0, parser.calculateValueOfOneUnit(20, 100.0));
    }

    // Testing of division works for odd results

    @Test
    void shouldEqualTwoPointFive() {
        assertEquals(2.5, parser.calculateValueOfOneUnit(6, 15.0));
    }

    // Testing of division works for odd inputs

    @Test
    void shouldEqualFourPointThreeFive() {
        assertEquals(4.35, parser.calculateValueOfOneUnit(4, 17.4));
    }

    //-------------------------------END OF TESTS FOR calculateValueOfOneUnit()-----------------------------------------

    //-------------------------------START OF TESTS FOR getValueOfXUnits()----------------------------------------------

    @Test
    void shouldEqualSixHundredSeventyFivePointSevenFour() throws Exception {
        parser.setMineralValue("Silver", 29.38);
        assertEquals(675.74, parser.getValueOfXUnits(23, "Silver"));
    }

    @Test
    void shouldThrowExceptionForUnknownMineral() {
        assertThrows(Exception.class,
                () -> parser.getValueOfXUnits(23, "Silver"));
    }

    //-------------------------------END OF TESTS FOR getValueOfXUnits()------------------------------------------------

    //-------------------------------START OF TESTS FOR isInputKeyWord()------------------------------------------------

    @TestFactory
    Stream<DynamicTest> keywordsShouldBeRecognized() {
        InputParser secondHandler = new InputParser();
        List<String> inputs = Arrays.asList("how", "many", "much", "is", "Credits");

        return inputs.stream().map(
                input -> DynamicTest.dynamicTest("Checking if " + input + " is a reserved word",
                        () -> assertTrue(secondHandler.isInputKeyWord(input))
                ));
    }

    @TestFactory
    Stream<DynamicTest> wordsShouldNotBeRecognized() {
        InputParser secondHandler = new InputParser();
        List<String> inputs = Arrays.asList("glek", "6578", "Silver", "X", "pish", "howika", "muchani", "umany", "Creditsukal");

        return inputs.stream().map(
                input -> DynamicTest.dynamicTest("Checking if " + input + " is a reserved word",
                        () -> assertFalse(secondHandler.isInputKeyWord(input))
                ));
    }

    //-------------------------------START OF TESTS FOR determineQueryType()--------------------------------------------

    @TestFactory
    Stream<DynamicTest> shouldDetermineQueryTypeCorrectly() {
        InputParser secondHandler = new InputParser();
        List<String> queries = Arrays.asList(
                "glob is I",
                "prok is V",
                "pish is X",
                "tegj is L",
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits",
                "how much is pish tegj glob glob ?",
                "how many Credits is glob prok Silver ?",
                "how many Credits is glob prok Gold ?",
                "how many Credits is glob prok Iron ?",
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        List<String> queryTypes = Arrays.asList(
                "1",
                "1",
                "1",
                "1",
                "2",
                "2",
                "2",
                "3",
                "4",
                "4",
                "4",
                "0");

        return queries.stream().map(
                query -> DynamicTest.dynamicTest("Is query " +  query + " type " + queryTypes.get(queries.indexOf(query)),
                        () -> assertEquals(queryTypes.get(queries.indexOf(query)), secondHandler.determineQueryType(query))
                )
        );
    }

    //---------------------------------END OF TESTS FOR determineQueryType()--------------------------------------------

    //-------------------------------START OF TESTS FOR extractDataFromQuery()------------------------------------------

    @TestFactory
    Stream<DynamicTest> shouldExtractQueryDataForTypeOneCorrectly() {
        InputParser secondHandler = new InputParser();

        List<String> queries = Arrays.asList(
                "glob is I",
                "prok is V",
                "pish is X",
                "tegj is L");

        List<List<String>> answers = Arrays.asList(
                Arrays.asList(
                        "glob",
                        "I"
                ),
                Arrays.asList(
                        "prok",
                        "V"
                ),
                Arrays.asList(
                        "pish",
                        "X"
                ),
                Arrays.asList(
                        "tegj",
                        "L"
                )
        );

        return queries.stream().map(
                query -> DynamicTest.dynamicTest("Is data correctly extracted from query " + query,
                        () -> assertEquals(answers.get(queries.indexOf(query)),
                                secondHandler.extractDataFromQuery(query, "1"))
                ));
    }

    @TestFactory
    Stream<DynamicTest> shouldExtractQueryDataForTypeTwoCorrectly() {
        InputParser secondHandler = new InputParser();

        List<String> queries = Arrays.asList(
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits");

        List<List<String>> answers = Arrays.asList(
                Arrays.asList(
                        "glob glob",
                        "Silver",
                        "34"
                ),
                Arrays.asList(
                        "glob prok",
                        "Gold",
                        "57800"
                ),
                Arrays.asList(
                        "pish pish",
                        "Iron",
                        "3910"
                )
        );

        return queries.stream().map(
                query -> DynamicTest.dynamicTest("Is data correctly extracted from query " + query,
                        () -> assertEquals(answers.get(queries.indexOf(query)),
                                secondHandler.extractDataFromQuery(query, "2"))
                ));
    }

    @Test
    void shouldExtractQueryDataForTypeThreeCorrectly() {
        assertEquals("pish tegj glob glob",
                parser.extractDataFromQuery("how much is pish tegj glob glob ?", "3").get(0)
        );
    }

    @TestFactory
    Stream<DynamicTest> shouldExtractQueryDataForTypeFourCorrectly() {
        InputParser secondHandler = new InputParser();

        List<String> queries = Arrays.asList(
                "how many Credits is glob prok Silver ?",
                "how many Credits is glob prok Gold ?",
                "how many Credits is glob prok Iron ?"
        );

        List<List<String>> answers = Arrays.asList(
                Arrays.asList(
                        "glob prok",
                        "Silver"
                ),
                Arrays.asList(
                        "glob prok",
                        "Gold"
                ),
                Arrays.asList(
                        "glob prok",
                        "Iron"
                )
        );

        return queries.stream().map(
                query -> DynamicTest.dynamicTest("Is data correctly extracted from query " + query,
                        () -> assertEquals(answers.get(queries.indexOf(query)),
                                secondHandler.extractDataFromQuery(query, "4"))
                ));
    }

    @Test
    void shouldReturnEmptyListWhenQueryTypeIsZero() {
        List<String> emptyList = Collections.emptyList();
        assertEquals(emptyList,
                parser.extractDataFromQuery(
                        "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?",
                        "0"
                )
        );
    }

    //--------------------------------------END OF TESTS FOR extractDataFromQuery()-------------------------------------

    //-----------------------------------------START OF TESTS FOR parseQuery()------------------------------------------

    @TestFactory
    Stream<DynamicTest> isQueryParsedCorrectly() {
        InputParser secondHandler = new InputParser();

        List<String> inputQueries = Arrays.asList(
                "glob is I",
                "prok is V",
                "pish is X",
                "tegj is L",
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits",
                "how much is pish tegj glob glob ?",
                "how many Credits is glob prok Silver ?",
                "how many Credits is glob prok Gold ?",
                "how many Credits is glob prok Iron ?",
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
        );

        List<String> answers = Arrays.asList(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "pish tegj glob glob is 42",
                "glob prok Silver is 68 Credits",
                "glob prok Gold is 57800 Credits",
                "glob prok Iron is 782 Credits",
                "I have no idea what you are talking about"
        );

        return inputQueries.stream().map(
                query -> DynamicTest.dynamicTest("Is answer to query " + query + " correct?",
                        () -> assertEquals(answers.get(inputQueries.indexOf(query)),
                                secondHandler.parseQuery(query))
                )
        );
    }

    @Test
    void shouldNotAcceptKeywordAsNumeralForTypeOne() {
        assertEquals("I have no idea what you are talking about", parser.parseQuery("is is I"));
    }

    @Test
    void shouldNotAcceptKeywordAsSymbolForTypeTwo() {
        parser.addIntergalacticNumeral("glob", "I");
        assertEquals("I have no idea what you are talking about", parser.parseQuery("glob is Silver is 57800 Credits"));
    }

    @Test
    void shouldNotAcceptInvalidNumeralForTypeTwo() {
        parser.addIntergalacticNumeral("glob", "I");
        assertEquals("I have no idea what you are talking about", parser.parseQuery("glob glob glob glob Silver is 57800 Credits"));
    }
    @Test
    void shouldNotAcceptInvalidMineralForTypeTwo() {
        parser.addIntergalacticNumeral("glob", "I");
        assertEquals("I have no idea what you are talking about", parser.parseQuery("glob glob glob Credits is 57800 Credits"));
    }

    @Test
    void shouldNotAcceptInvalidSymbolForTypeThree() {
        parser.addIntergalacticNumeral("glob", "I");
        assertEquals("I have no idea what you are talking about", parser.parseQuery("how much is glob is ?"));
    }

    @Test
    void shouldNotAcceptInvalidNumeralForTypeThree() {
        parser.addIntergalacticNumeral("glob", "I");
        assertEquals("I have no idea what you are talking about", parser.parseQuery("how much is glob glob glob glob ?"));
    }

    @Test
    void shouldNotAcceptInvalidSymbolForTypeFour() {
        parser.addIntergalacticNumeral("glob", "I");
        parser.setMineralValue("Iron", 82.0);
        assertEquals("I have no idea what you are talking about", parser.parseQuery("how many Credits is glob many Iron ?"));
    }

    @Test
    void shouldNotAcceptInvalidNumeralForTypeFour() {
        parser.addIntergalacticNumeral("glob", "I");
        parser.setMineralValue("Iron", 82.0);
        assertEquals("I have no idea what you are talking about", parser.parseQuery("how many Credits is glob glob glob glob Iron ?"));
    }

    @Test
    void shouldNotAcceptIllegalMineralForTypeFour() {
        parser.addIntergalacticNumeral("glob", "I");
        parser.setMineralValue("Iron", 82.0);
        assertEquals("I have no idea what you are talking about", parser.parseQuery("how many Credits is glob Credits ?"));
    }

    @Test
    void shouldNotAcceptUnknownMineralForTypeFour() {
        parser.addIntergalacticNumeral("glob", "I");
        parser.setMineralValue("Iron", 82.0);
        assertEquals("I have no idea what you are talking about", parser.parseQuery("how many Credits is glob Silver ?"));
    }

    @Test
    void shouldReturnUnevenPrice() {
        parser.addIntergalacticNumeral("glob", "I");
        parser.setMineralValue("Iron", 1.5);
        assertEquals("glob glob glob Iron is 4.5 Credits", parser.parseQuery("how many Credits is glob glob glob Iron ?"));
    }

    //-------------------------------------------END OF TESTS FOR parseQuery()------------------------------------------

}