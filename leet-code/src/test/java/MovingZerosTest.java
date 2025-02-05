import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MovingZerosTest {

    @ParameterizedTest
    @MethodSource(value = "moveZeroData")
    void moveZeroes(int[] nums, int[] expectedOutput) {
        MovingZeros.moveZeros(nums);
        assertArrayEquals(expectedOutput, nums);
    }

    public static Stream<Arguments> moveZeroData() {
        return Stream.of(
                arguments(new int[]{1,0,3,0,2}, new int[]{1,3,2,0,0}),
                arguments(new int[]{0,0,1}, new int[]{1,0,0}),
                arguments(new int[]{0,0,0}, new int[]{0,0,0}),
                arguments(new int[]{1,1,1}, new int[]{1,1,1})
        );
    }

}
