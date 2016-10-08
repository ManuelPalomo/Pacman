package com.mpr.Tools.map;

public class CellMap {
    private Cell[][] cells;
    private int coinsNumber;

    public CellMap(Cell[][] cells) {
        this.cells = cells;
        coinsNumber = countCoins();
    }

    private int countCoins() {
        int coins = 0;
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y] == Cell.COIN) {
                    coins++;
                }
            }
        }
        return coins;
    }

    public void eatCoin(int x, int y) {
        setCell(x, y, Cell.FLOOR);
        coinsNumber--;
    }

    public boolean allCoinsEaten() {
        if (coinsNumber != 0) {
            return false;
        }
        return true;
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
