package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Enumeration;
import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class show all content in HashTable*/
public class Show implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Enumeration enums = TicketsHashTable.keys();
        while (enums.hasMoreElements()) {
            String ticket = (String) enums.nextElement();
            System.out.println("Key: " + ticket);
            TicketsHashTable.get(ticket).writeTicket();
        }
    }
}
