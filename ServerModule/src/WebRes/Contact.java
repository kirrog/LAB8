package WebRes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Contact {

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Contact.class);

    private static int PORT = 4445;

    private static SocketAddress addr;

    private static DatagramChannel datagramChannel;

    static {
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DatagramChannel getDatach() {
        return datach;
    }

    private DatagramChannel datach;
    private Receiver receiver = null;
    private Sender sender = null;

    public Contact(int port) throws IOException {
        PORT = port;
        addr = new InetSocketAddress(PORT);
        datach = DatagramChannel.open();
        receiveConnection();
        log.info("Contact set");
    }

    private void receiveConnection() {
        byte b[] = new byte[2];
        try {
            ByteBuffer f = ByteBuffer.wrap(b);
            f.clear();
            int mesLength = 0;
            log.info("Wait for connection");
            //datach.socket().setSoTimeout(65535);
            addr = datagramChannel.receive(f);
            log.info("Connecting to " + addr);
            datach.bind(new InetSocketAddress(0));
            System.out.println(datach.socket().getLocalPort());
            datach.connect(addr);
            f.flip();
            datach.write(f);
            f.clear();
            datach.receive(f);
            f.flip();
            datach.write(f);
            log.info("Complete!");
            //datach.socket().setSoTimeout(1000);
            datach.configureBlocking(false);
        } catch (IOException | NullPointerException e) {
            log.info("Connection", e);
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
            log.info("Closing contact", e);
        }
    }
}
