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

    Receiver(Contact cont){
        contact = cont;
        datach = contact.getDatach();
    }

    public Command receive(){
        while (!received){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Command c = com;
        received = false;
        wakeReceiver();
        return c;
    }

    private synchronized void wakeReceiver(){
        notify();
    }

    private Command receiveCommand() throws IOException {
        try {
            read(bbf);
            ois = new ObjectInputStream(bais);
            Command com = (Command) ois.readObject();
            ois.close();
            log.info("Received");
            log.info(com.toString());
            if(com.getNameOfCommand().equals("exit")){
                stop = false;
            }
            return com;
        } catch (ClassNotFoundException e) {
            log.info("Bad command", e);
            return null;
        }
    }

    private void read(ByteBuffer b){
        b.clear();
        bais.reset();
        while (stop){
            try {
                int i = b.position();
                datach.read(b);
                if(b.position() == i){
                    sleep(100);
                    continue;
                }
                break;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void close(){
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

    private synchronized void waitThread(){
        try {
            while (received){
                wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (stop){
            if(received){
                waitThread();
            }else {
                try {
                    com = receiveCommand();
                    received = true;
                } catch (IOException e) {
                    log.info("Receiving exception", e);
                }
            }
        }
    }
}
