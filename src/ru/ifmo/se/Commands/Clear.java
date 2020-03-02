package ru.ifmo.se.Commands;


import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class clear all collection*/
public class Clear implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        TicketsHashTable.clear();
        System.out.println("Where are nothing in Hashtable");
    }

}
