package com.superklaas;

public class Frame {

    private final int frameIndex;
    private final Roll roll1;
    private final Roll roll2;
    private final boolean isSpare;
    private final boolean isStrike;

    public Frame(int frameIndex, Roll roll1, Roll roll2, boolean isSpare, boolean isStrike) {
        this.frameIndex = frameIndex;
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.isSpare = isSpare;
        this.isStrike = isStrike;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public Roll getRoll1() {
        return roll1;
    }

    public Roll getRoll2() {
        return roll2;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public boolean isStrike() {
        return isStrike;
    }
}
