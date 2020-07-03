package GUI.Tables;

import Collection.Address;
import Collection.Location;
import Collection.Ticket;
import Collection.Venue;
import GUI.Localization.LanguagesProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

public class AddressTableModel extends VenuesTableModel {


    protected static LinkedList<Location> towns = new LinkedList<>();

    public static void fillTheAddressTable() {
        addresses.stream()
                .filter(address -> {
                            if (address != null) {
                                return !towns.contains(address.getTown());
                            }
                            return false;
                        }
                )
                .forEachOrdered(address -> {
                            if (address != null) {
                                towns.add(address.getTown());
                            }
                        }
                );
    }

    private static String[] identif = new String[]{
            "Address Id",
            "Zipcode",
            "Town"
    };

    AddressTableModel() {
        fillTheAddressTable();
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
                return LanguagesProvider.adaptNumber(addresses.get(0).getId()).getClass();
            case 1:
                return LanguagesProvider.adaptPhrase(addresses.get(0).getZipCode()).getClass();
            case 2:
                return LanguagesProvider.adaptNumber(addresses.get(0).getTown().getId()).getClass();
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        if (filteredRows == null) {
            return 0;
        }
        return filteredRows.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return LanguagesProvider.adaptNumber(addresses.get(i).getId());
            case 1:
                return LanguagesProvider.adaptPhrase(addresses.get(i).getZipCode());
            case 2:
                return LanguagesProvider.adaptNumber(addresses.get(i).getTown().getId());
            default:
                return null;
        }
    }

    @Override
    Object getNotFormattedValue(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return addresses.get(i).getId();
            case 1:
                return addresses.get(i).getZipCode();
            case 2:
                return addresses.get(i).getTown().getId();
            default:
                return null;
        }
    }

    @Override
    void filter(int column, Object arg) {
        Stream workStream = addresses.stream();
        switch (column) {
            case 0:
                workStream = workStream.filter(addres -> ((((Address) addres).getId()) == (int) arg));
                break;
            case 1:
                workStream = workStream.filter(addres -> ((((Address) addres).getZipCode()).equals(arg)));
                break;
            case 2:
                workStream = workStream.filter(addres -> (((Integer) ((Address) addres).getTown().getId()).equals(arg)));
                break;
            default:
                break;
        }
        filteredRows.clear();
        workStream.forEachOrdered(tick -> filteredRows.add(fromViewToCollection.indexOf(addresses.indexOf(tick))));
    }

    @Override
    void cancel() {
        filteredRows.clear();
        fromCollectionToView.clear();
        fromViewToCollection.clear();
        for (int i = 0; i < addresses.size(); i++) {
            filteredRows.add(i);
            fromCollectionToView.add(i);
            fromViewToCollection.add(i);
        }
    }

    @Override
    void sort(boolean order, int column) {
        Stream workStream = addresses.stream();
        int ord = 1;
        if (!order) {
            ord = -1;
        }
        int finalOrd = ord;
        switch (column) {
            case 0:
                System.out.println("ID");
                workStream = workStream.sorted((o1, o2) -> {
                    Integer fir = ((Address) o1).getId();
                    int sec = ((Address) o2).getId();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 1:
                workStream = workStream.sorted((o1, o2) -> {
                    String fir = ((Address) o1).getZipCode();
                    String sec = ((Address) o2).getZipCode();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 2:
                workStream = workStream.sorted((o1, o2) -> {
                    Location fir = ((Address) o1).getTown();
                    Location sec = ((Address) o2).getTown();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            default:
                break;
        }
        adaptForFiltered(workStream);
    }

    private void adaptForFiltered(Stream<Address> workStream) {
        ArrayList<Integer> ints = null;
        try {
            ints = (ArrayList<Integer>) fromViewToCollection.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fromViewToCollection.clear();
        workStream.forEachOrdered(address -> fromViewToCollection.add(addresses.indexOf(address)));
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

    public static void removeAddress(int id) {
        ArrayList<Address> addressArrayList = new ArrayList<>();
        addresses.stream()
                .filter(address -> address.getId() == id)
                .forEach(address -> addressArrayList.add(address));
        addressArrayList.stream().forEach(address -> addresses.remove(address));
    }

    public static void updateAddress(Address address) {
        removeAddress(address.getId());
        addAddress(address);
    }

    public static void addAddress(Address address) {
        addresses.add(address);
    }

    Ticket getTicketByAddress(int id) throws Exception {
        if (addresses.stream().anyMatch(address -> address.getId() == id)) {
            Address addr = addresses.stream().filter(address -> address.getId() == id).findFirst().get();
            Venue venue = venues.stream().filter(v -> v.getAddress().equals(addr)).findFirst().get();
            return ticketLinkedList.stream().filter(t -> t.getVenue().equals(venue)).findFirst().get();
        } else {
            throw new Exception("No such thing in table");
        }
    }
}
