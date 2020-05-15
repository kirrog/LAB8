package UI.Commandes;

import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class ExecuteScript extends AbstractCommand {

    @Override
    public void check(String command, String arg) {
        if(arg == null){
            System.out.println("Bad argument");
            return;
        }
        this.command.setFirstArgument(arg);
        super.check(command,arg);
    }

    @Override
    public boolean receive() {
        try {
            try{
                String str = "";
                while (!str.equals("exit")){
                    str = this.send();
                }
            }catch (IOException | NullPointerException e){
                System.out.println("Problem with script");
                return false;
            }
            Command com = receiver.receive();
            System.out.println(com.getFirstArgument());
            return true;
        } catch (IOException e) {
            System.out.println("Server doesn't answer");
            return false;
        }
    }
}
