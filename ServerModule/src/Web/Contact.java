package Web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Contact {

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
            System.out.println("Wait for connection");
            datach.socket().setSoTimeout(65535);
            sockAddr = datach.receive(f);

            System.out.println("Connecting to " + sockAddr);
            datach.connect(sockAddr);
            f.flip();
            datach.write(f);
            System.out.println("Complete!");
            datach.socket().setSoTimeout(1000);
            return true;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }

}
