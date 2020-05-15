package UI.Commandes;

import UI.AbstractCommand;
import UI.TicketReader;
import WebRes.Command;

import java.io.IOException;
import java.util.Scanner;

import static Starter.ClientMain.receiver;

public class ReplaceIfLower extends AbstractCommand {
    @Override
    public void check(String command, String arg) {
        this.command.setFirstArgument(arg);
        this.command.setThirdArgument(new TicketReader(new Scanner(System.in),false).getTicket());
        super.check(command,arg);
    }

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            if ((int) com.getSecondArgument() == 1) {
                System.out.println("Replaced!");
            } else if((int) com.getSecondArgument() == 2) {
                System.out.println("It isn't lower!");
            }else {
                System.out.println("Can't find!");
            }

            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
