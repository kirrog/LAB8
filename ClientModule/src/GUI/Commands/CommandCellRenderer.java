package GUI.Commands;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CommandCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        //Get the status for the current row.
        TicketGetter.AbstrDefTabMod tableModel = (TicketGetter.AbstrDefTabMod) table.getModel();
        if (tableModel.getStatus(col) == 1) {
            l.setBackground(Color.GREEN);
        } else {
            l.setBackground(Color.RED);
        }

        //Return the JLabel which renders the cell.
        return l;
    }
}