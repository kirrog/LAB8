package se.ifmo.ru;

import se.ifmo.ru.Collection.Ticket;
import se.ifmo.ru.Parser.JsonParser;

import java.io.File;
import java.util.Hashtable;
/**
 *
 */
public class Main {
    public static int hashSize = 1000;
    public static Hashtable<String,Ticket> TicketsHashTable = new Hashtable<String,Ticket>(hashSize);

    public static void main(String[] args) {
        File f = new File(args[0]); //Check how works!
        //JsonParser jParser =
        new JsonParser(f);
        //Enter stream from file

        //Creation of hashTable

        //Filling out the collection

        //Work with terminal
    }
}
