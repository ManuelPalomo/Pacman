package com.mpr.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class AStarPathFinder {
    private NodeMap nodeMap;

    public AStarPathFinder(NodeMap nodeMap) {
        this.nodeMap = nodeMap;
    }

    public LinkedList<Node> findPath(int startX, int startY, int endX, int endY) {

        final NodeData data = new NodeData();

        Node startNode = nodeMap.getNode(startX, startY);
        data.setG(startNode.getId(), 0);
        Node endNode = nodeMap.getNode(endX, endY);
        data.setF(startNode.getId(), manhattanHeuristic(startNode, endNode));

        LinkedList<Node> closedSet = new LinkedList<Node>();
        PriorityQueue<Node> openSet = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                if (data.getF(node.getId()) > data.getF(t1.getId())) return 1;
                if (data.getF(t1.getId()) < data.getF(node.getId())) return -1;
                return 0;
            }
        });
        HashMap<Integer, Integer> cameFrom = new HashMap<Integer, Integer>();

        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node actualNode = openSet.poll();

            if (actualNode.getId() == endNode.getId()) {
                return path(cameFrom, endNode.getId());
            }

            closedSet.add(actualNode);

            for (Node neighbor : nodeMap.getNeighbors(actualNode)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double actualG = data.getG(neighbor.getId());
                double nextG = actualG + ((neighbor.getX() - actualNode.getX() == 0 || neighbor.getY() - actualNode.getY() == 0) ? 1 : sqrt(2));

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (nextG >= data.getG(neighbor.getId())) {
                    continue;
                }
                cameFrom.put(neighbor.getId(), actualNode.getId());
                data.setG(neighbor.getId(), nextG);
                data.setF(neighbor.getId(), data.getG(neighbor.getId()) + manhattanHeuristic(neighbor, endNode));

            }
        }

        return null;
    }

    private int manhattanHeuristic(Node initial, Node end) {
        int differenceX = abs(initial.getX() - end.getX());
        int differenceY = abs(initial.getY() - end.getY());
        return differenceX + differenceY;
    }

    private LinkedList<Node> path(HashMap<Integer, Integer> cameFrom, int destination) {
        LinkedList<Node> path = new LinkedList<Node>();
        path.add(nodeMap.getNodeById(destination));
        while (cameFrom.containsKey(destination)) {
            destination = cameFrom.get(destination);
            path.add(nodeMap.getNodeById(destination));
        }
        Collections.reverse(path);
        return path;
    }
}
