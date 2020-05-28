package Commands;


import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;
import WriteInOut.TicketReader;

import java.util.ArrayList;
import java.util.Scanner;


/** This class insert element by key*/
public class Insert extends AbstractCommand {

    public Insert(ThreadResurses threadResurses){
        name = "insert";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        String key = string;
        if(key.isEmpty()){
            System.out.println("Need a key");
            return;
        }
        Ticket tick = eCla.getTicket();
        tick.setKey(key);
        if(tr.ticketsList.containsKey(key)){
            System.out.println("Already exists!");
        }else {
            tick.setTowner(tr.ticketOwner);
            try{
                tr.putT(key,tick);
            }catch (NullPointerException e){
                System.out.println("Wrong ticket");
            }
        }
    }

    @Override
    public void exe() {
        if(tr.ticketsList.containsKey(com.getFirstArgument())){
            com.setFirstArgument("Already exists");
        }else {
            if(tr.putT(com.getFirstArgument(),(Ticket) com.getThirdArgument())){
                com.setFirstArgument("Ticket inserted with key: " + com.getFirstArgument());
            }else {
                com.setFirstArgument("Something wrong with inserting!");
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
        com.setFirstArgument(str);
        Ticket t = new TicketReader(scanner,true).readTicket();
        t.setKey(str);
        com.setThirdArgument(t);
    }

}
