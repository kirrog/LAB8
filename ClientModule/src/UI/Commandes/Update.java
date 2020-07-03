package UI.Commandes;

import Collection.Ticket;
import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class Update extends AbstractCommand {

    int id = -1;
    Ticket ticket;

    public void setArgs(int id, Ticket tick){
        this.id = id;
        ticket = tick;
    }

    @Override
    public void check(String command, String arg) {

        this.command.setSecondArgument(id);
        this.command.setThirdArgument(ticket);
        super.check(command, arg);
    }


    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            if ((int) com.getSecondArgument() == 0) {
                CommandFormer.answer = "Updated!";
            } else {
                CommandFormer.answer = "Can't find!";
                ticket = null;
            }

            return true;
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return ticket;
    }
}
