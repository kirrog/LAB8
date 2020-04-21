package WriteInOut;

import Commands.AbstractCommand;
import Commands.Save;
import Starter.Main;
import Web.Command;

import java.io.IOException;
import java.util.Scanner;

public class ServerUI {

    public static Command getCom() {
        return com;
    }

    public static Command com;

    private static boolean haveClient = false;

    public static void start(){
        com = new Command();

        Scanner scan = new Scanner(System.in);
        while (isHaveClient()){
            try {
                if(System.in.available() != 0){
                    String str = scan.nextLine();
                    if(str.equals("exit")){
                        com.setFirstArgument("Server end receiving");
                        com.setNameOfCommand(Main.receiver.receive().getNameOfCommand());
                        Main.sender.send(com);
                        return;
                    }else if(str.equals("save")){
                        new Save().execute(null,null,null);
                        com = Main.receiver.receive();
                        String string = com.getFirstArgument();
                        com.setFirstArgument("Collection saved");
                        Main.sender.send(com);
                        com.setFirstArgument(string);
                        AbstractCommand.replies.get(com.getNameOfCommand()).answer(com);
                    }else {
                        System.out.println("Wrong command");
                    }
                } else {
                    com = Main.receiver.receive();
                    Main.sender.send(com);
                    AbstractCommand.replies.get(com.getNameOfCommand()).answer(com);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isHaveClient() {
        return haveClient;
    }

    public static void setHaveClient(boolean haveClient) {
        ServerUI.haveClient = haveClient;
    }
}
