package com.superklaas;

import java.util.Arrays;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        BowlingValidator bowlingValidator = new BowlingValidator();
        BowlingParser bowlingParser = new BowlingParser();
        BowlingCalculator bowlingCalculator = new BowlingCalculator();

        String input = "X 6/ 81 26 9- 9/ 71 8/ 1- X2/";

        String[] frames = bowlingValidator.splitIntoFrames(input);
        bowlingValidator.validateFrames(frames);
        System.out.println(Arrays.toString(frames));
        System.out.println("-------------------");

        List<Roll> rollList = bowlingParser.createRollList(frames);
        System.out.println(rollList);
        System.out.println("-------------------");

        List<Frame> frameList = bowlingParser.createFrameList(frames);
        System.out.println(frameList);
        System.out.println("-------------------");

        int score = bowlingCalculator.calculateScore(frameList, rollList);
        System.out.println("Score: " + score);

    }

}
