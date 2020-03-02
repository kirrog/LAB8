package ru.ifmo.se.Commands;


import ru.ifmo.se.Main;

import java.util.Scanner;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class print information about collection*/
public class Info  implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Type: HashTable <Integer, Ticket>");
        System.out.println("Date of creation: " + Main.getHashCreationDate());
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
}
