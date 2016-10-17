package com.mpr.actors.ghosts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mpr.Tools.map.CellMap;
import com.mpr.actors.Direction;
import com.mpr.actors.MovementComponent;

public class Ghost extends Actor {
    private Texture texture;
    private MovementComponent movement;
    private AiComponent ai;
    private CellMap map;

    public Ghost(Texture texture, float x, float y, CellMap map, AiComponent ai) {
        this.texture = texture;
        this.setX(x);
        this.setY(y);
        this.map = map;
        this.ai = ai;
        this.movement = new MovementComponent(this, map, false);
        movement.changeMovement(Direction.RIGHT);
    }

    @Override
    public void act(float delta) {
        movement.changeMovement(ai.decideNextDirection(movement.getGridPositionX(), movement.getGridPositionY(), movement.getDirection()));
        System.out.println(movement.getDirection());
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
