package ru.ifmo.se;

import ru.ifmo.se.Collection.Ticket;
import ru.ifmo.se.Commands.ExeClass;
import ru.ifmo.se.WriteInOut.Terminal;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;


/**
 * Main class with minimum code
 *
 * @author Kirill Rohachev
 * @version 1.1
 */
public class Main {
    /**
     * Field with size of Hashtable
     */
    public static int hashSize = 1000;
    /**
     * Field Hashtable
     */
    public static Hashtable<String, Ticket> TicketsHashTable = new Hashtable<String, Ticket>(hashSize);
    /**
     * Field contains time of Hashtable creation
     */
    //DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ")
    private static ZonedDateTime hashCreationDate = ZonedDateTime.parse((ZonedDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ"))),DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ"));
    /**
     * Field with object of @see Terminal
     */
    public static Terminal terminal;

    public static ZonedDateTime getHashCreationDate() {
        return hashCreationDate;
    }

    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
        Main.hashCreationDate = hashCreationDate;
    }

    public static void main(String[] args) {

        terminal = new Terminal(args);
        terminal.start();

        ExeClass eCla = new ExeClass();
        eCla.start();

    }
}
