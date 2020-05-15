package ServerThreads;

import DataBase.ThreadResurses;
import WriteInOut.ServerUI;

public class ProcessThread extends Thread {

    ThreadResurses threadResurses;
    public ServerUI sUI = new ServerUI();

    ProcessThread(ThreadResurses tr){
        threadResurses = tr;
    }

    @Override
    public void run() {
        ServerUI serverUI = new ServerUI();
        serverUI.start();
        threadResurses.contact.close();
    }
}
