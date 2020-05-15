package UI.Commandes;

import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class Info extends AbstractCommand {
    @Override
    public void check(String command, String arg) {

        super.check(command,arg);
    }


    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            System.out.println(com.getFirstArgument());
            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
