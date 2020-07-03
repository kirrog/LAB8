package WebRes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.locks.ReentrantLock;

public class Receiver extends Thread {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Receiver.class);

    private byte bytes[] = new byte[32 * 1024];

    private Contact contact;
    private DatagramChannel datach;
    private ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    private ObjectInputStream ois;
    private ByteBuffer bbf = ByteBuffer.wrap(bytes);
    Command com = new Command();
    boolean received = false;
    boolean stop = true;
    private ReentrantLock rLock = new ReentrantLock();

    Receiver(Contact cont) {
        contact = cont;
        datach = contact.getDatach();
    }

    public Command receive() {
        while (!received) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
        rLock.lock();
        Command c = com;
        received = false;
        wakeReceiver();
        rLock.unlock();
        return c;
    }

    private synchronized void wakeReceiver() {
        notify();
    }

    private Command receiveCommand() throws IOException {
        try {
            read(bbf);
            ois = new ObjectInputStream(bais);
            Command com = (Command) ois.readObject();
            ois.close();
            log.info("Received - " + com.toString());
            if ("exit".equals(com.getNameOfCommand())) {
                stop = false;
            }
            return com;
        } catch (ClassNotFoundException e) {
            log.info("Bad command", e);
            return null;
        }
    }

    //(com.getNameOfCommand() != null) &&

    private void read(ByteBuffer b) {
        b.clear();
        bais.reset();
        while (stop) {
            try {
                int i = b.position();
                datach.read(b);
                if (b.position() == i) {
                    sleep(100);
                    continue;
                }
                break;
            } catch (IOException | InterruptedException e) {
                log.error("Reading from chanel", e);
            }
        }
    }

    void close() {
        stop = false;
        try {
            this.join();
        } catch (InterruptedException e) {
            log.info("Break waiting end receiving", e);
        }
        try {
            bais.close();
        } catch (IOException e) {
            log.info("Closing receiver", e);
        }
    }

    private synchronized void waitThread() {
        try {
            while (received) {
                wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (stop) {
            if (received) {
                waitThread();
            } else {
                try {
                    rLock.lock();
                    com = receiveCommand();
                    received = true;
                    rLock.unlock();
                } catch (IOException e) {
                    log.info("Receiving exception", e);
                }
            }
        }
    }
}
