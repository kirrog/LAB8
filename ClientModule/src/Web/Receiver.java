package Web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class Receiver {

    private Contact contact;

    public Receiver(Contact cont) {
        contact = cont;
    }

    public Command receive() throws IOException {
        byte bytes[] = new byte[32 * 1024];
        DatagramPacket input = new DatagramPacket(bytes, bytes.length);
        try {
            contact.getDataSock().receive(input);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Command com = (Command) ois.readObject();
            System.out.println("Received");
            return com;
        } catch (ClassNotFoundException e) {
            System.out.println("Bad command received");
            return null;
        }
    }

}
