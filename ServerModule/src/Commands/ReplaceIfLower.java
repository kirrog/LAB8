package Commands;


import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * This file change element if it's lower
 */
public class ReplaceIfLower extends AbstractCommand {

    public ReplaceIfLower(ThreadResurses threadResurses){
        name = "replace_if_lower";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        if (tr.ticketsList.containsKey(string)) {
            Ticket tick = eCla.getTicket();
            if (tr.ticketsList.get(string).compareTo(tick) < 0) {
                Ticket ticket = tr.ticketsList.get(string);
                tr.updateT(tick, ticket);
            } else {
                com.setFirstArgument("It's higher!");
            }
        } else {
            System.out.println("In HashTable isn't exist value with this key");
        }
    }

    @Override
    public void exe() {
        String string = com.getFirstArgument();
        if (tr.ticketsList.containsKey(string)) {
            Ticket tick = (Ticket) com.getThirdArgument();
            if (tr.ticketsList.get(string).compareTo(tick) < 0) {
                Ticket ticket = tr.ticketsList.get(string);
                tick.setId((int)ticket.getId());
                tr.updateT(tick, ticket);
            } else {
                com.setFirstArgument("It's higher!");
            }
        } else {
            com.setFirstArgument("In HashTable isn't exist value with this key");
        }
        com.setThirdArgument(null);
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        com.setFirstArgument(str);
        com.setThirdArgument(new TicketReader(scanner,true).readTicket());
    }
}
