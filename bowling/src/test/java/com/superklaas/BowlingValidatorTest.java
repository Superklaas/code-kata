package com.superklaas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BowlingValidatorTest {

    BowlingValidator bowlingValidator;
    String validInputString;
    String[] validFrames;

    @BeforeEach
    void setUp() {
        bowlingValidator = new BowlingValidator();
        validInputString = "X 6/ 81 26 9- 9/ 71 8/ 1- X25";
        validFrames = validInputString.split(" ");
    }

    @Test
    void splitIntoFrames_validInputString() {
        String[] actualFrames = bowlingValidator.splitIntoFrames(validInputString);
        assertArrayEquals(validFrames, actualFrames);
        //System.out.println("Valid input string split into frames: " + Arrays.toString(actualFrames));
    }

    @Test
    void validateFrames_validFrames() {
        bowlingValidator.validateFrames(validFrames);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "X 6/ 81 26 9- 9/ 71 8/ 1- X 25",
            "X 6/ 81 26 9- 9/ 71 8/ 1-"
    })
    void validateFrames_moreOrLessThan10Frames(String input) {
        String[] frames = input.split(" ");
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals(
                String.format("Input should have 10 frames: %s", Arrays.toString(frames)),
                bowlingException.getMessage()
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "X 6y 81 26 9- 9/ 71 8/ 1- X25",
            "X 6/ 8* 26 9- 9/ 71 8/ 1- X25"
    })
    void validateFrames_invalidCharactersInFrame(String input) {
        String[] frames = input.split(" ");
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals("Input can only contain x or - or / or digits: " + Arrays.toString(frames), bowlingException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "X91 6/ 81 26 9- 9/ 71 8/ 1- X25",
            "X 6/ 81 26 9- 9/ 712 8/ 1- X25"
    })
    void validateFrames_tooManyCharactersInRegularFrame(String input) {
        String[] frames = input.split(" ");
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals("Frames 1-9 can have max. 2 characters: " + Arrays.toString(frames), bowlingException.getMessage());
    }

    @Test
    void validateFrames_tooManyCharactersInLastFrame() {
        String[] frames = validFrames.clone();
        frames[9] = frames[9].concat("X");
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals("Frame 10 can have max. 3 characters: " + Arrays.toString(frames), bowlingException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "X9 6/ 81 26 9- 9/ 71 8/ 1- X25",
            "X 6/ 8X 26 9- 9/ 71 8/ 1- X25"
    })
    void validateFrames_invalidStrikeInRegularFrame(String input) {
        String[] frames = input.split(" ");
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals("A strike in frame 1-9 can only contain one character X: " + Arrays.toString(frames), bowlingException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("invalidStrikeInLastFrame")
    void validateFrames_invalidStrikeInLastFrame(String[] frames) {
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        String actualMessage = bowlingException.getMessage();
        String expectedMessage1 = "A strike in frame 10 should be one X followed by two characters: " + Arrays.toString(frames);
        String expectedMessage2 = "Sum of two digits in a frame cannot be greater than 10: " + Arrays.toString(frames);
        assertTrue(
                actualMessage.equals(expectedMessage1) || actualMessage.equals(expectedMessage2),
                "Unexpected exception message: " + actualMessage
        );
    }

    public static Stream<Arguments> invalidStrikeInLastFrame() {
        return Stream.of(
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 71 8/ 1- X2".split(" ")),
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 71 8/ 1- X".split(" ")),
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 71 8/ 1- 24X".split(" ")),
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 71 8/ 1- X29".split(" "))

        );
    }

    @ParameterizedTest
    @MethodSource("invalidSpareInRegularFrame")
    void validateFrames_invalidSpareInRegularFrame(String[] frames) {
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals(
                "A spare in frame 1-9 should be a digit/hyphen followed by a forward slash: " + Arrays.toString(frames),
                bowlingException.getMessage()
        );
    }

    public static Stream<Arguments> invalidSpareInRegularFrame() {
        return Stream.of(
                Arguments.arguments((Object) "X 6/ 81 26 9- / 71 8/ 1- X25".split(" ")),
                Arguments.arguments((Object) "X 6/ 81 26 9- /9 71 8/ 1- X25".split(" "))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidSpareInLastFrame")
    void validateFrames_invalidSpareInLastFrame(String[] frames) {
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals(
                "A spare in frame 10 should have 2 digits/hyphens separated by a forward slash: " + Arrays.toString(frames),
                bowlingException.getMessage()
        );
    }

    public static Stream<Arguments> invalidSpareInLastFrame() {
        return Stream.of(
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 71 8/ 1- 2/".split(" ")),
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 71 8/ 1- /25".split(" ")),
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 71 8/ 1- -//".split(" "))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidSumDigitsInRegularFrame")
    void validateFrames_invalidSumDigitsInRegularFrame(String[] frames) {
        BowlingException bowlingException = assertThrows(BowlingException.class, () -> bowlingValidator.validateFrames(frames));
        assertEquals(
                "Sum of two digits in a frame cannot be greater than 10: " + Arrays.toString(frames),
                bowlingException.getMessage()
        );
    }

    public static Stream<Arguments> invalidSumDigitsInRegularFrame() {
        return Stream.of(
                Arguments.arguments((Object) "X 6/ 81 28 9- 9/ 71 8/ 1- X25".split(" ")),
                Arguments.arguments((Object) "X 6/ 81 26 9- 9/ 78 8/ 1- X25".split(" "))
        );
    }

}
