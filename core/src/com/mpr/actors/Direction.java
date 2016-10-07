package com.mpr.actors;

public enum Direction {
    UP(0),
    DOWN(1),
    RIGHT(2),
    LEFT(3),
    STILL(4);

    private final int index;

    Direction(int index) {
        this.index = index;
    }
}
