package Collection;

import java.io.Serializable;


/** This class contains coordinates*/
public class Coordinates implements Serializable {



    private int id = -1;
    private long x;
    private double y;

    public void setId(int id){this.id = id;}

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public Coordinates(long x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(int i, long x, double y) {
        this.id = i;
        this.x = x;
        this.y = y;
    }
}
