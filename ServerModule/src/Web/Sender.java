package Web;

import java.io.*;
import java.nio.ByteBuffer;

public class Sender {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Sender.class);

    private Contact contact;

    public Sender(Contact cont){
        contact = cont;
    }

    private byte bytes[] = new byte[32*1024];

    public boolean send(Command command){
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        ObjectOutputStream ois = null;
        try {
            ois = new ObjectOutputStream(bais);
            ois.writeObject(command);
            bytes = bais.toByteArray();
            ByteBuffer bbf = ByteBuffer.wrap(bytes);
            contact.getDatach().write(bbf);
            log.info("Send");
            return true;
        } catch (IOException e) {
            log.info("Serialization, send", e);
            return false;
        }

    }

}
