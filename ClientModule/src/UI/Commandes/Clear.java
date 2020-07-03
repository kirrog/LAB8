package UI.Commandes;

import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;


public class Clear extends AbstractCommand {

    private Command command;

    @Override
    public void check(String command, String arg) {
        super.check(command,arg);
    }

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            CommandFormer.answer = com.getFirstArgument();
            return true;
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult(){
        return null;
    }

}
