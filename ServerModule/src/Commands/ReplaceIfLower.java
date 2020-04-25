package Commands;


import Collection.Ticket;
import Starter.Main;
import Web.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/**
 * This file change element if it's lower
 */
public class ReplaceIfLower extends AbstractCommand {

    public ReplaceIfLower(){
        name = "replace_if_lower";
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        if (TicketsHashTable.containsKey(string)) {
            Ticket tick = eCla.getTicket();
            if (TicketsHashTable.get(string).compareTo(tick) < 0) {
                TicketsHashTable.put(string, tick);
            }
        } else {
            System.out.println("In HashTable isn't exist value with this key");
        }
    }

    @Override
    public void exe() {
        String string = com.getFirstArgument();
        if (TicketsHashTable.containsKey(string)) {
            Ticket tick = (Ticket) com.getThirdArgument();
            if (TicketsHashTable.get(string).compareTo(tick) < 0) {
                TicketsHashTable.put(string, tick);
            } else {
                com.setFirstArgument("It's higher!");
            }
        } else {
            com.setFirstArgument("In HashTable isn't exist value with this key");
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        Main.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        com.setFirstArgument(str);
        com.setThirdArgument(new TicketReader(scanner,true).readTicket());
    }
}
