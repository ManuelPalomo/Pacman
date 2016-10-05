package com.mpr.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pacman extends Actor {
    private Texture texture;
    private Movement movement;


    public Pacman(float x, float y) {
        this.setX(x);
        this.setY(y);
        texture = new Texture("pacman.png");
        movement = movement.STILL;
    }

    public void changeMovement(Movement movement) {
        this.movement = movement;
    }

    private void move() {
        switch (this.movement) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
        }
    }

    private void moveRight() {
        this.setX(this.getX() + 1.33f);
    }

    private void moveLeft() {
        this.setX(this.getX() - 1.33f);
    }

    private void moveUp() {
        this.setY(this.getY() + 1.33f);
    }

    private void moveDown() {
        this.setY(this.getY() - 1.33f);
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
