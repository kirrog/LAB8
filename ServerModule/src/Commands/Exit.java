package Commands;

import DataBase.ThreadResurses;
import ServerThreads.ProcessThread;

import java.util.Scanner;


/**
 * This class ends program without save
 */
public class Exit extends AbstractCommand {

    public Exit() {
        name = "exit";
    }

    public Exit(ThreadResurses threadResurses) {
        name = "exit";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Something goes wrong!!!");
    }

    @Override
    public void exe() {
        ((ProcessThread) Thread.currentThread()).sUI.setHaveClient(false);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {}
}
