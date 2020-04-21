package UI.Commandes;

//Sends message

public interface MesSendable {

    void check(String command, String arg); //Get from client right command

    void send(String str);    //send and receive answer from server. Write message that all right or resend it. Call receive.

    boolean receive();  //Get from server answer or wait for it.

}
