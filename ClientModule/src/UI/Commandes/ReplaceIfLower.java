package UI.Commandes;

import Collection.Ticket;
import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class ReplaceIfLower extends AbstractCommand {

    String key;
    Ticket ticket;

    public void setArt(String key, Ticket ticket){
        this.key = key;
        this.ticket = ticket;
    }

    @Override
    public void check(String command, String arg) {
        this.command.setFirstArgument(key);
        this.command.setThirdArgument(ticket);
        super.check(command,arg);
    }

    int result = 4;

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            result = (int) com.getSecondArgument();
            if (result == 1) {
                CommandFormer.answer = "Replaced!";
                return true;
            } else if(result == 2) {
                CommandFormer.answer ="It isn't lower!";
                return false;
            }else {
                CommandFormer.answer ="Can't find!";
                return false;
            }
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return result;
    }
}
