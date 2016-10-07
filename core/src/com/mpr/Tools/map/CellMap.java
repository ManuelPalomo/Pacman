package com.mpr.Tools.map;

public class CellMap {
    private Cell[][] cells;

    public CellMap(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
