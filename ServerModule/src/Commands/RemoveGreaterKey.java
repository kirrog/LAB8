package Commands;


import Starter.Main;
import Web.Command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** Remove all elements in collection with higher key*/
public class RemoveGreaterKey extends AbstractCommand {

    public RemoveGreaterKey(){
        name = "remove_greater_key";
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Enumeration enums = TicketsHashTable.keys();
        while (enums.hasMoreElements()){
            String keyE = (String) enums.nextElement();
            if(string.compareTo(keyE) < 0){
                TicketsHashTable.remove(keyE);
            }
        }
    }

    @Override
    public void exe() {
        Enumeration enums = TicketsHashTable.keys();
        int i = 0;
        String string = com.getFirstArgument();
        while (enums.hasMoreElements()){
            String keyE = (String) enums.nextElement();
            if(string.compareTo(keyE) < 0){
                TicketsHashTable.remove(keyE);
                i++;
            }
        }
        com.setThirdArgument("Deleted: " + i + " Tickets");
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
