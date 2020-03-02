package ru.ifmo.se.Commands;


import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** Remove element on collection by key*/
public class RemoveKey implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        if(TicketsHashTable.containsKey(string)){
            TicketsHashTable.remove(string);
        }else {
            System.out.println("Can't find element with this key!");
        }

    }
}
