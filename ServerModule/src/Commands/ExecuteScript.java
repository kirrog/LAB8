package Commands;


import Starter.Main;
import Web.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class execute file script
 */
public class ExecuteScript extends AbstractCommand {

    public static int numberOfProceses = 0;

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        try {
            File file = new File(string);
            if (file.exists() & file.isFile() & file.canRead() & file.canExecute()) {
                ExeClass exeClass = new ExeClass(string);
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
            System.out.println("Bad recursion: " + numberOfProceses + " proc.");
        }
    }

    @Override
    public void exe() {
        String str = com.getFirstArgument();
        try {
            File file = new File(str);
            if (file.exists() & file.isFile() & file.canRead() & file.canExecute()) {
                ExeClass exeClass = new ExeClass(str);

                numberOfProceses++;

                exeClass.start();

                numberOfProceses--;

                com.setFirstArgument(com.getFirstArgument() + ": script end his job");

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
        }
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        Main.sender.send(com);
    }
}
