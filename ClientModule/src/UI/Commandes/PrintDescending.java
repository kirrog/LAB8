package UI.Commandes;

import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;
import java.util.ArrayList;

import static Starter.ClientMain.receiver;

public class PrintDescending extends AbstractCommand {
    @Override
    public void check(String command, String arg) {

        super.check(command,arg);
    }

    ArrayList<Command> ac = new ArrayList<>();

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            int cs = (int) com.getSecondArgument();
            ac = new ArrayList<>();
            for (int i = 0; i < cs; i++) {
                try {
                    ac.add(receiver.receive());
                }catch (IOException e) {
                    CommandFormer.setServerStatus(0);
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return ac;
    }
}
