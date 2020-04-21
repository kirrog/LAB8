package UI.Commandes;

import UI.AbstractCommand;
import UI.TicketReader;
import Web.Command;

import java.io.IOException;
import java.util.Scanner;

import static Starter.ClientMain.receiver;

public class Update extends AbstractCommand {
    @Override
    public void check(String command, String arg) {
        int num = 0;
        try {
            num = Integer.parseInt(arg);
            if (num > 0) {

            } else {
                System.out.println("Wrong sign");
                num = getId();
            }
        } catch (Exception e) {
            System.out.println("Wrong variable");
            num = getId();
        }
        this.command.setSecondArgument(num);
        this.command.setThirdArgument(new TicketReader(new Scanner(System.in), false).getTicket());
        super.check(command, arg);
    }


    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            if ((int) com.getSecondArgument() == 1) {
                System.out.println("Updated!");
            } else {
                System.out.println("Can't find!");
            }

            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
