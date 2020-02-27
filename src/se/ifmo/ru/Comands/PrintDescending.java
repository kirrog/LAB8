package se.ifmo.ru.Comands;


import se.ifmo.ru.Collection.Ticket;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import static se.ifmo.ru.Main.TicketsHashTable;

public class PrintDescending implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Set set = TicketsHashTable.entrySet();
        Iterator iter = set.iterator();
        Ticket [] TickArray = new Ticket[TicketsHashTable.size()];
        int i = 0;
        while (iter.hasNext()) {
            TickArray[i] = (Ticket) iter.next();
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
            ExeClass.writeTicket(TickArray[i]);
        }
    }
}
