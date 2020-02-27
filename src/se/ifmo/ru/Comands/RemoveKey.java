package se.ifmo.ru.Comands;


import java.util.Scanner;

import static se.ifmo.ru.Main.TicketsHashTable;

public class RemoveKey implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Enter key: ");
        String str = scan.nextLine();
        if(TicketsHashTable.containsKey(str)){
            TicketsHashTable.remove(str);
        }else {
            System.out.println("Can't find element with this key!");
        }

    }
}
