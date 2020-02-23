package se.ifmo.ru.Collection;

class Address {
    private String zipCode = null;
    private Location town = null;

    Address(String zipCode, Location town){
        this.zipCode = zipCode;
        this.town = town;
    }
}
