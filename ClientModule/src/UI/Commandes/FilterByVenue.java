package UI.Commandes;

import Collection.Venue;
import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;
import java.util.ArrayList;

import static Starter.ClientMain.receiver;

public class FilterByVenue extends AbstractCommand {

    Venue venue;

    public void setArg(Venue venue){
        this.venue = venue;
    }

    @Override
    public void check(String command, String arg) {
        this.command.setThirdArgument(venue);
        super.check(command,arg);
    }



    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            if(com.getFirstArgument() != null){
                System.out.println(com.getFirstArgument());
            }
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

    private ArrayList<Command> ac;

    @Override
    public Object getResult() {
        return ac;
    }
}
