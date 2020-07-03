package GUI.Tables;

import Collection.Coordinates;
import Collection.Ticket;
import GUI.Localization.LanguagesProvider;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CoordinatesTableModel extends TicketTableModel {

    private static String[] identif = new String[]{
            "Coordinates Id",
            "Coordinates X",
            "Coordinates Y"
    };

    public CoordinatesTableModel(){
        cancel();
    }

    @Override
    public void translate() {
        this.setColumnIdentifiers(LanguagesProvider.translateLine(identif));
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return LanguagesProvider.adaptNumber(coordinates.get(0).getId()).getClass();
            case 1:
                return LanguagesProvider.adaptNumber(coordinates.get(0).getX()).getClass();
            case 2:
                return LanguagesProvider.adaptNumber(coordinates.get(0).getY()).getClass();
            default:
                return null;
        }
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
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return LanguagesProvider.adaptNumber(coordinates.get(i).getId());
            case 1:
                return LanguagesProvider.adaptNumber(coordinates.get(i).getX());
            case 2:
                return LanguagesProvider.adaptNumber(coordinates.get(i).getY());
            default:
                return null;
        }
    }

    @Override
    Object getNotFormattedValue(int rowIndex, int columnIndex) {
        int i = fromViewToCollection.get(filteredRows.get(rowIndex));
        switch (columnIndex) {
            case 0:
                return coordinates.get(i).getId();
            case 1:
                return coordinates.get(i).getX();
            case 2:
                return coordinates.get(i).getY();
            default:
                return null;
        }
    }

    @Override
    void filter(int column, Object arg) {
        Stream workStream = coordinates.stream();
        switch (column) {
            case 0:
                workStream = workStream.filter(coord -> ((((Coordinates) coord).getId()) == (int) arg));
                break;
            case 1:
                workStream = workStream.filter(coord -> (((Long) ((Coordinates) coord).getX()).equals(arg)));
                break;
            case 2:
                workStream = workStream.filter(coord -> (((Double) ((Coordinates) coord).getY()).equals(arg)));
                break;
            default:
                break;
        }
        filteredRows.clear();
        workStream.forEachOrdered(tick -> filteredRows.add(fromViewToCollection.indexOf(coordinates.indexOf(tick))));
    }

    @Override
    void cancel() {
        filteredRows.clear();
        fromCollectionToView.clear();
        fromViewToCollection.clear();
        for (int i = 0; i < coordinates.size(); i++) {
            filteredRows.add(i);
            fromCollectionToView.add(i);
            fromViewToCollection.add(i);
        }
    }

    @Override
    void sort(boolean order, int column) {
        Stream workStream = coordinates.stream();
        int ord = 1;
        if(!order){
            ord = -1;
        }
        int finalOrd = ord;
        switch (column) {
            case 0:
                workStream = workStream.sorted((o1, o2) -> {
                    Integer fir = ((Coordinates) o1).getId();
                    int sec = ((Coordinates) o2).getId();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 1:
                workStream = workStream.sorted((o1, o2) -> {
                    Long fir = ((Coordinates) o1).getX();
                    long sec = ((Coordinates) o2).getX();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            case 2:
                workStream = workStream.sorted((o1, o2) -> {
                    Double fir = ((Coordinates) o1).getY();
                    double sec = ((Coordinates) o2).getY();
                    return finalOrd * fir.compareTo(sec);
                });
                break;
            default:
                break;
        }
        adaptForFiltered(workStream);

    }

    private void adaptForFiltered(Stream<Coordinates> workStream){
        ArrayList<Integer> ints = null;
        try {
            ints = (ArrayList<Integer>) fromViewToCollection.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        fromViewToCollection.clear();
        workStream.forEachOrdered(cord -> fromViewToCollection.add(coordinates.indexOf(cord)));
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

    public static boolean removeCoordinates(int id) {
        ArrayList<Coordinates> coordinatesArrayList = new ArrayList<>();
        coordinates.stream()
                .filter(coordinate -> coordinate.getId() == id)
                .forEach(coordinate -> coordinatesArrayList.add(coordinate));
        coordinatesArrayList.stream().forEach(coordinate -> coordinates.remove(coordinate));
        return true;
    }

    public static void updateCoordinates(Coordinates coordinates) {
        removeCoordinates(coordinates.getId());
        addCoordinates(coordinates);
    }

    public static void addCoordinates(Coordinates coordinate) {
        coordinates.add(coordinate);
    }

    Ticket getTicketByCoordinates(int id) throws Exception {
        if(coordinates.stream().anyMatch(c -> c.getId() == id)){
            Coordinates coord = coordinates.stream().filter(c -> c.getId() == id).findFirst().get();
            return ticketLinkedList.stream().filter(t -> t.getCoordinates().equals(coord)).findFirst().get();
        }
        else {
            throw new Exception("No such thing in table");
        }
    }
}
