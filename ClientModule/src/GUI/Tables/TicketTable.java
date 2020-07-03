package GUI.Tables;

import GUI.AbstractPageShower;
import GUI.Commands.CommandsPage;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

import static GUI.Commands.TicketGetter.getTicketTypeEditor;

public class TicketTable extends AbstractPageShower implements TableNavigation {

    private JTable jTable;
    private JScrollPane jScrollPane;
    private TicketTableModel tableModel;
    private JPanel jp;
    private CardLayout cl;

    private CommandsPage comPage;

    TicketTable(CardLayout cardLayout, JPanel jPanel) {
        cl = cardLayout;
        jp = jPanel;
        tableModel = new TicketTableModel();
        tableModel.translate();
        jTable = new JTable(tableModel);
        jTable.getColumnModel().getColumn(7).setCellEditor(getTicketTypeEditor());

        jTable.addMouseListener(new ClickMouseAdapter() {
            @Override
            protected void fireSingleClick(ActionEvent e, Point p) {
                int y = jTable.getSelectedRow();
                int x = jTable.getSelectedColumn();
                filter(x, ((TicketTableModel) jTable.getModel()).getNotFormattedValue(y, x));
            }

            @Override
            protected void fireDoubleClick(ActionEvent e, Point p) {
                int x = jTable.getSelectedColumn();
                try {
                    if (!(x == 2 || x == 8)) {
                        return;
                    }
                    switch (x) {
                        case 2:
                            AbstractTablePage.showTable(2);
                            break;
                        case 8:
                            AbstractTablePage.showTable(3);
                            break;
                        default:
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
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
                int y = jTable.getSelectedRow();
                int id = (int) (long) tableModel.getNotFormattedValue(y, 0);
                comPage = new CommandsPage();
                try {
                    comPage.makeUpdate(id, BaseTableModel.getTicket(id));
                } catch (Exception except) {
                    except.printStackTrace();
                }
                comPage.translate();
                comPage.show(true);
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
        jPanel.add(jScrollPane, "1");
    }

    @Override
    public void show(boolean show) {
        cl.show(jp, "1");
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
