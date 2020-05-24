package ServerThreads;

import DataBase.ThreadResurses;
import WriteInOut.ServerUI;

public class ProcessThread extends Thread {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProcessThread.class);

    ThreadResurses threadResurses;
    public ServerUI sUI;

    ProcessThread(ThreadResurses tr){
        threadResurses = tr;
    }

    @Override
    public void run() {
        sUI = new ServerUI(threadResurses);
        sUI.start();
        threadResurses.contact.close();
    }
}
