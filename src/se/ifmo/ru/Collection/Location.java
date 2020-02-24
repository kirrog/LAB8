package se.ifmo.ru.Collection;

import org.json.simple.JSONObject;

public class Location {
    private Double x;
    private Float y;
    private Long z;
    private String name;

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    Location(Double x, Float y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    Location(JSONObject jo) {
        this.x = (Double) jo.get("x");
        this.y =(Float) jo.get("y");
        this.z = (Long) jo.get("z");
        this.name = (String) jo.get("name");
    }
}
