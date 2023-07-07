package com.superklaas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingCalculatorTest {

    BowlingCalculator bowlingCalculator;
    BowlingParser bowlingParser;

    @BeforeEach
    void setUp() {
        bowlingCalculator = new BowlingCalculator();
        bowlingParser = new BowlingParser();
    }

    @ParameterizedTest
    @MethodSource(value = "calculateScoreData")
    void calculateScore(String input, int expectedScore) {
        // ARRANGE
        List<Roll> rollList = bowlingParser.createRollList(input);
        List<Frame> frameList = bowlingParser.createFrameList(input);
        // ACT
        int actualScore = bowlingCalculator.calculateScore(frameList, rollList);
        // ASSERT
        assertEquals(expectedScore, actualScore);
        System.out.println(input + " equals score " + actualScore);
    }

    static Stream<Arguments> calculateScoreData() {
        return Stream.of(
                arguments("X X X X X X X X X X X X", 300),
                arguments("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", 90),
                arguments("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5", 150),
                arguments("X 6/ 81 26 9- 9/ 71 8/ 1- X 25", 118)
        );
    }

}