package Web;

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

    private byte bytes[] = new byte[32*1024];

    public boolean send(Command command){
        byte b[];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            if (command.equals(null)){
//                Ticket ticket = new Ticket("vas",
//                        new Coordinates(123, 23.0d),
//                        125,"Comment",
//                        true, TicketType.BUDGETARY,
//                        new Venue("Afanas", 120, VenueType.LOFT,
//                                new Address("348590bnakfsngiaw0",
//                                        new Location(245.32,134.324f, 345L,"Cas"))));
//                command.setThirdArgument(ticket);
//                command.setFirstArgument("Test");
//            }

            oos.writeObject(command);
            b = baos.toByteArray();
            baos.reset();
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
            System.out.println("Successfully send!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

}
