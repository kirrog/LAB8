package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Enumeration;
import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class remove all elements which lower then element*/
public class RemoveLower implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Ticket tick = eCla.getTicket();
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
