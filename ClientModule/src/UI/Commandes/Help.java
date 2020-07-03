package UI.Commandes;

import GUI.CommandFormer;
import UI.AbstractCommand;
import WebRes.Command;

import java.io.IOException;

import static Starter.ClientMain.receiver;

public class Help extends AbstractCommand {
    @Override
    public void check(String command, String arg) {
        super.check(command,arg);
    }


    @Override
    public boolean receive() {
        try {
            Command com = receiver.receive();
            java.lang.String help =  com.getFirstArgument();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html>");
            for(java.lang.String str: help.split("\n")){
                stringBuilder.append(str + "<br>");
            }
            stringBuilder.append("</html>");

            CommandFormer.answer = stringBuilder.toString();
            return true;
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            return false;
        }
    }

    @Override
    public Object getResult() {
        return null;
    }
}
