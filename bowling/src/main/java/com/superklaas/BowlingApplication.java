package com.superklaas;

import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        BowlingParser bowlingParser = new BowlingParser();
        BowlingCalculator bowlingCalculator = new BowlingCalculator();

        String input = "X 6/ 81 26 9- 9/ 71 8/ 1- X 25";

        List<Roll> rollList = bowlingParser.createRollList(input);
        System.out.println(rollList);
        System.out.println("-------------------");

        List<Frame> frameList = bowlingParser.createFrameList(input);
        System.out.println(frameList);
        System.out.println("-------------------");

        int score = bowlingCalculator.calculateScore(frameList, rollList);
        System.out.println("Score: " + score);

    }

}
