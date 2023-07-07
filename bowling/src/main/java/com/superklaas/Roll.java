package com.superklaas;

public record Roll(int rollIndex, int pins) {

    @Override
    public String toString() {
        return "{" + rollIndex + "," + pins + "}";
    }

}
