package UI.Commandes;

import UI.AbstractCommand;
import Web.Command;

import java.io.IOException;
import java.util.ArrayList;

import static Starter.ClientMain.receiver;

public class Show extends AbstractCommand {
    @Override
    public void check(String command, String arg) {

        super.check(command,arg);
    }


    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            int cs = (int) com.getSecondArgument();
            System.out.println(cs);
            ArrayList<Command> ac = new ArrayList<>();
            for (int i = 0; i < cs; i++) {
                try {
                    ac.add(receiver.receive());
                }catch (IOException e) {
                    System.out.println("Server doesn't answer");
                    return false;
                }
            }
            if (ac.size() > 0){
                printSorted(ac, cs);
            }else {
                System.out.println("There aren't any Tickets");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
