package Commands;

import DataBase.ThreadResurses;
import DataBase.TicketOwner;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangeRegister extends AbstractCommand{

    public ChangeRegister(ThreadResurses threadResurses) {
        name = "change_register";
        tr = threadResurses;
    }

    @Override
    public void exe() {
        TicketOwner tow = (TicketOwner) com.getFourthArgument();
        TicketOwner towF = tr.ticketOwner;
        int Id = towF.getId();
        String mail = towF.getMail();
        towF.setMail(tow.getMail());
        if(tr.updateO(towF)){
            com.setFirstArgument("You've changed your mail '" + towF.getName() + "'");
        }else{
            com.setFirstArgument("Something wrong with your login! " + com.getFirstArgument());
            towF.setId(Id);
            towF.setMail(mail);
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        TicketOwner t = new TicketReader(scanner,true).getTicketOwner(true,false);
        if(t == null){
            com.setFirstArgument("Wrong Ticket owner in file!");
            return;
        }
        com.setFourthArgument(t);
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        TicketOwner tow = eCla.getTicketOwner(true,false);
        int id = tr.ticketOwner.getId();
        tow.setId(id);
        if(tr.updateO(tow)){
            System.out.println("Changed");
        }else{
            System.out.println("Mistake");
        }
    }
}
