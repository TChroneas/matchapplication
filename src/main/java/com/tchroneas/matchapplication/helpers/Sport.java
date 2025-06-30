package com.tchroneas.matchapplication.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sport {


    FOOTBALL(1),
    BASKETBALL(2);


    private final int value;

    Sport(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static Sport fromValue(int value) {
        for (Sport s : Sport.values()) {
            if (s.value == value) return s;
        }
        throw new IllegalArgumentException("Invalid sport value: " + value);
    }
}
