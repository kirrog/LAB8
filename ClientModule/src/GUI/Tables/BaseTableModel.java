package GUI.Tables;

import Collection.Ticket;
import DataBase.TicketOwner;
import GUI.CommandFormer;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class BaseTableModel extends DefaultTableModel {

    protected static LinkedList<Ticket> ticketLinkedList = new LinkedList<>();

    public static LinkedList<Ticket> getTicketLinkedList(){
        return ticketLinkedList;
    }

    public static void fillTheTable() {
        fillTheTable(CommandFormer.show());
    }

    public static void fillTheTable(ArrayList<Ticket> tickets) {
        if(tickets != null){
            ticketLinkedList.addAll(tickets);
        }
    }

    public static void clear(){
        ticketLinkedList.clear();
        TicketTableModel.fillTheTable();
        VenuesTableModel.fillTheVenueTable();
        AddressTableModel.fillTheAddressTable();
    }

    public static boolean findTicketById(int id){
        return ticketLinkedList.stream().anyMatch(ticket -> ((Long)ticket.getId()).equals((long)id));
    }

    public static boolean checkUserByTicketId(int ticketId, TicketOwner ticketOwner){
        return ticketLinkedList.stream().anyMatch(ticket -> ((Long)ticket.getId()).equals((long)ticketId) && ticket.getTowner().getName().equals(ticketOwner.getName()));
    }

    public static Ticket getTicket(int index) throws Exception{
        if(ticketLinkedList.stream().anyMatch(ticket -> ticket.getId() == index)){
            return ticketLinkedList.stream().filter(ticket -> ticket.getId() == index).findFirst().get();
        }
        else {
            throw new Exception("No such thing in table");
        }
    }

    public static boolean removeTicket(Ticket ticket) {
        if(ticket == null){
            return false;
        }else {
            TicketTableModel.removeTicket(ticket);
            CoordinatesTableModel.removeCoordinates(ticket.getCoordinates().getId());
            VenuesTableModel.removeVenue(ticket.getVenue().getId());
            AddressTableModel.removeAddress(ticket.getVenue().getAddress().getId());
            TownTableModel.removeTown(ticket.getVenue().getAddress().getTown().getId());
            return true;
        }
    }

    public static boolean updateTicket(Ticket ticket) {
        return removeTicket(ticket) & addTicket(ticket);
    }

    public static boolean addTicket(Ticket ticket) {
        if(ticketLinkedList.stream().noneMatch(t -> t.getId() == ticket.getId())){
            TicketTableModel.addTicket(ticket);
            CoordinatesTableModel.addCoordinates(ticket.getCoordinates());
            VenuesTableModel.addVenue(ticket.getVenue());
            AddressTableModel.addAddress(ticket.getVenue().getAddress());
            TownTableModel.addTown(ticket.getVenue().getAddress().getTown());
            ticketLinkedList.add(ticket);
            return true;
        }
        return false;
    }

    protected ArrayList<Integer> fromCollectionToView = new ArrayList<>(0);

    protected ArrayList<Integer> fromViewToCollection = new ArrayList<>(0);

    protected ArrayList<Integer> filteredRows = new ArrayList<>(0);

    abstract void filter(int column, Object arg);

    abstract void cancel();

    abstract void sort(boolean order, int column);

    abstract Object getNotFormattedValue(int rowIndex, int columnIndex);

}
