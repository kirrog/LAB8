package se.ifmo.ru.Comands;


import se.ifmo.ru.Collection.Ticket;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import static se.ifmo.ru.Main.TicketsHashTable;

public class Update implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        int Id=0;
        while (true) {
            try {
                System.out.println("Enter Id: ");
                Id = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                continue;
            }
            if (Id > 0) {
                break;
            } else {
                System.out.println("Id must be more then 0!");
            }
        }

        Enumeration enums = TicketsHashTable.keys();
        boolean find = false;
        while (enums.hasMoreElements()) {
            String str = (String) enums.nextElement();
            Ticket tick = ( TicketsHashTable.get(string));
            if(tick.getId() == Id){

                TicketsHashTable.put(str,eCla.readTicket());
                find = true;
                break;
            }
        }
        if (!find){
            System.out.println("Didn't find object with this id: " + Id);
        }

    }
}
