package ru.ifmo.se.Collection;

import org.json.simple.JSONObject;


/** This class contains address*/
public class Address {
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
    Address(JSONObject jo){
        this.zipCode = (String) jo.get("zipCode");
        this.town = new Location((JSONObject)jo.get("town"));
    }
}
