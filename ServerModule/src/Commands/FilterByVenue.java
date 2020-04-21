package Commands;


import Collection.Venue;
import Web.Command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** This class print all elements with this venue*/
public class FilterByVenue extends AbstractCommand {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Enter venue");
        Venue ven = eCla.getVenue();
        Enumeration enums = TicketsHashTable.keys();

        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }

        stringLinkedList.stream()
                .filter(key -> TicketsHashTable.get(key).getVenue().equals(ven))
                .forEach(key -> TicketsHashTable.get(key).writeTicket());

//        while (enums.hasMoreElements()){
//            String key = (String) enums.nextElement();
//            if(ven.equals(TicketsHashTable.get(key).getVenue())){
//                TicketsHashTable.get(key).writeTicket();
//            }
//        }
    }

    @Override
    public void exe() {

        Venue ven = (Venue) com.getThirdArgument();
        Enumeration enums = TicketsHashTable.keys();
        ArrayList<String> stringLinkedList = new ArrayList<>();
        ArrayList<Command> commands = new ArrayList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }
        stringLinkedList.stream()
                .filter(key -> TicketsHashTable.get(key).getVenue().equals(ven))
                .forEach(key->{
            Command c = new Command();
            c.setNameOfCommand("filter_by_venue");
            c.setFirstArgument(key);
            c.setThirdArgument(TicketsHashTable.get(key));
            commands.add(c);
        });
        this.sort(commands);
        send(commands);
    }
}
