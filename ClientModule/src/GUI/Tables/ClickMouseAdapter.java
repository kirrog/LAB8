package GUI.Tables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ClickMouseAdapter extends MouseAdapter {


    private int doubleClickDelay = 1000;

    private Timer timer;

    public ClickMouseAdapter() {

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        };

        timer = new Timer(doubleClickDelay, actionListener);
        timer.setRepeats(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Point point = e.getPoint();

        ActionEvent ev = new ActionEvent(
                e.getSource(), e.getID(), e.paramString());
        if (e.getButton() == MouseEvent.BUTTON2){
            fireCenterClick(ev, point);
            return;
        }
        if (e.getButton() == MouseEvent.BUTTON3){
            fireRightClick(ev, point);
            return;
        }
        if (timer.isRunning()) {
            timer.stop();
            fireSingleClick(ev, point);
            fireDoubleClick(ev, point);
        } else {
            timer.start();
            fireSingleClick(ev, point);
        }
    }

    protected abstract void fireSingleClick(ActionEvent e, Point p);

    protected abstract void fireDoubleClick(ActionEvent e, Point p);

    protected abstract void fireRightClick(ActionEvent e, Point p);

    protected abstract void fireCenterClick(ActionEvent e, Point p);
}