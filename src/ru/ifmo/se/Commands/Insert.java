package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class insert element by key*/
public class Insert implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Enter key: ");
        String key = string;
        Ticket tick = eCla.getTicket();
        TicketsHashTable.put(key,tick);
    }
}
