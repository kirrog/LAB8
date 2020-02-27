package se.ifmo.ru.Parser;

import se.ifmo.ru.Collection.*;
import se.ifmo.ru.Main;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import static se.ifmo.ru.Main.TicketsHashTable;

public class InFileParser {

    String a = "\t";
    private FileOutputStream outputStream;

    public InFileParser(File outFile) {
        try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
            System.out.println("File successfully opened to write in!");
            this.outputStream = outputStream;
        } catch (IOException ex) {
            System.out.println("Can't open file to write!");
        }
    }

    public void saveInFile() {

        Enumeration enums = TicketsHashTable.elements();

        try (BufferedOutputStream bos = new BufferedOutputStream(outputStream)){


            String str = ("file:{\n");
            byte[] buffer = str.getBytes();
            bos.write(buffer, 0, buffer.length);

            str = ("\t\"NumberOfTickets\": " + TicketsHashTable.size() + ",\n");
            buffer = str.getBytes();
            bos.write(buffer, 0, buffer.length);

            str = ("\t\"DateOfCreation\": "+ Main.getHashCreationDate() +"\n");
            buffer = str.getBytes();
            bos.write(buffer, 0, buffer.length);

            str = ("\t\"Tickets\": [\n");
            buffer = str.getBytes();
            bos.write(buffer, 0, buffer.length);

            for (; enums.hasMoreElements(); ) {
                str = makeJSONTicket((Ticket) enums.nextElement());
                buffer = str.getBytes();
                bos.write(buffer, 0, buffer.length);
            }

            str = ("\t]\n");
            buffer = str.getBytes();
            bos.write(buffer, 0, buffer.length);

            str = ("}\n");
            buffer = str.getBytes();
            bos.write(buffer, 0, buffer.length);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String makeJSONTicket(Ticket t) {

        long id = t.getId();
        String name = t.getName();
        Coordinates coordinates = t.getCoordinates();
        ZonedDateTime creationDate = t.getCreationDate();
        long price = t.getPrice();
        String comment = t.getComment();
        boolean refundable = t.isRefundable();
        TicketType type = t.getType();
        Venue venue = t.getVenue();

        String str[] = new String[13];
        str[0] = (a + a + "{\n");
        str[1] = (a + a + a + "\"id\": " + id + ",\n");
        str[2] = (a + a + a + "\"name\": " + "\"" + name + "\"" + ",\n");
        str[3] = (a + a + a + "\"coordinates\": {\n");
        str[4] = makeJSONCoordinates(coordinates);
        str[5] = (a + a + a + "\"creationDate\": " + creationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z")) + "\n");
        str[6] = (a + a + a + "\"price\": " + price + ",\n");
        str[7] = (a + a + a + "\"comment\": " + "\"" + comment + "\"" + ",\n");
        str[8] = (a + a + a + "\"refundable\": " + "\"" + refundable + "\"" + ",\n");
        str[9] = (a + a + a + "\"type\": " + "\"" + type.toString() + "\"" + ",\n");
        str[10] = (a + a + a + "\"venue\": {\n");
        str[11] = makeJSONVenue(venue);
        str[12] = a + a + "}\n";
        return String.join("", str);
    }

    private String makeJSONCoordinates(Coordinates coor) {
        String str[] = new String[3];
        str[0] = (a + a + a + a + "\"x\": " + coor.getX()) + ",\n";
        str[1] = (a + a + a + a + "\"y\": " + coor.getY()) + ",\n";
        str[2] = (a + a + a + "},\n");
        return String.join("", str);
    }

    private String makeJSONVenue(Venue ven) {
        String str[] = new String[7];

        str[0] = (a + a + a + a + "\"id\": " + ven.getId() + ",\n");
        str[1] = (a + a + a + a + "\"name\": " + "\"" + ven.getName() + "\"" + ",\n");
        str[2] = (a + a + a + a + "\"capacity\": " + ven.getCapacity() + ",\n");
        str[3] = (a + a + a + a + "\"type\": " + "\"" + ven.getType().toString() + "\"" + ",\n");

        if (ven.getAddress() == null) {
            str[4] = (a + a + a + a + "\"address\": \"null\"\n");
            str[5] = "";
        } else {
            str[4] = (a + a + a + a + "\"address\": {\n");
            str[5] = makeJSONAdress(ven.getAddress());
        }

        str[6] = (a + a + a + "}\n");

        return String.join("", str);
    }

    private String makeJSONAdress(Address add) {
        String str[] = new String[4];

        str[0] = (a + a + a + a + a + "\"zipCode\": " + "\"" + add.getZipCode() + "\"" + ",\n");
        if (add.getTown() == null) {
            str[1] = (a + a + a + a + a + "\"town\": \"null\"\n");
            str[2] = "";
        } else {
            str[1] = (a + a + a + a + a + "\"town\": {\n");
            str[2] = makeJSONLocation(add.getTown());
        }
        str[3] = (a + a + a + a + "}\n");
        return String.join("", str);
    }

    private String makeJSONLocation(Location loc) {
        String str[] = new String[5];

        str[0] = (a + a + a + a + a + a + "\"x\": " + loc.getX()) + ",\n";
        str[1] = (a + a + a + a + a + a + "\"y\": " + loc.getY()) + ",\n";
        str[2] = (a + a + a + a + a + a + "\"z\": " + loc.getZ()) + ",\n";
        str[3] = (a + a + a + a + a + a + "\"name\": " + "\"" + loc.getName()) + "\"" + ",\n";
        str[4] = (a + a + a + a + a + "}\n");

        return String.join("", str);
    }
}
