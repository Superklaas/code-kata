package com.superklaas;

import java.util.ArrayList;
import java.util.List;

public class BowlingParser {

    public List<Roll> createRollList(String input) {
        List<Roll> rollList = new ArrayList<>();
        int rollIndex = 0;
        for (String frame : input.split(" ")) {
            if ("X".equals(frame)) {
                rollList.add(new Roll(rollIndex, 10));
            } else {
                if (frame.length() == 2) {
                    String[] rollsPerFrame = frame.split("");
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
                    rollList.add(new Roll(rollIndex, Integer.parseInt(frame)));
                }
            }
            rollIndex++;
        }
        return rollList;
    }

    public List<Frame> createFrameList(String input) {
        List<Frame> frameList = new ArrayList<>();
        int rollIndex = 0;
        int frameIndex = 0;
        String[] frames = input.split(" ");
        for (int i = 0; i < 10; i++) {
            Roll roll1;
            Roll roll2 = null;
            boolean isSpare = false;
            boolean isStrike = false;
            String frame = frames[i];
            if ("X".equals(frame)) {
                roll1 = new Roll(rollIndex, 10);
                isStrike = true;
            } else {
                String[] rollsPerFrame = frame.split("");
                roll1 = new Roll(rollIndex, Integer.parseInt(rollsPerFrame[0]));
                rollIndex++;
                if ("-".equals(rollsPerFrame[1])) {
                    roll2 = new Roll(rollIndex, 0);
                } else if ("/".equals(rollsPerFrame[1])) {
                    roll2 = new Roll(rollIndex, 10 - Integer.parseInt(rollsPerFrame[0]));
                    isSpare = true;
                } else {
                    roll2 = new Roll(rollIndex, Integer.parseInt(rollsPerFrame[1]));
                }
            }
            frameList.add(new Frame(frameIndex, roll1, roll2, isSpare, isStrike));
            rollIndex++;
            frameIndex++;
        }
        return frameList;
    }

}
