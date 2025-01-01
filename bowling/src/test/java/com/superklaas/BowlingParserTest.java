package com.superklaas;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingParserTest {

    BowlingParser bowlingParser;

    @BeforeEach
    void setUp() {
        bowlingParser = new BowlingParser();
    }

    @Test
    void createFrameList_allStrikes() {
        // ARRANGE
        String input = "X X X X X X X X X X X X";
        List<Integer> frameIndexes = IntStream.range(0, 10).boxed().toList();
        List<Tuple> firstRolls = List.of(tuple(0, 10), tuple(1, 10), tuple(2, 10),
                tuple(2, 10), tuple(3, 10), tuple(4, 10), tuple(5, 10),
                tuple(6, 10), tuple(7, 10), tuple(8, 10), tuple(9, 10));
        List<Tuple> secondRolls = new ArrayList<>(Collections.nCopies(10, null));
        List<Boolean> spares = new ArrayList<>(Collections.nCopies(10, false));
        List<Boolean> strikes = new ArrayList<>(Collections.nCopies(10, true));
        // ACT
        List<Frame> actualFrameList = bowlingParser.createFrameList(input);
        // ASSERT
        assertAll(
                () -> assertThat(actualFrameList)
                        .extracting("roll1")
                        .extracting("rollIndex", "pins")
                        .hasSameElementsAs(firstRolls),
                () -> assertThat(actualFrameList)
                        .extracting("roll2")
                        .hasSameElementsAs(secondRolls),
                () -> assertThat(actualFrameList)
                        .extracting("frameIndex")
                        .containsAll(frameIndexes),
                () -> assertThat(actualFrameList)
                        .extracting("isSpare")
                        .hasSameElementsAs(spares),
                () -> assertThat(actualFrameList)
                        .extracting("isStrike")
                        .hasSameElementsAs(strikes)
        );
    }

    @Test
    void createFrameList_allNinesAndZeros() {
        // ARRANGE
        String input = "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-";
        List<Integer> frameIndexes = IntStream.range(0, 10).boxed().toList();
        List<Tuple> firstRolls = List.of(tuple(0, 9), tuple(2, 9), tuple(4, 9), tuple(6, 9),
                tuple(8, 9), tuple(10, 9), tuple(12, 9), tuple(14, 9),
                tuple(16, 9), tuple(18, 9));
        List<Tuple> secondRolls = List.of(tuple(1, 0), tuple(3, 0), tuple(5, 0), tuple(7, 0),
                tuple(9, 0), tuple(11, 0), tuple(13, 0), tuple(15, 0),
                tuple(17, 0), tuple(19, 0));
        List<Boolean> spares = new ArrayList<>(Collections.nCopies(10, false));
        List<Boolean> strikes = new ArrayList<>(Collections.nCopies(10, false));
        // ACT
        List<Frame> actualFrameList = bowlingParser.createFrameList(input);
        // ASSERT
        assertAll(
                () -> assertThat(actualFrameList)
                        .extracting("roll1")
                        .extracting("rollIndex", "pins")
                        .hasSameElementsAs(firstRolls),
                () -> assertThat(actualFrameList)
                        .extracting("roll2")
                        .extracting("rollIndex", "pins")
                        .hasSameElementsAs(secondRolls),
                () -> assertThat(actualFrameList)
                        .extracting("frameIndex")
                        .containsAll(frameIndexes),
                () -> assertThat(actualFrameList)
                        .extracting("isSpare")
                        .hasSameElementsAs(spares),
                () -> assertThat(actualFrameList)
                        .extracting("isStrike")
                        .hasSameElementsAs(strikes)
        );
    }

    @Test
    void createFrameList_allFivesAndSpares() {
        // ARRANGE
        String input = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5";
        List<Integer> frameIndexes = IntStream.range(0, 10).boxed().toList();
        List<Tuple> firstRolls = List.of(tuple(0, 5), tuple(2, 5), tuple(4, 5), tuple(6, 5),
                tuple(8, 5), tuple(10, 5), tuple(12, 5), tuple(14, 5),
                tuple(16, 5), tuple(18, 5));
        List<Tuple> secondRolls = List.of(tuple(1, 5), tuple(3, 5), tuple(5, 5), tuple(7, 5),
                tuple(9, 5), tuple(11, 5), tuple(13, 5), tuple(15, 5),
                tuple(17, 5), tuple(19, 5));
        List<Boolean> spares = new ArrayList<>(Collections.nCopies(10, true));
        List<Boolean> strikes = new ArrayList<>(Collections.nCopies(10, false));
        // ACT
        List<Frame> actualFrameList = bowlingParser.createFrameList(input);
        // ASSERT
        assertAll(
                () -> assertThat(actualFrameList)
                        .extracting("roll1")
                        .extracting("rollIndex", "pins")
                        .hasSameElementsAs(firstRolls),
                () -> assertThat(actualFrameList)
                        .extracting("roll2")
                        .extracting("rollIndex", "pins")
                        .hasSameElementsAs(secondRolls),
                () -> assertThat(actualFrameList)
                        .extracting("frameIndex")
                        .containsAll(frameIndexes),
                () -> assertThat(actualFrameList)
                        .extracting("isSpare")
                        .hasSameElementsAs(spares),
                () -> assertThat(actualFrameList)
                        .extracting("isStrike")
                        .hasSameElementsAs(strikes)
        );
    }

    @Test
    void createFrameList_mixedInput() {
        // ARRANGE
        String input = "X 6/ 81 26 9- 9/ 71 8/ 1- X 25";
        List<Integer> frameIndexes = IntStream.range(0, 10).boxed().toList();
        List<Tuple> firstRolls = List.of(
                tuple(0, 10), tuple(1, 6), tuple(3, 8), tuple(5, 2),
                tuple(7, 9), tuple(9, 9), tuple(11, 7), tuple(13, 8),
                tuple(15, 1), tuple(17, 10));
        List<Boolean> spares = List.of(false, true, false, false, false, true, false, true, false, false);
        List<Boolean> strikes = List.of(true, false, false, false, false, false, false, false, false, true);
        // ACT
        List<Frame> actualFrameList = bowlingParser.createFrameList(input);
        // ASSERT
        assertAll(
                () -> assertThat(actualFrameList)
                        .extracting("roll1")
                        .extracting("rollIndex", "pins")
                        .hasSameElementsAs(firstRolls),
                () -> assertThat(actualFrameList)
                        .extracting("roll2")
                        .containsNull(),
                () -> assertThat(actualFrameList)
                        .extracting("frameIndex")
                        .containsAll(frameIndexes),
                () -> assertThat(actualFrameList)
                        .extracting("isSpare")
                        .hasSameElementsAs(spares),
                () -> assertThat(actualFrameList)
                        .extracting("isStrike")
                        .hasSameElementsAs(strikes)
        );
    }

    @ParameterizedTest
    @MethodSource("createRollListData")
    void createRollList(String input, List<Tuple> expectedRollList) {
        // ARRANGE
        // ACT
        List<Roll> actualRollList = bowlingParser.createRollList(input);
        // ASSERT
        assertThat(actualRollList)
                .extracting("rollIndex", "pins")
                .hasSameElementsAs(expectedRollList);
        System.out.println(actualRollList);
    }

    static Stream<Arguments> createRollListData() {
        return Stream.of(
                arguments(
                        "X X X X X X X X X XXX",
                        List.of(tuple(0, 10), tuple(1, 10),
                                tuple(2, 10), tuple(3, 10),
                                tuple(4, 10), tuple(5, 10),
                                tuple(6, 10), tuple(7, 10),
                                tuple(8, 10), tuple(9, 10),
                                tuple(10, 10), tuple(11, 10)
                        )
                ),
                arguments(
                        "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-",
                        List.of(tuple(0, 9), tuple(1, 0),
                                tuple(2, 9), tuple(3, 0),
                                tuple(4, 9), tuple(5, 0),
                                tuple(6, 9), tuple(7, 0),
                                tuple(8, 9), tuple(9, 0),
                                tuple(10, 9), tuple(11, 0),
                                tuple(12, 9), tuple(13, 0),
                                tuple(14, 9), tuple(15, 0),
                                tuple(16, 9), tuple(17, 0),
                                tuple(18, 9), tuple(19, 0)
                        )
                ),
                arguments(
                        "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5",
                        List.of(tuple(0, 5), tuple(1, 5),
                                tuple(2, 5), tuple(3, 5),
                                tuple(4, 5), tuple(5, 5),
                                tuple(6, 5), tuple(7, 5),
                                tuple(8, 5), tuple(9, 5),
                                tuple(10, 5), tuple(11, 5),
                                tuple(12, 5), tuple(13, 5),
                                tuple(14, 5), tuple(15, 5),
                                tuple(16, 5), tuple(17, 5),
                                tuple(18, 5), tuple(19, 5),
                                tuple(20, 5)
                        )
                ),
                arguments(
                        "X 6/ 81 26 9- 9/ 71 8/ 1- X25",
                        List.of(tuple(0, 10), tuple(1, 6),
                                tuple(2, 4), tuple(3, 8),
                                tuple(4, 1), tuple(5, 2),
                                tuple(6, 6), tuple(7, 9),
                                tuple(8, 0), tuple(9, 9),
                                tuple(10, 1), tuple(11, 7),
                                tuple(12, 1), tuple(13, 8),
                                tuple(14, 2), tuple(15, 1),
                                tuple(16, 0), tuple(17, 10),
                                tuple(18, 2), tuple(19, 5)
                        )
                )
        );
    }

}
