package UI.Commandes;

import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class Info extends AbstractCommand {
    @Override
    public void check(String command, String arg) {

        super.check(command,arg);
    }

String str = "";

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            str = com.getFirstArgument();
            return true;
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return str;
    }
}
