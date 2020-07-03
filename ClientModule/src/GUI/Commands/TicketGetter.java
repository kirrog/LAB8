package GUI.Commands;

import Collection.*;
import GUI.CommandFormer;
import GUI.Localization.LanguagesProvider;
import GUI.Tables.TicketTableModel;
import GUI.Tables.Translatable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TicketGetter {

    private JTable ticket;
    private JTable coordinates;
    private JTable venue;
    private JTable address;
    private JTable town;
    private JLabel printer;
    private static TicketType[] ticketTypes = TicketType.values();
    private static VenueType[] venueTypes = VenueType.values();

    public void printText(String str) {
        StringBuilder s = new StringBuilder();
        for (String st : str.split("\n")) {
            s.append(st + "<br>");
        }
        printer.setText("<html>" + s.toString() + "</html>");
    }

    public String getPrinterText() {
        return printer.getText();
    }

    public void setPrinterVisible(boolean b) {
        printer.setVisible(b);
    }

    private static JComboBox<TicketType> comboT = new JComboBox<TicketType>(ticketTypes);

    public static DefaultCellEditor getTicketTypeEditor() {
        return new DefaultCellEditor(comboT);
    }


    private static JComboBox<VenueType> comboV = new JComboBox<VenueType>(venueTypes);

    public static DefaultCellEditor getVenueTypeEditor() {
        return new DefaultCellEditor(comboV);
    }


    private boolean ticketOrVenue = true;

    private String[] ticketColumnNames = new String[]{
            "Key",
            "Ticket Name",
            "Ticket Price",
            "Ticket Comment",
            "Ticket Refundable",
            "Ticket Type"
    };
    private Object[][] ticketData = new Object[][]{{"", "", 0L, "", false, TicketType.USUAL}};
    private boolean[] ticketStatus = new boolean[]{false, false, false, false, false, false};
    private String[] ticketMessages = new String[]{
            "Ticket key - Already exists",
            "Ticket name - Not null",
            "Ticket price - > 0 and int",
            "Ticket comment - can be null",
            "Ticket refundable - true or false",
            "Ticket type - only list"
    };

    private String[] coordinatesColumnNames = new String[]{
            "Coordinates X",
            "Coordinates Y"
    };
    private Object[][] coordinatesData = new Object[][]{{0L, 0d}};
    private boolean[] coordinatesStatus = new boolean[]{false, false};
    private String[] coordinatesMessages = new String[]{
            "Coordinates x - any long integer",
            "Coordinates y - double and > -959"
    };

    private String[] venueColumnNames = new String[]{
            "Venue Name",
            "Venue Capacity",
            "Venue Type"
    };
    private Object[][] venueData = new Object[][]{{"", 0, VenueType.OPEN_AREA}};
    private boolean[] venueStatus = new boolean[]{false, false, false};
    private String[] venueMessages = new String[]{
            "Venue name - not null",
            "Venue capacity - integer > 0",
            "Venue type - from list"
    };

    private String[] addressColumnNames = new String[]{
            "Address Zipcode"
    };
    private Object[][] addressData = new Object[][]{{""}};
    private boolean[] addressStatus = new boolean[]{false};
    private String[] addressMessages = new String[]{
            "Address zipcode - longer then 8 symbols"
    };

    private String[] townColumnNames = new String[]{
            "Town X",
            "Town Y",
            "Town Z",
            "Town Name"
    };
    private Object[][] townData = new Object[][]{{0d, 0f, 0L, ""}};
    private boolean[] townStatus = new boolean[]{false, false, false, false};
    private String[] townMessages = new String[]{
            "Town x - double",
            "Town y - float",
            "Town z - long",
            "Town name - can be null, or less then 882 symbols"
    };


    public TicketGetter(JPanel jpanel) {
        printer = new JLabel();
        modelsSetter();

        GridBagConstraints bagC1 = new GridBagConstraints();
        bagC1.insets = new Insets(10, 0, 10, 0);
        bagC1.weightx = 0;
        bagC1.weighty = 0;
        bagC1.gridx = 0;
        bagC1.gridy = 1;
        bagC1.gridheight = 1;
        bagC1.gridwidth = 6;
        bagC1.fill = GridBagConstraints.HORIZONTAL;
        jspT = new JScrollPane(ticket);
        jspT.setPreferredSize(new Dimension(120, 40));
        jpanel.add(jspT, bagC1);

        GridBagConstraints bagC2 = new GridBagConstraints();
        bagC2.insets = new Insets(10, 0, 10, 0);
        bagC2.weightx = 0.33;
        bagC2.weighty = 0;
        bagC2.gridx = 4;
        bagC2.gridy = 2;
        bagC2.gridheight = 1;
        bagC2.gridwidth = 2;
        bagC2.fill = GridBagConstraints.HORIZONTAL;
        jspC = new JScrollPane(coordinates);
        jspC.setPreferredSize(new Dimension(40, 40));
        jpanel.add(jspC, bagC2);

        GridBagConstraints bagC3 = new GridBagConstraints();
        bagC3.insets = new Insets(10, 0, 10, 0);
        bagC3.weightx = 0.5;
        bagC3.weighty = 0;
        bagC3.gridx = 0;
        bagC3.gridy = 2;
        bagC3.gridheight = 1;
        bagC3.gridwidth = 3;
        bagC3.fill = GridBagConstraints.HORIZONTAL;
        jspV = new JScrollPane(venue);
        jspV.setPreferredSize(new Dimension(60, 40));
        jpanel.add(jspV, bagC3);

        GridBagConstraints bagC4 = new GridBagConstraints();
        bagC4.insets = new Insets(10, 0, 10, 0);
        bagC4.weightx = 0.16;
        bagC4.weighty = 0;
        bagC4.gridx = 3;
        bagC4.gridy = 2;
        bagC4.gridheight = 1;
        bagC4.gridwidth = 1;
        bagC4.fill = GridBagConstraints.HORIZONTAL;
        jspA = new JScrollPane(address);
        jspA.setPreferredSize(new Dimension(25, 40));
        jpanel.add(jspA, bagC4);

        GridBagConstraints bagC5 = new GridBagConstraints();
        bagC5.insets = new Insets(10, 0, 10, 0);
        bagC5.weightx = 0.66;
        bagC5.weighty = 0;
        bagC5.gridx = 0;
        bagC5.gridy = 3;
        bagC5.gridheight = 1;
        bagC5.gridwidth = 4;
        bagC5.fill = GridBagConstraints.HORIZONTAL;
        jspto = new JScrollPane(town);
        jspto.setPreferredSize(new Dimension(80, 40));
        jpanel.add(jspto, bagC5);

        GridBagConstraints bagC6 = new GridBagConstraints();
        bagC6.insets = new Insets(10, 0, 10, 0);
        bagC6.weightx = 1;
        bagC6.weighty = 1;
        bagC6.gridx = 0;
        bagC6.gridy = 5;
        bagC6.gridheight = 2;
        bagC6.gridwidth = 6;
        bagC6.fill = GridBagConstraints.BOTH;
        jspPr = new JScrollPane(printer);
        jpanel.add(jspPr, bagC6);

        jpanel.setMinimumSize(new Dimension(400, 250));
    }

    private JScrollPane jspPr;
    private JScrollPane jspto;
    private JScrollPane jspA;
    private JScrollPane jspV;
    private JScrollPane jspC;
    private JScrollPane jspT;

    private String resultMessage = "";

    public boolean isReady() {
        boolean result = true;
        resultMessage = "";
        result &= checkPart(ticketStatus, ticketMessages);
        result &= checkPart(coordinatesStatus, coordinatesMessages);
        result &= checkPart(venueStatus, venueMessages);
        result &= checkPart(addressStatus, addressMessages);
        result &= checkPart(townStatus, townMessages);
        printer.setText(resultMessage);
        return result;
    }

    public boolean isVenueReady() {
        boolean result = true;
        resultMessage = "";
        result &= checkPart(venueStatus, venueMessages);
        result &= checkPart(addressStatus, addressMessages);
        result &= checkPart(townStatus, townMessages);
        printer.setText(resultMessage);
        return result;
    }

    private boolean checkPart(boolean[] status, String[] messages) {
        boolean state = true;
        for (int i = 0; i < status.length; i++) {
            if (status[i]) {
                continue;
            } else {
                state = false;
                resultMessage += "\n " + messages[i];
            }
        }
        return state;
    }

    public void show(boolean show) {
        if (ticketOrVenue || !show) {
            jspT.setVisible(show);
            jspC.setVisible(show);
        }
        jspV.setVisible(show);
        jspA.setVisible(show);
        jspto.setVisible(show);
        jspPr.setVisible(show);
    }

    AbstrDefTabMod modelT;
    AbstrDefTabMod modelC;
    AbstrDefTabMod modelV;
    AbstrDefTabMod modelA;
    AbstrDefTabMod modelTo;

    private DefaultCellEditor tte = getTicketTypeEditor();
    private DefaultCellEditor vte = getVenueTypeEditor();

    abstract class AbstrDefTabMod extends DefaultTableModel implements Translatable {

        protected Object[][] workTable;

        public AbstrDefTabMod(Object[][] date, Object[] colomnNames) {
            super(date, colomnNames);
        }

        abstract int getStatus(int col);

        @Override
        public abstract void translate();

        public void setVal(int column, Object obj) {
            workTable[0][column] = obj;
        }
    }

    private void modelsSetter() {
        modelT = new AbstrDefTabMod(ticketData, ticketColumnNames) {
            {
                workTable = ticketData;
            }

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                    case 1:
                    case 3:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 4:
                        return Boolean.class;
                    case 5:
                        return TicketType.USUAL.getClass();
                    default:
                        return null;
                }
            }

            @Override
            public Object getValueAt(int row, int column) {
                return ticketData[row][column];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        if(bySetTicket){
                            ticketStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }else if (TicketTableModel.hasThisKey((String) aValue)) {
                            ticketStatus[columnIndex] = false;
                        } else {
                            ticketStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    case 1:
                        if (aValue == null || aValue.equals("")) {
                            ticketStatus[columnIndex] = false;
                        } else {
                            ticketStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    case 2:
                        if ((int) aValue < 1) {
                            ticketStatus[columnIndex] = false;
                        } else {
                            ticketStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    case 3:
                    case 4:
                    case 5:
                        ticketStatus[columnIndex] = true;
                        setVal(columnIndex, aValue);
                        break;
                    default:
                        printer.setBackground(Color.RED);
                        printer.setText(printer.getText() + "Something goes wrong in Ticket from TicketGetter");
                        break;
                }

            }

            @Override
            int getStatus(int col) {
                if (ticketStatus[col]) {
                    return 1;
                } else {
                    return -1;
                }
            }

            @Override
            public void translate() {
                this.setColumnIdentifiers(LanguagesProvider.translateLine(ticketColumnNames));
            }
        };
        ticket = new JTable(modelT);
        ticket.getColumnModel().getColumn(5).setCellEditor(tte);
        ticket.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        ticket.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());
        ticket.getColumnModel().getColumn(2).setCellRenderer(new CommandCellRenderer());
        ticket.getColumnModel().getColumn(3).setCellRenderer(new CommandCellRenderer());
        //ticket.getColumnModel().getColumn(4).setCellRenderer(new CommandCellRenderer());
        ticket.getTableHeader().setReorderingAllowed(false);

        modelC = new AbstrDefTabMod(coordinatesData, coordinatesColumnNames) {

            {
                workTable = coordinatesData;
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {
                    return String.class;
                }
                return coordinatesData[0][column].getClass();
            }

            @Override
            public Object getValueAt(int row, int column) {
                return coordinatesData[row][column];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        coordinatesStatus[columnIndex] = true;
                        setVal(columnIndex, aValue);
                        break;
                    case 1:
                        if ((Double) aValue <= -959) {
                            coordinatesStatus[columnIndex] = false;
                        } else {
                            coordinatesStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    default:
                        printer.setBackground(Color.RED);
                        printer.setText(printer.getText() + "Something goes wrong in Coordinates from TicketGetter");
                        break;
                }

            }

            @Override
            int getStatus(int col) {
                if (coordinatesStatus[col]) {
                    return 1;
                } else {
                    return -1;
                }
            }

            @Override
            public void translate() {
                this.setColumnIdentifiers(LanguagesProvider.translateLine(coordinatesColumnNames));
            }
        };
        coordinates = new JTable(modelC);
        coordinates.getTableHeader().setReorderingAllowed(false);
        coordinates.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        coordinates.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());


        modelV = new AbstrDefTabMod(venueData, venueColumnNames) {

            {
                workTable = venueData;
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return venueData[0][column].getClass();
            }

            @Override
            public Object getValueAt(int row, int column) {
                return venueData[row][column];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        if (aValue == null || aValue.equals("")) {
                            venueStatus[columnIndex] = false;
                        } else {
                            venueStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    case 1:
                        if ((int) aValue <= 0) {
                            venueStatus[columnIndex] = false;
                        } else {
                            venueStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    case 2:
                        venueStatus[columnIndex] = true;
                        setVal(columnIndex, aValue);
                        break;
                    default:
                        printer.setBackground(Color.RED);
                        printer.setText(printer.getText() + "Something goes wrong in Venue from TicketGetter");
                        break;
                }

            }

            @Override
            int getStatus(int col) {
                if (venueStatus[col]) {
                    return 1;
                } else {
                    return -1;
                }
            }

            public void translate() {
                this.setColumnIdentifiers(LanguagesProvider.translateLine(venueColumnNames));
            }
        };
        venue = new JTable(modelV);
        venue.getColumnModel().getColumn(2).setCellEditor(vte);
        venue.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        venue.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());
        venue.getTableHeader().setReorderingAllowed(false);


        modelA = new AbstrDefTabMod(addressData, addressColumnNames) {

            {
                workTable = addressData;
            }

            @Override
            public int getColumnCount() {
                return 1;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return addressData[0][column].getClass();
            }

            @Override
            public Object getValueAt(int row, int column) {
                return addressData[row][column];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        if (((String) aValue).length() < 9) {
                            addressStatus[columnIndex] = false;
                        } else {
                            addressStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    default:
                        printer.setBackground(Color.RED);
                        printer.setText(printer.getText() + "Something goes wrong in Address from TicketGetter");
                        break;
                }

            }

            @Override
            int getStatus(int col) {
                if (addressStatus[col]) {
                    return 1;
                } else {
                    return -1;
                }
            }

            @Override
            public void translate() {
                this.setColumnIdentifiers(LanguagesProvider.translateLine(addressColumnNames));
            }
        };
        address = new JTable(modelA);
        address.getTableHeader().setReorderingAllowed(false);
        address.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());


        modelTo = new AbstrDefTabMod(townData, townColumnNames) {

            {
                workTable = townData;
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return townData[0][column].getClass();
            }

            @Override
            public Object getValueAt(int row, int column) {
                return townData[row][column];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                    case 1:
                    case 2:
                        if (aValue == null) {
                            townStatus[columnIndex] = false;
                        } else {
                            townStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    case 3:
                        if (aValue == null || ((String) aValue).length() > 881) {
                            townStatus[columnIndex] = false;
                        } else {
                            townStatus[columnIndex] = true;
                            setVal(columnIndex, aValue);
                        }
                        break;
                    default:
                        printer.setBackground(Color.RED);
                        printer.setText(printer.getText() + "Something goes wrong in Town from TicketGetter");
                        break;
                }

            }

            @Override
            int getStatus(int col) {
                if (townStatus[col]) {
                    return 1;
                } else {
                    return -1;
                }
            }

            @Override
            public void translate() {
                this.setColumnIdentifiers(LanguagesProvider.translateLine(townColumnNames));
            }
        };
        town = new JTable(modelTo);
        town.getTableHeader().setReorderingAllowed(false);
        town.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        town.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());
        town.getColumnModel().getColumn(2).setCellRenderer(new CommandCellRenderer());
        town.getColumnModel().getColumn(3).setCellRenderer(new CommandCellRenderer());

    }

    public void translate() {
        modelT.translate();
        ticket.getColumnModel().getColumn(5).setCellEditor(tte);
        modelA.translate();
        modelC.translate();
        modelV.translate();
        venue.getColumnModel().getColumn(2).setCellEditor(vte);
        modelTo.translate();
        ticket.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        ticket.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());
        ticket.getColumnModel().getColumn(2).setCellRenderer(new CommandCellRenderer());
        ticket.getColumnModel().getColumn(3).setCellRenderer(new CommandCellRenderer());
        coordinates.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        coordinates.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());
        venue.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        venue.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());
        address.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        town.getColumnModel().getColumn(0).setCellRenderer(new CommandCellRenderer());
        town.getColumnModel().getColumn(1).setCellRenderer(new CommandCellRenderer());
        town.getColumnModel().getColumn(2).setCellRenderer(new CommandCellRenderer());
        town.getColumnModel().getColumn(3).setCellRenderer(new CommandCellRenderer());
    }

    private boolean bySetTicket = false;

    public boolean setTicket(Ticket tic) {
        if (tic == null) {
            return false;
        }
        bySetTicket = true;
        modelT.setValueAt(tic.getKey(), 0, 0);
        modelT.setValueAt(tic.getName(), 0, 1);
        modelT.setValueAt((int)(long)tic.getPrice(), 0, 2);
        modelT.setValueAt(tic.getComment(), 0, 3);
        modelT.setValueAt(tic.isRefundable(), 0, 4);
        modelT.setValueAt(tic.getType(), 0, 5);
//        ticketData[0][0] = tic.getKey();
//        ticketData[0][1] = tic.getName();
//        ticketData[0][2] = tic.getPrice();
//        ticketData[0][3] = tic.getComment();
//        ticketData[0][4] = tic.isRefundable();
//        ticketData[0][5] = tic.getType();

        Coordinates c = tic.getCoordinates();
        modelC.setValueAt(c.getX(), 0, 0);
        modelC.setValueAt(c.getY(), 0, 1);
//        coordinatesData[0][0] = c.getX();
//        coordinatesData[0][1] = c.getY();

        Venue v = tic.getVenue();
        modelV.setValueAt(v.getName(), 0, 0);
        modelV.setValueAt(v.getCapacity(), 0, 1);
        modelV.setValueAt(v.getType(), 0, 2);
//        venueData[0][0] = v.getName();
//        venueData[0][1] = v.getCapacity();
//        venueData[0][2] = v.getType();

        Address a = v.getAddress();
        //addressData[0][0] = a.getZipCode();
        modelA.setValueAt(a.getZipCode(), 0, 0);

        Location t = a.getTown();
        modelTo.setValueAt(t.getX(), 0, 0);
        modelTo.setValueAt(t.getY(), 0, 1);
        modelTo.setValueAt(t.getZ(), 0, 2);
        modelTo.setValueAt(t.getName(), 0, 3);

        bySetTicket = false;
        return true;
    }

    public Ticket getTicket() {
        String key = (String) ticketData[0][0];
        String name = (String) ticketData[0][1];
        long price = (long) (int) ticketData[0][2];
        String comment = (String) ticketData[0][3];
        boolean ref = (boolean) ticketData[0][4];
        TicketType tt = (TicketType) ticketData[0][5];

        long x = (long) coordinatesData[0][0];
        double y = (double) coordinatesData[0][1];
        Coordinates c = new Coordinates(x, y);

        double tx = (double) townData[0][0];
        float ty = (float) townData[0][1];
        long tz = (long) townData[0][2];
        String tname = (String) townData[0][3];
        Location t = new Location(tx, ty, tz, tname);

        String zip = (String) addressData[0][0];
        Address a = new Address(zip, t);

        String vname = (String) venueData[0][0];
        int capas = (int) venueData[0][1];
        VenueType vt = (VenueType) venueData[0][2];
        Venue v = new Venue(vname, capas, vt, a);

        Ticket tick = new Ticket(name, c, price, comment, ref, tt, v, key);
        tick.setTowner(CommandFormer.getTicketOwner());
        return tick;
    }

    public void setTicketOrVenueGetter(boolean svg) {
        ticketOrVenue = svg;
    }

    public Venue getVenue() {
        double tx = (double) townData[0][0];
        float ty = (float) townData[0][1];
        long tz = (long) townData[0][2];
        String tname = (String) townData[0][3];
        Location t = new Location(tx, ty, tz, tname);

        String zip = (String) addressData[0][0];
        Address a = new Address(zip, t);

        String vname = (String) venueData[0][0];
        int capas = (int) venueData[0][1];
        VenueType vt = (VenueType) venueData[0][2];
        return new Venue(vname, capas, vt, a);
    }


    public String getCurrentKey() {
        return (String) ticketData[0][0];
    }
}
