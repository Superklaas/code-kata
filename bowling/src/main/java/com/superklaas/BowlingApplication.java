package com.superklaas;

import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {

        BowlingService bowlingService = new BowlingService();

        String input = "X 6/ 81 26 9- 9/ 71 8/ 1- X 25";

        List<Roll> rollList = bowlingService.createRollList(input);
        System.out.println(rollList);

        List<Frame> frameList = bowlingService.createFrameList(input);
        System.out.println(frameList);

    }

}
