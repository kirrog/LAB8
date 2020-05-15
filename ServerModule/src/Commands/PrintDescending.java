package Commands;


import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.*;



/** Print sorted collection*/
public class PrintDescending extends AbstractCommand {

    public PrintDescending(){
        name = "print_descending";
    }

    public PrintDescending(ThreadResurses threadResurses){
        name = "print_descending";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
//        Enumeration enumeration = TicketsHashTable.elements();
//
//        Ticket [] TickArray = new Ticket[TicketsHashTable.size()];
//        int i = 0;


        tr.getStreamT().sorted(Comparator.naturalOrder()).forEachOrdered(Ticket::writeTicket);

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
        ArrayList<Command> commands = new ArrayList<>();
        tr.getStreamT()
                .forEach(ticket->{
                    Command c = new Command();
                    c.setNameOfCommand("print_descending");
                    c.setFirstArgument(ticket.getKey());
                    c.setThirdArgument(ticket);
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
