package Commands;

import DataBase.CriptoMaker;
import DataBase.ThreadResurses;
import DataBase.TicketOwner;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;

public class Register extends AbstractCommand{

    private TicketOwner tow;

    public Register(ThreadResurses threadResurses) {
        name = "register";
        tr = threadResurses;
    }

    @Override
    public void exe() {
        tow = (TicketOwner) com.getFourthArgument();
        System.out.println(tow.getName());
        if(tr.getStreamO().anyMatch(ticketOwner -> ticketOwner.getName().equals(tow.getName()))){
            com.setFirstArgument("Where are acc with this name!");
            com.setSecondArgument(-1);
        }else {
            CriptoMaker.saltAndPaper(tow, tow.getSalt());
            if(tr.putO(tow)){
                com.setFirstArgument("You've registered with name '" + tow.getName() + "'");
                com.setSecondArgument(0);
            }else {
                com.setFirstArgument("Problem with base!");
            }
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        TicketOwner t = new TicketReader(scanner,true).getTicketOwner(true,true);
        com.setSecondArgument(t.getId());
        com.setFourthArgument(t);
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        TicketOwner t = eCla.getTicketOwner(true,true);
        if(tr.getStreamO().anyMatch(ticketOwner -> ticketOwner.getName().equals(tow.getName()))){
            System.out.println("Where are acc with this name!");
        }else {
            tr.putO(t);
            System.out.println("You've registered with name '" + t.getName() + "'");
        }

    }
}
