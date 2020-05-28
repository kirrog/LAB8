package DataBase;

import Collection.Ticket;
import WebRes.Contact;
import WebRes.Receiver;
import WebRes.Sender;
import WriteInOut.ServerUI;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.stream.Stream;

public class ThreadResurses {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ThreadResurses.class);
    public TicketOwner ticketOwner;
    public TicketsList ticketsList;
    public BaseOwners owners;
    DataBaseManagerTickets dbmt;
    public Contact contact;
    public Receiver receiver;
    public Sender sender;
    public ServerUI serverUI;

    public ThreadResurses(DataBaseManagerTickets d, Contact con) {
        dbmt = d;
        ticketsList = dbmt.getTickList();
        owners = dbmt.getOwners();
        contact = con;
        receiver = con.getReceiver();
        sender = con.getSender();
    }

    public ThreadResurses(DataBaseManagerTickets d) {
        dbmt = d;
        ticketsList = dbmt.getTickList();
        owners = dbmt.getOwners();
    }

    public boolean clearT() {
        dbmt.rLock.lock();
        getStreamT().filter(ticket -> ticket.getTowner().getId() == ticketOwner.getId()).forEach(ticket -> ticketsList.remove(ticket.getKey()));
        dbmt.rLock.unlock();
        return true;
    }

//    public boolean clearO(){
//        dbmt.rLock.lock();
//        dbmt.rLock.unlock();
//        return false;
//    }

    public Stream<Ticket> getStreamT() {
        dbmt.rLock.lock();
        Enumeration enums = ticketsList.keys();
        LinkedList<Ticket> stringLinkedList = new LinkedList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add(ticketsList.get((String) enums.nextElement()));
        }
        dbmt.rLock.unlock();
        return stringLinkedList.stream();
    }

    public Stream<TicketOwner> getStreamO() {
        dbmt.rLock.lock();
        Enumeration enums = owners.keys();
        LinkedList<TicketOwner> stringLinkedList = new LinkedList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add(owners.get((Integer) enums.nextElement()));
        }
        dbmt.rLock.unlock();
        return stringLinkedList.stream();
    }

    public boolean putT(String key, Ticket t) {
        boolean is = false;
        dbmt.rLock.lock();
        t.setKey(key);
        t.setTowner(ticketOwner);
        if (dbmt.sendTicket(t, key) > 0) {
            ticketsList.put(key, t);
            is = true;
        }
        dbmt.rLock.unlock();
        return is;
    }

    public boolean removeT(Ticket t, String key) {
        boolean is = false;
        dbmt.rLock.lock();
        t.setKey(key);
        if (dbmt.deleteTicket(t)) {
            ticketsList.remove(key);
            is = true;
        }
        dbmt.rLock.unlock();
        return is;
    }

    public boolean updateT(Ticket t, Ticket prev) {
        boolean is = false;
        t.setTowner(ticketOwner);
        t.setKey(prev.getKey());
        dbmt.rLock.lock();
        if (dbmt.updateTicket(t,prev, prev.getKey()) > 0) {
            ticketsList.replace(prev.getKey(), t);
            is = true;
        }
        dbmt.rLock.unlock();
        return is;
    }

    public boolean putO(TicketOwner t) {
        boolean is = false;
        dbmt.rLock.lock();
        if (dbmt.sendTOwner(t, true) > 0) {
            owners.put(t.getId(), t);
            is = true;
        }
        dbmt.rLock.unlock();
        return is;
    }

    public boolean removeO(TicketOwner t) {
        boolean is = false;
        dbmt.rLock.lock();
        if (dbmt.deleteTOwner(t)) {
            owners.remove(t.getId());
            is = true;
        }
        dbmt.rLock.unlock();
        return is;
    }

    public boolean updateO(TicketOwner t) {
        boolean is = false;
        dbmt.rLock.lock();
        if (dbmt.sendTOwner(t, false) > 0) {
            owners.replace(t.getId(), t);
            is = true;
        }
        dbmt.rLock.unlock();
        return is;
    }

//    public Ticket getT(String key){
//        dbmt.rLock.lock();
//        Ticket t = ticketsList.get(key);
//        dbmt.rLock.unlock();
//        return t;
//    }
//
//    public TicketOwner getO(Integer key){
//        dbmt.rLock.lock();
//        TicketOwner t = owners.get(key);
//        dbmt.rLock.unlock();
//        return t;
//    }
}
