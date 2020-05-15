package Commands;

import DataBase.ThreadResurses;
import DataBase.TicketOwner;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;

public class Register extends AbstractCommand{

    private TicketOwner tow;

    public Register() {
        name = "register";
    }

    public Register(ThreadResurses threadResurses) {
        name = "register";
        tr = threadResurses;
    }

    @Override
    public void exe() {
        tow = (TicketOwner) com.getFourthArgument();
        tr.putO(tow);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        com.setFirstArgument("You've regestered with name '" + tow.getName() + "'");
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
        tr.putO(t);
    }
}
