package Collection;

import java.io.Serializable;


/** This class contains address*/
public class Address implements Serializable {

    private int id = -1;
    private String zipCode = null;
    private Location town = null;

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

    public String getZipCode() {
        return zipCode;
    }

    public Location getTown() {
        return town;
    }

    public Address(String zipCode, Location town){
        this.zipCode = zipCode;
        this.town = town;
    }

    public Address(int i, String zipCode, Location town){
        this.zipCode = zipCode;
        this.town = town;
        this.id = i;
    }

}
