import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingServiceTest {

    BowlingService bowlingService;

    @BeforeEach
    void setUp() {
        bowlingService = new BowlingService();
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void parseInput(String input, List<Integer> expectedPinsPerRoll) {
        // ARRANGE
        // ACT
        List<Integer> actualPinsPerRoll = bowlingService.parseInput(input);
        // ASSERT
        Assertions.assertThat(actualPinsPerRoll).hasSameElementsAs(expectedPinsPerRoll);
        System.out.println(actualPinsPerRoll);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                arguments("X X X X X X X X X X X", List.of(10,10,10,10,10,10,10,10,10,10,10)),
                arguments("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", List.of(9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0)),
                arguments("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5", List.of(5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5)),
                arguments("X 6/ 81 26 9- 9/ 71 8/ 1- X 25", List.of(10,6,4,8,1,2,6,9,0,9,1,7,1,8,2,1,0,10,2,5))
        );
    }

}