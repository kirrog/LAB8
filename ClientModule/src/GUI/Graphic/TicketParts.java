package GUI.Graphic;

import Collection.Ticket;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

//Class with instruments

public class TicketParts {

    private static Color backGroundColor = Color.WHITE;
    private Color printingColor = Color.BLUE;

    private int stepLong = 20;

    private Ticket picturedTicket;
    private Graphics2D graphics;

    private double XScale = 1;
    private double YScale = 1;

    private double xCenter = 0;
    private double yCenter = 0;

    private venueParts vp;
    private wayParts wp;
    private townParts tp;

    public static int gcd(int a, int b) {
        if (b == 0)
            return Math.abs(a);
        if (a == 0)
            return Math.abs(b);
        return gcd(b, a % b);
    }

    public void setScale(Dimension scaleMax, Dimension pageDimension) {
        XScale = (pageDimension.getWidth() / scaleMax.getWidth());
        YScale = (pageDimension.getHeight() / scaleMax.getHeight());
        xCenter = pageDimension.getWidth() / 2;
        yCenter = pageDimension.getHeight() / 2;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = (Graphics2D) graphics.create();
        float color = Math.abs(picturedTicket.getTowner().getName().hashCode());
        printingColor = new Color((color % 256) / 256, ((color + 32 * picturedTicket.getTowner().getId()) % 256) / 256, ((color + 64 * (picturedTicket.getTowner().getId())) % 256) / 256);
        this.graphics.setColor(printingColor);
        vp = new venueParts(this.graphics);
        tp = new townParts(this.graphics);
        wp = new wayParts(this.graphics);
        steps = (vp.numberOfSteps - vSteps) + (wp.numberOfSteps - wSteps) + (tp.numberOfSteps - tSteps);
    }

    public TicketParts(Ticket ticket) {
        picturedTicket = ticket;
    }

    public void showVenue() {
        vp.show();
    }

    public void showWay() {
        wp.show();
        wp.paintPointer();
    }

    public void showTown() {
        tp.show();
    }

    private int steps = -1;

    public int printTicket() {
        vp.print();
        wp.print();
        tp.print();
        steps = (vp.numberOfSteps - vSteps) + (wp.numberOfSteps - wSteps) + (tp.numberOfSteps - tSteps);
        return steps;
    }

//    public void hide() {
//        vp.hide();
//        wp.hide();
//        tp.hide();
//    }

    double getVenueSize() {
        return Math.sqrt(picturedTicket.getVenue().getCapacity() / (Math.PI));
    }

    double getWidth() {
        int v = (int)(picturedTicket.getCoordinates().getX() + (picturedTicket.getVenue().getCapacity()/(Math.PI)));
        int t = (int)(picturedTicket.getVenue().getAddress().getTown().getX() + (picturedTicket.getVenue().getAddress().getTown().getZ()/2));
        if(v < t){
            return 2*t;
        }else {
            return 2*v;
        }
    }

    double getHeight() {
        int v = (int)(Math.abs(picturedTicket.getCoordinates().getY()) + (picturedTicket.getVenue().getCapacity()/(Math.PI)));
        int t = (int)(Math.abs(picturedTicket.getVenue().getAddress().getTown().getY()) + (picturedTicket.getVenue().getAddress().getTown().getZ()/2));
        if(v < t){
            return 2*t;
        }else {
            return 2*v;
        }
    }

    boolean isTicketPart(Point point) {
        return vp.isContainsPoint(point) || wp.isContainsPoint(point) || tp.isContainsPoint(point);
    }

    public void incAnimation(){
        vp.incPartAnimation();
        wp.incPartAnimation();
        tp.incPartAnimation();
    }

    private interface partOfTicket {

        void show();

        void hide();

        void print();

        void printElement(double x1, double y1, double widthOr, double heightOr);

        boolean isContainsPoint(Point p);

        double xSize();

        double ySize();

        int calculateElements();

        void incPartAnimation();

    }

    private int vSteps = 0;
    private class venueParts implements partOfTicket {

        private Graphics2D graphics2D;

        private Circle workCircle;

        double xCoordinates;
        double yCoordinates;

        venueParts(Graphics2D g) {
            graphics2D = (Graphics2D) g.create();
            xCoordinates = xCenter + (picturedTicket.getCoordinates().getX() * XScale);
            yCoordinates = yCenter + (picturedTicket.getCoordinates().getY() * YScale);
            numberOfSteps = calculateElements();
        }

        @Override
        public void show() {
            graphics2D.fillOval((int) (xCoordinates - (xSize() * XScale / 2)), (int) (yCoordinates - (ySize() * YScale / 2)), (int) (xSize() * XScale), (int) (ySize() * YScale));
            workCircle = new Circle(xCoordinates, yCoordinates, xSize());
        }

        @Override
        public void hide() {
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillOval((int) (xCoordinates - (xSize() * XScale / 2)), (int) (yCoordinates - (ySize() * YScale / 2)), (int) (xSize() * XScale), (int) (ySize() * YScale));
            workCircle = new Circle(0, 0, 0);
            graphics2D.dispose();
        }


        @Override
        public void print() {
            if (numberOfSteps < 1) {
                return;
            }
            double lvlSize = vSteps / (double)numberOfSteps;
            printElement((int) (xCoordinates - (xSize() * XScale * (double) lvlSize / 2)),
                    (int) (yCoordinates - (ySize() * YScale * (double) lvlSize / 2)),
                    (int) (xSize() * XScale) * (double) lvlSize,
                    (int) (ySize() * YScale) * (double) lvlSize);
            workCircle = new Circle(xCoordinates, yCoordinates, lvlSize * XScale);
        }

        int numberOfSteps;

        @Override
        public void printElement(double x1, double y1, double widthOr, double heightOr) {
            graphics2D.fillOval((int) x1, (int) y1, (int) widthOr, (int) heightOr);
        }

        @Override
        public boolean isContainsPoint(Point p) {
            return workCircle.contains(p.getX(), p.getY());
        }

        @Override
        public double xSize() {
            return Math.sqrt(picturedTicket.getVenue().getCapacity() / (Math.PI));
        }

        @Override
        public double ySize() {
            return xSize();
        }

        @Override
        public int calculateElements() {
            return (int) (xSize() * ((XScale + YScale) / 2) / (stepLong/2));
        }

        @Override
        public void incPartAnimation() {
            if(vSteps < numberOfSteps){
                vSteps++;
            }
        }
    }

    private int wSteps = 0;
    private int currentLineForPointer = 0;
    private class wayParts implements partOfTicket {

        ArrayList<Line2D> lines = new ArrayList<>();
        Polygon polygon;

        int pointerLength = 2;
        int pointerWidth = 5;

        private Graphics2D graphics2D;

        wayParts(Graphics2D g) {
            int xCorrection = (picturedTicket.getVenue().getAddress().getTown().getX() > picturedTicket.getCoordinates().getX()) ? 1 : -1;
            int yCorrection = (picturedTicket.getVenue().getAddress().getTown().getY() > picturedTicket.getCoordinates().getY()) ? 1 : -1;
            graphics2D = (Graphics2D) g.create();
            numberOfSteps = calculateElements() + 2;
            int numXSteps = calculateXElements();
            int numYSteps = calculateYElements();
            Line2D lPrevious;
            boolean prev = true;
            for (int i = 0; i < numberOfSteps - 2; i++) {
                double x1;
                double x2;
                double y1;
                double y2;
                if (i == 0) {
                    x1 = vp.xCoordinates;
                    x2 = vp.xCoordinates;
                    y1 = vp.yCoordinates;
                    y2 = vp.yCoordinates;
                } else {
                    lPrevious = lines.get(lines.size() - 1);
                    x1 = lPrevious.getX2();
                    x2 = lPrevious.getX2();
                    y1 = lPrevious.getY2();
                    y2 = lPrevious.getY2();
                }

                if (numXSteps > 0 && numYSteps > 0) {
                    if (prev) {
                        x2 += xCorrection * stepLong;
                        numXSteps--;
                        prev = false;
                    } else {
                        y2 += yCorrection * stepLong;
                        numYSteps--;
                        prev = true;
                    }
                } else {
                    if (numXSteps > 0) {
                        x2 += xCorrection * stepLong;
                        numXSteps--;
                    } else if (numYSteps > 0) {
                        y2 += yCorrection * stepLong;
                        numYSteps--;
                    } else {
                        System.out.println("Ways have trouble with steps!!!");
                    }
                }
                Line2D lCurrent = new Line2D.Double(x1, y1, x2, y2);
                lines.add(lCurrent);
            }
            double x2 = vp.xCoordinates;
            double y2 = vp.yCoordinates;
            if(lines.size() > 0){
                lPrevious = lines.get(lines.size() - 1);
                x2 = lPrevious.getX2();
                y2 = lPrevious.getY2();
            }
            lines.add(new Line2D.Double(x2, y2, tp.xCoordinates, y2));
            lines.add(new Line2D.Double(tp.xCoordinates, y2, tp.xCoordinates, tp.yCoordinates));
        }

        @Override
        public void show() {
            lines.stream().forEach(line2D -> graphics2D.draw(line2D));
        }

        private void showPointer(Line2D l) {
            int[] x = new int[4];
            int[] y = new int[4];
            x[0] = (int) l.getX2();
            y[0] = (int) l.getY2();
            boolean right = l.getX1() < l.getX2();
            boolean down = l.getY1() < l.getY2();
            if (!(l.getX1() == l.getX2())) {
                if (right) {
                    x[1] = x[0] - (int) (pointerWidth * XScale);
                    x[2] = x[0] - (int) (pointerLength * XScale);
                    x[3] = x[0] - (int) (pointerWidth * XScale);
                } else {
                    x[1] = x[0] + (int) (pointerWidth * XScale);
                    x[2] = x[0] + (int) (pointerLength * XScale);
                    x[3] = x[0] + (int) (pointerWidth * XScale);
                }
                y[1] = y[0] + (int) (pointerWidth * YScale);
                y[2] = y[0];
                y[3] = y[0] - (int) (pointerWidth * YScale);

            } else if (!(l.getY1() == l.getY2())) {
                if (down) {
                    y[1] = y[0] - (int) (pointerWidth * YScale);
                    y[2] = y[0] - (int) (pointerLength * YScale);
                    y[3] = y[0] - (int) (pointerWidth * YScale);
                } else {
                    y[1] = y[0] + (int) (pointerWidth * YScale);
                    y[2] = y[0] + (int) (pointerLength * YScale);
                    y[3] = y[0] + (int) (pointerWidth * YScale);
                }
                x[1] = x[0] + (int) (pointerWidth * XScale);
                x[2] = x[0];
                x[3] = x[0] - (int) (pointerWidth * XScale);
            }
            polygon = new Polygon(x, y, 4);
        }


        public void paintPointer() {
            if(currentLineForPointer > lines.size()-1){
                currentLineForPointer = 0;
            }
            showPointer(lines.get(currentLineForPointer));
            graphics2D.fill(polygon);
        }

        @Override
        public void hide() {
            graphics2D.setColor(backGroundColor);
            lines.stream().forEach(line2D -> graphics2D.draw(line2D));
        }

        @Override
        public void print() {
            for (int i = 0; i < wSteps; i++) {
                graphics2D.draw(lines.get(i));
            }
        }

        int numberOfSteps;

        @Override
        public void printElement(double x1, double y1, double widthOr, double heightOr) {
            return;
        }

        @Override
        public boolean isContainsPoint(Point p) {
            return lines.stream().anyMatch(line2D -> line2D.contains(p.x, p.y) || polygon.contains(p));
        }

        @Override
        public double xSize() {
            return picturedTicket.getVenue().getAddress().getTown().getX() - picturedTicket.getCoordinates().getX();
        }

        @Override
        public double ySize() {
            return picturedTicket.getVenue().getAddress().getTown().getY() - picturedTicket.getCoordinates().getY();
        }

        double xPart = 0;
        double yPart = 0;

        @Override
        public int calculateElements() {
            xPart = (xSize() - Math.floor(xSize())) * XScale;
            yPart = (ySize() - Math.floor(ySize())) * YScale;
            return calculateXElements() + calculateYElements();
        }

        @Override
        public void incPartAnimation() {
            currentLineForPointer++;
            if (currentLineForPointer == numberOfSteps) {
                currentLineForPointer = 0;
            }
            if(wSteps < numberOfSteps){
                wSteps++;
            }

        }

        private int calculateXElements() {
            return (int) Math.floor(Math.abs(xSize() * XScale / stepLong));
        }

        private int calculateYElements() {
            return (int) Math.floor(Math.abs(ySize() * YScale / stepLong));
        }

    }

    private int tSteps = 0;
    private class townParts implements partOfTicket {

        private Graphics2D graphics2D;

        private Rectangle workRectangle;

        double xCoordinates;
        double yCoordinates;

        townParts(Graphics2D g) {
            graphics2D = (Graphics2D) g.create();
            xCoordinates = xCenter + (picturedTicket.getVenue().getAddress().getTown().getX() * XScale);
            yCoordinates = yCenter + (picturedTicket.getVenue().getAddress().getTown().getY() * YScale);
            numberOfSteps = calculateElements();
        }

        @Override
        public void show() {
            graphics2D.fillRect((int) (xCoordinates - ((xSize() * XScale) / 2)), (int) (yCoordinates - ((ySize() * YScale) / 2)), (int) (xSize() * XScale), (int) (ySize() * YScale));
            workRectangle = new Rectangle((int) (xCoordinates - ((xSize() * XScale) / 2)), (int) (yCoordinates - ((ySize() * YScale) / 2)), (int) (xSize() * XScale), (int) (ySize() * YScale));
        }

        @Override
        public void hide() {
            graphics2D.setColor(backGroundColor);
            graphics2D.fillRect((int) (xCoordinates - ((xSize() * XScale) / 2)), (int) (yCoordinates - ((ySize() * YScale) / 2)), (int) (xSize() * XScale), (int) (ySize() * YScale));
            workRectangle = new Rectangle((int) (xCoordinates - ((xSize() * XScale) / 2)), (int) (yCoordinates - ((ySize() * YScale) / 2)), (int) (xSize() * XScale), (int) (ySize() * YScale));
        }

        @Override
        public void print() {
            if (numberOfSteps < 1) {
                return;
            }
            double lvlSize = tSteps / (double)numberOfSteps;
            printElement((xCoordinates - ((xSize() * XScale) * lvlSize / 2)), (yCoordinates - ((ySize() * YScale) * lvlSize / 2)), xSize() * XScale * lvlSize, ySize() * YScale * lvlSize);
        }

        int numberOfSteps;

        @Override
        public void printElement(double x1, double y1, double widthOr, double heightOr) {
            graphics2D.fillRect((int) x1, (int) y1, (int) widthOr, (int) heightOr);
            workRectangle = new Rectangle((int) x1, (int) y1, (int) widthOr, (int) heightOr);
        }

        @Override
        public boolean isContainsPoint(Point p) {
            return workRectangle.contains(p);
        }

        @Override
        public double xSize() {
            double a = Math.abs(picturedTicket.getVenue().getAddress().getTown().getZ()/5);
            if(a > 1){
                return a;
            }
            return 2;
        }

        @Override
        public double ySize() {
            return xSize();
        }

        @Override
        public int calculateElements() {
            return (int) (xSize() * ((XScale + YScale) / 2) / stepLong);
        }

        @Override
        public void incPartAnimation() {
            if(tSteps < numberOfSteps){
                tSteps++;
            }
        }
    }
}

