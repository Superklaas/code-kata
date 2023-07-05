package com.superklaas;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingServiceTest {

    BowlingService bowlingService;

    @BeforeEach
    void setUp() {
        bowlingService = new BowlingService();
    }

    @ParameterizedTest
    @MethodSource("createRollListData")
    void createRollList(String input, List<Tuple> expectedRollList) {
        // ARRANGE
        // ACT
        List<Roll> actualRollList = bowlingService.createRollList(input);
        // ASSERT
        assertThat(actualRollList)
                .extracting("rollIndex", "pins")
                .hasSameElementsAs(expectedRollList);
        System.out.println(actualRollList);
    }

    static Stream<Arguments> createRollListData() {
        return Stream.of(
                arguments(
                        "X X X X X X X X X X X X",
                        List.of(tuple(1, 10), tuple(2, 10),
                                tuple(3, 10), tuple(4, 10),
                                tuple(5, 10), tuple(6, 10),
                                tuple(7, 10), tuple(8, 10),
                                tuple(9, 10), tuple(10, 10),
                                tuple(11, 10), tuple(12, 10)
                        )
                ),
                arguments(
                        "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-",
                        List.of(tuple(1, 9), tuple(2, 0),
                                tuple(3, 9), tuple(4, 0),
                                tuple(5, 9), tuple(6, 0),
                                tuple(7, 9), tuple(8, 0),
                                tuple(9, 9), tuple(10, 0),
                                tuple(11, 9), tuple(12, 0),
                                tuple(13, 9), tuple(14, 0),
                                tuple(15, 9), tuple(16, 0),
                                tuple(17, 9), tuple(18, 0),
                                tuple(19, 9), tuple(20, 0)
                        )
                ),
                arguments(
                        "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5",
                        List.of(tuple(1, 5), tuple(2, 5),
                                tuple(3, 5), tuple(4, 5),
                                tuple(5, 5), tuple(6, 5),
                                tuple(7, 5), tuple(8, 5),
                                tuple(9, 5), tuple(10, 5),
                                tuple(11, 5), tuple(12, 5),
                                tuple(13, 5), tuple(14, 5),
                                tuple(15, 5), tuple(16, 5),
                                tuple(17, 5), tuple(18, 5),
                                tuple(19, 5), tuple(20, 5),
                                tuple(21, 5)
                        )
                ),
                arguments(
                        "X 6/ 81 26 9- 9/ 71 8/ 1- X 25",
                        List.of(tuple(1, 10), tuple(2, 6),
                                tuple(3, 4), tuple(4, 8),
                                tuple(5, 1), tuple(6, 2),
                                tuple(7, 6), tuple(8, 9),
                                tuple(9, 0), tuple(10, 9),
                                tuple(11, 1), tuple(12, 7),
                                tuple(13, 1), tuple(14, 8),
                                tuple(15, 2), tuple(16, 1),
                                tuple(17, 0), tuple(18, 10),
                                tuple(19, 2), tuple(20, 5)
                        )
                )
        );
    }


}