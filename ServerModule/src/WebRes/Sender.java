package WebRes;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender implements Runnable {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Sender.class);

    private Contact contact;
    private DatagramChannel datach;

    Sender(Contact cont) {
        contact = cont;
        datach = contact.getDatach();
    }

    private byte bytes[] = new byte[32 * 1024];
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private ObjectOutputStream oos;
    private ByteBuffer bbf = ByteBuffer.wrap(bytes);
    boolean haveMes = false;
    boolean stop = true;
    Command com;

    {
        try {
            oos = new ObjectOutputStream(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean send(Command command) {
        com = command;
        haveMes = true;
        notify();
        return true;
    }

    synchronized void close() {
        while (haveMes){
            try {
                wait(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop = false;
        notify();
        try {
            oos.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void waitThread(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (stop){
            if(haveMes == false){
                waitThread();
            }else {
                try {
                    haveMes = false;
                    oos.writeObject(com);
                    datach.write(bbf);
                    log.info("Send");
                } catch (IOException e) {
                    log.info("Serialization, send", e);
                }
            }
        }
    }
}
