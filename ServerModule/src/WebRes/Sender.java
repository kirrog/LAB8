package WebRes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender extends Thread {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Sender.class);

    private Contact contact;
    private DatagramChannel datach;

    Sender(Contact cont) {
        contact = cont;
        datach = contact.getDatach();
    }

    private byte bytes[] = new byte[32 * 1024];
    private boolean haveMes = false;
    private boolean sended = true;
    private boolean work = true;
    Command com;

    public boolean send(Command command) {
        com = command;
        haveMes = true;
        sended = false;
        wakeSender();
        while (!sended) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                log.info("",e);
            }
        }
        haveMes = false;
        return true;
    }

    private synchronized void wakeSender(){
        notify();
    }

    synchronized void close() {
        log.info("Closed");
    }

    private synchronized void waitThread(){
        try {
            wait();
        } catch (InterruptedException e) {
            log.info("",e);
        }
    }

    @Override
    public void run() {
        while (work){
            if(haveMes){
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)){
                    oos.writeObject(com);
                    bytes = baos.toByteArray();
                    ByteBuffer bbf = ByteBuffer.wrap(bytes);
                    datach.write(bbf);
                    sended = true;
                    haveMes = false;
                    log.info("Send");
                } catch (IOException e) {
                    log.info("Serialization, send", e);
                }
            }else {
                waitThread();
            }
        }
    }
}
