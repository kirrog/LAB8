package UI.Commandes;

import Collection.Ticket;
import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class Insert extends AbstractCommand {

    public void setArg(Ticket t){
        ticket = t;
    }

    Ticket ticket;

    @Override
    public void check(String command, String arg) {
        this.command.setFirstArgument(ticket.getKey());
        this.command.setThirdArgument(ticket);
        super.check(command,arg);
    }


    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            CommandFormer.answer = com.getFirstArgument();
            if((int)(com.getSecondArgument()) == 0){
                ticket = (Ticket) com.getThirdArgument();
                return true;
            }else {
                ticket = null;
                return false;
            }
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
