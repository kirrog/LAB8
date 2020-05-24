package WebRes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class Sender {

    private Contact contact;

    public Sender(Contact cont){
        contact = cont;
    }

    public boolean send(Command command){
        byte b[];
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();ObjectOutputStream oos = new ObjectOutputStream(baos);){
            oos.writeObject(command);
            b = baos.toByteArray();
            return sendData(b);
        } catch (IOException e) {
            System.out.println("Problem with serialization!");
            return false;
        }
    }

    public boolean sendData(byte[] bytes) {
        DatagramSocket dataSock = contact.getDataSock();
        SocketAddress sockAddr = contact.getSockAddr();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, sockAddr);
        try {
            dataSock.send(datagramPacket);
            System.out.println("Send");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
