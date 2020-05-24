package Commands;

import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.ArrayList;
import java.util.Scanner;


/** This class clear all collection*/
public class Clear extends AbstractCommand {

    public Clear(ThreadResurses threadResurses){
        name = "clear";
        tr = threadResurses;
    }



    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        tr.clearT();
        System.out.println("Cleared");
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        com.setFirstArgument("");
    }

    @Override
    public void exe(){
        tr.clearT();
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands){
        com.setNameOfCommand("clear");
        com.setFirstArgument("Collection cleared");
        tr.sender.send(com);
    }


}
