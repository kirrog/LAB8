package Commands;


import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;


/**
 * This class print sorted elements by field
 */
public class PrintFieldDescending extends AbstractCommand {

    public PrintFieldDescending(ThreadResurses threadResurses){
        name = "print_field_descending_type";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {

        if (tr.ticketsList.size() == 0) {
            System.out.println("Empty table");
            return;
        }
        System.out.println("id");
        System.out.println("name");
        System.out.println("coordinates.x");
        System.out.println("coordinates.y");
        System.out.println("creation date");
        System.out.println("prise");
        System.out.println("comment");
        System.out.println("refundable");
        System.out.println("type");
        System.out.println("venue.id");
        System.out.println("venue.name");
        System.out.println("venue.capacity");
        System.out.println("venue.type");
        System.out.println("venue.address.zipCode");
        System.out.println("venue.address.town.x");
        System.out.println("venue.address.town.y");
        System.out.println("venue.address.town.z");
        System.out.println("venue.address.town.name");

        Stream<Ticket> tickets = new ArrayList<Ticket>().stream();
        switch (string) {
            case "id":
                tickets = writeField(0);
                break;
            case "name":
                tickets = writeField(1);
                break;
            case "coordinates.x":
                tickets = writeField(2);
                break;
            case "coordinates.y":
                tickets = writeField(3);
                break;
            case "creation date":
                tickets = writeField(4);
                break;
            case "prise":
                tickets = writeField(5);
                break;
            case "comment":
                tickets = writeField(6);
                break;
            case "refundable":
                tickets = writeField(7);
                break;
            case "type":
                tickets = writeField(8);
                break;
            case "venue.id":
                tickets = writeField(9);
                break;
            case "venue.name":
                tickets = writeField(10);
                break;
            case "venue.capacity":
                tickets = writeField(11);
                break;
            case "venue.type":
                tickets = writeField(12);
                break;
            case "venue.address.zipCode":
                tickets = writeField(13);
                break;
            case "venue.address.town.x":
                tickets = writeField(14);
                break;
            case "venue.address.town.y":
                tickets = writeField(15);
                break;
            case "venue.address.town.z":
                tickets = writeField(16);
                break;
            case "venue.address.town.name":
                tickets = writeField(17);
                break;
            default:
                System.out.println("There is not field with this name!");
                break;
        }
        tickets.forEachOrdered(Ticket::writeTicket);
    }

    private Stream<Ticket> writeField(int field) {
        return tr.getStreamT().sorted((t1, t2)->t1.compByField(field,t2));
    }

    @Override
    public void exe() {
        ArrayList<Command> commands = new ArrayList<>();
        if(tr.ticketsList.size() > 0){
            writeField((int)com.getSecondArgument())
                    .forEachOrdered(t->{
                        Command c = new Command();
                        c.setNameOfCommand("print_field_descending_type");
                        c.setFirstArgument(t.getKey());
                        c.setThirdArgument(t);
                        commands.add(c);
                    });
        }
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).setSecondArgument(i);
        }
        send(commands);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {
        int i = chooseType(str);
        com.setSecondArgument(i);
    }

    private int chooseType(String str){
        switch (str) {
            case "id":
                return 0;
            case "name":
                return 1;
            case "coordinates.x":
                return 2;
            case "coordinates.y":
                return 3;
            case "creation date":
                return 4;
            case "prise":
                return 5;
            case "comment":
                return 6;
            case "refundable":
                return 7;
            case "type":
                return 8;
            case "venue.id":
                return 9;
            case "venue.name":
                return 10;
            case "venue.capacity":
                return 11;
            case "venue.type":
                return 12;
            case "venue.address.zipCode":
                return 13;
            case "venue.address.town.x":
                return 14;
            case "venue.address.town.y":
                return 15;
            case "venue.address.town.z":
                return 16;
            case "venue.address.town.name":
                return 17;
            default:
                com.setFirstArgument(com.getFirstArgument() + "There is not field with this name!");
                return -1;
        }
    }

}
