package Commands;


import Starter.Main;
import Web.Command;

import java.util.ArrayList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** Remove element on collection by key*/
public class RemoveKey extends AbstractCommand {

    public RemoveKey(){
        name = "remove_key";
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        if(TicketsHashTable.containsKey(string)){
            TicketsHashTable.remove(string);
        }else {
            System.out.println("Can't find element with this key!");
        }

    }

    @Override
    public void exe() {
        String string = com.getFirstArgument();
        if(TicketsHashTable.containsKey(string)){
            TicketsHashTable.remove(string);
            com.setFirstArgument("Deleted!");
        }else {
            com.setFirstArgument("Can't find element with this key!");
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        Main.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        com.setFirstArgument(str);
    }
}
