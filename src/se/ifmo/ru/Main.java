package se.ifmo.ru;

import se.ifmo.ru.Collection.Ticket;
import se.ifmo.ru.Comands.ExeClass;
import se.ifmo.ru.Parser.JsonParser;

import java.io.File;
import java.util.Hashtable;

public class Main {
    public static int hashSize = 1000;  /**Number of keys in TicketsHashTable */
    public static Hashtable<String,Ticket> TicketsHashTable = new Hashtable<String,Ticket>(hashSize);   /**Global Hashtable. Program work with it */

    /**Program starts hear
     * There are 2 steps
     * First: Parsing from file collection
     * Second: Start scanning commands from user
     */
    public static void main(String[] args) {

        File f = new File(args[0]);

        JsonParser jPars = new JsonParser(f);
        jPars.parse();

        ExeClass eCla = new ExeClass();

        eCla.start(false);

    }
}
