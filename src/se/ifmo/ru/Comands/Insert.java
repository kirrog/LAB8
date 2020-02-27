package se.ifmo.ru.Comands;


import se.ifmo.ru.Collection.Ticket;

import java.util.Scanner;

import static se.ifmo.ru.Main.TicketsHashTable;

public class Insert implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Enter key: ");
        String key = scan.nextLine();
        Ticket tick = eCla.readTicket();
        TicketsHashTable.put(key,tick);
    }
}
