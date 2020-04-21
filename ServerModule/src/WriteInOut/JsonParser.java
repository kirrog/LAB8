package WriteInOut;

import org.json.simple.parser.*;
import org.json.simple.*;
import Collection.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;
import static Starter.Main.setHashCreationDate;


/**
 * This class parse json file!
 */
public class JsonParser {

    private File jsonFile;

    public File getjString() {
        return jsonFile;
    }

    public void setjString(File jString) {
        this.jsonFile = jString;
    }

    public JsonParser(File jString) {
        jsonFile = jString;
    }

    public void parse() {

        File f = jsonFile;

        try {
            Scanner scan = new Scanner(f);
            StringBuilder string = new StringBuilder();
            for (; scan.hasNextLine(); ) {
                String str = scan.nextLine();
                string.append(str);
            }
            try {
                JSONObject jObj = (JSONObject) new JSONParser().parse(new StringReader(string.toString()));
                //int numberOfTickets = (int) (long) jObj.get("NumberOfTickets");
                setHashCreationDate(ZonedDateTime.parse((String) jObj.get("DateOfCreation"), DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ")));
                JSONArray tickets = (JSONArray) jObj.get("Tickets");
                Iterator tickers = tickets.iterator();
                for (; tickers.hasNext(); ) {
                    JSONObject jObject = (JSONObject) tickers.next();
                    String key = (String) jObject.get("key");
                    Ticket ticket = getTicket(jObject);
                    TicketsHashTable.put(key, ticket);
                }
                System.out.println("Reading from file: success");
            } catch (ParseException | IOException e) {
                System.out.println("Reading from file: mistake in");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Reading from file: file not found");
        }

    }

    /**
     * Create Ticket without checking fields' values
     *
     * @param jsonObject
     * @return
     */
    private Ticket getTicket(JSONObject jsonObject) {

        int id = (int) (long) jsonObject.get("id");
        String name = (String) jsonObject.get("name");
        Coordinates coordinates = getCoordinates((JSONObject) jsonObject.get("coordinates"));
        java.time.ZonedDateTime creationDate = ZonedDateTime.parse((String) jsonObject.get("creationDate"), DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ"));
        long price = (long) jsonObject.get("price");
        String comment = (String) jsonObject.get("comment");
        boolean refundable = (boolean) jsonObject.get("refundable");
        TicketType type = TicketType.valueOf((String) jsonObject.get("type"));
        Venue venue = getVenue((JSONObject) jsonObject.get("venue"));
        return new Ticket(id, name, coordinates, creationDate, price, comment, refundable, type, venue);

    }

    /**
     * Create Coordinates without checking fields' values
     *
     * @param jsonObject
     * @return
     */
    private Coordinates getCoordinates(JSONObject jsonObject) {
        long x = (long) jsonObject.get("x");
        double y = (double) jsonObject.get("y");
        return new Coordinates(x, y);

    }

    /**
     * Create Venue without checking fields' values
     *
     * @param jsonObject
     * @return
     */
    private Venue getVenue(JSONObject jsonObject) {

        long id = (long) jsonObject.get("id");
        String name = (String) jsonObject.get("name");
        Integer capacity = (int) (long) jsonObject.get("capacity");
        VenueType type = VenueType.valueOf((String) jsonObject.get("type"));
        Address address;
        if (jsonObject.get("address").equals("null")) {
            address = null;
        } else {
            address = getAddress((JSONObject) jsonObject.get("address"));
        }
        return new Venue(id, name, capacity, type, address);

    }


    /**
     * Create Address without checking fields' values
     *
     * @param jsonObject
     * @return
     */
    private Address getAddress(JSONObject jsonObject) {
        String zipCode = (String) jsonObject.get("zipCode");
        Location location;
        if (jsonObject.get("town").equals("null")) {
            location = null;
        } else {
            location = getLocation((JSONObject) jsonObject.get("town"));
        }
        return new Address(zipCode, location);

    }

    /**
     * Create Location without checking fields' values
     *
     * @param jsonObject
     * @return
     */
    private Location getLocation(JSONObject jsonObject) {

        Double x = (Double) jsonObject.get("x");

        Float y = (float) (double) jsonObject.get("y");

        Long z = (Long) jsonObject.get("z");
        String name;
        if(jsonObject.get("name").equals("null")){
            name = null;
        }else {
            name = (String) jsonObject.get("name");
        }


        return new Location(x, y, z, name);
    }
}
