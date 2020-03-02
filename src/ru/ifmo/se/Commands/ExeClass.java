package ru.ifmo.se.Commands;

import ru.ifmo.se.Collection.*;
import se.ifmo.ru.Collection.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


/** This class contains methods for execution commands*/
public class ExeClass {

    private HashMap<String, Execute> hashMap = new HashMap<String, Execute>();

    private Scanner inner;

    public ExeClass() {
        hashMap.put("help", new Help());
        hashMap.put("clear", new Clear());
        hashMap.put("execute_script", new ExecuteScript());
        hashMap.put("exit", new Exit());
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

    public ExeClass(File str) {
        hashMap.put("help", new Help());
        hashMap.put("clear", new Clear());
        hashMap.put("execute_script", new ExecuteScript());
        hashMap.put("exit", new Exit());
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
            inner = new Scanner(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start(boolean file) {
        boolean notExit = true;
        while (notExit) {
            System.out.print("Enter command: ");
            String command = inner.nextLine();
            if (command.equals("exit")) {
                notExit = false;
                break;
            }
            if (file & !(inner.hasNext())) {
                notExit = false;
                break;
            }
            if (hashMap.containsKey(command)) {
                String arguments = null;
//                if (inner.hasNextLine()) {
//                    arguments = inner.nextLine();
//                }
                hashMap.get(command).execute(arguments, inner, this);
            }else {
                System.out.println("Can't find this command");
            }
        }
    }

    boolean isNotRight = true;

    public Ticket readTicket() {

        int id = IdGenerator.toGenerate();
        long price = 0;
        boolean refundable = false;
        TicketType type = TicketType.USUAL;
        System.out.println("Enter \"Ticket\" name: ");
        String name = inner.nextLine();

        Coordinates coordinates = getCoordinates();

        java.time.ZonedDateTime creationDate = java.time.ZonedDateTime.now();

        while (isNotRight) {
            try {
                System.out.println("It should cost more then 0! Enter price: ");

                try {
                    inner.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                price = inner.nextLong();

            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
            if (price > 0) {
                break;
            } else {
                System.out.println("Price must be more then 0!");
            }
        }

        String comment = inner.nextLine();
        if (comment.isEmpty()) {
            comment = null;
        }

        while (isNotRight) {
            try {
                System.out.println("Is it refundable?\n Enter \"true\" or \"false\": ");
                refundable = inner.nextBoolean();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("Is it refundable?\n Enter \"true\" or \"false\": ");
                refundable = inner.nextBoolean();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("Type of \"Ticket\"\n Enter \"VIP\" or \"USUAL\" or \"BUDGETARY\" or \"CHEAP\": ");
                type = TicketType.valueOf(inner.nextLine());
                break;
            } catch (java.lang.IllegalArgumentException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        Venue venue = getVenue();

        return new Ticket(id, name, coordinates, creationDate, price, comment, refundable, type, venue);
    }

    private Coordinates getCoordinates() {

        long x = 0;
        double y = 0;

        while (isNotRight) {
            try {
                System.out.println("Enter \"x\" position: ");
                x = inner.nextLong();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("It should be more then -959! Enter \"y\" position: ");
                y = inner.nextDouble();

            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
            if (y > -959) {
                break;
            }
        }

        return new Coordinates(x, y);
    }

    public Venue getVenue() {

        long id = IdGenerator.toGenerate();
        String name = "";
        Integer capacity = 0;
        VenueType type = null;

        while (isNotRight) {
            System.out.println("It can't be \"\"! Enter \"name\": ");
            name = inner.nextLine();
            if (name.length() > 0) {
                break;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("Enter \"capacity\": ");
                capacity = inner.nextInt();

            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
            if (capacity > 0) {
                break;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("Type of \"Venue\"\n Enter \"PUB\" or \"LOFT\" or \"OPEN_AREA\" or \"MALL\" or \"STADIUM\": ");
                type = VenueType.valueOf(inner.nextLine());
                break;
            } catch (java.lang.IllegalArgumentException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        return new Venue(name, capacity, type, getAddress());
    }

    private Address getAddress() {
        String zipCode = null;

        while (isNotRight) {
            System.out.println("It should be taller then 8 symbols! Enter \"zipcode\": ");
            zipCode = inner.nextLine();
            if (zipCode.isEmpty()) {
                zipCode = null;
            } else {
                if (zipCode.length() < 9) {
                    continue;
                } else {
                    break;
                }
            }
        }

        return new Address(zipCode, getLocation());
    }

    private Location getLocation() {
        Double x = 0.0;
        Float y = (float) 0.0; //Поле не может быть null
        Long z = (long) 0; //Поле не может быть null
        String name = ""; //Длина строки не должна быть больше 881, Поле может быть null


        while (isNotRight) {
            try {
                System.out.println("Enter \"x\" position: ");
                x = inner.nextDouble();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("Enter \"y\" position: ");
                y = inner.nextFloat();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("Enter \"z\" position: ");
                z = inner.nextLong();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
        }

        while (isNotRight) {
            try {
                System.out.println("It should be less then 881 symbols! Enter \"name\": ");
                name = inner.nextLine();

            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
            if (name.length() < 882) {
                break;
            }
        }


        return new Location(x, y, z, name);
    }

    public static void writeTicket(Ticket tick){
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

//    private static boolean isNumber(String s){
//        try {
//            Integer.parseInt(s);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
}
