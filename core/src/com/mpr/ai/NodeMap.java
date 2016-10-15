package com.mpr.ai;

import com.mpr.Tools.map.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class NodeMap {
    private Cell[][] map;
    private ArrayList<ArrayList<Node>> nodes;
    private HashMap<Integer, Node> nodeIdMap;

    public NodeMap(Cell[][] map) {
        this.map = map;
        nodeIdMap = new HashMap<Integer, Node>();
        nodes = generateNodes();
    }

    private ArrayList<ArrayList<Node>> generateNodes() {
        int idCounter = 0;
        ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>(map.length);
        for (int i = 0; i < map.length; i++) {
            nodes.add(i, new ArrayList<Node>(map[i].length));
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == Cell.WALL) {
                    Node newNode = new Node(i, j, idCounter, false);
                    nodeIdMap.put(idCounter, newNode);
                    nodes.get(i).add(j, newNode);
                } else {
                    Node newNode = new Node(i, j, idCounter, true);
                    nodeIdMap.put(idCounter, newNode);
                    nodes.get(i).add(j, newNode);
                }
                idCounter++;
            }
        }
        return nodes;
    }

    public Node getNode(int x, int y) {
        return nodes.get(x).get(y);
    }

    public Node getNodeById(int id) {
        return nodeIdMap.get(id);
    }

    public LinkedList<Node> getNeighbors(Node node) {
        LinkedList<Node> neighbours = new LinkedList<Node>();
        int x = node.getX();
        int y = node.getY();

        if (isWalkable(x + 1, y)) {
            neighbours.add(getNode(x + 1, y));
        }

        if (isWalkable(x - 1, y)) {
            neighbours.add(getNode(x - 1, y));
        }

        if (isWalkable(x, y + 1)) {
            neighbours.add(getNode(x, y + 1));
        }

        if (isWalkable(x, y - 1)) {
            neighbours.add(getNode(x, y - 1));
        }

        return neighbours;
    }

    private boolean isWalkable(int x, int y) {
        return isInside(x, y) && getNode(x, y).isWalkable();
    }

    private boolean isInside(int x, int y) {
        return (x >= 0 && x < map.length) && (y >= 0 && y < map[x].length);
    }
}
