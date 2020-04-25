package Commands;


import Web.Command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** This class show all content in HashTable*/
public class Show extends AbstractCommand {

    public Show(){
        name = "show";
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        Enumeration enums = TicketsHashTable.keys();
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }
        stringLinkedList.stream().forEach(ticket -> {
            System.out.println("Key: " + ticket);
            TicketsHashTable.get(ticket).writeTicket();
        });
    }

    @Override
    public void exe() {
        Enumeration enums = TicketsHashTable.keys();
        ArrayList<String> stringLinkedList = new ArrayList<>();
        ArrayList<Command> commands = new ArrayList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }
        stringLinkedList.stream()
                .forEach(key->{
                    Command c = new Command();
                    c.setNameOfCommand("show");
                    c.setFirstArgument(key);
                    c.setThirdArgument(TicketsHashTable.get(key));
                    commands.add(c);
                });
        this.sort(commands);
        send(commands);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        return;
    }
}
