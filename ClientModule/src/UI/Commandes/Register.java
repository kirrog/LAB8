package UI.Commandes;

import UI.AbstractCommand;
import UI.TicketReader;
import WebRes.Command;

import java.io.IOException;
import java.util.Scanner;

import static Starter.ClientMain.receiver;

public class Register extends AbstractCommand  {

    @Override
    public void check(String command, String arg) {
        this.command.setFirstArgument(arg);
        this.command.setFourthArgument(new TicketReader(new Scanner(System.in),false).getTicketOwner(false));
        super.check(command,arg);
    }

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            System.out.println("Get result of registration!");
            System.out.println(com.getFirstArgument());
            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
