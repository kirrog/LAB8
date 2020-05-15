package Commands;


import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class remove all elements which lower then element
 */
public class RemoveLower extends AbstractCommand {

    public RemoveLower() {
        name = "remove_lower";
    }

    public RemoveLower(ThreadResurses threadResurses) {
        name = "remove_lower";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Ticket tick = eCla.getTicket();
        tr.getStreamT().filter(ticket -> ticket.compareTo(tick) < 0).forEach(ticket -> tr.removeT(ticket, ticket.getKey()));
    }

    @Override
    public void exe() {
        Ticket tick = (Ticket) com.getThirdArgument();
        int i = tr.ticketsList.size();
        tr.getStreamT().filter(ticket -> ticket.compareTo(tick) < 0).forEach(ticket -> tr.removeT(ticket, ticket.getKey()));
        i -= tr.ticketsList.size();
        com.setFirstArgument("Deleted " + i + " Tickets");
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        com.setThirdArgument(new TicketReader(scanner, true).readTicket());
    }
}
