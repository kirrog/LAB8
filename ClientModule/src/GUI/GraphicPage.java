package GUI;

//About showing Tickets in it
// Can create TicketView and provide opportunity to work with them
//

import Collection.Ticket;
import GUI.Graphic.Painter;
import GUI.Localization.LanguagesProvider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GraphicPage extends AbstractPageShower {

    private WorkPage workPage;
    private JPanel jpTemplate;
    private JPanel jpTable;
    private JPanel artPanel;

    private JTable jTable;
    private DefaultTableModel tableModel;
    private JScrollPane jsP;

    private Painter painter;

    public Painter getPainter(){
        return painter;
    }

    private int rowCount = 23;

    private Object data[][] = new Object[rowCount][2];
    private String columnNames[] = {"", "Ticket"};

    public Dimension getPageSize(){
        return painter.getSize();
    }

    public GraphicPage(WorkPage workP) {
        workPage = workP;

        jp = workPage.jp;
        jpTemplate = workP.jpTemplate;

        jpTable = new JPanel();
        artPanel = new JPanel();
        painter = new Painter(this);
        //artPanel.add(painter);

        jpTable.setBackground(Color.darkGray);

        jpTable.setPreferredSize(new Dimension(300, 200));

        jpTemplate.add(jpTable, "2");

        jpTable.setLayout(new GridBagLayout());



        tableModel = new DefaultTableModel(data, columnNames) {

            @Override
            public int getRowCount() {
                return rowCount;
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public String getColumnName(int columnIndex) {
                if(columnIndex == 0){
                    return LanguagesProvider.adaptPhrase("Name");
                }
                return LanguagesProvider.adaptPhrase("Ticket");
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int row, int column) {
                if(column == 0){
                    switch (row){
                        case 0: return LanguagesProvider.adaptPhrase("Id");
                        case 1: return LanguagesProvider.adaptPhrase("Ticket Name");
                        case 2: return LanguagesProvider.adaptPhrase("Creation Date");
                        case 3: return LanguagesProvider.adaptPhrase("Price");
                        case 4: return LanguagesProvider.adaptPhrase("Comment");
                        case 5: return LanguagesProvider.adaptPhrase("Refundable");
                        case 6: return LanguagesProvider.adaptPhrase("Ticket Type");
                        case 7: return LanguagesProvider.adaptPhrase("Coordinates Id");
                        case 8: return LanguagesProvider.adaptPhrase("Coordinates X");
                        case 9: return LanguagesProvider.adaptPhrase("Coordinates Y");
                        case 10: return LanguagesProvider.adaptPhrase("Venue Id");
                        case 11: return LanguagesProvider.adaptPhrase("Venue Name");
                        case 12: return LanguagesProvider.adaptPhrase("Venue Capacity");
                        case 13: return LanguagesProvider.adaptPhrase("Venue Type");
                        case 14: return LanguagesProvider.adaptPhrase("Address Id");
                        case 15: return LanguagesProvider.adaptPhrase("Address Zipcode");
                        case 16: return LanguagesProvider.adaptPhrase("Town Id");
                        case 17: return LanguagesProvider.adaptPhrase("Town Name");
                        case 18: return LanguagesProvider.adaptPhrase("Town X");
                        case 19: return LanguagesProvider.adaptPhrase("Town Y");
                        case 20: return LanguagesProvider.adaptPhrase("Town Z");
                        case 21: return LanguagesProvider.adaptPhrase("Owner Name");
                        case 22: return LanguagesProvider.adaptPhrase("Owner Mail");
                    }
                }
                if(data[row][column] == null){
                    return "";
                }
                return data[row][column];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                data[rowIndex][1] = aValue;
            }

            public void translate() {
                jsP.repaint();
            }
        };

        jTable = new JTable(tableModel);

        GridBagConstraints bagC1 = new GridBagConstraints();
        bagC1.insets = new Insets(10, 0, 10, 10);
        bagC1.weightx = 0.75;
        bagC1.weighty = 1;
        bagC1.gridx = 0;
        bagC1.gridy = 0;
        bagC1.gridheight = 1;
        bagC1.gridwidth = 3;
        bagC1.fill = GridBagConstraints.BOTH;
        jpTable.add(painter, bagC1);

        GridBagConstraints bagC2 = new GridBagConstraints();
        bagC2.insets = new Insets(10, 10, 10, 0);
        bagC2.weightx = 0.25;
        bagC2.weighty = 1;
        bagC2.gridx = 3;
        bagC2.gridy = 0;
        bagC2.gridheight = 1;
        bagC2.gridwidth = 1;
        bagC2.fill = GridBagConstraints.BOTH;
        jsP = new JScrollPane(jTable);
        jpTable.add(jsP, bagC2);
        for (int i = 0; i < rowCount; i++) {
            data[i][0] = null;
        }
        jsP.repaint();
        painter.setVisible(true);
    }

    @Override
    public void show(boolean show) {
        if (show) {
            workPage.cardLayout.show(jpTemplate, "2");
        }
    }

    @Override
    public void translate() {
        jsP.repaint();
    }

    public void setTicket(Ticket ticket) {
        tableModel.setValueAt(ticket.getId(), 0, 1);
        tableModel.setValueAt(ticket.getName(), 1, 1);
        tableModel.setValueAt(ticket.getCreationDate(), 2, 1);
        tableModel.setValueAt(ticket.getPrice(), 3, 1);
        tableModel.setValueAt(ticket.getComment(), 4, 1);
        tableModel.setValueAt(ticket.isRefundable(), 5, 1);
        tableModel.setValueAt(ticket.getType(), 6, 1);

        tableModel.setValueAt(ticket.getCoordinates().getId(), 7, 1);
        tableModel.setValueAt(ticket.getCoordinates().getX(), 8, 1);
        tableModel.setValueAt(ticket.getCoordinates().getY(), 9, 1);

        tableModel.setValueAt(ticket.getVenue().getId(), 10, 1);
        tableModel.setValueAt(ticket.getVenue().getName(), 11, 1);
        tableModel.setValueAt(ticket.getVenue().getCapacity(), 12, 1);
        tableModel.setValueAt(ticket.getVenue().getType(), 13, 1);
        if (ticket.getVenue().getAddress() != null) {
            tableModel.setValueAt(ticket.getVenue().getAddress().getId(), 14, 1);
            tableModel.setValueAt(ticket.getVenue().getAddress().getZipCode(), 15, 1);
            if (ticket.getVenue().getAddress().getTown() != null) {
                tableModel.setValueAt(ticket.getVenue().getAddress().getTown().getId(), 16, 1);
                tableModel.setValueAt(ticket.getVenue().getAddress().getTown().getName(), 17, 1);
                tableModel.setValueAt(ticket.getVenue().getAddress().getTown().getX(), 18, 1);
                tableModel.setValueAt(ticket.getVenue().getAddress().getTown().getY(), 19, 1);
                tableModel.setValueAt(ticket.getVenue().getAddress().getTown().getZ(), 20, 1);
            }
        }

        tableModel.setValueAt(ticket.getTowner().getName(), 21, 1);
        tableModel.setValueAt(ticket.getTowner().getMail(), 22, 1);
        jsP.repaint();
    }

    public void cancelTicket(){
        for (int i = 0; i < rowCount; i++) {
            tableModel.setValueAt(null, i, 1);
        }
        jsP.repaint();
    }
}
