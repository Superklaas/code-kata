package com.superklaas;

public class Roll {

    private final int rollIndex;
    private final int pins;

    public Roll(int rollIndex, int pins) {
        this.rollIndex = rollIndex;
        this.pins = pins;
    }

    public int getRollIndex() {
        return rollIndex;
    }

    public int getPins() {
        return pins;
    }

    @Override
    public String toString() {
        return "{" + rollIndex + "," + pins + "}";
    }
}
