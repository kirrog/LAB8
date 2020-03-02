package ru.ifmo.se.Parser;

import org.json.simple.parser.*;
import org.json.simple.*;
import ru.ifmo.se.Collection.*;
import ru.ifmo.se.Main;
import se.ifmo.ru.Collection.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/** This class parse json file!*/
public class JsonParser {

    private File jString;

    public File getjString() {
        return jString;
    }

    public void setjString(File jString) {
        this.jString = jString;
    }

    public JsonParser(File jsonString) {
        jString = jsonString;
    }

    public void parse() {
        try {
            Scanner scanner = new Scanner(jString);
            String line = "";
//            while (scanner.hasNextLine()){
//                System.out.println(scanner.nextLine());
//                //line = line + "\n" + scanner.nextLine();
//            }

//            try {

            scanner.nextLine();
            line = scanner.nextLine();
            line = line + "\n" + scanner.nextLine();
            scanner.nextLine();
            line = "{" + line + "}";
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) new JSONParser().parse(line);
            } catch (ParseException e) {
                System.out.println("At beginning -1");
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ");
            int numberOfTickets = (int) (long) jsonObject.get("NumberOfTickets");
            Main.setHashCreationDate(java.time.ZonedDateTime.parse((String) jsonObject.get("DateOfCreation"), dtf));


            for (int i = 0; i < numberOfTickets; i++) {
                Ticket Tick;
                JSONObject tick = null;
                line = "";
                long id = 0;
                String name = "";
                long xCord = 9;
                double yCord = 9;
                java.time.ZonedDateTime creationDate = null;
                long price = 0;
                String comment = "";
                boolean refundable = false;
                TicketType type = null;
                Venue venue = null;
                line = "";

                line = scanner.nextLine();
                line = line + "\n" + scanner.nextLine();
                line = line + "\n" + scanner.nextLine();
                line = line + "}";

                try {
                    tick = (JSONObject) new JSONParser().parse(line);
                } catch (ParseException e) {
                    System.out.println("At beginning");
                }


                id = (long)tick.get("id");
                name = (String) tick.get("name");

                line = "";

                scanner.nextLine();
                line = "{" + "\n" + scanner.nextLine();
                line = line + "\n" + scanner.nextLine() + "}";
                scanner.nextLine();
                try {
                    tick = (JSONObject) new JSONParser().parse(line);
                } catch (ParseException e) {
                    System.out.println("At beginning 2");
                }

                xCord = (long) tick.get("x");
                yCord = (double) tick.get("y");

                line = "";

                line = "{" + scanner.nextLine();
                line = line + "\n" + scanner.nextLine();
                line = line + "\n" + scanner.nextLine();
                line = line + "\n" + scanner.nextLine();
                line = line + "\n" + scanner.nextLine() + "}";
                try {
                    tick = (JSONObject) new JSONParser().parse(line);
                } catch (ParseException e) {
                    System.out.println("At beginning 3");
                }

                creationDate = java.time.ZonedDateTime.parse((String) tick.get("creationDate"), dtf);
                price = (long) tick.get("price");
                comment = (String) tick.get("comment");
                refundable = (boolean) tick.get("refundable");
                type = TicketType.valueOf((String) tick.get("type"));

                line = "";
                venue = getVenue(scanner);


                Tick = new Ticket((int)id, name, new Coordinates(xCord, yCord), creationDate, price, comment, refundable, type, venue);
                Main.TicketsHashTable.put((String) Tick.getName(), Tick);
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
            }

//            } catch (ParseException e) {
//                System.out.println("There are mistakes in the file! While parsing.");
//            }


        } catch (FileNotFoundException e) {
            System.out.println("Can't find file!");
        }


    }

    private Venue getVenue(Scanner scanner) {
        String line = "{";
        scanner.nextLine();
        line = line + "\n" + scanner.nextLine();
        line = line + "\n" + scanner.nextLine();
        line = line + "\n" + scanner.nextLine();
        line = line + "\n" + scanner.nextLine() + "}";

        try {
            JSONObject tick = (JSONObject) new JSONParser().parse(line);

            long id = (long) tick.get("id");
            String name = (String) tick.get("name");
            Integer capacity = (int)(long) tick.get("capacity");
            VenueType type = VenueType.valueOf((String) tick.get("type"));
            Address address = getAddress(scanner);

            return new Venue(id, name, capacity, type, address);

        } catch (ParseException e) {
            System.out.println("There are mistakes in venue");
        }

        return null;
    }

    private Address getAddress(Scanner scanner) {
        String line = "{";
        scanner.nextLine();
        line = line + "\n" + scanner.nextLine() + "}";

        try {
            JSONObject tick = (JSONObject) new JSONParser().parse(line);

            String zipCode = (String) tick.get("zipCode");

            return new Address(zipCode, getLocation(scanner));

        } catch (ParseException e) {
            System.out.println("There are mistakes in address");
        }

        return null;
    }

    private Location getLocation(Scanner scanner) {
        String line = "{";
        scanner.nextLine();
        line = line + "\n" + scanner.nextLine();
        line = line + "\n" + scanner.nextLine();
        line = line + "\n" + scanner.nextLine();
        line = line + "\n" + scanner.nextLine();
        line = line + "\n" + scanner.nextLine();

        try {
            JSONObject tick = (JSONObject) new JSONParser().parse(line);

            Double x = (Double) tick.get("x");
            Float y = (float)(double) tick.get("y");
            Long z = (Long) tick.get("z");
            String name = (String) tick.get("name");
            return new Location(x, y, z, name);

        } catch (ParseException e) {
            System.out.println("There are mistakes in location");
        }

        return null;
    }

}
