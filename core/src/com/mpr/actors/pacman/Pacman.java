package com.mpr.actors.pacman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mpr.Tools.map.CellMap;
import com.mpr.actors.Direction;
import com.mpr.actors.MovementComponent;

public class Pacman extends Actor {
    private CellMap cellMap;
    private Texture texture;
    private MovementComponent movement;


    public Pacman(float x, float y, CellMap cellMap) {
        this.setX(x);
        this.setY(y);
        this.cellMap = cellMap;
        texture = new Texture("pacman.png");
        movement = new MovementComponent(this, cellMap);
    }

    public void changeMovement(Direction direction) {
        movement.changeMovement(direction);
    }

    @Override
    public void act(float delta) {
        movement.move();

        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {

        batch.draw(texture, this.getX(), this.getY());
    }

    public void dispose() {
        texture.dispose();
    }
}
