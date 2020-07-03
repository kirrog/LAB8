package GUI.Tables;

import Collection.Coordinates;
import Collection.Ticket;
import Collection.TicketType;
import Collection.Venue;
import DataBase.TicketOwner;
import GUI.Localization.LanguagesProvider;
import GUI.Localization.TimeAdapter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

public class TicketTableModel extends BaseTableModel {

    private static LinkedList<Ticket> ticketsForFiltering = new LinkedList<>();
    protected static LinkedList<Coordinates> coordinates = new LinkedList<>();
    protected static LinkedList<Venue> venues = new LinkedList<>();
    private static LinkedList<TicketOwner> userName = new LinkedList<>();

    private static String[] identif = new String[]{
            "Ticket Id",
            "Ticket Name",
            "Coordinates",
            "Creation Date",
            "Price",
            "Comment",
            "Refundable",
            "Ticket Type",
            "Venue",
            "Owner"
    };

    public TicketTableModel(){
        fillTheTicketsTable();
        cancel();
    }

    public void translate() {
        this.setColumnIdentifiers(LanguagesProvider.translateLine(identif));
    }

    public static boolean hasThisKey(String k) {
        return ticketLinkedList.stream()
                .map(ticket -> ticket.getKey())
                .anyMatch(key -> key.equals(k));
    }

    public static void fillTheTicketsTable() {
        ticketLinkedList.stream()
                .filter(ticket -> !ticketsForFiltering.contains(ticket))
                .forEachOrdered(ticket -> {
                    ticketsForFiltering.add(ticket);
                });
        ticketLinkedList.stream()
                .filter(ticket -> !coordinates.contains(ticket.getCoordinates()))
                .forEachOrdered(ticket -> {
                    coordinates.add(ticket.getCoordinates());
                });
        ticketLinkedList.stream()
                .filter(ticket -> !venues.contains(ticket.getVenue()))
                .forEachOrdered(ticket -> {
                    venues.add(ticket.getVenue());
                });
        ticketLinkedList.stream()
                .filter(ticket -> !userName.contains(ticket.getTowner()))
                .forEachOrdered(ticket -> {
                    userName.add(ticket.getTowner());
                });
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public int getRowCount() {
        if(filteredRows == null){
            return 0;
        }
        return filteredRows.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return LanguagesProvider.adaptNumber(ticketsForFiltering.get(0).getId()).getClass();
            case 1:
                return LanguagesProvider.adaptPhrase(ticketsForFiltering.get(0).getName()).getClass();
            case 2:
                return LanguagesProvider.adaptNumber(coordinates.get(0).getId()).getClass();
            case 3:
                return TimeAdapter.adaptTime(ticketsForFiltering.get(0).getCreationDate()).getClass();
            case 4:
                return LanguagesProvider.adaptNumber(ticketsForFiltering.get(0).getPrice()).getClass();
            case 5:
                return LanguagesProvider.adaptPhrase(ticketsForFiltering.get(0).getComment()).getClass();
            case 6:
                return new Boolean(false).getClass();
            case 7:
                return ticketsForFiltering.get(0).getType().getClass();
            case 8:
                return LanguagesProvider.adaptNumber(venues.get(0).getId()).getClass();
            case 9:
                return LanguagesProvider.adaptPhrase(userName.get(0).getName()).getClass();
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return LanguagesProvider.adaptNumber(ticketsForFiltering.get(i).getId());
            case 1:
                return LanguagesProvider.adaptPhrase(ticketsForFiltering.get(i).getName());
            case 2:
                return LanguagesProvider.adaptNumber(ticketsForFiltering.get(i).getCoordinates().getId());
            case 3:
                return TimeAdapter.adaptTime(ticketsForFiltering.get(i).getCreationDate());
            case 4:
                return LanguagesProvider.adaptNumber(ticketsForFiltering.get(i).getPrice());
            case 5:
                return LanguagesProvider.adaptPhrase(ticketsForFiltering.get(i).getComment());
            case 6:
                return ticketsForFiltering.get(i).isRefundable();
            case 7:
                return ticketsForFiltering.get(i).getType();
            case 8:
                return LanguagesProvider.adaptNumber(ticketsForFiltering.get(i).getVenue().getId());
            case 9:
                return LanguagesProvider.adaptPhrase(ticketsForFiltering.get(i).getTowner().getName());
            default:
                return null;
        }
    }

    @Override
    Object getNotFormattedValue(int rowIndex, int columnIndex){
        int i = fromViewToCollection.get(
                filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return ticketsForFiltering.get(i).getId();
            case 1:
                return ticketsForFiltering.get(i).getName();
            case 2:
                return ticketsForFiltering.get(i).getCoordinates().getId();
            case 3:
                return ticketsForFiltering.get(i).getCreationDate();
            case 4:
                return ticketsForFiltering.get(i).getPrice();
            case 5:
                return ticketsForFiltering.get(i).getComment();
            case 6:
                return ticketsForFiltering.get(i).isRefundable();
            case 7:
                return ticketsForFiltering.get(i).getType();
            case 8:
                return ticketsForFiltering.get(i).getVenue().getId();
            case 9:
                return ticketsForFiltering.get(i).getTowner().getName();
            default:
                return null;
        }
    }

    @Override
    void filter(int column, Object arg) {
        Stream workStream = ticketsForFiltering.stream();
        switch (column) {
            case 0:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).getId()) == (long) arg));
                break;
            case 1:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).getName()).equals(arg)));
                break;
            case 2:
                workStream = workStream.filter(ticket -> (((Integer)((Ticket) ticket).getCoordinates().getId()).equals(arg)));
                break;
            case 3:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).getCreationDate()).equals(arg)));
                break;
            case 4:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).getPrice()) == (long) arg));
                break;
            case 5:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).getComment()).equals(arg)));
                break;
            case 6:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).isRefundable()) == (boolean) arg));
                break;
            case 7:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).getType()).equals(arg)));
                break;
            case 8:
                workStream = workStream.filter(ticket -> (((Long)((Ticket) ticket).getVenue().getId()).equals(arg)));
                break;
            case 9:
                workStream = workStream.filter(ticket -> ((((Ticket) ticket).getTowner().getName()).equals(arg)));
                break;
            default:
                break;
        }
        filteredRows.clear();
        workStream.forEachOrdered(tick ->
                {
                    filteredRows.add(fromViewToCollection.indexOf(ticketsForFiltering.indexOf(tick)));
                }
        );
    }

    @Override
    void cancel() {
        filteredRows.clear();
        fromCollectionToView.clear();
        fromViewToCollection.clear();
        for (int i = 0; i < ticketsForFiltering.size(); i++) {
            filteredRows.add(i);
            fromCollectionToView.add(i);
            fromViewToCollection.add(i);
        }
    }

    @Override
    void sort(boolean order, int column) {
        Stream<Ticket> workStream = ticketsForFiltering.stream();
        int ord = 1;
        if(!order){
            ord = -1;
        }
        int finalOrd = ord;
        switch (column) {

            case 0:
                workStream = workStream.sorted((o1, o2) -> {
                    Long fir = ((Ticket)o1).getId();
                    long sec = ((Ticket)o2).getId();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 1:
                workStream = workStream.sorted((o1, o2) -> {
                    String fir = ((Ticket)o1).getName();
                    String sec = ((Ticket)o2).getName();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 2:
                workStream = workStream.sorted((o1, o2) -> {
                    Coordinates fir = ((Ticket)o1).getCoordinates();
                    Coordinates sec = ((Ticket)o2).getCoordinates();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 3:
                workStream = workStream.sorted((o1, o2) -> {
                    ZonedDateTime fir = ((Ticket)o1).getCreationDate();
                    ZonedDateTime sec = ((Ticket)o2).getCreationDate();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 4:
                workStream = workStream.sorted((o1, o2) -> {
                    Long fir = ((Ticket)o1).getPrice();
                    long sec = ((Ticket)o2).getPrice();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 5:
                workStream = workStream.sorted((o1, o2) -> {
                    String fir = ((Ticket)o1).getComment();
                    String sec = ((Ticket)o2).getComment();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 6:
                workStream = workStream.sorted((o1, o2) -> {
                    Boolean fir = ((Ticket)o1).isRefundable();
                    Boolean sec = ((Ticket)o2).isRefundable();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 7:
                workStream = workStream.sorted((o1, o2) -> {
                    TicketType fir = ((Ticket)o1).getType();
                    TicketType sec = ((Ticket)o2).getType();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 8:
                workStream = workStream.sorted((o1, o2) -> {
                    Venue fir = ((Ticket)o1).getVenue();
                    Venue sec = ((Ticket)o2).getVenue();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 9:
                workStream = workStream.sorted((o1, o2) -> {
                    TicketOwner fir = ((Ticket)o1).getTowner();
                    TicketOwner sec = ((Ticket)o2).getTowner();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            default:
                break;
        }
        adaptForFiltered(workStream);
    }

    private void adaptForFiltered(Stream<Ticket> workStream){
        ArrayList<Integer> ints = null;
        try {
            ints = (ArrayList<Integer>) fromViewToCollection.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        fromViewToCollection.clear();
        workStream.forEachOrdered(tick -> {
            fromViewToCollection.add(ticketsForFiltering.indexOf(tick));
        });
        for (int i = 0; i < filteredRows.size(); i++) {
            int filterIndex = filteredRows.get(i);
            int numberOfTicket = ints.get(filterIndex);
            int newPosition = fromViewToCollection.indexOf(numberOfTicket);
            filteredRows.set(i, newPosition);
        }
        filteredRows.sort((o1, o2) -> {
            Integer fir = (int) o1;
            int sec = (int) o2;
            return (-1) * fir.compareTo(sec);
        });
    }

    public static boolean removeTicket(Ticket t) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        ticketsForFiltering.stream()
                .filter(ticket -> ticket.getId() == t.getId())
                .forEach(ticket -> tickets.add(ticket));
        tickets.stream().forEach(ticket -> ticketsForFiltering.remove(ticket));
        return true;
    }

    public static boolean updateTicket(Ticket ticket) {
        return removeTicket(ticket) & addTicket(ticket);
    }

    public static boolean addTicket(Ticket ticket) {
        return ticketsForFiltering.add(ticket);
    }
}
