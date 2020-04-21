package Web;

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

    public Contact(int port, String host) throws SocketException {
        PORT = port;
        HOST = host;
        sockAddr = new InetSocketAddress(HOST, PORT);
        dataSock = new DatagramSocket();
    }

    public boolean setContact(){
        byte [] bytes = new byte[]{1,2,3};
        Command com = new Command();
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,sockAddr);
        try {
            dataSock.send(dp);
            for(byte b: bytes){
                b = 0;
            }
            DatagramPacket dpr = new DatagramPacket(bytes,bytes.length);
            dataSock.setSoTimeout(1000);
            try{
                dataSock.receive(dpr);
                dataSock.connect(sockAddr);
                return true;
            }catch (SocketTimeoutException e){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
