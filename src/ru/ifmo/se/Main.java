package ru.ifmo.se;

import ru.ifmo.se.Collection.Ticket;
import ru.ifmo.se.Commands.ExeClass;
import ru.ifmo.se.WriteInOut.Terminal;

import java.time.ZonedDateTime;
import java.util.Hashtable;

public class Main {

    public static int hashSize = 1000;

    public static Hashtable<String, Ticket> TicketsHashTable = new Hashtable<String, Ticket>(hashSize);

    static ZonedDateTime hashCreationDate = ZonedDateTime.now();

    public static Terminal justTerm;

    public static ZonedDateTime getHashCreationDate() {
        return hashCreationDate;
    }

    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
        Main.hashCreationDate = hashCreationDate;
    }

    public static void main(String[] args) {
        justTerm = new Terminal(args);
        justTerm.start();

        ExeClass eCla = new ExeClass();
        eCla.start();
    }

}
