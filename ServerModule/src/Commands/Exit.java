package Commands;

import WriteInOut.ServerUI;

import java.util.Scanner;


/** This class ends program without save*/
public class Exit extends AbstractCommand {

    public Exit(){
        name = "exit";
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Something goes wrong!!!");
    }

    @Override
    public void exe() {
        ServerUI.setHaveClient(false);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        return;
    }
}
