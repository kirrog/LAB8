package Collection;

import java.io.Serializable;


/**
 * This class contains venue
 */
public class Venue implements Serializable {

    private long id;
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

    public Venue(String name, Integer capacity, VenueType type, Address address) {
        this.id = IdGenerator.toGenerate();
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

        return (this.getId() == ven.getId()) & (this.getAddress().equals(ven.getAddress())) & (this.getType().equals(ven.getType())) & (this.getName().equals(ven.getName())) & (this.getCapacity().equals(ven.getCapacity()));
    }
}
