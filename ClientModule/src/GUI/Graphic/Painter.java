package GUI.Graphic;

import Collection.Ticket;
import GUI.CommandFormer;
import GUI.Commands.CommandsPage;
import GUI.GraphicPage;
import GUI.Tables.BaseTableModel;
import GUI.Tables.ClickMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Painter extends JPanel {

    private static ExecutorService paintings = Executors.newCachedThreadPool();

    private static ArrayList<TicketPicture> pictureArrayList = new ArrayList<>();
    private static ArrayList<TicketPicture> animationsArray = new ArrayList<>();

    private static GraphicPage graphicMainPage;

    private Graphics2D graphics2D;

    private CommandsPage comPage;
    private int delay = 500;
    private Timer timer;

    public Painter(GraphicPage gp) {
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pictureArrayList.stream().forEach(ticketPicture -> ticketPicture.incAnimation());
                animationsArray.stream().forEach(ticketPicture -> ticketPicture.incAnimation());
                repaint();
            }
        });

        graphicMainPage = gp;
        addMouseListener(new ClickMouseAdapter() {
            @Override
            protected void fireSingleClick(ActionEvent e, Point p) {
                try {
                    Ticket t = getTicketByClick(p);
                    gp.setTicket(t);
                } catch (Exception ex) {
                    if (!ex.getMessage().equals("Isn't Ticket")) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            protected void fireDoubleClick(ActionEvent e, Point p) {
                fireSingleClick(e, p);
            }

            @Override
            protected void fireRightClick(ActionEvent e, Point p) {
                gp.cancelTicket();
            }

            @Override
            protected void fireCenterClick(ActionEvent e, Point p) {
                comPage = new CommandsPage();
                try {
                    Ticket t = getTicketByClick(p);
                    comPage.makeUpdate((int) t.getId(), t);
                } catch (Exception except) {
                    if (!except.getMessage().equals("Isn't Ticket")) {
                        except.printStackTrace();
                    }
                }
                comPage.translate();
            }
        });
        BaseTableModel.getTicketLinkedList().stream()
                .sorted((o1, o2) -> {
                    Ticket t1 = (Ticket) o1;
                    Ticket t2 = (Ticket) o2;
                    byte i = 0;
                    if (t1.getTowner().equals(CommandFormer.getTicketOwner())) {
                        i++;
                    }
                    if (t2.getTowner().equals(CommandFormer.getTicketOwner())) {
                        i--;
                    }
                    return i;
                }).forEachOrdered(ticket -> {
            pictureArrayList.add(new TicketPicture(ticket));
        });
    }

    private Ticket getTicketByClick(Point p) throws Exception {
        if (pictureArrayList.stream().anyMatch(ticketPicture -> ticketPicture.isTicketPoint(p))) {
            return pictureArrayList.stream().filter(ticketPicture -> ticketPicture.isTicketPoint(p)).findFirst().get().getPicturedTicket();
        }
        System.out.println("Not Ticket");
        throw new Exception("Isn't Ticket");
    }

    private void showThePicture(Graphics2D g2D) {
        pictureArrayList.stream().forEach(ticketPicture -> ticketPicture.show(g2D));
    }

    private void animateThePicture(Graphics2D g2D) {
        animationsArray.stream().filter(ticketPicture ->
                ticketPicture.printTicketAnimation(g2D) == 0
        ).forEach(ticketPicture -> pictureArrayList.add(ticketPicture));
    }


    public boolean addTicket(Ticket ticket) {
        TicketPicture ticketPicture = new TicketPicture(ticket);
        return animationsArray.add(ticketPicture);
    }

    public boolean removeTicket(Ticket ticket) {
        ArrayList<TicketPicture> pictures = new ArrayList<>();
        pictureArrayList.stream()
                .filter(ticketPicture -> ticketPicture.getPicturedTicket().equals(ticket))
                .forEach(ticketPicture -> pictures.add(ticketPicture));
        pictures.stream().forEach(ticketPicture -> {
            pictureArrayList.remove(ticketPicture);
            animationsArray.remove(ticketPicture);
        });
        return true;
    }

    public boolean updateTicket(Ticket ticket) {
        if (pictureArrayList.stream().anyMatch(ticketPicture -> ticketPicture.getPicturedTicket().getId() == ticket.getId())) {
            ArrayList<TicketPicture> pictures = new ArrayList<>();
            pictureArrayList.stream()
                    .filter(ticketPicture -> ticketPicture.getPicturedTicket().getId() == ticket.getId())
                    .forEach(ticketPicture -> pictures.add(ticketPicture));
            System.out.println("______________________"+pictures.size());
            pictures.stream().forEach(ticketPicture -> pictureArrayList.remove(ticketPicture));
            boolean b = animationsArray.add(new TicketPicture(ticket));
            ticket.writeTicket();
            repaint();
            return b;
        } else if (animationsArray.stream().anyMatch(ticketPicture -> ticketPicture.getPicturedTicket().getId() == ticket.getId())) {
            animationsArray.stream().filter(ticketPicture -> ticketPicture.getPicturedTicket().getId() == ticket.getId()).forEach(ticketPicture -> pictureArrayList.remove(ticketPicture));
            boolean b = animationsArray.add(new TicketPicture(ticket));
            repaint();
            ticket.writeTicket();
            return b;
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        TicketPicture.setPageDimension(graphicMainPage.getPageSize());
        graphics2D = (Graphics2D) g;
        showThePicture((Graphics2D) g);
        animateThePicture((Graphics2D) g);
        timer.start();
    }


}
