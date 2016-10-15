package com.mpr.ai;


public class Node {
    private int x;
    private int y;
    private int id;
    private boolean walkable;

    public Node(int x, int y, int id, boolean walkable) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Node)) return false;
        Node otherNode = (Node) other;
        return ((this.x == otherNode.getX()) && (this.y == otherNode.getY()));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public int getId() {
        return id;
    }
}
