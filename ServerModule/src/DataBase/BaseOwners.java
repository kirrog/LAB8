package DataBase;

import java.util.Enumeration;
import java.util.Hashtable;

public class BaseOwners {
    private Hashtable<Integer, TicketOwner> toal = new Hashtable<Integer, TicketOwner>();

    public synchronized int size() {
        return toal.size();
    }

    synchronized TicketOwner replace(Integer key, TicketOwner t){
        return toal.replace(key, t);
    }

    public synchronized Enumeration keys() {
        return toal.keys();
    }

    public synchronized boolean containsKey(Integer key) {
        return toal.containsKey(key);
    }

    public synchronized TicketOwner get(Integer key) {
        return toal.get(key);
    }

    synchronized Object put(Integer key, TicketOwner value) {
        return toal.put(key, value);
    }

    synchronized Object remove(Integer key) {
        return toal.remove(key);
    }
}
