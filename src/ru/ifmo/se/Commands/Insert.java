package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class insert element by key*/
public class Insert implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        String key = string;
        if(key.isEmpty()){
            System.out.println("Need a key");
            return;
        }
        Ticket tick = eCla.getTicket();

        try{
            if(tick == null){
                System.out.println("Bad ticket");
            }else {
                TicketsHashTable.put(key,tick);
            }
        }catch (NullPointerException e){
            System.out.println("Wrong ticket");
        }

    }
}
