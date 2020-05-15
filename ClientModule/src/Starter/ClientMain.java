package Starter;

import UI.Terminal;
import WebRes.Command;
import WebRes.Contact;
import WebRes.Receiver;
import WebRes.Sender;

import java.io.IOException;
import java.net.SocketException;

public class ClientMain {

    public static Contact contact;
    public static Receiver receiver;
    public static Sender sender;

    public static void main(String[] args) {
        contact = null;
        try {
            contact = new Contact(4445, "Localhost");
            System.out.println("Contact with server: " + contact.setContact());
        } catch (SocketException e) {
            e.printStackTrace();
        }
        receiver = new Receiver(contact);
        sender = new Sender(contact);
        startWorking();
    }

    public static boolean check(){

        sender.send(new Command());
        Command command = null;
        try {
            command = receiver.receive();
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
        if(command != null){
            System.out.println(command.getNameOfCommand());
            return true;
        }
        return false;
    }

    public static void startWorking(){
        Terminal term = new Terminal();
        term.startFromConsole();
    }

}
