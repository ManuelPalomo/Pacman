package com.mpr.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mpr.Tools.Constants;
import com.mpr.Tools.Cell;

public class Pacman extends Actor {
    private Texture texture;
    private Movement movement;
    private Cell[][] cellMap;


    public Pacman(float x, float y, Cell[][] cellMap) {
        this.setX(x);
        this.setY(y);
        this.cellMap = cellMap;
        texture = new Texture("pacman.png");
        movement = movement.STILL;
    }

    public void changeMovement(Movement movement) {
        this.movement = movement;
    }

    private void move() {
        int x, y;
        switch (this.movement) {
            case UP:
                x = (int) this.getX() / Constants.TILESIZE;
                y = (int) (this.getY()) / Constants.TILESIZE;
                if (cellMap[x][y + 1] != Cell.WALL) {
                    moveUp();
                }
                break;
            case DOWN:
                x = (int) this.getX() / Constants.TILESIZE;
                y = (int) (this.getY() + 7) / Constants.TILESIZE;
                if (cellMap[x][y - 1] != Cell.WALL) {
                    moveDown();
                }
                break;
            case RIGHT:
                x = (int) (this.getX()) / Constants.TILESIZE;
                y = (int) this.getY() / Constants.TILESIZE;
                if (cellMap[x + 1][y] != Cell.WALL) {
                    moveRight();
                }
                break;
            case LEFT:
                x = (int) (this.getX() + 7) / Constants.TILESIZE;
                y = (int) (this.getY()) / Constants.TILESIZE;
                if (cellMap[x - 1][y] != Cell.WALL) {
                    moveLeft();
                }
                break;
        }

    }

    private void moveRight() {
        this.setX(this.getX() + Constants.PACMAN_SPEED);
    }

    private void moveLeft() {
        this.setX(this.getX() - Constants.PACMAN_SPEED);
    }

    private void moveUp() {
        this.setY(this.getY() + Constants.PACMAN_SPEED);
    }

    private void moveDown() {
        this.setY(this.getY() - Constants.PACMAN_SPEED);
    }


    @Override
    public void act(float delta) {
        move();
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {

        batch.draw(texture, this.getX(), this.getY());
    }
}
