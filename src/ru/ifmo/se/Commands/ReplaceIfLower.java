package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This file change element if it's lower*/
public class ReplaceIfLower implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        if(TicketsHashTable.containsKey(string)){
            Ticket tick = eCla.getTicket();
            if (TicketsHashTable.get(string).compareTo(tick) < 0 ){
                TicketsHashTable.put(string,tick);
            }
        }else {
            System.out.println("In HashTable isn't exist value with this key");
        }
    }
}
