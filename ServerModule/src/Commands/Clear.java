package Commands;

import Starter.Main;
import Web.Command;

import java.util.ArrayList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** This class clear all collection*/
public class Clear extends AbstractCommand {

    public Clear(){
        name = "clear";
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        TicketsHashTable.clear();
        System.out.println("Cleared");
    }

    @Override
    public void exe(){
        TicketsHashTable.clear();
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands){
        com.setNameOfCommand("clear");
        com.setFirstArgument("Collection cleared");
        Main.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        return;
    }
}
