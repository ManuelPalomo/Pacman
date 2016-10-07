package com.mpr.Tools.map;

public enum Cell {

    WALL(0),
    FLOOR(1),
    COIN(2),
    POWERUP(3);

    private final int index;

    Cell(int index) {
        this.index = index;
    }

}
