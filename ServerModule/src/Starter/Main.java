package Starter;

import Commands.ExeClass;
import DataBase.DataBaseManagerTickets;
import DataBase.ThreadResurses;
import ServerThreads.ServerManager;

import java.util.Scanner;


/**
 * Main class with minimum code
 *
 * @author Kirill Rohachev
 * @version 1.1
 */
public class Main {
//    /**
//     * Field with size of Hashtable
//     */
//    public static int hashSize = 1000;
//    /**
//     * Field Hashtable
//     */
//    public static Hashtable<String, Ticket> TicketsHashTable = new Hashtable<String, Ticket>(hashSize);
//    /**
//     * Field contains time of Hashtable creation
//     */
//    //DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ")
//    private static ZonedDateTime hashCreationDate = ZonedDateTime.parse((ZonedDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ"))),DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss ZZ"));
//    /**
//     * Field with object of @see Terminal
//     */
//    public static Terminal terminal;
//    public static Contact contact;
//    public static Sender sender;
//    public static Receiver receiver;
//
//    public static ZonedDateTime getHashCreationDate() {
//        return hashCreationDate;
//    }
//
//    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
//        Main.hashCreationDate = hashCreationDate;
//    }

    private static String user = "programiifromskolkovo";
    private static String password = "programiifromskolkovo";
    private static String url = "jdbc:postgresql://localhost:5432/Tickets";

    public static void main(String[] args) {
        askServerer();
    }

//    public static boolean setContact(){
//        contact = null;
//        try {
//            contact = new Contact(4445);
//            contact.receiveConnection();
//            sender = new Sender(contact);
//            receiver = new Receiver(contact);
////            Command com = receiver.receive();
////            System.out.println(com.getNameOfCommand());
////            sender.send(com);
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public static void askServerer(){

        Scanner scan = new Scanner(System.in);
        String string;
        System.out.print("Work with client or server user? (Client/User/exit)\n> ");
        string = scan.nextLine();
        //-----------------------------------------------------------------
        if (string.contains("Client")){
            DataBaseManagerTickets dbmt = new DataBaseManagerTickets(user, password, url);
            while (ServerManager.work){
                ServerManager.makeThreads(dbmt);
            }
        //-----------------------------------------------------------------
        }else if(string.contains("User")){
            DataBaseManagerTickets dbmt = new DataBaseManagerTickets(user, password, url);
            ThreadResurses tr = new ThreadResurses(dbmt, null);
            ExeClass eCla = new ExeClass(tr);
            eCla.start();
        }else if (string.equals("exit")){
            return;
        }else {
            System.out.println("Wrong entering");
        }
        askServerer();
    }

}
