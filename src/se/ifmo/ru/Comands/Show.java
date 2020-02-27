package se.ifmo.ru.Comands;


import se.ifmo.ru.Collection.*;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import static se.ifmo.ru.Main.TicketsHashTable;

public class Show implements Execute {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {

        Set set = TicketsHashTable.entrySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            ExeClass.writeTicket((Ticket) iter.next());
        }
    }
}
