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
import java.util.concurrent.ForkJoinTask;

public class ServerManager {

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerManager.class);

    public static boolean work = true;

    public static ForkJoinPool fjPool = new ForkJoinPool();
    public static ExecutorService cthPoolRecSend = Executors.newCachedThreadPool();


    //Реализует подключение и запуск потоков обработки клиентов
    // Организация потоков тоже тут

    public static void makeThreads(DataBaseManagerTickets dbmt) {
        try {
            Contact contact = new Contact(4445);
            Sender s = contact.getSender();
            Receiver r = contact.getReceiver();
            ThreadResurses tr = new ThreadResurses(dbmt, contact);
            ProcessThread pt = new ProcessThread(tr);
            cthPoolRecSend.submit(r);
            cthPoolRecSend.submit(s);
            fjPool.submit(ForkJoinTask.adapt(pt));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
