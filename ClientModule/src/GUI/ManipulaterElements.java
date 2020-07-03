package GUI;

import Collection.Ticket;
import GUI.Tables.AbstractTablePage;
import GUI.Tables.BaseTableModel;

public class ManipulaterElements {

    private static JMainFrame jMainFrame;

    public static void setJMainFrame(JMainFrame jmf) {
        jMainFrame = jmf;
    }

    public static boolean addTicket(Ticket ticket) {
        boolean b = jMainFrame.getPainter().addTicket(ticket) & BaseTableModel.addTicket(ticket);
        repaint();
        return b;
    }

    public static boolean updateTicket(Ticket ticket) {
        boolean b = BaseTableModel.updateTicket(ticket);
        b &= jMainFrame.getPainter().updateTicket(ticket);
        repaint();
        System.out.println("Updated!!!");
        return b;
    }

    public static boolean removeTicket(Ticket ticket) {
        boolean b = BaseTableModel.removeTicket(ticket) & jMainFrame.getPainter().removeTicket(ticket);
        repaint();
        return b;
    }

    public static void clear() {
        BaseTableModel.clear();
    }

    public static void repaint() {
        AbstractTablePage.updateTables();
        jMainFrame.getPainter().repaint();
    }

}
