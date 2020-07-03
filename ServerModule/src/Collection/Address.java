package Collection;

import java.io.Serializable;
import java.util.Objects;


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

    @Override
    public boolean equals(Object obj) {
        Address address = (Address) obj;
        if(address == null){
            return false;
        }
        boolean result = address.getZipCode().equals(this.getZipCode());
        result = result & Objects.equals(this.town, address.town);
        return result;
    }

    public int compareTo(Address sec) {
        int i = 0;
        i += 5*((Integer)this.getId()).compareTo(sec.getId());
        i += 3*(this.getZipCode()).compareTo(sec.getZipCode());
        i += (this.getTown()).compareTo(sec.getTown());
        return i;
    }
}
