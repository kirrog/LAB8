package Starter;

import Commands.ExeClass;
import Web.Command;
import Web.Contact;
import Web.Receiver;
import Web.Sender;
import WriteInOut.Terminal;

import java.io.IOException;

public class ServerMain {

    static Contact contact;
    static Sender sender;
    static Receiver receiver;
    static Terminal terminal;


    public static void main(String[] args) {

        contact = null;
        try {
            contact = new Contact(4445);
            contact.receiveConnection();
            sender = new Sender(contact);
            receiver = new Receiver(contact);
            Command com = receiver.receive();
            System.out.println(com.getNameOfCommand());
            sender.send(com);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start(String[] args){
        terminal = new Terminal(args);
        terminal.start();
        ExeClass eCla = new ExeClass();
        eCla.start();
    }
}
