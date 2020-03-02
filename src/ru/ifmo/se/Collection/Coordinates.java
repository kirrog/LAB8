package ru.ifmo.se.Collection;

import org.json.simple.JSONObject;


/** This class contains coordinates*/
public class Coordinates {
    private long x;
    private double y;

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Coordinates(long x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(JSONObject jo) {
        this.x = (long)jo.get("x");
        this.y = (double)jo.get("y");
    }
}
