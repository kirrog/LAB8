package Commands;


import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.*;



/** Print sorted collection*/
public class PrintDescending extends AbstractCommand {

    public PrintDescending(ThreadResurses threadResurses){
        name = "print_descending";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        tr.getStreamT().sorted(Comparator.naturalOrder()).forEachOrdered(Ticket::writeTicket);
    }

    @Override
    public void exe() {
        ArrayList<Command> commands = new ArrayList<>();
        if(tr.ticketsList.size() > 0){
            tr.getStreamT()
                    .forEach(ticket->{
                        Command c = new Command();
                        c.setNameOfCommand("print_descending");
                        c.setFirstArgument(ticket.getKey());
                        c.setThirdArgument(ticket);
                        commands.add(c);
                    });
            this.sort(commands);
        }
        send(commands);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {}
}
