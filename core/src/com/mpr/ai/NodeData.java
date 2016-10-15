package com.mpr.ai;

import java.util.HashMap;

public class NodeData {
    public final int G = 0;
    public final int F = 1;
    public final int H = 2;

    private HashMap<Integer, Double[]> data;

    public NodeData() {
        data = new HashMap<Integer, Double[]>();
    }

    public double getG(int id) {
        if (data.get(id) == null) {
            Double[] initialValue = {0.0, 0.0, 0.0};
            data.put(id, initialValue);
            return 0.0;
        } else {
            return data.get(id)[G];
        }
    }

    public double getF(int id) {
        if (data.get(id) == null) {
            Double[] initialValue = {0.0, 0.0, 0.0};
            data.put(id, initialValue);
            return 0.0;
        } else {
            return data.get(id)[F];
        }
    }

    public double getH(int id) {
        if (data.get(id) == null) {
            Double[] initialValue = {0.0, 0.0, 0.0};
            data.put(id, initialValue);
            return 0.0;
        } else {
            return data.get(id)[H];
        }
    }

    public void setG(int id, double g) {
        Double[] values = {g, getF(id), getH(id)};
        data.put(id, values);
    }

    public void setF(int id, double f) {
        Double[] values = {getG(id), f, getH(id)};
        data.put(id, values);
    }

    public void setH(int id, double h) {
        Double[] values = {getG(id), getF(id), h};
        data.put(id, values);
    }


}
