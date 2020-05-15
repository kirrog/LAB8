package UI.Commandes;

import UI.AbstractCommand;
import UI.TicketReader;
import WebRes.Command;

import java.io.IOException;
import java.util.Scanner;

import static Starter.ClientMain.receiver;

public class RemoveLower extends AbstractCommand {
    @Override
    public void check(String command, String arg) {
        this.command.setThirdArgument(new TicketReader(new Scanner(System.in),false).getTicket());
        super.check(command,arg);
    }


    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            System.out.println("Removed " + com.getSecondArgument() + " Tickets");
            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
