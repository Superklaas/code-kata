package com.superklaas;

public record Frame(int frameIndex, Roll roll1, Roll roll2, boolean isSpare, boolean isStrike) {

    @Override
    public String toString() {
        return "{" +
                "frameIndex=" + frameIndex +
                ", roll1=" + roll1 +
                ", roll2=" + roll2 +
                ", isSpare=" + isSpare +
                ", isStrike=" + isStrike +
                "}";
    }

}
