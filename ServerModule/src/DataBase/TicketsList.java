package DataBase;

import Collection.Ticket;

import java.util.Enumeration;
import java.util.Hashtable;

public class TicketsList {
    private Hashtable<String, Ticket> ticketHashtable = new Hashtable<>();

    public synchronized int size() {
        return ticketHashtable.size();
    }

    synchronized Ticket replace(String key, Ticket t){
        return ticketHashtable.replace(key, t);
    }

    synchronized Enumeration keys() {
        return ticketHashtable.keys();
    }

    public synchronized boolean containsKey(String key) {
        return ticketHashtable.containsKey(key);
    }

    public synchronized Ticket get(String key) {
        return ticketHashtable.get(key);
    }

    synchronized Object put(String key, Ticket value) {
        return ticketHashtable.put(key, value);
    }

    synchronized Object remove(String key) {
        return ticketHashtable.remove(key);
    }

    synchronized void clear() {
        ticketHashtable.clear();
    }
}
