package com.mpr.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mpr.Tools.Constants;
import com.mpr.Tools.map.Cell;
import com.mpr.Tools.map.CellMap;

public class MovementComponent {
    private Direction direction;
    private Actor actor;
    private CellMap cellMap;
    private boolean canEatCoins;

    public MovementComponent(Actor actor, CellMap cellMap, boolean canEatCoins) {
        this.direction = Direction.STILL;
        this.actor = actor;
        this.cellMap = cellMap;
        this.canEatCoins = canEatCoins;
    }

    public void changeMovement(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        int x = getGridPositionX();
        int y = getGridPositionY();
        switch (this.direction) {
            case UP:
                if (cellMap.getCell(x, y + 1) != Cell.WALL) {
                    moveUp();
                }
                break;
            case DOWN:
                if (cellMap.getCell(x, y - 1) != Cell.WALL) {
                    moveDown();
                }
                break;
            case RIGHT:
                if (cellMap.getCell(x + 1, y) != Cell.WALL) {
                    moveRight();
                }
                break;
            case LEFT:
                x = (int) (actor.getX() + Constants.TILEOFFSET) / Constants.TILESIZE;
                y = (int) (actor.getY()) / Constants.TILESIZE;
                if (cellMap.getCell(x - 1, y) != Cell.WALL) {
                    moveLeft();
                }
                break;
        }
        if (canEatCoins) {
            eatCoin(x, y);
        }


    }

    public Direction getDirection() {
        return direction;
    }

    public int getGridPositionX() {
        switch (this.direction) {
            case UP:
            case DOWN:
            case RIGHT:
                return (int) (actor.getX()) / Constants.TILESIZE;
            case LEFT:
                return (int) (actor.getX() + Constants.TILEOFFSET) / Constants.TILESIZE;
        }
        return (int) actor.getX() / Constants.TILESIZE;
    }

    public int getGridPositionY() {
        switch (this.direction) {
            case UP:
            case RIGHT:
            case LEFT:
                return (int) (actor.getY()) / Constants.TILESIZE;
            case DOWN:
                return (int) (actor.getY() + Constants.TILEOFFSET) / Constants.TILESIZE;
        }
        return (int) (actor.getY()) / Constants.TILESIZE;
    }

    private void moveRight() {
        actor.setX(actor.getX() + Constants.PACMAN_SPEED);
    }

    private void moveLeft() {
        actor.setX(actor.getX() - Constants.PACMAN_SPEED);
    }

    private void moveUp() {
        actor.setY(actor.getY() + Constants.PACMAN_SPEED);
    }

    private void moveDown() {
        actor.setY(actor.getY() - Constants.PACMAN_SPEED);
    }

    public void eatCoin(int x, int y) {
        if (cellMap.getCell(x, y) == Cell.COIN) {
            cellMap.eatCoin(x, y);
        }
    }

}
