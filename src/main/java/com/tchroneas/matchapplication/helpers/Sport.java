package com.tchroneas.matchapplication.helpers;

public enum Sport {


    FOOTBALL(1),
    BASKETBALL(2);


    private int value;

    Sport(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
