package GUI.Tables;

import Collection.Ticket;
import GUI.AbstractPageShower;
import GUI.Commands.CommandsPage;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

import static GUI.Commands.TicketGetter.getVenueTypeEditor;

public class VenuesTable extends AbstractPageShower implements TableNavigation {

    public JTable getjTable() {
        return jTable;
    }

    private JTable jTable;
    private JScrollPane jScrollPane;
    private VenuesTableModel tableModel;
    private JPanel jp;
    private CardLayout cl;

    private CommandsPage comPage;

    VenuesTable(CardLayout cardLayout, JPanel jPanel) {
        cl = cardLayout;
        jp = jPanel;
        tableModel = new VenuesTableModel();
        tableModel.translate();
        jTable = new JTable(tableModel);
        jTable.getColumnModel().getColumn(3).setCellEditor(getVenueTypeEditor());

        jTable.addMouseListener(new ClickMouseAdapter() {
            @Override
            protected void fireSingleClick(ActionEvent e, Point p) {
                int y = jTable.getSelectedRow();
                int x = jTable.getSelectedColumn();
                filter(x, ((VenuesTableModel) jTable.getModel()).getNotFormattedValue(y, x));
            }

            @Override
            protected void fireDoubleClick(ActionEvent e, Point p) {
                int y = jTable.getSelectedColumn();
                if (y == 4) {
                    AbstractTablePage.showTable(4);
                }
            }

            @Override
            protected void fireRightClick(ActionEvent e, Point p) {
                cancel();
            }

            @Override
            protected void fireCenterClick(ActionEvent e, Point p) {
                if (jTable.getSelectedRow() < 0) {
                    return;
                }
                int x = jTable.getSelectedRow();
                int id = (int) (long) tableModel.getNotFormattedValue(x, 0);
                comPage = new CommandsPage();
                try {
                    Ticket t = ((VenuesTableModel) tableModel).getTicketByVenue(id);
                    comPage.makeUpdate((int)t.getId(), t);
                } catch (Exception except) {
                    except.printStackTrace();
                }
                comPage.translate();
            }
        });

        jTable.getTableHeader().addMouseListener(new ClickMouseAdapter() {

            boolean iffer = true;

            @Override
            protected void fireSingleClick(ActionEvent e, Point p) {

                sort(((JTableHeader) e.getSource()).columnAtPoint(p));
            }

            @Override
            protected void fireDoubleClick(ActionEvent e, Point p) {
                sort(((JTableHeader) e.getSource()).columnAtPoint(p));
            }

            @Override
            protected void fireRightClick(ActionEvent e, Point p) {
                cancel();
            }

            @Override
            protected void fireCenterClick(ActionEvent e, Point p) {
                sort(((JTableHeader) e.getSource()).columnAtPoint(p));
            }

            private void sort(int i) {
                if (iffer) {
                    sortHigh(i);
                    iffer = false;
                } else {
                    sortLow(i);
                    iffer = true;
                }
            }
        });

        jTable.getTableHeader().setReorderingAllowed(false);

        jScrollPane = new JScrollPane(jTable);
        jPanel.add(jScrollPane, "3");
    }

    @Override
    public void show(boolean show) {
        cl.show(jp, "3");
    }

    @Override
    public void translate() {
        tableModel.translate();
        jTable.updateUI();
    }

    @Override
    public void sortHigh(int column) {
        tableModel.sort(true, column);
        jTable.updateUI();
    }

    @Override
    public void sortLow(int column) {
        tableModel.sort(false, column);
        jTable.updateUI();
    }

    @Override
    public void filter(int column, Object filter) {
        tableModel.filter(column, filter);
        jTable.updateUI();
    }

    @Override
    public void cancel() {
        tableModel.cancel();
        jTable.updateUI();
    }
}
