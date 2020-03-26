package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import static ru.ifmo.se.Main.TicketsHashTable;


/** Print sorted collection*/
public class PrintDescending implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Enumeration enumeration = TicketsHashTable.elements();

        Ticket [] TickArray = new Ticket[TicketsHashTable.size()];
        int i = 0;
        while (enumeration.hasMoreElements()) {
            TickArray[i] = (Ticket) enumeration.nextElement();
            i++;
        }

        Ticket tickOne;
        Ticket tickTwo;

        for (int j = 0; j < TickArray.length - 1; j++) {
            tickOne = TickArray[j];
            for (int k = j + 1; k < TickArray.length; k++) {
                tickTwo = TickArray[k];
                if(tickOne.compareTo(tickTwo) > 0){
                    Ticket t = TickArray[j];
                    TickArray[j] = TickArray[k];
                    TickArray[k] = t;
                }
            }
        }

        for (i = 0; i < TickArray.length; i++) {
            TickArray[i].writeTicket();
        }
    }
}
