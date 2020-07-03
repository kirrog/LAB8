package GUI.Tables;

import Collection.Address;
import Collection.Ticket;
import Collection.Venue;
import Collection.VenueType;
import GUI.Localization.LanguagesProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

public class VenuesTableModel extends TicketTableModel {

    protected static LinkedList<Address> addresses = new LinkedList<>();

    public static void fillTheVenueTable() {
        venues.stream()
                .filter(venue -> !addresses.contains(venue.getAddress()))
                .forEachOrdered(venue -> addresses.add(venue.getAddress()));
    }

    private static String[] identif = new String[]{
            "Venue Id",
            "Venue Name",
            "Capacity",
            "Venue Type",
            "Address"
    };

    public VenuesTableModel(){
        fillTheVenueTable();
        cancel();
    }

    @Override
    public void translate() {
        this.setColumnIdentifiers(LanguagesProvider.translateLine(identif));
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return LanguagesProvider.adaptNumber(venues.get(0).getId()).getClass();
            case 1:
                return venues.get(0).getName().getClass();
            case 2:
                return LanguagesProvider.adaptNumber(venues.get(0).getCapacity()).getClass();
            case 3:
                return venues.get(0).getType().getClass();
            case 4:
                return LanguagesProvider.adaptNumber(venues.get(0).getAddress().getId()).getClass();
            default:
                return null;
        }
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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return LanguagesProvider.adaptNumber(venues.get(i).getId());
            case 1:
                return venues.get(i).getName();
            case 2:
                return LanguagesProvider.adaptNumber(venues.get(i).getCapacity());
            case 3:
                return venues.get(i).getType();
            case 4:
                return LanguagesProvider.adaptNumber(venues.get(i).getAddress().getId());
            default:
                return null;
        }
    }

    @Override
    Object getNotFormattedValue(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return venues.get(i).getId();
            case 1:
                return venues.get(i).getName();
            case 2:
                return venues.get(i).getCapacity();
            case 3:
                return venues.get(i).getType();
            case 4:
                return venues.get(i).getAddress().getId();
            default:
                return null;
        }
    }

    @Override
    void filter(int column, Object arg) {
        Stream workStream = venues.stream();
        switch (column) {
            case 0:
                workStream = workStream.filter(venue -> ((((Venue) venue).getId()) == (long) arg));
                break;
            case 1:
                workStream = workStream.filter(venue -> ((((Venue) venue).getName()).equals(arg)));
                break;
            case 2:
                workStream = workStream.filter(venue -> ((((Venue) venue).getCapacity()).equals(arg)));
                break;
            case 3:
                workStream = workStream.filter(venue -> ((((Venue) venue).getType()).equals(arg)));
                break;
            case 4:
                workStream = workStream.filter(venue -> (((Integer)((Venue) venue).getAddress().getId()).equals(arg)));
                break;
            default:
                break;
        }
        filteredRows.clear();
        workStream.forEachOrdered(venue -> filteredRows.add(fromViewToCollection.indexOf(venues.indexOf(venue))));
    }

    @Override
    void cancel() {
        filteredRows.clear();
        fromCollectionToView.clear();
        fromViewToCollection.clear();
        for (int i = 0; i < venues.size(); i++) {
            filteredRows.add(i);
            fromCollectionToView.add(i);
            fromViewToCollection.add(i);
        }
    }

    @Override
    void sort(boolean order, int column) {
        Stream workStream = venues.stream();
        int ord = 1;
        if(!order){
            ord = -1;
        }
        int finalOrd = ord;
        switch (column) {
            case 0:
                workStream = workStream.sorted((o1, o2) -> {
                    Long fir = ((Venue) o1).getId();
                    long sec = ((Venue) o2).getId();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 1:
                workStream = workStream.sorted((o1, o2) -> {
                    String fir = ((Venue) o1).getName();
                    String sec = ((Venue) o2).getName();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 2:
                workStream = workStream.sorted((o1, o2) -> {
                    Integer fir = ((Venue) o1).getCapacity();
                    int sec = ((Venue) o2).getCapacity();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 3:
                workStream = workStream.sorted((o1, o2) -> {
                    VenueType fir = ((Venue) o1).getType();
                    VenueType sec = ((Venue) o2).getType();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 4:
                workStream = workStream.sorted((o1, o2) -> {
                    Address fir = ((Venue) o1).getAddress();
                    Address sec = ((Venue) o2).getAddress();
                    return fir.compareTo(sec);
                });
                break;
            default:
                break;
        }
        adaptForFiltered(workStream);
    }


    private void adaptForFiltered(Stream<Venue> workStream){
        ArrayList<Integer> ints = null;
        try {
            ints = (ArrayList<Integer>) fromViewToCollection.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        fromViewToCollection.clear();
        workStream.forEachOrdered(venue -> {
            fromViewToCollection.add(venues.indexOf(venue));
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

    public static void removeVenue(long id) {
        ArrayList<Venue> venueArrayList = new ArrayList<>();
        venues.stream()
                .filter(venue -> venue.getId() == id)
                .forEach(venue -> venueArrayList.add(venue));
        venueArrayList.stream().forEach(venue -> venues.remove(venue));
    }

    public static void updateVenue(Venue venue) {
        removeVenue(venue.getId());
        addVenue(venue);
    }

    public static void addVenue(Venue venue) {
        venues.add(venue);
    }

    Ticket getTicketByVenue(int id) throws Exception {
        if(venues.stream().anyMatch(v -> v.getId() == id)){
            Venue venue = venues.stream().filter(v -> v.getId() == id).findFirst().get();
            return ticketLinkedList.stream().filter(t -> t.getVenue().equals(venue)).findFirst().get();
        }
        else {
            throw new Exception("No such thing in table");
        }
    }
}
