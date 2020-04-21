package Commands;


import Starter.Main;
import Web.Command;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static Starter.Main.TicketsHashTable;


/** This class print information about collection*/
public class Info  extends AbstractCommand {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Type: HashTable <Integer, Ticket>");
        System.out.println("Date of creation: " + Main.getHashCreationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ")));
        System.out.println("Number of elements: " + TicketsHashTable.size());
        System.out.println("Template: ");
        System.out.println("\"id\": int,");
        System.out.println("\"name\": \"String\",");
        System.out.println("\"coordinates\": {");
        System.out.println("\t\"x\": int,");
        System.out.println("\t\"y\": double");
        System.out.println("},");
        System.out.println("\"creationDate\": \"12/23/1990 - 12:56:34 05:00\",");
        System.out.println("\"price\": int,");
        System.out.println("\"comment\": \"String\",");
        System.out.println("\"refundable\": boolean,");
        System.out.println("\"type\": \"Enum\",");
        System.out.println("\"venue\": {");
        System.out.println("\t\"id\": int,");
        System.out.println("\t\"name\": \"String\",");
        System.out.println("\t\"capacity\": int,");
        System.out.println("\t\"type\": \"Enum\",");
        System.out.println("\t\"address\": {");
        System.out.println("\t\t\"zipCode\": \"String\",");
        System.out.println("\t\t\"town\": {");
        System.out.println("\t\t\t\"x\": Double,");
        System.out.println("\t\t\t\"y\": Double,");
        System.out.println("\t\t\t\"z\": int,");
        System.out.println("\t\t\t\"name\": \"String\"");
        System.out.println("\t\t}");
        System.out.println("\t}");
        System.out.println("}");
    }

    @Override
    public void exe() {
        com.setFirstArgument(
                "Type: HashTable <Integer, Ticket>"+ "\n" +
        "Date of creation: " + Main.getHashCreationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ"))+ "\n" +
        "Number of elements: " + TicketsHashTable.size()+ "\n" +
        "Template: "+ "\n" +
        "\"id\": int,"+ "\n" +
        "\"name\": \"String\","+ "\n" +
        "\"coordinates\": {"+ "\n" +
        "\t\"x\": int,"+ "\n" +
        "\t\"y\": double"+ "\n" +
        "},"+ "\n" +
        "\"creationDate\": \"12/23/1990 - 12:56:34 05:00\","+ "\n" +
        "\"price\": int,"+ "\n" +
        "\"comment\": \"String\","+ "\n" +
        "\"refundable\": boolean,"+ "\n" +
        "\"type\": \"Enum\","+ "\n" +
        "\"venue\": {"+ "\n" +
        "\t\"id\": int,"+ "\n" +
        "\t\"name\": \"String\","+ "\n" +
        "\t\"capacity\": int,"+ "\n" +
        "\t\"type\": \"Enum\","+ "\n" +
        "\t\"address\": {"+ "\n" +
        "\t\t\"zipCode\": \"String\","+ "\n" +
        "\t\t\"town\": {"+ "\n" +
        "\t\t\t\"x\": Double,"+ "\n" +
        "\t\t\t\"y\": Double,"+ "\n" +
        "\t\t\t\"z\": int,"+ "\n" +
        "\t\t\t\"name\": \"String\""+ "\n" +
        "\t\t}"+ "\n" +
        "\t}"+ "\n" +
        "}"
        );
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        Main.sender.send(com);
    }
}
