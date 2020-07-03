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

    public int compareTo(Coordinates sec) {
        int i = 0;
        if(this.getId() < sec.getId()){
            i -= 5;
        }else if(this.getId() > sec.getId()){
            i += 5;
        }

        if(this.getX() < sec.getX()){
            i -= 3;
        }else if(this.getX() > sec.getX()){
            i += 3;
        }

        if(this.getY() < sec.getY()){
            i -= 1;
        }else if(this.getY() > sec.getY()){
            i += 1;
        }

        return i;
    }
}
