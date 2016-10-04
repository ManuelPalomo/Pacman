package com.mpr.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pacman extends Actor {
    private Texture texture;

    public Pacman(float x, float y) {
        this.setX(x);
        this.setY(y);
        texture = new Texture("pacman.png");
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY());
    }
}
