package WebRes;

import java.io.IOException;
import java.net.*;

public class Contact {

    private static int PORT = 4445;
    private static String HOST = "Localhost";
    //se.ifmo.ru

    public SocketAddress getSockAddr() {
        return sockAddr;
    }

    public DatagramSocket getDataSock() {
        return dataSock;
    }

    private SocketAddress sockAddr;
    private DatagramSocket dataSock;

    public Contact() throws SocketException {
        sockAddr = new InetSocketAddress(HOST, PORT);
        dataSock = new DatagramSocket();
    }

    public boolean setContact(){
        byte [] bytes = new byte[2];
        Command com = new Command();
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,sockAddr);
        try {
            dataSock.send(dp);
            DatagramPacket dpr = new DatagramPacket(bytes,bytes.length);
            dataSock.setSoTimeout(10000);
            dataSock.receive(dpr);
            sockAddr = dpr.getSocketAddress();
            dataSock.connect(sockAddr);
            dp.setSocketAddress(sockAddr);
            dataSock.send(dp);
            dataSock.receive(dpr);
            return true;
        } catch (IOException e) {
            System.out.println("Server not reachable");
            return false;
        }

    }

}
