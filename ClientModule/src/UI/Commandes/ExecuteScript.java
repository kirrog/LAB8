package UI.Commandes;

import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class ExecuteScript extends AbstractCommand {

    @Override
    public void check(String command, String arg) {
        if(arg == null){
            return;
        }
        this.command.setFirstArgument(arg);
        super.check(command,arg);
    }

    String result = "";

    @Override
    public boolean receive() {
        try {
            try{
                String str = "";
                while (!str.equals("exit")){
                    str = this.send();
                }
            }catch (IOException | NullPointerException e){
                CommandFormer.setServerStatus(3);
                return false;
            }
            Command com = receiver.receive();
            result += com.getFirstArgument() + "\n";
            return true;
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return result;
    }
}
