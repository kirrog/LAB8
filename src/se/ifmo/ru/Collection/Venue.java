package se.ifmo.ru.Collection;

import org.json.simple.JSONObject;

public class Venue {

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

    Venue(String name, Integer capacity, VenueType type, Address address) {
        this.id = IdGenerator.toGenerate();
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.address = address;
    }
    public Venue(JSONObject jo) {
        this.id = (long)jo.get("id");
        this.name = (String)jo.get("name");
        this.capacity = (Integer)jo.get("capacity");
        this.type = VenueType.valueOf((String)jo.get("type"));
        this.address = new Address((JSONObject)jo.get("address"));
    }

}
