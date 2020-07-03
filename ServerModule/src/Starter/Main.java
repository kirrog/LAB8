package Starter;

import Commands.ExeClass;
import DataBase.BasesTableCreater;
import DataBase.DataBaseManagerTickets;
import DataBase.ThreadResurses;
import ServerThreads.ServerManager;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * Main class with minimum code
 *
 * @author Kirill Rohachev
 * @version 3.2
 */
public class Main {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    private static String user = "programiifromskolkovo";
    private static String password = "programiifromskolkovo";
    private static String url = "jdbc:postgresql://localhost:5432/Tickets";
//    private static String user = "s283904";
//    private static String password = "ipe789";
//    private static String url = "jdbc:postgresql://pg:5432/studs";

    public static void main(String[] args) {
        askServerer();
        System.exit(0);
    }

    public static void askServerer(){

        Scanner scan = new Scanner(System.in);
        String string;
        System.out.print("Work with client or server user? (Client/User/exit)\n> ");
        string = scan.nextLine();
        //-----------------------------------------------------------------
        if (string.contains("Client")){
            ServerManager sm = new ServerManager();
            sm.start();
            DataBaseManagerTickets dbmt = BasesTableCreater.getDataBase(user, password, url);
            while (ServerManager.work){
                ServerManager.makeThreads(dbmt);
            }
            try {
                dbmt.close();
            } catch (SQLException e) {
                log.error("Closing bd: " + e);
            }
            //-----------------------------------------------------------------
        }else if(string.contains("User")){
            DataBaseManagerTickets dbmt = BasesTableCreater.getDataBase(user, password, url);
            ThreadResurses tr = new ThreadResurses(dbmt);
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
