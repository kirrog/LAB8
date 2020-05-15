package WebRes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.locks.ReentrantLock;

public class Contact {

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Contact.class);

    private static int PORT = 4445;

    private SocketAddress addr;

    public DatagramChannel getDatach() {
        return datach;
    }

    public ReentrantLock rLock = new ReentrantLock();

    private DatagramChannel datach;
    private SocketAddress sockAddr;
    private Receiver receiver = null;
    private Sender sender = null;

    public Contact(int port) throws IOException {
        PORT = port;
        addr = new InetSocketAddress(PORT);
        datach = DatagramChannel.open();
    }

    public boolean receiveConnection() {
        byte b[] = new byte[]{3, 2, 1};
        try {
            datach.bind(addr);
            ByteBuffer f = ByteBuffer.wrap(b);
            f.clear();
            int mesLength = 0;
            log.info("Wait for connection");
            //datach.socket().setSoTimeout(65535);
            sockAddr = datach.receive(f);

            log.info("Connecting to " + sockAddr);
            datach.connect(sockAddr);
            f.flip();
            datach.write(f);
            log.info("Complete!");
            //datach.socket().setSoTimeout(1000);
            datach.configureBlocking(false);
            return true;
        } catch (IOException | NullPointerException e) {
            log.info("Connection", e);
            return false;
        }

    }

    public Receiver getReceiver() {
        if (receiver == null) {
            receiver = new Receiver(this);
        }
        return receiver;
    }

    public Sender getSender() {
        if (sender == null) {
            sender = new Sender(this);
        }
        return sender;

    }

    public void close() {
        receiver.close();
        sender.close();
        try {
            datach.disconnect();
            datach.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
