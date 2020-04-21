package WriteInOut;

import Collection.*;

import java.util.Scanner;

public class TicketReader {
    private Scanner inner;
    private boolean isFile;
    boolean status = true;

    public TicketReader(Scanner in, boolean isF) {
        inner = in;
        isFile = isF;
        status = true;
    }

    public Ticket getTicket() {
        status = true;

        int id = IdGenerator.toGenerate();
        if (isFile) {
            return readTicket();
        }

        long price;
        boolean refundable;
        TicketType type;
        String name;
        String comment;


        while (true) {
            System.out.print("Ticket name\n>");
            name = readTicketName();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        Coordinates coordinates = getCoordinates();

        java.time.ZonedDateTime creationDate = java.time.ZonedDateTime.now();

        while (true) {
            System.out.print("Price. It should cost more then 0!\n>");
            try {
                price = readTicketPrice();
            } catch (NullPointerException e) {
                status = true;
                System.out.println("Wrong value");
                continue;
            }
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        while (true) {
            System.out.print("Comment. It can be null - enter \"\"!\n>");
            comment = readTicketComment();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        while (true) {
            System.out.print("Refundable. Is this ticket refundable?\n Enter \"true\" or \"false\"\n>");
            refundable = readTicketRefundable();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        while (true) {
            System.out.print("Ticket type. Type of \"Ticket\"\n Enter \"VIP\" or \"USUAL\" or \"BUDGETARY\" or \"CHEAP\"\n>");
            type = readTicketType();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        Venue venue = getVenue();

        return new Ticket(id, name, coordinates, creationDate, price, comment, refundable, type, venue);
    }

    private Coordinates getCoordinates() {
        long x = 0;
        double y = 0;

        while (true) {
            System.out.print("Coordinates X\n>");
            x = readCoordinatesX();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        while (true) {
            System.out.print("Coordinates Y. This number should be more then -959!\n>");
            y = readCoordinatesY();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        return new Coordinates(x, y);
    }

    public Venue getVenue() {

        if(isFile){
            return readVenue();
        }

        long id = IdGenerator.toGenerate();
        String name;
        Integer capacity;
        VenueType type;

        while (true) {
            System.out.print("Venue name. It can't be \"\"!\n>");
            name = readVenueName();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        while (true) {
            System.out.print("Venue capacity. It should be > 0\n>");
            capacity = readVenueCapacity();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }


        while (true) {
            System.out.print("Venue type. Type of \"Venue\"\n Enter \"PUB\" or \"LOFT\" or \"OPEN_AREA\" or \"MALL\" or \"STADIUM\"\n>");
            type = readVenueType();
            if (status) {
                break;
            } else {
                status = true;
                System.out.println("Wrong value");
            }
        }

        return new Venue(name, capacity, type, getAddress());
    }

    private Address getAddress() {
        String zipCode = null;
        System.out.print("Address. It can be null, so write \"\" if null, or \"Address:\" if not null\n>");
        while (true) {
            String string = inner.nextLine();
            if (string.isEmpty()) {
                return null;
            } else if (string.equals("Address:")) {
                while (true) {
                    System.out.print("Address Zipcode. It should be taller then 8 symbols!\n>");
                    zipCode = readAddressZipcode();
                    if (status) {
                        break;
                    } else {
                        status = true;
                        System.out.println("Wrong value");
                    }
                }
                return new Address(zipCode, getLocation());
            }else {
                System.out.println("Wrong value");
                System.out.print("Write \"\" or \"Address:\"\n>");
            }
        }
    }

    private Location getLocation() {

        System.out.print("Town. It can be null, so write \"Town:\" if you want write it, or \"\" if null\n>");
        while (true) {
            String string = inner.nextLine();
            if (string.isEmpty()) {
                return null;
            } else if (string.equals("Town:")) {
                Double x = 0.0;
                Float y = (float) 0.0;
                Long z = (long) 0;
                String name = "";
                while (true) {
                    System.out.print("Town X. Enter \"x\" position\n>");
                    x = readLocationX();
                    if (status) {
                        break;
                    } else {
                        status = true;
                        System.out.println("Wrong value");
                    }
                }


                while (true) {
                    System.out.print("Town Y. Enter \"y\" position\n>");
                    y = readLocationY();
                    if (status) {
                        break;
                    } else {
                        status = true;
                        System.out.println("Wrong value");
                    }
                }


                while (true) {
                    System.out.print("Town Z. Enter \"z\" position\n>");
                    z = readLocationZ();
                    if (status) {
                        break;
                    } else {
                        status = true;
                        System.out.println("Wrong value");
                    }
                }


                while (true) {
                    System.out.print("Town name. It should be less then 881 symbols or null - \"\"!\n>");
                    name = readLocationName();
                    if (status) {
                        break;
                    } else {
                        status = true;
                        System.out.println("Wrong value");
                    }
                }
                return new Location(x, y, z, name);
            } else {
                System.out.println("Wrong value");
                System.out.print("Write \"\" or \"Town:\"\n>");
            }
        }
    }


    public Ticket readTicket() {
        status = true;
        Ticket tick = null;
        try {
            tick = new Ticket(IdGenerator.toGenerate(), readTicketName(), readCoordinates(), java.time.ZonedDateTime.now(), readTicketPrice(), readTicketComment(), readTicketRefundable(), readTicketType(), readVenue());
        } catch (NullPointerException e) {
            status = false;
        }
        if (status) {
            return tick;
        }
        return null;
    }

    private String readTicketName() {
        if (inner.hasNextLine()) {
            String name = inner.nextLine();
            if (name.isEmpty()) {
                status = false;
                return null;
            }
            return name;
        } else {
            status = false;
            return null;
        }
    }

    private Long readTicketPrice() {
        if (inner.hasNextLine()) {
            try {
                long price = Long.parseLong(inner.nextLine());
                if (price > 0) {
                    return price;
                } else {

                    status = false;
                    return null;
                }
            } catch (NumberFormatException e) {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }
    }

    private String readTicketComment() {
        if (inner.hasNextLine()) {
            String comment = inner.nextLine();
            if (comment.isEmpty()) {
                return null;
            }
            return comment;
        } else {
            status = false;
            return null;
        }

    }

    private boolean readTicketRefundable() {
        if (inner.hasNextLine()) {
            try {
                return Boolean.parseBoolean(inner.nextLine());
            } catch (NumberFormatException e) {
                status = false;
                return false;
            }
        } else {
            status = false;
            return false;
        }
    }

    private TicketType readTicketType() {
        if (inner.hasNextLine()) {
            try {
                String str = inner.nextLine();
                if (str.equals("/0")) {
                    return null;
                } else {
                    return TicketType.valueOf(str);
                }
            } catch (java.lang.IllegalArgumentException e) {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }
    }

    private Coordinates readCoordinates() {
        return new Coordinates(readCoordinatesX(), readCoordinatesY());
    }

    private long readCoordinatesX() {
        if (inner.hasNextLine()) {
            try {
                return Long.parseLong(inner.nextLine());
            } catch (NumberFormatException e) {
                status = false;
                return -1;
            }
        } else {
            status = false;
            return -1;
        }
    }

    private double readCoordinatesY() {
        if (inner.hasNextLine()) {
            double y;
            if (inner.hasNext("-958.9999999999999")) {
                inner.nextLine();
                return -958.9999999999999;
            } else {
                try {
                    y = Double.parseDouble(inner.nextLine());
                    if (y > -959) {
                        return y;
                    } else {
                        status = false;
                    }
                } catch (NumberFormatException e) {
                    status = false;
                }
            }
        } else {
            status = false;
        }
        return -1;
    }

    public Venue readVenue() {
        if (status) {
            return new Venue(readVenueName(), readVenueCapacity(), readVenueType(), readAddress());
        } else {
            return null;
        }
    }

    private String readVenueName() {
        if (inner.hasNextLine()) {
            String name = inner.nextLine();
            if (name.length() > 0) {
                return name;
            } else {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }
    }

    private Integer readVenueCapacity() {
        if (inner.hasNextLine()) {
            String string = inner.nextLine();
            if (string.isEmpty()) {
                return null;
            } else {
                try {
                    int capacity = Integer.parseInt(string);
                    if (capacity > 0) {
                        return capacity;
                    } else {
                        status = false;
                        return -1;
                    }
                } catch (NumberFormatException e) {
                    status = false;
                    return -1;
                }
            }
        } else {
            status = false;
            return -1;
        }
    }


    private VenueType readVenueType() {
        if (inner.hasNextLine()) {
            try {
                return VenueType.valueOf(inner.nextLine());
            } catch (java.lang.IllegalArgumentException e) {
                status = false;
                return null;
            }
        }
        status = false;
        return null;
    }

    private Address readAddress() {

        if (inner.hasNextLine()) {
            String string = inner.nextLine();
            if (string.isEmpty()) {
                return null;
            } else if (string.equals("Address:")) {
                return new Address(readAddressZipcode(), readLocation());
            } else {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }

    }

    private String readAddressZipcode() {
        if (inner.hasNextLine()) {
            String zipCode = null;
            String str = inner.nextLine();
            if (str.isEmpty()) {
                return null;
            } else {
                if (str.length() > 8) {
                    zipCode = str;
                } else {
                    status = false;
                }

                return zipCode;
            }
        } else {
            status = false;
            return null;
        }
    }

    private Location readLocation() {
        if (inner.hasNextLine()) {
            String str = inner.nextLine();
            if (str.isEmpty()) {
                return null;
            } else if (str.equals("Town:")) {
                return new Location(readLocationX(), readLocationY(), readLocationZ(), readLocationName());
            } else {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }
    }

    private Double readLocationX() {
        if (inner.hasNextLine()) {
            Double x = null;
            try {
                x = Double.parseDouble(inner.nextLine());
                return x;
            } catch (NumberFormatException e) {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }

    }

    private Float readLocationY() {
        if (inner.hasNextLine()) {
            try {
                return Float.parseFloat(inner.nextLine());
            } catch (NumberFormatException e) {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }
    }

    private Long readLocationZ() {
        if (inner.hasNextLine()) {
            try {
                return Long.parseLong(inner.nextLine());
            } catch (NumberFormatException e) {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }
    }

    private String readLocationName() {
        if (inner.hasNextLine()) {
            String name = inner.nextLine();
            if (name.isEmpty()) {
                return null;
            } else if (name.length() < 882) {
                return name;
            } else {
                status = false;
                return null;
            }
        } else {
            status = false;
            return null;
        }
    }

}