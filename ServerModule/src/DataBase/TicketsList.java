package DataBase;

import Collection.Ticket;
import Commands.CheckCondition;

import java.util.Enumeration;
import java.util.Hashtable;

public class TicketsList {
    private Hashtable<String, Ticket> ticketHashtable = new Hashtable<>();

    public synchronized int size() {
        return ticketHashtable.size();
    }

    synchronized Ticket replace(String key, Ticket t){
        Ticket tPrev = ticketHashtable.replace(key, t);
        CheckCondition.updateTicket(ticketHashtable.get(key));
        return tPrev;
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
        ticketHashtable.put(key, value);
        CheckCondition.addTicket(ticketHashtable.get(key));
        return true;
    }

    synchronized Object remove(String key) {
        CheckCondition.removeTicket(ticketHashtable.get(key));
        return ticketHashtable.remove(key);
    }

//    synchronized void clear() {
//        ticketHashtable.clear();
//    }
}
