package GUI.Tables;

import Collection.Ticket;
import GUI.AbstractPageShower;
import GUI.Commands.CommandsPage;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;


public class TownTable extends AbstractPageShower implements TableNavigation {

    public JTable getjTable() {
        return jTable;
    }

    private JTable jTable;
    private JScrollPane jScrollPane;
    private TownTableModel tableModel;
    private JPanel jp;
    private CardLayout cl;

    private CommandsPage comPage;

    TownTable(CardLayout cardLayout, JPanel jPanel) {
        cl = cardLayout;
        jp = jPanel;
        tableModel = new TownTableModel();
        tableModel.translate();
        jTable = new JTable(tableModel);

        jTable.addMouseListener(new ClickMouseAdapter() {
            @Override
            protected void fireSingleClick(ActionEvent e, Point p) {
                int y = jTable.getSelectedRow();
                int x = jTable.getSelectedColumn();
                filter(x, ((TownTableModel) jTable.getModel()).getNotFormattedValue(y, x));
            }

            @Override
            protected void fireDoubleClick(ActionEvent e, Point p) {
                int y = jTable.getSelectedRow();
                int x = jTable.getSelectedColumn();
                filter(x, ((TownTableModel) jTable.getModel()).getNotFormattedValue(y, x));
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
                int id = (int) tableModel.getNotFormattedValue(x, 0);
                comPage = new CommandsPage();
                try {
                    Ticket t = ((TownTableModel) tableModel).getTicketByTown(id);
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
        jPanel.add(jScrollPane, "5");
    }

    @Override
    public void show(boolean show) {
        jTable.repaint();
        cl.show(jp, "5");
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
