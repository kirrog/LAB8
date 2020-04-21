package Commands;


import Collection.Ticket;
import Starter.Main;
import Web.Command;

import java.util.ArrayList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** This class insert element by key*/
public class Insert extends AbstractCommand {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        String key = string;
        if(key.isEmpty()){
            System.out.println("Need a key");
            return;
        }
        Ticket tick = eCla.getTicket();

        try{
            if(tick == null){
                System.out.println("Bad ticket");
            }else {
                TicketsHashTable.put(key,tick);
            }
        }catch (NullPointerException e){
            System.out.println("Wrong ticket");
        }

    }

    @Override
    public void exe() {
        TicketsHashTable.put(com.getFirstArgument(),(Ticket) com.getThirdArgument());
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        com.setFirstArgument("Ticket inserted with key: " + com.getFirstArgument());
        Main.sender.send(com);
    }

}
