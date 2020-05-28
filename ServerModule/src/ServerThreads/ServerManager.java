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
    private static StackPrinter stackP = new StackPrinter();

    public static boolean work = true;
    public static int numberOfExers = -1;

    public static ForkJoinPool fjPool = new ForkJoinPool();
    public static ExecutorService cthPoolRecSend = Executors.newCachedThreadPool();
    private static StackPrinter stackPrinter = new StackPrinter();


    //Реализует подключение и запуск потоков обработки клиентов
    //Организация потоков тоже тут

    public static void makeThreads(DataBaseManagerTickets dbmt) {
        check();
        try {
            Contact contact = new Contact(4445);
            Sender s = contact.getSender();
            s.setName("Sender_" + numberOfExers);
            s.setUncaughtExceptionHandler(stackPrinter);
            Receiver r = contact.getReceiver();
            r.setName("Receiver_" + numberOfExers);
            r.setUncaughtExceptionHandler(stackPrinter);
            ThreadResurses tr = new ThreadResurses(dbmt, contact);
            ProcessThread pt = new ProcessThread(tr);
            pt.setName("Client_" + numberOfExers);
            pt.setUncaughtExceptionHandler(stackPrinter);
            cthPoolRecSend.submit(r);
            cthPoolRecSend.submit(s);
            fjPool.submit(ForkJoinTask.adapt(pt));
            numberOfExers++;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void check(){
        if(numberOfExers == 0){
            work =false;
        }
        if (numberOfExers == -1){
            numberOfExers = 0;
        }
    }
}
