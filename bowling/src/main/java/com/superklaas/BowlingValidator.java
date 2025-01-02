package com.superklaas;

import java.util.Arrays;

public class BowlingValidator {

    public String[] splitIntoFrames(String input) {
        return input.split(" ");
    }

    public void validateFrames(String[] frames) {
        checkNumberOfFrames(frames);
        checkCharactersInFrames(frames);
        checkNumberOfCharactersPerFrame(frames);
        checkStrikes(frames);
        checkSpares(frames);
        checkFramesWithoutSpareOrStrike(frames);
    }

    /**
     * 10 frames in input string.
     */
    private void checkNumberOfFrames(String[] frames) {
        if (frames.length != 10) {
            throw new BowlingException("Input should have 10 frames: " + Arrays.toString(frames));
        }
    }

    /**
     * Allowed characters: digits 1-9, hyphen, forward slash, x (case-insensitive)
     */
    private void checkCharactersInFrames(String[] frames) {
        String regex = "(?i)[1-9x\\-/]+";
        for (String frame : frames) {
            if (!frame.matches(regex)) {
                throw new BowlingException("Input can only contain x or - or / or digits: " + Arrays.toString(frames));
            }
        }
    }

    /**
     * Max. 2 characters in frame 1-9.
     * Max. 3 characters in frame 10.
     */
    private void checkNumberOfCharactersPerFrame(String[] frames) {
        // last frame
        if (frames[9].length() > 3) {
            throw new BowlingException("Frame 10 can have max. 3 characters: " + Arrays.toString(frames));
        }
        // other frames
        for (int i = 0; i < frames.length - 1; i++) {
            if (frames[i].length() > 2) {
                throw new BowlingException("Frames 1-9 can have max. 2 characters: " + Arrays.toString(frames));
            }
        }
    }

    /**
     * Frame 1-9: one character x (case-insensitive). <br>
     * Frame 10: 1) X, 2) X or hyphen or digit 1-9, 3) X or hyphen or digit 1-9 or forward slash.
     * If two digits, sum should be smaller than 10.
     */
    private void checkStrikes(String[] frames) {
        // last frame: three characters starting with X
        if (frames[9].contains("X")) {
            if (!frames[9].matches("(?i)x[x1-9\\-][x1-9\\-/]")) {
                throw new BowlingException("A strike in frame 10 should be one X followed by two characters: " + Arrays.toString(frames));
            }
            if (frames[9].matches("(?i)x[1-9]{2}")) {
                int secondCharacter = Integer.parseInt(frames[9].substring(1, 2));
                int thirdCharacter = Integer.parseInt(frames[9].substring(2));
                if ((secondCharacter + thirdCharacter) > 9) {
                    throw new BowlingException("Sum of two digits in a frame cannot be greater than 10: " + Arrays.toString(frames));
                }
            }
        }
        // other frames: one character X
        for (int i = 0; i < frames.length - 1; i++) {
            if (frames[i].toUpperCase().contains("X") && frames[i].length() > 1) {
                throw new BowlingException("A strike in frame 1-9 can only contain one character X: " + Arrays.toString(frames));
            }
        }
    }

    /**
     * Frame 1-9:  1) hyphen or digit 1-9, 2) forward slash. <br>
     * Frame 10: 1) hyphen or digit 1-9, 2) forward slash or hyphen or digit 1-9, 3) forward slash or hyphen or digit 1-9.
     */
    private void checkSpares(String[] frames) {
        // last frame: three characters with / as second character or third character
        if (frames[9].contains("/") && !frames[9].matches("(?i)^[1-9\\-]/[x1-9\\-]$|^[x1-9\\-][1-9\\-]/$")) {
            throw new BowlingException("A spare in frame 10 should have a forward slash as second or third character: "
                    + Arrays.toString(frames));
        }
        // regular frames: 2 characters with / as second character
        for (int i = 0; i < frames.length - 1; i++) {
            if (frames[i].contains("/") && !frames[i].matches("[1-9\\-]/")) {
                throw new BowlingException("A spare in frame 1-9 should be a digit/hyphen followed by a forward slash: "
                        + Arrays.toString(frames));
            }
        }
    }

    /**
     * Regular frame with two digits 1-9 or hyphens.
     * If two digits, sum should be smaller than 10.
     */
    private void checkFramesWithoutSpareOrStrike(String[] frames) {
        for (String frame : frames) {
            if (frame.matches("[1-9]{2}")) {
                int firstCharacter = Integer.parseInt(frame.substring(0, 1));
                int secondCharacter = Integer.parseInt(frame.substring(1));
                if ((firstCharacter + secondCharacter) > 9) {
                    throw new BowlingException("Sum of two digits in a frame cannot be greater than 10: " + Arrays.toString(frames));
                }
            }
        }
    }

}
