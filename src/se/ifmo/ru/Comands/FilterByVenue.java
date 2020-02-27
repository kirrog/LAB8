package se.ifmo.ru.Comands;


import se.ifmo.ru.Collection.Venue;

import java.util.Enumeration;
import java.util.Scanner;

import static se.ifmo.ru.Main.TicketsHashTable;

public class FilterByVenue implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Enter venue:");
        Venue ven = eCla.getVenue();
        Enumeration enums = TicketsHashTable.keys();
        while (enums.hasMoreElements()){
            String key = (String) enums.nextElement();
            if(ven.equals(TicketsHashTable.get(key).getVenue())){
                eCla.writeTicket(TicketsHashTable.get(key));
            }
        }
    }
}
