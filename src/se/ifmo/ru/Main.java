package se.ifmo.ru;

import se.ifmo.ru.Collection.Ticket;
import se.ifmo.ru.Comands.ExeClass;
import se.ifmo.ru.Parser.JsonParser;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.Hashtable;

public class Main {

    /**
     * Number of keys in TicketsHashTable
     */
    public static int hashSize = 1000;

    /**
     * Global Hashtable. Program work with it
     */
    public static Hashtable<String, Ticket> TicketsHashTable = new Hashtable<String, Ticket>(hashSize);

    public static ZonedDateTime getHashCreationDate() {
        return hashCreationDate;
    }

    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
        Main.hashCreationDate = hashCreationDate;
    }

    /**Date when collection was created. */
    static ZonedDateTime hashCreationDate = ZonedDateTime.now();

    public static String fileOutPutName = "Tickets.json";

    /**
     * Program starts hear
     * There are 2 steps
     * First: Parsing from file collection
     * Second: Start scanning commands from user
     */
    public static void main(String[] args) {
        File f = new File("Test.json");
        if (args.length > 0){
            f = new File(args[0]);
        }
        if (f.exists()){
            JsonParser jPars = new JsonParser(f);
            jPars.parse();

            ExeClass eCla = new ExeClass();

            eCla.start(false);
        }else{
            System.out.println("This file doesn't exists! Please, restart program!");
        }


    }
}
