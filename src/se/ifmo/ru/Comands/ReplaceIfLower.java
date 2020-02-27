package se.ifmo.ru.Comands;


import se.ifmo.ru.Collection.Ticket;

import java.util.Scanner;

import static se.ifmo.ru.Main.TicketsHashTable;

public class ReplaceIfLower implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Enter key: ");
        String str = scan.nextLine();
        if(TicketsHashTable.containsKey(str)){
            Ticket tick = eCla.readTicket();
            if (TicketsHashTable.get(str).compareTo(tick) < 0 ){
                TicketsHashTable.put(str,tick);
            }
        }else {
            System.out.println("In HashTable isn't exist value with this key");
        }
    }
}
