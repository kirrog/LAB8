package ServerThreads;

import DataBase.DataBaseManagerTickets;
import DataBase.ThreadResurses;
import WebRes.Contact;
import WebRes.Receiver;
import WebRes.Sender;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ServerManager {

    public static boolean work = true;

    public static ForkJoinPool fjPool;
    public static ExecutorService cthPoolRec = Executors.newCachedThreadPool();
    public static ExecutorService cthPoolSend = Executors.newCachedThreadPool();


    //Реализует подключение и запуск потоков обработки клиентов
    // Организация потоков тоже тут

    public static void makeThreads(DataBaseManagerTickets dbmt){
        try {
            Contact contact = new Contact(4445);
            if(contact.receiveConnection()){
                Sender s = contact.getSender();
                Receiver r = contact.getReceiver();
                ThreadResurses tr = new ThreadResurses(dbmt, contact);
                ProcessThread pt = new ProcessThread(tr);
                cthPoolRec.execute(r);
                fjPool.execute(pt);
                cthPoolSend.execute(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
