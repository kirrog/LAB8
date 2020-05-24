package Commands;


import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.ArrayList;
import java.util.Scanner;


/** Remove element on collection by key*/
public class RemoveKey extends AbstractCommand {

    public RemoveKey(ThreadResurses threadResurses){
        name = "remove_key";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        if(tr.ticketsList.containsKey(string)){
            tr.removeT(tr.ticketsList.get(string),string);
        }else {
            System.out.println("Can't find element with this key!");
        }

    }

    @Override
    public void exe() {
        String string = com.getFirstArgument();
        if(tr.ticketsList.containsKey(string)){
            tr.removeT(tr.ticketsList.get(string),string);
            com.setFirstArgument("Deleted!");
        }else {
            com.setFirstArgument("Can't find element with this key!");
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
    }
}
