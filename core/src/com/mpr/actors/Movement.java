package com.mpr.actors;

public enum Movement {
    UP(0),
    DOWN(1),
    RIGHT(2),
    LEFT(3),
    STILL(4);

    private final int index;

    Movement(int index) {
        this.index = index;
    }
}
