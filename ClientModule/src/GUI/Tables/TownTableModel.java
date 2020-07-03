package GUI.Tables;

import Collection.Address;
import Collection.Location;
import Collection.Ticket;
import Collection.Venue;
import GUI.Localization.LanguagesProvider;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TownTableModel extends AddressTableModel {

    private static String[] identif = new String[]{
            "Town Id",
            "Town X",
            "Town Y",
            "Town Z",
            "Town Name"
    };

    public TownTableModel(){
        cancel();
    }

    @Override
    public void translate(){
        this.setColumnIdentifiers(LanguagesProvider.translateLine(identif));
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column){
            case 0: return LanguagesProvider.adaptNumber(towns.get(0).getId()).getClass();
            case 1: return LanguagesProvider.adaptNumber(towns.get(0).getX()).getClass();
            case 2: return LanguagesProvider.adaptNumber(towns.get(0).getY()).getClass();
            case 3: return LanguagesProvider.adaptNumber(towns.get(0).getZ()).getClass();
            case 4: return LanguagesProvider.adaptPhrase(towns.get(0).getName()).getClass();
            default: return null;
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
        switch (columnIndex){
            case 0: return LanguagesProvider.adaptNumber(towns.get(i).getId());
            case 1: return LanguagesProvider.adaptNumber(towns.get(i).getX());
            case 2: return LanguagesProvider.adaptNumber(towns.get(i).getY());
            case 3: return LanguagesProvider.adaptNumber(towns.get(i).getZ());
            case 4: return LanguagesProvider.adaptPhrase(towns.get(i).getName());
            default: return null;
        }
    }

    @Override
    Object getNotFormattedValue(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex){
            case 0: return towns.get(i).getId();
            case 1: return towns.get(i).getX();
            case 2: return towns.get(i).getY();
            case 3: return towns.get(i).getZ();
            case 4: return towns.get(i).getName();
            default: return null;
        }
    }

    @Override
    void filter(int column, Object arg) {
        Stream workStream = towns.stream();
        switch (column) {
            case 0:
                workStream = workStream.filter(town -> ((((Location) town).getId()) == (int)arg));
                break;
            case 1:
                workStream = workStream.filter(town -> ((((Location) town).getX()).equals(arg)));
                break;
            case 2:
                workStream = workStream.filter(town -> ((((Location) town).getY()).equals(arg)));
                break;
            case 3:
                workStream = workStream.filter(town -> ((((Location) town).getZ()).equals(arg)));
                break;
            case 4:
                workStream = workStream.filter(town -> ((((Location) town).getName()).equals(arg)));
                break;
            default:
                break;
        }
        filteredRows.clear();
        workStream.forEachOrdered(town -> filteredRows.add(fromViewToCollection.indexOf(towns.indexOf(town))));
    }

    @Override
    void cancel() {
        filteredRows.clear();
        fromCollectionToView.clear();
        fromViewToCollection.clear();
        for (int i = 0; i < towns.size(); i++) {
            filteredRows.add(i);
            fromCollectionToView.add(i);
            fromViewToCollection.add(i);
        }
    }

    @Override
    void sort(boolean order, int column) {
        Stream workStream = towns.stream();
        int ord = 1;
        if(!order){
            ord = -1;
        }
        int finalOrd = ord;
        switch (column) {
            case 0:
                workStream = workStream.sorted((o1, o2) -> {
                    Integer fir = ((Location)o1).getId();
                    int sec = ((Location)o2).getId();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 1:
                workStream = workStream.sorted((o1, o2) -> {
                    Double fir = ((Location)o1).getX();
                    Double sec = ((Location)o2).getX();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 2:
                workStream = workStream.sorted((o1, o2) -> {
                    Float fir = ((Location)o1).getY();
                    Float sec = ((Location)o2).getY();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 3:
                workStream = workStream.sorted((o1, o2) -> {
                    Long fir = ((Location)o1).getZ();
                    Long sec = ((Location)o2).getZ();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 4:
                workStream = workStream.sorted((o1, o2) -> {
                    String fir = ((Location)o1).getName();
                    String sec = ((Location)o2).getName();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            default:
                break;
        }

        adaptForFiltered(workStream);
    }

    private void adaptForFiltered(Stream<Location> workStream){
        ArrayList<Integer> ints = null;
        try {
            ints = (ArrayList<Integer>) fromViewToCollection.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        fromViewToCollection.clear();
        workStream.forEachOrdered(town -> {
            fromViewToCollection.add(towns.indexOf(town));
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

    public static void removeTown(int id) {
        ArrayList<Location> locations = new ArrayList<>();
        towns.stream()
                .filter(town -> town.getId() == id)
                .forEach(town -> locations.add(town));
        locations.stream().forEach(town -> towns.remove(town));
    }

    public static void updateTown(Location town) {
        removeTown(town.getId());
        addTown(town);
    }

    public static void addTown(Location town) {
        towns.add(town);
    }

    Ticket getTicketByTown(int id) throws Exception {
        if(towns.stream().anyMatch(town -> town.getId() == id)){
            Location loc = towns.stream().filter(town -> town.getId() == id).findFirst().get();
            Address addr = addresses.stream().filter(address -> address.getTown().equals(loc) ).findFirst().get();
            Venue venue = venues.stream().filter(v -> v.getAddress().equals(addr)).findFirst().get();
            return ticketLinkedList.stream().filter(t -> t.getVenue().equals(venue)).findFirst().get();
        }
        else {
            throw new Exception("No such thing in table");
        }
    }
}
