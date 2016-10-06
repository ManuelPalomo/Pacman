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

    public Vector2 getCurrentCell() {
        return new Vector2(((this.getX()) / Constants.TILESIZE), ((this.getY()) / Constants.TILESIZE));
    }

    private void move() {
        Vector2 position = getCurrentCell();
        int x = Math.round(position.x);
        int y = Math.round(position.y);

        switch (this.movement) {
            case UP:
                if (cellMap[x][y + 1] != Cell.WALL) {
                    moveUp();
                }
                break;
            case DOWN:
                if (cellMap[x][y - 1] != Cell.WALL) {
                    moveDown();
                }
                break;
            case RIGHT:
                if (cellMap[x + 1][y] != Cell.WALL) {
                    moveRight();
                }
                break;
            case LEFT:
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
