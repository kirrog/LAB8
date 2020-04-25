package Collection;

import java.io.Serializable;


/** This class contains address*/
public class Address implements Serializable {
    private String zipCode = null;
    private Location town = null;

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


}
