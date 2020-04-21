package Commands;


import Collection.Ticket;
import Web.Command;

import java.util.*;

import static Starter.Main.TicketsHashTable;


/** Print sorted collection*/
public class PrintDescending extends AbstractCommand {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
//        Enumeration enumeration = TicketsHashTable.elements();
//
//        Ticket [] TickArray = new Ticket[TicketsHashTable.size()];
//        int i = 0;

        Enumeration enums = TicketsHashTable.keys();
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (; enums.hasMoreElements(); ) {
            stringLinkedList.add((String) enums.nextElement());
        }

        stringLinkedList.stream().map(key -> TicketsHashTable.get(key)).sorted(Ticket::compareTo).forEachOrdered(Ticket::writeTicket);

//        while (enumeration.hasMoreElements()) {
//            TickArray[i] = (Ticket) enumeration.nextElement();
//            i++;
//        }
//
//        Ticket tickOne;
//        Ticket tickTwo;
//
//        for (int j = 0; j < TickArray.length - 1; j++) {
//            tickOne = TickArray[j];
//            for (int k = j + 1; k < TickArray.length; k++) {
//                tickTwo = TickArray[k];
//                if(tickOne.compareTo(tickTwo) > 0){
//                    Ticket t = TickArray[j];
//                    TickArray[j] = TickArray[k];
//                    TickArray[k] = t;
//                }
//            }
//        }
//
//        for (i = 0; i < TickArray.length; i++) {
//            TickArray[i].writeTicket();
//        }
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
                    c.setNameOfCommand("print_descending");
                    c.setFirstArgument(key);
                    c.setThirdArgument(TicketsHashTable.get(key));
                    commands.add(c);
                });
        this.sort(commands);
        send(commands);
    }
}
