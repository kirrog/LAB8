package Commands;


import Collection.Ticket;
import Starter.Main;
import Web.Command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** This class remove all elements which lower then element*/
public class RemoveLower extends AbstractCommand {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Ticket tick = eCla.getTicket();
        Enumeration enums = TicketsHashTable.keys();

        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }

        stringLinkedList.stream().filter(key -> TicketsHashTable.get(key).compareTo(tick) < 0).forEach(key -> TicketsHashTable.remove(key));

//        while (enums.hasMoreElements()){
//            String key = (String) enums.nextElement();
//            Ticket t = TicketsHashTable.get(key);
//            if(t.compareTo(tick) < 0){
//                TicketsHashTable.remove(key);
//            }
//        }
    }

    @Override
    public void exe() {
        Ticket tick = (Ticket) com.getThirdArgument();
        Enumeration enums = TicketsHashTable.keys();

        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }
        int i = stringLinkedList.size();
        stringLinkedList.stream().filter(key -> TicketsHashTable.get(key).compareTo(tick) < 0).forEach(key -> TicketsHashTable.remove(key));
        i-=stringLinkedList.size();
        com.setFirstArgument("Deleted " + i + " Tickets");
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        Main.sender.send(com);
    }
}
