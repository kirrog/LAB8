package Commands;

import DataBase.ThreadResurses;
import DataBase.TicketOwner;
import ServerThreads.ProcessThread;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;

public class Login extends AbstractCommand {

    public Login() {
        name = "login";
    }

    public Login(ThreadResurses threadResurses) {
        name = "login";
        tr = threadResurses;
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        TicketOwner t = new TicketReader(scanner,true).getTicketOwner();
        com.setSecondArgument(t.getId());
        com.setFourthArgument(t);
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        TicketOwner t = eCla.getTicketOwner();
        if(tr.getStreamO().filter(te -> te.getName().equals(t.getName())).findFirst().isPresent()){
            System.out.println("You've logined as " + t.getName());
            eCla.logined = true;
            tr.ticketOwner = t;
        }
    }

    @Override
    public void exe() {
        TicketOwner t = (TicketOwner) com.getFourthArgument();
        if(tr.getStreamO().filter(te -> te.getName().equals(t.getName())).findFirst().isPresent()){
            com.setFirstArgument("You've logined as " + t.getName());
            com.setSecondArgument(-128);
            ((ProcessThread)Thread.currentThread()).sUI.logined = true;
            tr.ticketOwner = t;
        }
    }
}
