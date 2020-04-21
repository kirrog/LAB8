package Collection;

import java.io.Serializable;


/** This class contains coordinates*/
public class Coordinates implements Serializable {
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
}
