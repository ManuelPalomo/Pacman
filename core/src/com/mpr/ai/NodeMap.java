package com.mpr.ai;

import com.mpr.Tools.map.Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NodeMap {
    private Cell[][] map;
    private ArrayList<ArrayList<Node>> nodes;

    public NodeMap(Cell[][] map) {
        this.map = map;
        nodes = generateNodes();
    }

    private ArrayList<ArrayList<Node>> generateNodes() {
        ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>(map.length);
        for (int i = 0; i < map.length; i++) {
            nodes.add(i, new ArrayList<Node>(map[i].length));
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == Cell.WALL) {
                    nodes.get(i).add(j, new Node(i, j, false));
                } else {
                    nodes.get(i).add(j, new Node(i, j, true));
                }

            }
        }
        return nodes;
    }

    public Node getNode(int x, int y) {
        return nodes.get(x).get(y);
    }

    public LinkedList<Node> getNeighbors(Node node) {
        LinkedList<Node> neighbours = new LinkedList<Node>();
        int x = node.getX();
        int y = node.getY();

        if (isWalkable(x + 1, y)) {
            neighbours.add(getNode(x + 1, y));
        }

        if (isWalkable(x - 1, y)) {
            neighbours.add(getNode(x + 1, y));
        }

        if (isWalkable(x, y + 1)) {
            neighbours.add(getNode(x + 1, y));
        }

        if (isWalkable(x, y - 1)) {
            neighbours.add(getNode(x + 1, y));
        }

        return neighbours;
    }

    private boolean isWalkable(int x, int y) {
        return isInside(x, y) && getNode(x, y).isWalkable();
    }

    private boolean isInside(int x, int y) {
        return (x >= 0 && x < map.length) && (y >= 0 && y < map[0].length);
    }
}
