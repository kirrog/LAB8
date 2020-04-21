package Web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Receiver {

    private byte bytes[] = new byte[32*1024];

    private Contact contact;

    public Receiver(Contact cont){
        contact = cont;
    }

    public Command receive() throws IOException {
        DatagramChannel datach = contact.getDatach();
        ByteBuffer bbf = ByteBuffer.wrap(bytes);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try {
            bbf.clear();
            datach.read(bbf);
            ois = new ObjectInputStream(bais);
            Command com = (Command) ois.readObject();
            System.out.println("Command Received");
            return com;
        } catch (ClassNotFoundException e) {
            System.out.println("Bad command");
            return null;
        }
    }

}
