package Commands;


import DataBase.ThreadResurses;
import WebRes.Command;
import WriteInOut.ServerUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class execute file script
 */
public class ExecuteScript extends AbstractCommand {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ExecuteScript.class);

    public static int numberOfProceses = 0;

    public ExecuteScript(ThreadResurses threadResurses){
        name = "execute_script";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        try {
            File file = new File(string);
            if (file.exists() & file.isFile() & file.canRead() & file.canExecute()) {
                ExeClass exeClass = new ExeClass(string,tr);
                numberOfProceses++;
                exeClass.start();
                numberOfProceses--;
            } else {
                if (!file.exists()) {
                    System.out.println("This doesn't exists");
                } else if (!file.isFile()) {
                    System.out.println("It isn't file");
                } else if (!file.canRead()) {
                    System.out.println("Can't read it");
                } else if (!file.canExecute()) {
                    System.out.println("Can't execute it");
                }
            }
        } catch (StackOverflowError error) {
            log.info("Bad recursion: " + numberOfProceses + " proc.");
        }
    }

    @Override
    public void exe() {
        String str = com.getFirstArgument();
        try {
            File file = new File(str);
            if (file.exists() & file.isFile() & file.canRead() & file.canExecute()) {

                ServerUI serverUI = new ServerUI(tr);

                numberOfProceses++;
                try {
                    serverUI.startFromScript(str, com);
                } catch (NullPointerException e) {
                    if (numberOfProceses > 1) {
                        numberOfProceses--;
                        throw new NullPointerException();
                    }else {
                        log.info("Script killed, because of miss message: " + numberOfProceses);

                    }
                }
                numberOfProceses--;
            } else {
                if (!file.exists()) {
                    com.setFirstArgument(com.getFirstArgument() + "This doesn't exists");
                } else if (!file.isFile()) {
                    com.setFirstArgument(com.getFirstArgument() + "It isn't file");
                } else if (!file.canRead()) {
                    com.setFirstArgument(com.getFirstArgument() + "Can't read it");
                } else if (!file.canExecute()) {
                    com.setFirstArgument(com.getFirstArgument() + "Can't execute it");
                }
            }
        } catch (StackOverflowError error) {
            com.setFirstArgument(com.getFirstArgument() + "Bad recursion: " + numberOfProceses + " proc.");
            log.info("Bad recursion: " + numberOfProceses + " proc.");
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        com.setFirstArgument(str);
    }


}
