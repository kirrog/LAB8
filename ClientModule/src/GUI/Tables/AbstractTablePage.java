package GUI.Tables;

import GUI.AbstractPageShower;

import javax.swing.*;
import java.awt.*;


public abstract class AbstractTablePage extends AbstractPageShower {

    private static JPanel jModelPanel;
    private static CardLayout modelCardLayout;

    private static TicketTable ticketTable;
    private static CoordinatesTable coordinatesTable;
    private static VenuesTable venuesTable;
    private static AddressTable addressTable;
    private static TownTable townTable;

    private static AbstractPageShower[] aps;

    public static AbstractPageShower[] makeTables(CardLayout cardLayout, JPanel jPanel){
        jModelPanel = jPanel;
        modelCardLayout = cardLayout;
        aps = new AbstractPageShower[5];

        ticketTable = new TicketTable(cardLayout, jPanel);
        aps[0] = ticketTable;

        coordinatesTable = new CoordinatesTable(cardLayout, jPanel);
        aps[1] = coordinatesTable;

        venuesTable = new VenuesTable(cardLayout, jPanel);
        aps[2] = venuesTable;

        addressTable = new AddressTable(cardLayout, jPanel);
        aps[3] = addressTable;

        townTable = new TownTable(cardLayout, jPanel);
        aps[4] = townTable;

        return aps;
    }

    public static void updateTables(){
        ((TableNavigation)aps[0]).cancel();
        ((TableNavigation)aps[1]).cancel();
        ((TableNavigation)aps[2]).cancel();
        ((TableNavigation)aps[3]).cancel();
        ((TableNavigation)aps[4]).cancel();
    }

    public static void showTable(int i){
        if(i > 4) return;
        aps[i].show(true);
    }


}
