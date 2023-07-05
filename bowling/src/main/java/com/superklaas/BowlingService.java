package com.superklaas;

import java.util.ArrayList;
import java.util.List;

public class BowlingService {

    public List<Roll> createRollList(String input) {
        List<Roll> rollList = new ArrayList<>();
        int rollIndex = 1;
        for (String scorePerFrame : input.split(" ")) {
            if ("X".equals(scorePerFrame)) {
                rollList.add(new Roll(rollIndex, 10));
            } else {
                if (scorePerFrame.length() == 2) {
                    String[] rollsPerFrame = scorePerFrame.split("");
                    int pinsRoll1 = Integer.parseInt(rollsPerFrame[0]);
                    rollList.add(new Roll(rollIndex, pinsRoll1));
                    rollIndex++;
                    String roll2 = rollsPerFrame[1];
                    if ("-".equals(roll2)) {
                        rollList.add(new Roll(rollIndex, 0));
                    } else if ("/".equals(roll2)) {
                        rollList.add(new Roll(rollIndex, 10 - pinsRoll1));
                    } else {
                        rollList.add(new Roll(rollIndex, Integer.parseInt(roll2)));
                    }
                } else {
                    rollList.add(new Roll(rollIndex, Integer.parseInt(scorePerFrame)));
                }
            }
            rollIndex++;
        }
        return rollList;
    }


}
