package ru.ifmo.se.Commands;


import java.util.Enumeration;
import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class show all content in HashTable*/
public class Show implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Start execution show");
        Enumeration enums = TicketsHashTable.keys();
        while (enums.hasMoreElements()) {
            ExeClass.writeTicket(TicketsHashTable.get( enums.nextElement()));
        }
    }
}
