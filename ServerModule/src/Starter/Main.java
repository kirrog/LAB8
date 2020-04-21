package Starter;

import Collection.Ticket;
import Commands.ExeClass;
import Commands.Save;
import Web.Contact;
import Web.Receiver;
import Web.Sender;
import WriteInOut.ServerUI;
import WriteInOut.Terminal;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Scanner;


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
    public static Contact contact;
    public static Sender sender;
    public static Receiver receiver;

    public static ZonedDateTime getHashCreationDate() {
        return hashCreationDate;
    }

    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
        Main.hashCreationDate = hashCreationDate;
    }

    public static void main(String[] args) {
        terminal = new Terminal(args);
        terminal.start();
        askServerer();
    }

    public static boolean setContact(){
        contact = null;
        try {
            contact = new Contact(4445);
            contact.receiveConnection();
            sender = new Sender(contact);
            receiver = new Receiver(contact);
//            Command com = receiver.receive();
//            System.out.println(com.getNameOfCommand());
//            sender.send(com);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void askServerer(){
        Scanner scan = new Scanner(System.in);
        String string;
        System.out.print("Work with client or server user? (Client/User/exit)\n> ");
        string = scan.nextLine();
        if (string.contains("Client")){
            ServerUI.setHaveClient(setContact());
            if(ServerUI.isHaveClient()){
                ServerUI.start();
            }else {
                System.out.println("Haven't client");
            }
        }else if(string.contains("User")){
            ExeClass eCla = new ExeClass();
            eCla.start();
        }else if (string.equals("exit")){
            new Save().execute(null,null,null);
            return;
        }else {
            System.out.println("Wrong entering");
        }
        askServerer();
    }

}
