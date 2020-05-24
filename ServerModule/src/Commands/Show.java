package Commands;


import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.ArrayList;
import java.util.Scanner;


/** This class show all content in HashTable*/
public class Show extends AbstractCommand {

    public Show(ThreadResurses threadResurses){
        name = "show";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        tr.getStreamT().forEach(ticket -> {
            System.out.println("Key: " + ticket.getKey());
            ticket.writeTicket();
        });
    }

    @Override
    public void exe() {
        ArrayList<Command> commands = new ArrayList<Command>();
        if(tr.ticketsList.size() > 0){
            tr.getStreamT()
                    .forEach(ticket->{
                        Command c = new Command();
                        c.setNameOfCommand("show");
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
