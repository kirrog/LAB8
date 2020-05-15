package Commands;

import DataBase.ThreadResurses;
import DataBase.TicketOwner;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangeRegister extends AbstractCommand{

    TicketOwner tow;
    boolean registred;

    public ChangeRegister() {
        name = "change_register";
    }

    public ChangeRegister(ThreadResurses threadResurses) {
        name = "change_register";
        tr = threadResurses;
    }

    @Override
    public void exe() {
        tow = (TicketOwner) com.getFourthArgument();
        int id = tr.ticketOwner.getId();
        tow.setId(id);
        if(tr.updateO(tow)){
            com.setFirstArgument("Changed");
        }else{
            com.setFirstArgument("Mistake");
        }
    }

    @Override
    public void send(ArrayList<Command> commands) {
        if(registred){
            com.setFirstArgument("You've re regestered with name '" + tow.getName() + "'");
        }else {
            com.setFirstArgument("Something wrong with your login! " + com.getFirstArgument());
        }
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        TicketOwner t = new TicketReader(scanner,true).getTicketOwner();
        if(t == null){
            com.setFirstArgument("Wrong Ticket owner in file!");
            return;
        }
        com.setFourthArgument(t);
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        tow = (TicketOwner) eCla.getTicketOwner();
        int id = tr.ticketOwner.getId();
        tow.setId(id);
        if(tr.updateO(tow)){
            System.out.println("Changed");
        }else{
            System.out.println("Mistake");
        }
    }
}
