package UI.Commandes;

import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static Starter.ClientMain.receiver;

public class CheckCondition extends AbstractCommand {

    public static ZonedDateTime currentVersionTime = ZonedDateTime.now();

    @Override
    public void check(String command, String arg) {

        super.check(command,arg);
    }

    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            currentVersionTime = (ZonedDateTime) com.getThirdArgument();
            if("Last version".equals(com.getFirstArgument())){
                return true;
            }else if("Prev version".equals(com.getFirstArgument())){
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
            }
            return false;
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    private ArrayList<Command> ac;

    @Override
    public Object getResult() {
        return ac;
    }

}
