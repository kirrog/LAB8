package UI.Commandes;

import UI.AbstractCommand;
import UI.TicketReader;
import WebRes.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Starter.ClientMain.receiver;

public class FilterByVenue extends AbstractCommand {

    @Override
    public void check(String command, String arg) {
        this.command.setThirdArgument(new TicketReader(new Scanner(System.in),false).getVenue());
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
                System.out.println("There aren't any Tickets with this Venue");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
