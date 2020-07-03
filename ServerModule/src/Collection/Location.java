package Collection;

import java.io.Serializable;
import java.util.Objects;


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

    @Override
    public boolean equals(Object obj) {
        Location loc = (Location) obj;
        if(loc == null){
            return false;
        }
        boolean result = (Objects.equals(this.name, loc.name));
        result = result & (Objects.equals(this.x, loc.x));
        result = result & (Objects.equals(this.y, loc.y));
        result = result & (Objects.equals(this.z, loc.z));
        return result;
    }

    public int compareTo(Location sec) {
        int i = 0;
        i += 20*((Integer)this.getId()).compareTo(sec.getId());
        i += 10*((String)this.getName()).compareTo(sec.getName());
        i += 5*((Double)this.getX()).compareTo(sec.getX());
        i += 3*((Float)this.getY()).compareTo(sec.getY());
        i += ((Long)this.getZ()).compareTo(sec.getZ());
        return i;
    }
}
