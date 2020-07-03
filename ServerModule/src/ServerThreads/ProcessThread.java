package ServerThreads;

import Commands.CheckCondition;
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
        ServerManager.decrease();
        ((CheckCondition)threadResurses.serverUI.replies.get("check_condition")).closeSynchronisation();
        try {
            threadResurses.sender.join();
            threadResurses.receiver.join();
        } catch (InterruptedException e) {
            log.error("Closing s and r", e);
        }
    }
}
