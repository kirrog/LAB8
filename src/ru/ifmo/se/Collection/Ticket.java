package ru.ifmo.se.Collection;

import java.time.ZonedDateTime;


/** This class contains ticket*/
public class Ticket implements Comparable<Ticket>{
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private long price;
    private String comment;
    private boolean refundable;
    private TicketType type;
    private Venue venue;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public TicketType getType() {
        return type;
    }

    public Venue getVenue() {
        return venue;
    }

    public Ticket(String name, Coordinates coordinates, long price, String comment, boolean refundable, TicketType type, Venue venue) {
        this.id = IdGenerator.toGenerate();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.ZonedDateTime.now();
        this.price = price;
        this.comment = comment;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
    }

    public Ticket(int id, String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, long price, String comment, boolean refundable, TicketType type, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.comment = comment;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
    }

    @Override
    public int compareTo(Ticket o) {
        return name.compareTo(o.getName());
    }

    public int compByField(int i, Ticket o){
        switch (i){
            case 0:
                if (this.getId() < o.getId()){
                    return -1;
                }else {
                    return 1;
                }
            case 1:
                return this.getName().compareTo(o.getName());
            case 2:
                if (this.getCoordinates().getX() < o.getCoordinates().getX()){
                    return -1;
                }else {
                    return 1;
                }
            case 3:
                if (this.getCoordinates().getY() < o.getCoordinates().getY()){
                    return -1;
                }else {
                    return 1;
                }
            case 4:
                return this.getCreationDate().compareTo(o.getCreationDate());
            case 5:
                if (this.getPrice() < o.getPrice()){
                    return -1;
                }else {
                    return 1;
                }
            case 6:
                if (!this.isRefundable() & o.isRefundable()){
                    return -1;
                }else {
                    return 1;
                }
            case 7:
                return this.getType().compareTo(o.getType());
            case 8:
                if (this.getVenue().getId() < o.getVenue().getId()){
                    return -1;
                }else {
                    return 1;
                }
            case 9:
                return this.getVenue().getName().compareTo(o.getVenue().getName());
            case 10:
                if (this.getVenue().getCapacity() < o.getVenue().getCapacity()){
                    return -1;
                }else {
                    return 1;
                }
            case 11:
                return this.getVenue().getAddress().getZipCode().compareTo(o.getVenue().getAddress().getZipCode());
            case 12:
                if (this.getVenue().getAddress().getTown().getX() < o.getVenue().getAddress().getTown().getX()){
                    return -1;
                }else {
                    return 1;
                }
            case 13:
                if (this.getVenue().getAddress().getTown().getY() < o.getVenue().getAddress().getTown().getY()){
                    return -1;
                }else {
                    return 1;
                }
            case 14:
                if (this.getVenue().getAddress().getTown().getZ() < o.getVenue().getAddress().getTown().getZ()){
                    return -1;
                }else {
                    return 1;
                }
            case 15:
                return this.getVenue().getAddress().getTown().getName().compareTo(o.getVenue().getAddress().getTown().getName());
            default: return 0;

        }
    }

    public void writeTicket() {
        Coordinates coord = this.getCoordinates();
        Venue ven = this.getVenue();

        System.out.print("Id: " + this.getId() + " ");
        System.out.print("Name: " + this.getName() + " ");
        System.out.print("Coordinates:{" + " ");
        System.out.print("X: " + coord.getX() + " ");
        System.out.print("Y: " + coord.getX() + " ");
        System.out.print("}" + " ");
        System.out.print("Creation date: " + this.getCreationDate() + " ");
        System.out.print("Price: " + this.getPrice() + " ");
        System.out.print("Comment: " + this.getComment() + " ");
        System.out.print("Refundable: " + this.isRefundable() + " ");
        System.out.print("Type: " + this.getType() + " ");
        System.out.print("Venue:{" + " ");
        System.out.print("Id: " + ven.getId() + " ");
        System.out.print("Name: " + ven.getName() + " ");
        System.out.print("Capacity: " + ven.getCapacity() + " ");
        System.out.print("Type: " + ven.getType() + " ");
        System.out.print("Address:{" + " ");
        if(ven.getAddress() == null){
            System.out.println("null }");
        }else {
            Address addr = ven.getAddress();
            System.out.print("ZipCode: " + addr.getZipCode() + " ");
            System.out.print("Town:{" + " ");
            if(addr.getTown() == null){
                System.out.println("null }");
            }else {
                Location loc = addr.getTown();
                System.out.print("X: " + loc.getX() + " ");
                System.out.print("Y: " + loc.getY() + " ");
                System.out.print("Z: " + loc.getZ() + " ");
                System.out.print("Name: " + loc.getName() + " ");
                System.out.print("}" + " ");
                System.out.print("}" + " ");
                System.out.println("}");
            }
        }
    }
}

