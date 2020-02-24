package se.ifmo.ru.Parser;

import org.json.simple.parser.*;
import org.json.simple.*;
import se.ifmo.ru.Collection.Coordinates;
import se.ifmo.ru.Collection.Ticket;
import se.ifmo.ru.Collection.TicketType;
import se.ifmo.ru.Collection.Venue;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Scanner;

import static se.ifmo.ru.Main.TicketsHashTable;


public class JsonParser {


    public JsonParser(File jsonString) {
        try {
            Scanner scanner = new Scanner(jsonString);

            String line = scanner.next();
//            int numberOfHooks = 0;
//
//            if (line.contains("{")) {
//                numberOfHooks++;
//            }

//            while (numberOfHooks > 0) { //Cutting file to objects
//                line = scanner.nextLine();
//                boolean read = false;
//                if (line.contains("{")) {
//                    numberOfHooks++;
//                }
//                if (line.contains("}")) {
//                    numberOfHooks--;
//                }
//                if (line.contains("[")) {
//                    read = true;
//                }
//                if (line.contains("]")) {
//                    read = false;
//                }
//
//                if(read){
//
//                }
//
//            }
            try {
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(line);;
                int numberOfTickets = (int) jsonObject.get("numberOfTickets");
                JSONArray TicketsArr = (JSONArray) jsonObject.get("phoneNumbers");
                Iterator TicketsItr = TicketsArr.iterator();
                Ticket[] tickets = new Ticket[numberOfTickets];
                while (TicketsItr.hasNext()) {
                    JSONObject tick = (JSONObject) TicketsItr.next();
                    Ticket Tick = getTicket(tick);
                    TicketsHashTable.put((int)Tick.getId(),Tick);
                }

            } catch (ParseException e) {
                System.out.println("There are mistakes in the file!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file!");
        }

    }

    private Ticket getTicket(JSONObject jo){
         int id = (int)jo.get("id");
         String name = (String)jo.get("name");
         Coordinates coordinates = new Coordinates((JSONObject)jo.get("Coordinates"));
         java.time.ZonedDateTime creationDate = ZDTgetter((JSONObject)jo.get("creationDate"));
         long price = (long)jo.get("price");
         String comment = (String)jo.get("comment");
         boolean refundable = (boolean)jo.get("refundable");
         TicketType type = TicketType.valueOf((String)jo.get("type"));
         Venue venue = new Venue((JSONObject)jo.get("Venue"));
         return new Ticket(id, name, coordinates, creationDate, price, comment, refundable, type, venue);
    }

    private java.time.ZonedDateTime ZDTgetter(JSONObject jo){
        int year = (int) jo.get("year");
        int month = (int) jo.get("month");
        int dayOfMonth = (int) jo.get("dayOfMonth");
        int hour = (int) jo.get("hour");
        int minute = (int) jo.get("minute");
        int second = (int) jo.get("second");
        int nanoOfSecond = (int) jo.get("nanoOfSecond");
        ZoneId zone = ZoneId.of((String)jo.get("zone"));
        return ZonedDateTime.of(year,month,dayOfMonth,hour,minute,second,nanoOfSecond,zone);
    }

}
