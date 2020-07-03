package GUI;

//Just a table with all of Tickets
//Provide ability to call commands
//5 Tables contains it it

import GUI.Localization.LanguagesProvider;
import GUI.Tables.AbstractTablePage;
import GUI.Tables.BaseTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablePage extends AbstractPageShower {

    private WorkPage workPage;

    private JPanel jpTemplate;
    private JPanel jpTable;
    private JButton jButtonTicket;
    private JButton jButtonCoordinates;
    private JButton jButtonVenue;
    private JButton jButtonAddress;
    private JButton jButtonTown;

    private CardLayout tablesCards;
    private AbstractPageShower[] tables;

    public TablePage(WorkPage workP) {

        workPage = workP;

        jp = workPage.jp;
        jpTemplate = workPage.jpTemplate;

        jpTable = new JPanel();
        jpTable.setBackground(Color.RED);
        tablesCards = new CardLayout();
        jpTable.setLayout(tablesCards);

        BaseTableModel.fillTheTable();

        tables = AbstractTablePage.makeTables(tablesCards, jpTable);

        jButtonTicket = new JButton("Ticket");
        jButtonTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tables[0].show(true);
            }
        });
        GridBagConstraints bagC5 = new GridBagConstraints();
        bagC5.insets = new Insets(10, 0, 10, 0);
        bagC5.weightx = 0;
        bagC5.weighty = 0;
        bagC5.gridx = 0;
        bagC5.gridy = 1;
        bagC5.gridheight = 1;
        bagC5.gridwidth = 1;
        jp.add(jButtonTicket, bagC5);
        jButtonTicket.setVisible(false);

        jButtonCoordinates = new JButton("Coordinates");
        jButtonCoordinates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tables[1].show(true);
            }
        });
        GridBagConstraints bagC6 = new GridBagConstraints();
        bagC6.insets = new Insets(10, 0, 10, 0);
        bagC6.weightx = 0;
        bagC6.weighty = 0;
        bagC6.gridx = 1;
        bagC6.gridy = 1;
        bagC6.gridheight = 1;
        bagC6.gridwidth = 1;
        jp.add(jButtonCoordinates, bagC6);
        jButtonCoordinates.setVisible(false);

        jButtonVenue = new JButton("Venue");
        jButtonVenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tables[2].show(true);
            }
        });
        GridBagConstraints bagC7 = new GridBagConstraints();
        bagC7.insets = new Insets(10, 0, 10, 0);
        bagC7.weightx = 0;
        bagC7.weighty = 0;
        bagC7.gridx = 2;
        bagC7.gridy = 1;
        bagC7.gridheight = 1;
        bagC7.gridwidth = 1;
        jp.add(jButtonVenue, bagC7);
        jButtonVenue.setVisible(false);

        jButtonAddress = new JButton("Address");
        jButtonAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tables[3].show(true);
            }
        });
        GridBagConstraints bagC8 = new GridBagConstraints();
        bagC8.insets = new Insets(10, 0, 10, 0);
        bagC8.weightx = 0;
        bagC8.weighty = 0;
        bagC8.gridx = 3;
        bagC8.gridy = 1;
        bagC8.gridheight = 1;
        bagC8.gridwidth = 1;
        jp.add(jButtonAddress, bagC8);
        jButtonAddress.setVisible(false);

        jButtonTown = new JButton("Town");
        jButtonTown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tables[4].show(true);
            }
        });
        GridBagConstraints bagC9 = new GridBagConstraints();
        bagC9.insets = new Insets(10, 0, 10, 0);
        bagC9.weightx = 0;
        bagC9.weighty = 0;
        bagC9.gridx = 4;
        bagC9.gridy = 1;
        bagC9.gridheight = 1;
        bagC9.gridwidth = 1;
        jp.add(jButtonTown, bagC9);
        jButtonTown.setVisible(false);



        jpTemplate.add(jpTable, "1");
    }

    @Override
    public void show(boolean show) {
        if (show) {
            workPage.cardLayout.show(jpTemplate, "1");
        }
        jButtonTicket.setVisible(show);
        jButtonCoordinates.setVisible(show);
        jButtonVenue.setVisible(show);
        jButtonAddress.setVisible(show);
        jButtonTown.setVisible(show);
    }

    private String[] names = {"Ticket", "Coordinates", "Venue", "Address", "Town"};

    @Override
    public void translate() {
        jButtonTicket.setText(LanguagesProvider.adaptPhrase(names[0]));
        jButtonCoordinates.setText(LanguagesProvider.adaptPhrase(names[1]));
        jButtonVenue.setText(LanguagesProvider.adaptPhrase(names[2]));
        jButtonAddress.setText(LanguagesProvider.adaptPhrase(names[3]));
        jButtonTown.setText(LanguagesProvider.adaptPhrase(names[4]));
        for (int i = 0; i < tables.length; i++) {
            tables[i].translate();
        }
    }
}
