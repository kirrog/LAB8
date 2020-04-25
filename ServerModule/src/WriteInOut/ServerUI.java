package WriteInOut;

import Commands.AbstractCommand;
import Commands.Save;
import Starter.Main;
import Web.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ServerUI {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerUI.class);

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
                        log.info("Server end receiving");
                        return;
                    }else if(str.equals("save")){
                        new Save().execute(null,null,null);
                        log.info("Collection saved");
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
                    System.out.println(com.toString());
                    if(com.getNameOfCommand() == null){
                        com.setNameOfCommand("exit");
                    }
                    Main.sender.send(com);
                    AbstractCommand.replies.get(com.getNameOfCommand()).answer(com);
                }
            } catch (IOException e) {
                log.info("", e);
            } catch (NullPointerException e){
                log.info("Null command received", e);
            }
        }
    }

    public void startFromScript(String fileName, Command com) {
        boolean notExit = true;
        int i = 0;

        try (Scanner scan = new Scanner(new File(fileName))) {
            while (notExit) {
                i++;
                if(scan.hasNextLine()) {
                    String command = scan.nextLine();

                    String arguments = "";
                    if (command.contains(" ")) {
                        arguments = command.substring(command.indexOf(" ") + 1);
                        command = command.substring(0, command.indexOf(" "));
                    }
                    if (command.isEmpty()) {
                        com.setFirstArgument(com.getFirstArgument()+"Wrong entering at " + i + " command!");
                        log.info("Wrong entering at " + i + " command!");
                        break;
                    }
                    if (arguments.contains(" ")) {
                        com.setFirstArgument(com.getFirstArgument()+"Too many arguments at " + i + " command!\nWill work only with first");
                        log.info("Too many arguments at " + i + " command!\nWill work only with first");
                        arguments = arguments.substring(0, arguments.indexOf(" "));
                    }

                    if (command.equals("exit") | !scan.hasNextLine()) {
                        notExit = false;
                        Main.receiver.receive();
                        Command command1 = new Command();
                        command1.setNameOfCommand("exit");
                        Main.sender.send(command1);
                    } else {
                        if (AbstractCommand.replies.containsKey(command)) {
                            AbstractCommand.replies.get(command).talk(arguments, scan);
                        } else {
                            com.setFirstArgument(com.getFirstArgument()+"This is not command in file!");
                            log.info("This is not command in file!");
                            i--;
                        }
                    }
                } else {
                    com.setFirstArgument(com.getFirstArgument()+"Script" +  " complete at " + (i-1) +" command!");
                    log.info("Script" +  " complete at " + (i-1) +" command!");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            log.info("Script", e);
        } catch (IOException e) {
            log.info("Try end client script", e);
        }

    }

    public static boolean isHaveClient() {
        return haveClient;
    }

    public static void setHaveClient(boolean haveClient) {
        ServerUI.haveClient = haveClient;
    }
}
