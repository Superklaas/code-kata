package com.superklaas;

import java.util.ArrayList;
import java.util.List;

public class BowlingParser {

    public List<Roll> createRollList(String[] frames) {
        List<Roll> rollList = new ArrayList<>();
        int rollIndex = 0;
        for (String frame : frames) {
            rollIndex = addRollsToRollList(rollList, rollIndex, frame);
        }
        return rollList;
    }

    public List<Frame> createFrameList(String[] frames) {
        List<Frame> frameList = new ArrayList<>();
        int rollIndex = 0;
        int frameIndex = 0;
        for (String frame : frames) {
            List<Roll> rollList = new ArrayList<>();
            rollIndex = addRollsToRollList(rollList, rollIndex, frame);
            Roll roll1 = rollList.get(0);
            Roll roll2 = rollList.size() > 1 ? rollList.get(1) : null;
            Roll roll3 = rollList.size() > 2 ? rollList.get(2) : null;
            boolean isSpare = frame.contains("/");
            boolean isStrike = frame.toUpperCase().contains("X");
            frameList.add(new Frame(frameIndex, roll1, roll2, roll3, isSpare, isStrike));
            frameIndex++;
        }
        return frameList;
    }

    private static int addRollsToRollList(List<Roll> rollList, int rollIndex, String frame) {
        String[] rollsPerFrame = frame.split("");
        for (int i = 0; i < rollsPerFrame.length; i++) {
            if ("X".equals(rollsPerFrame[i])) {
                rollList.add(new Roll(rollIndex, 10));
            } else if ("-".equals(rollsPerFrame[i])) {
                rollList.add(new Roll(rollIndex, 0));
            } else if ("/".equals(rollsPerFrame[i])) {
                rollList.add(new Roll(rollIndex, 10 - Integer.parseInt(rollsPerFrame[i-1])));
            } else {
                rollList.add(new Roll(rollIndex, Integer.parseInt(rollsPerFrame[i])));
            }
            rollIndex++;
        }
        return rollIndex;
    }

}
