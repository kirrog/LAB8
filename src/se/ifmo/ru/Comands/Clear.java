package se.ifmo.ru.Comands;


import java.util.Scanner;

import static se.ifmo.ru.Main.TicketsHashTable;

public class Clear implements Execute {

    @Override
    public void execute(String string, Scanner scan) {
        TicketsHashTable.clear();
        System.out.println("Where are nothing in Hashtable");
    }

}
