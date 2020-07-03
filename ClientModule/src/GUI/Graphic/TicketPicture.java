package GUI.Graphic;

import Collection.Ticket;

import java.awt.*;

public class TicketPicture{

    private static int gap = 50;
    private static Dimension scaleMax = new Dimension(300, 200);
    private static Dimension pageDimension;

    private Ticket picturedTicket;
    private TicketParts ticketParts;

    public Ticket getPicturedTicket() {
        return picturedTicket;
    }

    boolean isTicketPoint(Point p){
        return ticketParts.isTicketPart(p);
    }

    public static void setPageDimension(Dimension pageDimension) {
        TicketPicture.pageDimension = pageDimension;
    }

    public Dimension getScaleMax(){
        return scaleMax;
    }

    public TicketPicture(Ticket ticket){
        picturedTicket = ticket;
        ticketParts = new TicketParts(ticket);
        getDimension();
    }


    //Print it quickly
    public void show(Graphics2D graphics){
        setTicketScale();
        ticketParts.setGraphics(graphics);
        ticketParts.showVenue();
        ticketParts.showWay();
        ticketParts.showTown();
    }

    public void incAnimation(){
        ticketParts.incAnimation();
    }


    //animation one more with new Ticket
    public void changeTicket(Ticket ticket){
        ticketParts = new TicketParts(ticket);
    }

    public int printTicketAnimation(Graphics2D graphics){
        setTicketScale();
        ticketParts.setGraphics(graphics);
        return ticketParts.printTicket();
    }

    public Dimension getDimension(){
        Dimension currentDimension = calculateDimension();
        if(currentDimension.getHeight() > scaleMax.getHeight() && currentDimension.getWidth() > scaleMax.getWidth()){
            scaleMax = currentDimension;
        }else {
            if(currentDimension.getHeight() > scaleMax.getHeight()){
                scaleMax = new Dimension((int)scaleMax.getWidth(), (int)currentDimension.getHeight());
            }
            if(currentDimension.getWidth() > scaleMax.getWidth()){
                scaleMax = new Dimension((int)currentDimension.getHeight(), (int)scaleMax.getHeight());
            }
        }
        return currentDimension;
    }

    private Dimension calculateDimension(){
        if(picturedTicket.getVenue().getAddress() == null || picturedTicket.getVenue().getAddress().getTown() == null){
            int radius = (int)(ticketParts.getVenueSize());
            return new Dimension(radius + gap, radius + gap);
        }
        int width = (int) (ticketParts.getWidth());
        int height = (int) (ticketParts.getHeight());
        return new Dimension(width,height);

    }

    public void setTicketScale(){
        ticketParts.setScale(scaleMax, pageDimension);
    }
}
//figure.getWidth()*XScale;
//XScale =