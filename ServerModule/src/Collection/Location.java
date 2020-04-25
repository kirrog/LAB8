package Collection;

import java.io.Serializable;


/** This class contains location*/
public class Location implements Serializable {
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

    public Location(Double x, Float y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

}