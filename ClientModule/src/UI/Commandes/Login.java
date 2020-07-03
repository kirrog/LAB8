package UI.Commandes;

import DataBase.TicketOwner;
import GUI.CommandFormer;
import UI.AbstractCommand;
import UI.Terminal;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class Login extends AbstractCommand {

    TicketOwner ticketOwner;

    public void setTOwner(TicketOwner tOwner) {
        ticketOwner = tOwner;
    }

    @Override
    public void check(String command, String arg) {
        this.command.setFourthArgument(ticketOwner);
        super.check(command, arg);
    }

    int result = -4;

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            System.out.println(com);
            if ((com.getSecondArgument() != null) && ((int) com.getSecondArgument() == -128)) {
                Terminal.logined = true;
                result = 0;
                CommandFormer.answer = com.getFirstArgument();
                return true;
            } else if ((int) com.getSecondArgument() == -1) {
                result = -2;
            } else {
                result = -1;
            }
            CommandFormer.answer = com.getFirstArgument();
            return false;
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
