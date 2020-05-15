package Collection;

import java.io.Serializable;


/**
 * This class contains location
 */
public class Location implements Serializable {


    private int id = -1;
    private Double x;
    private Float y;
    private Long z;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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

    public Location(Double x, Float y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Location(int i, Double x, Float y, Long z, String name) {
        this.id = i;
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

}
