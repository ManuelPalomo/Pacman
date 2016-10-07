package com.mpr.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mpr.Tools.Constants;
import com.mpr.Tools.map.Cell;
import com.mpr.Tools.map.CellMap;

public class MovementComponent {
    private Direction direction;
    private Actor actor;
    private CellMap cellMap;

    public MovementComponent(Actor actor, CellMap cellMap) {
        this.direction = Direction.STILL;
        this.actor = actor;
        this.cellMap = cellMap;
    }

    public void changeMovement(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        int x, y;
        int tileOffset = 7;
        switch (this.direction) {
            case UP:
                x = (int) actor.getX() / Constants.TILESIZE;
                y = (int) (actor.getY()) / Constants.TILESIZE;
                if (cellMap.getCell(x, y + 1) != Cell.WALL) {
                    moveUp();
                }
                eatCoin(x,y);
                break;
            case DOWN:
                x = (int) actor.getX() / Constants.TILESIZE;
                y = (int) (actor.getY() + tileOffset) / Constants.TILESIZE;
                if (cellMap.getCell(x, y - 1) != Cell.WALL) {
                    moveDown();
                }
                eatCoin(x,y);
                break;
            case RIGHT:
                x = (int) (actor.getX()) / Constants.TILESIZE;
                y = (int) actor.getY() / Constants.TILESIZE;
                if (cellMap.getCell(x + 1, y) != Cell.WALL) {
                    moveRight();
                }
                eatCoin(x,y);
                break;
            case LEFT:
                x = (int) (actor.getX() + tileOffset) / Constants.TILESIZE;
                y = (int) (actor.getY()) / Constants.TILESIZE;
                if (cellMap.getCell(x - 1, y) != Cell.WALL) {
                    moveLeft();
                }
                eatCoin(x,y);
                break;
        }

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

    public void eatCoin(int x,int y){
        if(cellMap.getCell(x,y)==Cell.COIN){
            cellMap.setCell(x,y,Cell.FLOOR);
        }
    }

}
