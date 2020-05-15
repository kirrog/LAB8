package WebRes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Receiver extends Thread {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Receiver.class);

    private byte bytes[] = new byte[32*1024];

    private Contact contact;
    private DatagramChannel datach;
    private ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    private ObjectInputStream ois;
    private ByteBuffer bbf = ByteBuffer.wrap(bytes);
    Command com = new Command();
    boolean received = false;
    boolean stop = true;

    {
        try {
            ois = new ObjectInputStream(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Receiver(Contact cont){
        contact = cont;
        datach = contact.getDatach();
    }

    public synchronized Command receive() throws IOException {
        received = false;
        notify();
        return com;
    }

    private synchronized Command receiveCommand() throws IOException {
        contact.rLock.lock();
        try {
            read(bbf);
            Command com = (Command) ois.readObject();
            log.info("Received");
            contact.rLock.unlock();
            bbf.clear();
            return com;
        } catch (ClassNotFoundException e) {
            log.info("Bad command", e);
            contact.rLock.unlock();
            return null;
        }
    }

    private synchronized void read(ByteBuffer b){
        int i = 0;
        while (stop){
            try {
                b.clear();
                i = b.position();
                datach.read(b);
                if(b.position() == i){
                    wait(100);
                    continue;
                }
                break;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void close(){
        stop = false;
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
            bais.close();
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
            if(received == false){
                try {
                    com = receiveCommand();
                    received = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                waitThread();
            }
        }

    }
}
