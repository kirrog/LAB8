package Commands;


import Collection.Ticket;
import Collection.Venue;
import DataBase.ThreadResurses;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;


/** This class print all elements with this venue*/
public class FilterByVenue extends AbstractCommand {

    public FilterByVenue(){
        name = "filter_by_venue";
    }

    public FilterByVenue(ThreadResurses threadResurses){
        name = "filter_by_venue";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Enter venue");
        Venue ven = eCla.getVenue();
        tr.getStreamT()
                .filter(ticket -> ticket.getVenue().equals(ven))
                .forEach(Ticket::writeTicket);
    }

    @Override
    public void exe() {

        Venue ven = (Venue) com.getThirdArgument();
        ArrayList<Command> commands = new ArrayList<>();

        if(tr.getStreamT().noneMatch(ticket -> ticket.getVenue().equals(ven))){
            com.setFirstArgument(com.getFirstArgument() + "None with this venue");
            com.setSecondArgument(0);
            tr.sender.send(com);
        }else {
            tr.getStreamT()
                    .filter(ticket -> ticket.getVenue().equals(ven))
                    .forEach(ticket->{
                        Command c = new Command();
                        c.setNameOfCommand("filter_by_venue");
                        c.setFirstArgument(ticket.getKey());
                        c.setThirdArgument(ticket);
                        commands.add(c);
                    });
            this.sort(commands);
            send(commands);
        }
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        com.setThirdArgument(new TicketReader(scanner, true).readVenue());
    }
}
