package Commands;


import Collection.Ticket;
import Starter.Main;
import Web.Command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/**
 * This class compare in element and if bigger change it at key
 */
public class Update extends AbstractCommand {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        int Id;
        try {
            Id = Integer.parseInt(string);
            if (Id > 0) {

                LinkedList<String> stringLinkedList = new LinkedList<>();

                Enumeration enums = TicketsHashTable.keys();

                for (; enums.hasMoreElements(); ) {
                    stringLinkedList.add((String) enums.nextElement());
                }

                String str = stringLinkedList.stream().filter(t -> TicketsHashTable.get(t).getId() == Id).findFirst().get();
                if ((!str.isEmpty()) & (!str.equals("Optional.empty"))) {
                    System.out.println(str);
                    Ticket tick = (TicketsHashTable.get(str));
                    if (tick.getId() == Id) {
                        Ticket ticker = eCla.getTicket();

                        if (ticker == null) {
                            System.out.println("Null Ticket entered");
                        } else {
                            TicketsHashTable.put(str, ticker);
                        }
                    }
                } else {
                    System.out.println("Didn't find object with this id: " + Id);
                }

            } else {
                System.out.println("Id must be more then 0!");
            }
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Wrong type of variable");
        }
    }


    @Override
    public void exe() {
        int Id = (int) com.getSecondArgument();
        ArrayList<String> stringLinkedList = new ArrayList<>();

        Enumeration enums = TicketsHashTable.keys();

        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }

        String str = stringLinkedList.stream().filter(t -> TicketsHashTable.get(t).getId() == Id).findFirst().get();
        if ((!str.isEmpty()) & (!str.equals("Optional.empty"))) {
            Ticket tick = (TicketsHashTable.get(str));
            Ticket ticker = (Ticket) com.getThirdArgument();
            TicketsHashTable.put(str, ticker);
            com.setFirstArgument("Updated!");
        } else {
            com.setFirstArgument("Didn't find object with this id: " + Id);
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        Main.sender.send(com);
    }
}
