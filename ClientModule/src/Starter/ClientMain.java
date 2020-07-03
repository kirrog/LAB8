package Starter;

import GUI.JMainFrame;
import GUI.Localization.LanguagesProvider;
import GUI.ManipulaterElements;
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
            contact = new Contact();
            System.out.println("Contact with server: " + contact.setContact());
        } catch (SocketException e) {
            e.printStackTrace();
        }
        receiver = new Receiver(contact);
        sender = new Sender(contact);
        //check();
        startWorking();
    }

    public static boolean check(){
        Command command = new Command();
        command.setNameOfCommand("Test");
        sender.send(command);
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
        JMainFrame mf = new JMainFrame();
        LanguagesProvider.setJmf(mf);
        ManipulaterElements.setJMainFrame(mf);
//        Terminal term = new Terminal();
//        term.startFromConsole();
    }

}
