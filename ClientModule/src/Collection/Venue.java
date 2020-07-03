package Collection;

import java.io.Serializable;
import java.util.Objects;


/**
 * This class contains venue
 */
public class Venue implements Serializable {

    private long id = -1;
    private String name;
    private Integer capacity;
    private VenueType type;
    private Address address;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public VenueType getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(long id){this.id = id;}

    public Venue(String name, Integer capacity, VenueType type, Address address) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }

    public Venue(long id, String name, Integer capacity, VenueType type, Address address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }


    @Override
    public boolean equals(Object obj) {
        Venue ven = (Venue) obj;
        boolean result = (this.getId() == ven.getId());
        result = result & (Objects.equals(this.getAddress(), ven.getAddress()));
        result = result & (this.getType().equals(ven.getType()));
        result = result & (this.getName().equals(ven.getName()));
        return result & (this.getCapacity().equals(ven.getCapacity()));
    }

    //1 3 5 10 20

    public int compareTo(Venue sec) {
        int i = 0;
        i += 20*((Long)this.getId()).compareTo(sec.getId());
        i += 10*((String)this.getName()).compareTo(sec.getName());
        i += 5*((Integer)this.getCapacity()).compareTo(sec.getCapacity());
        i += 3*(this.getType()).compareTo(sec.getType());
        i += (this.getAddress()).compareTo(sec.getAddress());
        return i;
    }
}
