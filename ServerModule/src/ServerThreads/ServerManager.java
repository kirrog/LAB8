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
import java.util.concurrent.locks.ReentrantLock;

public class ServerManager extends Thread {

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerManager.class);

    public static boolean work = true;
    public static int numberOfExers = -1;
    public static ForkJoinPool fjPool = new ForkJoinPool();
    public static ExecutorService cthPoolRecSend = Executors.newCachedThreadPool();

    private static StackPrinter stackPrinter = new StackPrinter();
    private static ReentrantLock rLock = new ReentrantLock();


    //Реализует подключение и запуск потоков обработки клиентов
    //Организация потоков тоже тут

    public static void makeThreads(DataBaseManagerTickets dbmt) {
        try {
            Contact contact = new Contact(4445);
            rLock.lock();
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
            if (numberOfExers == -1) {
                numberOfExers = 0;
            }
            numberOfExers++;
            rLock.unlock();
        } catch (IOException e) {
            log.info("While making threads: ", e);
        } catch (Exception e){
            log.info(e.getMessage());
        }

    }

    static synchronized void decrease() {
        numberOfExers--;
    }

    private static void check() {
        rLock.lock();
        if (numberOfExers == 0) {
            work = false;
        }
        rLock.unlock();
    }

    @Override
    public void run() {
        work = true;
        numberOfExers = -1;
        while (work) {
            check();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                log.error("Checker shutdown", e);
            }
        }
    }
}
