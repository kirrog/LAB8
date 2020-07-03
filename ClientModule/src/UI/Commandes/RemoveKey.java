package UI.Commandes;

import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class RemoveKey extends AbstractCommand {



    @Override
    public void check(String command, String arg) {
        this.command.setFirstArgument(arg);
        super.check(command,arg);
    }

    int res = -1;

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            res = (int)(com.getSecondArgument());
            if(res == 0){
                CommandFormer.answer = "Ticket Deleted!";
                return true;
            }
            return false;

        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return res;
    }
}
