package Commands;


import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.ArrayList;
import java.util.Scanner;


/** Remove all elements in collection with higher key*/
public class RemoveGreaterKey extends AbstractCommand {

    public RemoveGreaterKey(ThreadResurses threadResurses){
        name = "remove_greater_key";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        tr.getStreamT().forEach((t)-> {
            if(t.getKey().compareTo(string) > 0){
                tr.removeT(t, t.getKey());
            }
        });
    }

    @Override
    public void exe() {
        int i = tr.ticketsList.size();
        String string = com.getFirstArgument();
        tr.getStreamT().forEach((t)-> {
            if(t.getKey().compareTo(string) > 0){
                tr.removeT(t, t.getKey());
            }
        });
        i -= tr.ticketsList.size();
        com.setFirstArgument("Deleted: " + i + " Tickets");
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
