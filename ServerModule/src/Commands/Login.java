package Commands;

import DataBase.ThreadResurses;
import DataBase.TicketOwner;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Arrays;
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
    protected void setArgs(String str, Scanner scanner) {
        TicketOwner t = new TicketReader(scanner,true).getTicketOwner(false, true);
        com.setSecondArgument(t.getId());
        com.setFourthArgument(t);
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        TicketOwner t = eCla.getTicketOwner(false, true);
        if(tr.getStreamO().anyMatch(te -> te.getName().equals(t.getName()))){
            byte [] password = tr.getStreamO().filter(te -> te.getName().equals(t.getName())).findFirst().get().getPassword();
            if(Arrays.equals(password, t.getPassword())){
                System.out.println("You've logined as " + t.getName());
                eCla.logined = true;
                tr.ticketOwner = t;
            }else {
                System.out.println("Wrong password!");
            }
        }else {
            System.out.println("Wrong login!");
        }
    }

    @Override
    public void exe() {
        TicketOwner t = (TicketOwner) com.getFourthArgument();
        if(tr.getStreamO().anyMatch(te -> te.getName().equals(t.getName()))){
            TicketOwner tt = tr.getStreamO().filter(te -> te.getName().equals(t.getName())).findFirst().get();
            byte [] password = tt.getPassword();
            if(Arrays.equals(password, t.getPassword())){
                com.setFirstArgument("You've logined as " + t.getName());
                com.setSecondArgument(-128);
                tr.serverUI.logined = true;
                tr.ticketOwner = tt;
            }else {
                com.setFirstArgument("Wrong password!");
            }
        }else {
            com.setFirstArgument("Wrong login!");
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }
}
