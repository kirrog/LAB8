package ru.ifmo.se.Commands;

import ru.ifmo.se.Collection.*;
import ru.ifmo.se.WriteInOut.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;


/**
 * This class contains methods for execution commands
 */
public class ExeClass {

    private HashMap<String, Execute> hashMap = new HashMap<String, Execute>();

    private Scanner inner;

    public boolean isFile() {
        return isFile;
    }

    private boolean isFile = false;

    private String status = "";

    public static byte numberOfProceses = 0;

    public ExeClass() {
        hashMap.put("help", new Help());
        hashMap.put("clear", new Clear());
        hashMap.put("execute_script", new ExecuteScript());
        hashMap.put("filter_by_venue", new FilterByVenue());
        hashMap.put("info", new Info());
        hashMap.put("insert", new Insert());
        hashMap.put("print_descending", new PrintDescending());
        hashMap.put("print_fiend_descending", new PrintFieldDescending());
        hashMap.put("remove_greater_key", new RemoveGreaterKey());
        hashMap.put("remove_key", new RemoveKey());
        hashMap.put("remove_lower", new RemoveLower());
        hashMap.put("replace_if_lower", new ReplaceIfLower());
        hashMap.put("save", new Save());
        hashMap.put("show", new Show());
        hashMap.put("update", new Update());
        inner = new Scanner(System.in);
    }

    public ExeClass(String str) {
        hashMap.put("help", new Help());
        hashMap.put("clear", new Clear());
        hashMap.put("execute_script", new ExecuteScript());
        hashMap.put("filter_by_venue", new FilterByVenue());
        hashMap.put("info", new Info());
        hashMap.put("insert", new Insert());
        hashMap.put("print_descending", new PrintDescending());
        hashMap.put("print_fiend_descending", new PrintFieldDescending());
        hashMap.put("remove_greater_key", new RemoveGreaterKey());
        hashMap.put("remove_key", new RemoveKey());
        hashMap.put("remove_lower", new RemoveLower());
        hashMap.put("replace_if_lower", new ReplaceIfLower());
        hashMap.put("save", new Save());
        hashMap.put("show", new Show());
        hashMap.put("update", new Update());
        try {
            inner = new Scanner(new File(str));
            isFile = true;
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading script");
            Terminal.writeFileStatus(str);
        }
    }

    public void start() {

        boolean notExit = true;

        while (notExit) {
            System.out.print("Enter command: \n> ");
            String command = inner.nextLine();
            String arguments = "";
            if (command.contains(" ")) {
                arguments = command.substring(command.indexOf(" ") + 1);
                command = command.substring(0, command.indexOf(" "));
            }
            if (command.equals("exit")) {
                notExit = false;
            } else {
                if (isFile) {
                    if (!(inner.hasNext())) {
                        notExit = false;
                    } else {
                        if (hashMap.containsKey(command)) {
                            hashMap.get(command).execute(arguments, inner, this);
                        } else {
                            System.out.println("This is not command in file!");
                        }
                    }
                } else {
                    if (hashMap.containsKey(command)) {
                        hashMap.get(command).execute(arguments, inner, this);
                    } else {
                        System.out.println("Can't find this command!");
                    }
                }
            }
        }
    }

    boolean isNotRight = true;

    public Ticket getTicket() {

        int id = IdGenerator.toGenerate();
        if (isFile) {
            return readTicket();
        }

        long price = 0;
        boolean refundable = false;
        TicketType type = TicketType.USUAL;

        System.out.print("Enter \"Ticket\" name: \n> ");
        String name = inner.nextLine();

        Coordinates coordinates = getCoordinates();

        java.time.ZonedDateTime creationDate = java.time.ZonedDateTime.now();

        isNotRight = true;
        while (isNotRight) {
            System.out.print("It should cost more then 0! Enter price: \n>");
            price = readTicketPrice();
            checkValue();
        }

        System.out.print("It can be null (\"/0\"). Enter comment: \n> ");
        String comment = inner.nextLine();
        if (comment.isEmpty()) {
            comment = null;
        }

        isNotRight = true;
        while (isNotRight) {
            System.out.print("Is it refundable?\n Enter \"true\" or \"false\": \n> ");
            refundable = readTicketRefundable();
            checkValue();
        }

        isNotRight = true;
        while (isNotRight) {
            System.out.print("Type of \"Ticket\"\n Enter \"VIP\" or \"USUAL\" or \"BUDGETARY\" or \"CHEAP\": \n>");
            type = readTicketType();
            checkValue();
        }

        Venue venue = getVenue();

        return new Ticket(id, name, coordinates, creationDate, price, comment, refundable, type, venue);
    }

    private Coordinates getCoordinates() {

        long x = 0;
        double y = 0;

        isNotRight = true;
        while (isNotRight) {
            System.out.print("Enter \"x\": \n> ");
            x = readCoordinatesX();
            checkValue();
        }

        isNotRight = true;
        while (isNotRight) {
            System.out.print("This number should be more then -959! Enter \"y\": \n> ");
            y = readCoordinatesY();
            checkValue();
        }

        return new Coordinates(x, y);
    }

    public Venue getVenue() {

        long id = IdGenerator.toGenerate();
        String name = "";
        Integer capacity = 0;
        VenueType type = null;

        isNotRight = true;
        while (isNotRight) {
            System.out.print("It can't be \"\"! Enter \"name\": \n> ");
            name = readLocationName();
            checkValue();
        }

        isNotRight = true;
        while (isNotRight) {
            System.out.print("It should be > 0. Enter \"capacity\": \n> ");
            capacity = readVenueCapacity();
            checkValue();
        }

        isNotRight = true;
        while (isNotRight) {
            System.out.print("Type of \"Venue\"\n Enter \"PUB\" or \"LOFT\" or \"OPEN_AREA\" or \"MALL\" or \"STADIUM\": \n> ");
            type = readVenueType();
            checkValue();
        }

        return new Venue(name, capacity, type, getAddress());
    }

    private Address getAddress() {
        String zipCode = null;

        if (inner.hasNext("/0")) {
            return null;
        } else {
            isNotRight = true;
            while (isNotRight) {
                System.out.print("It should be taller then 8 symbols! Enter \"zipcode\": \n> ");
                zipCode = readAddressZipcode();
                checkValue();
            }
        }

        return new Address(zipCode, getLocation());
    }

    private Location getLocation() {
        Double x = 0.0;
        Float y = (float) 0.0; //Поле не может быть null
        Long z = (long) 0; //Поле не может быть null
        String name = ""; //Длина строки не должна быть больше 881, Поле может быть null

        if (inner.hasNext("/0")) {
            return null;
        } else {

            isNotRight = true;
            while (isNotRight) {
                System.out.print("Enter \"x\" position: \n> ");
                x = readLocationX();
                checkValue();
            }

            isNotRight = true;
            while (isNotRight) {
                System.out.print("Enter \"y\" position: \n> ");
                y = readLocationY();
                checkValue();
            }

            isNotRight = true;
            while (isNotRight) {
                System.out.print("Enter \"z\" position: \n> ");
                z = readLocationZ();
                checkValue();
            }

            isNotRight = true;
            while (isNotRight) {
                System.out.print("It should be less then 881 symbols! Enter \"name\": \n> ");
                name = readLocationName();
                checkValue();
            }

            return new Location(x, y, z, name);
        }
    }


    private void checkValue() {
        if (!status.endsWith("-")) {
            isNotRight = false;
        } else {
            System.out.println("Wrong value!");
            status = status.substring(0, (status.length() - 1));
        }
    }


    private boolean checkTicketStatus() {
        if (status.equals("T: NC: XYPCRTV: NCTA: /0") | status.equals("T: NC: XYPCRTV: NCTA: ZT: /0") | status.equals("T: NC: XYPCRTV: NCTA: ZT: XYZN")) {
            status = "";
            return true;
        } else {
            status = "";
            return false;
        }
    }

    private boolean checkVenueStatus() {
        if (status.equals("V: NCTA: /0") | status.equals("V: NCTA: ZT: /0") | status.equals("V: NCTA: ZT: XYZN")) {
            status = "";
            return true;
        } else {
            status = "";
            return false;
        }
    }

    public static void writeTicket(Ticket tick) {
        Coordinates coord = tick.getCoordinates();
        Venue ven = tick.getVenue();
        Address addr = ven.getAddress();
        Location loc = addr.getTown();
        System.out.print("Id: " + tick.getId() + " ");
        System.out.print("Name: " + tick.getName() + " ");
        System.out.print("Coordinates:{" + " ");
        System.out.print("X: " + coord.getX() + " ");
        System.out.print("Y: " + coord.getX() + " ");
        System.out.print("}" + " ");
        System.out.print("Creation date: " + tick.getCreationDate() + " ");
        System.out.print("Price: " + tick.getPrice() + " ");
        System.out.print("Comment: " + tick.getComment() + " ");
        System.out.print("Refundable: " + tick.isRefundable() + " ");
        System.out.print("Type: " + tick.getId() + " ");
        System.out.print("Venue:{" + " ");
        System.out.print("Id: " + ven.getId() + " ");
        System.out.print("Name: " + ven.getName() + " ");
        System.out.print("Capacity: " + ven.getCapacity() + " ");
        System.out.print("Type: " + ven.getType() + " ");
        System.out.print("Address:{" + " ");
        System.out.print("ZipCode: " + addr.getZipCode() + " ");
        System.out.print("Town:{" + " ");
        System.out.print("X: " + loc.getX() + " ");
        System.out.print("Y: " + loc.getY() + " ");
        System.out.print("Z: " + loc.getZ() + " ");
        System.out.print("Name: " + loc.getName() + " ");
        System.out.print("}" + " ");
        System.out.print("}" + " ");
        System.out.println("}");
    }


    public Ticket readTicket() {
        status = "";
        status += "T: ";
        Ticket tick = new Ticket(IdGenerator.toGenerate(), readTicketName(), readCoordinates(), java.time.ZonedDateTime.now(), readTicketPrice(), readTicketComment(), readTicketRefundable(), readTicketType(), readVenue());
        if (checkTicketStatus()) {
            return tick;
        }
        return null;
    }

    private String readTicketName() {
        if (inner.hasNextLine()) {
            String name = inner.nextLine();
            status += "N";
            return name;
        } else {
            status += "-";
            return null;
        }
    }

    private Long readTicketPrice() {
        if (inner.hasNextLine()) {
            if (inner.hasNextLong()) {
                Long price = inner.nextLong();
                if (price > 0) {
                    status += "P";
                    endser();
                    return price;
                } else {
                    endser();
                    status += "-";
                    return null;
                }
            } else {
                endser();
                status += "-";
                return null;
            }
        }
        status += "-";
        return null;
    }

    private String readTicketComment() {
        if (inner.hasNextLine()) {
            String comment = inner.nextLine();
            if (comment.equals("/0")) {
                comment = null;
            }
            status += "C";
            return comment;
        } else {
            status += "-";
            return null;
        }

    }

    private boolean readTicketRefundable() {
        if (inner.hasNextLine()) {
            if (inner.hasNextBoolean()) {
                boolean refundable = inner.nextBoolean();
                status += "R";
                endser();
                return refundable;
            } else {
                status += "-";
                endser();
                return false;
            }
        } else {
            status += "-";
            return false;
        }
    }

    private TicketType readTicketType() {
        if (inner.hasNextLine()) {
            try {
                String str = inner.nextLine();
                if (str.equals("/0")) {
                    status += "T";
                    return null;
                } else {
                    TicketType tt = TicketType.valueOf(str);
                    status += "T";
                    return tt;
                }
            } catch (java.lang.IllegalArgumentException e) {
                status += "-";
                return null;
            }
        } else {
            status += "-";
            return null;
        }
    }

    private Coordinates readCoordinates() {
        status += "C: ";
        return new Coordinates(readCoordinatesX(), readCoordinatesY());
    }

    private long readCoordinatesX() {
        if (inner.hasNextLine()) {
            if (inner.hasNextLong()) {
                long x = inner.nextLong();
                status += "X";
                endser();
                return x;
            } else {
                inner.nextLine();
                status += "-";
            }
        }
        status += "-";
        return -1;
    }

    private double readCoordinatesY() {
        if (inner.hasNextLine()) {
            if (inner.hasNextDouble()) {
                double y;
                if (inner.hasNext("-958.9999999")) {
                    y = -958.9999999;
                    inner.nextLine();
                    status += "Y";
                    return y;
                } else {
                    y = inner.nextDouble();
                    if (y > -959) {
                        endser();
                        status += "Y";
                        return y;
                    } else {
                        endser();
                        status += "-";
                    }
                }
            } else {
                endser();
                status += "-";
            }
        } else {
            status += "-";
        }
        return -1;
    }

    public Venue readVenue() {
        status += "V: ";
        if(checkVenueStatus()){
            return new Venue(readVenueName(), readVenueCapacity(), readVenueType(), readAddress());
        }else{
            return null;
        }
    }

    private String readVenueName() {
        if (inner.hasNextLine()) {
            String name = inner.nextLine();
            if (name.length() > 0) {
                status += "N";
                return name;
            } else {
                status += "-";
                return null;
            }
        } else {
            status += "-";
            return null;
        }
    }

    private Integer readVenueCapacity() {
        if (inner.hasNextLine()) {
            if (inner.hasNext(("/0"))) {
                status += "C";
                inner.nextLine();
                return null;
            } else {
                if (inner.hasNextInt()) {
                    Integer capacity = inner.nextInt();
                    endser();
                    if (capacity > 0) {
                        status += "С";
                        return capacity;
                    } else {
                        status += "-";
                        return -1;
                    }
                } else {
                    status += "-";
                    return -1;
                }
            }
        }
        status += "-";
        return -1;
    }

    private VenueType readVenueType() {
        if (inner.hasNextLine()) {
            try {
                VenueType type = VenueType.valueOf(inner.nextLine());
                status += "T";
                return type;
            } catch (java.lang.IllegalArgumentException e) {
                status += "-";
                return null;
            }
        }
        status += "-";
        return null;
    }

    private Address readAddress() {
        if (inner.hasNextLine()) {
            status += "A: ";
            if (inner.hasNext(("/0"))) {
                status += "/0";
                inner.nextLine();
                return null;
            } else {
                return new Address(readAddressZipcode(), readLocation());
            }
        } else {
            status += "-";
            return null;
        }
    }

    private String readAddressZipcode() {
        if (inner.hasNextLine()) {
            String zipCode = null;
            String str = inner.nextLine();
            if (str.equals("/0")) {
                status += "Z";
            } else {
                if (str.length() > 8) {
                    status += "Z";
                    zipCode = str;
                } else {
                    status += "-";
                }
            }
            return zipCode;
        } else {
            status += "-";
            return null;
        }

    }

    private Location readLocation() {
        if (inner.hasNextLine()) {
            status += "T: ";
            if (inner.hasNext(("/0"))) {
                status += "/0";
                inner.nextLine();
                return null;
            } else {
                return new Location(readLocationX(), readLocationY(), readLocationZ(), readLocationName());
            }
        } else {
            status += "-";
            return null;
        }
    }

    private Double readLocationX() {
        if (inner.hasNextLine()) {
            Double x = null;
            if (inner.hasNextDouble()) {
                x = inner.nextDouble();
                endser();
                status += "X";
            } else {
                status += "-";
                inner.nextLine();
            }
            return x;
        } else {
            status += "-";
            return null;
        }

    }

    private Float readLocationY() {
        if (inner.hasNextLine()) {
            Float y = null;
            if (inner.hasNextFloat()) {
                y = inner.nextFloat();
                status += "Y";
                endser();
            } else {
                status += "-";
                inner.nextLine();
            }
            return y;
        } else {
            status += "-";
            return null;
        }

    }

    private Long readLocationZ() {
        if (inner.hasNextLine()) {
            Long z = null;
            if (inner.hasNextLong() & inner.hasNextLine()) {
                z = inner.nextLong();
                status += "Z";
                endser();
            } else {
                status += "-";
                inner.nextLine();
            }
            return z;
        } else {
            status += "-";
            return null;
        }
    }

    private String readLocationName() {
        if (inner.hasNextLine()) {
            String name = inner.nextLine();
            if (name.equals("/0")) {
                status += "N";
                name = null;
            } else if (name.length() < 882) {
                status += "N";
            } else {
                status += "-";
            }
            return name;
        } else {
            status += "-";
            return null;
        }
    }

    private void endser() {
        if (inner.hasNextLine() & !isFile) {
            inner.nextLine();
        }
    }
}
