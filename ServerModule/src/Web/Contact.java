package Web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Contact {

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Contact.class);

    private static int PORT = 4445;

    private SocketAddress addr;

    public DatagramChannel getDatach() {
        return datach;
    }

    private DatagramChannel datach;
    private SocketAddress sockAddr;

    public Contact(int port) throws IOException {
        PORT = port;
        addr = new InetSocketAddress(PORT);
        datach = DatagramChannel.open();
    }

    public boolean receiveConnection() {
        byte b[] = new byte[]{3,2,1};
        try {
            datach.bind(addr);
            ByteBuffer f = ByteBuffer.wrap(b);
            f.clear();
            int mesLength = 0;
            log.info("Wait for connection");
            datach.socket().setSoTimeout(65535);
            sockAddr = datach.receive(f);

            log.info("Connecting to " + sockAddr);
            datach.connect(sockAddr);
            f.flip();
            datach.write(f);
            log.info("Complete!");
            datach.socket().setSoTimeout(1000);
            return true;
        } catch (IOException | NullPointerException e) {
            log.info("Connection", e);
            return false;
        }

    }

}
