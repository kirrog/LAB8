package Commands;


import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class compare in element and if bigger change it at key
 */
public class Update extends AbstractCommand {

    public Update(ThreadResurses threadResurses) {
        name = "update";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        int Id;
        try {
            Id = Integer.parseInt(string);
            if (Id > 0) {
                Ticket tick = null;
                if (tr.getStreamT().anyMatch(t -> t.getId() == Id)) {
                    tick = tr.getStreamT().filter(t -> t.getId() == Id).findFirst().get();
                }
                if (!(tick == null)) {
                    System.out.println(tick.getKey());
                    Ticket ticker = eCla.getTicket();
                    if (ticker == null) {
                        System.out.println("Null Ticket entered");
                    } else {
                        ticker.setTowner(tr.ticketOwner);
                        tr.updateT(ticker, tick);
                    }
                } else {
                    System.out.println("Didn't find object with this id: " + Id);
                }
            } else {
                System.out.println("Id must be more then 0!");
            }
        } catch (java.lang.NumberFormatException | NullPointerException e) {
            System.out.println("Wrong type of variable");
        }
    }


    @Override
    public void exe() {
        int Id = (int) com.getSecondArgument();
        if (tr.getStreamT().noneMatch(t -> t.getId() == Id)) {
            com.setFirstArgument("Didn't find object with this id: " + Id);
        } else {
            Ticket ticket = tr.getStreamT().filter(t -> t.getId() == Id).findFirst().get();
            if(ticket.getTowner().equals(tr.ticketOwner)){
                Ticket ticker = (Ticket) com.getThirdArgument();
                ticker.setId(Id);
                tr.updateT(ticker, ticket);
                com.setFirstArgument("Updated!");
            }else {
                com.setFirstArgument("You aren't owner of ticket, owner is: " + ticket.getTowner().getName());
            }
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
        int i = Integer.parseInt(str);
        com.setSecondArgument(i);
        com.setThirdArgument(new TicketReader(scanner, true).readTicket());
    }
}
