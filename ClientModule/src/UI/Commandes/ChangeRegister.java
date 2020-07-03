package UI.Commandes;

import DataBase.TicketOwner;
import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class ChangeRegister extends AbstractCommand {

    TicketOwner ticketOwner;

    public void setTOwner(TicketOwner tOwner){
        ticketOwner = tOwner;
    }

    @Override
    public void check(String command, String arg) {
        this.command.setFourthArgument(ticketOwner);
        super.check(command, arg);
    }

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            if (((int) com.getSecondArgument()) == 0 ) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return null;
    }
}
