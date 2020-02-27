package se.ifmo.ru.Comands;


import se.ifmo.ru.Collection.Ticket;

import java.util.Enumeration;
import java.util.Scanner;

import static se.ifmo.ru.Main.TicketsHashTable;

public class RemoveLower implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Ticket tick = eCla.readTicket();
        Enumeration enums = TicketsHashTable.keys();
        while (enums.hasMoreElements()){
            String key = (String) enums.nextElement();
            Ticket t = TicketsHashTable.get(key);
            if(t.compareTo(tick) < 0){
                TicketsHashTable.remove(key);
            }
        }
    }
}
