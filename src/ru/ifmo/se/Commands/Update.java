package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Enumeration;
import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class compare in element and if bigger change it at key*/
public class Update implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        int Id=0;
        boolean whileTrue = true;

        while (whileTrue) {
            try {
                System.out.print("Enter Id: \n> ");
                Id = scan.nextInt();
                if (Id > 0) {
                    whileTrue = false;
                } else {
                    System.out.println("Id must be more then 0!");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Wrong type of variable");
                scan.nextLine();
            }
        }

        String str = null;
        Ticket ticker = null;
        Enumeration enums = TicketsHashTable.keys();
        boolean find = false;
        while (enums.hasMoreElements()) {
            str = (String) enums.nextElement();
            Ticket tick = ( TicketsHashTable.get(str));
            if(tick.getId() == Id){
                ticker = eCla.getTicket();
                if(ticker == null){
                    System.out.println("Entered ticket is null");
                    return;
                }
                find = true;
                break;
            }
        }
        if (!find){
            System.out.println("Didn't find object with this id: " + Id);
        }else{
            TicketsHashTable.put(str,ticker);
        }
    }
}
