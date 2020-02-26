package se.ifmo.ru.Collection;

import java.time.ZonedDateTime;
import java.util.Comparator;

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
}

