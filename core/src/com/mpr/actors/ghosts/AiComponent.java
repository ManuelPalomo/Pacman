package com.mpr.actors.ghosts;

import com.mpr.actors.Direction;
import com.mpr.actors.pacman.Pacman;
import com.mpr.ai.AStarPathFinder;
import com.mpr.ai.Node;

import java.util.LinkedList;

public class AiComponent {
    private AStarPathFinder pathFinder;
    private Pacman pacman;

    public AiComponent(AStarPathFinder pathFinder, Pacman pacman) {
        this.pathFinder = pathFinder;
        this.pacman = pacman;

    }

    public Direction decideNextDirection(int actualX, int actualY, Direction direction) {

        LinkedList<Node> pathToPacman = pathFinder.findPath(actualX, actualY, pacman.getGridPositionX(), pacman.getGridPositionY());

        if (pathToPacman == null || pathToPacman.size() == 1) {
            return direction;
        }
        int nextX = pathToPacman.get(1).getX();
        int nextY = pathToPacman.get(1).getY();

        switch (direction) {
            case UP:
                if (nextX > actualX) {
                    return Direction.RIGHT;
                }
                if (nextX < actualX) {
                    return Direction.LEFT;
                }
                return Direction.UP;


            case DOWN:
                if (nextX > actualX) {
                    return Direction.RIGHT;
                }
                if (nextX < actualX) {
                    return Direction.LEFT;
                }
                return Direction.DOWN;


            case RIGHT:
                if (nextY > actualY) {
                    return Direction.UP;
                }
                if (nextY < actualY) {
                    return Direction.DOWN;
                }
                return Direction.RIGHT;


            case LEFT:
                if (nextY > actualY) {
                    return Direction.UP;
                }
                if (nextY < actualY) {
                    return Direction.DOWN;
                }
                return Direction.LEFT;

        }
        return Direction.STILL;
    }
}
