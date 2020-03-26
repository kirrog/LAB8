package ru.ifmo.se.Commands;


import ru.ifmo.se.Collection.Ticket;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import static ru.ifmo.se.Main.TicketsHashTable;


/** This class print sorted elements by field*/
public class PrintFieldDescending implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
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

        Ticket [] tickets = new Ticket [0];
        switch (string){
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
        for (int j = 0; j < tickets.length; j++) {
            tickets[j].writeTicket();
        }
    }

    private Ticket [] writeField(int field){

        Enumeration enumeration = TicketsHashTable.keys();
        Ticket [] TickArray = new Ticket[TicketsHashTable.size()];
        int i = 0;
        while (enumeration.hasMoreElements()) {
            TickArray[i] = TicketsHashTable.get(enumeration.nextElement());
            i++;
        }

        Ticket tickOne;
        Ticket tickTwo;

        for (int j = 0; j < TickArray.length - 1; j++) {
            tickOne = TickArray[j];
            for (int k = j + 1; k < TickArray.length; k++) {
                tickTwo = TickArray[k];
                if(tickOne.compByField(field,tickTwo) < 0 ){
                    Ticket t = TickArray[j];
                    TickArray[j] = TickArray[k];
                    TickArray[k] = t;
                }
            }
        }
        return TickArray;
    }
}
