package com.superklaas;

import java.util.List;

public class BowlingCalculator {

    public int calculateScore(List<Frame> frameList, List<Roll> rollList) {
        int totalScore = 0;
        for (Frame frame : frameList) {
            int scorePerFrame = 0;
            if (frame.isStrike()) {
                scorePerFrame += frame.roll1().pins();
                scorePerFrame += rollList.get(frame.roll1().rollIndex() + 1).pins();
                scorePerFrame += rollList.get(frame.roll1().rollIndex() + 2).pins();
            } else if (frame.isSpare()) {
                scorePerFrame += frame.roll1().pins();
                scorePerFrame += frame.roll2().pins();
                scorePerFrame += rollList.get(frame.roll2().rollIndex() + 1).pins();
            } else {
                scorePerFrame += frame.roll1().pins();
                scorePerFrame += frame.roll2().pins();
            }
            totalScore += scorePerFrame;
        }
        return totalScore;
    }

}
