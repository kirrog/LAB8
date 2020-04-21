package UI;

import Collection.Ticket;
import UI.Commandes.MesSendable;
import Web.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import static Starter.ClientMain.receiver;
import static Starter.ClientMain.sender;

public abstract class AbstractCommand implements MesSendable {

    protected Command command = new Command();

    private static Hashtable<String, String > replies = new Hashtable<String, String>();

    static {
        replies.put("clear","Cleared!");
        replies.put("execute_script","Executing!");
        replies.put("filter_by_venue","Filtering!");
        replies.put("help","Writing!");
        replies.put("info","Writing!");
        replies.put("insert","Inserting!");
        replies.put("print_descending","Printing!");
        replies.put("print_field_descending","Printing!");
        replies.put("remove_greater_key","Removing!");
        replies.put("remove_key","Removing!");
        replies.put("remove_lower","Removing!");
        replies.put("replace_if_lower","Replacing!");
        replies.put("show","Showing!");
        replies.put("update","Updating!");
    }

    @Override
    public void check(String command, String arg) {
        this.command.setNameOfCommand(command);
        send(replies.get(command));
    }

    @Override
    public void send(String str) {
        Command com;
        try{
            sender.send(command);
            com = receiver.receive();
            System.out.println("Server start executing: "+str);
            String string = com.getFirstArgument();
            if(string != null){
                if(string.contains("Collection saved")){
                    System.out.println("Collection saved");
                }
                if(string.contains("Server end receiving")){
                    System.out.println("Server end receiving");
                }
                System.out.println(com.getFirstArgument());
            }
            receive();
        }catch (IOException e){
            System.out.println("Server doesn't answer");
            System.out.println("Send one more, or stop? (y/n)");
            Scanner scan = new Scanner(System.in);
            String string = scan.nextLine();
            if(string.contains("y")){
                this.send(str);
            }
        }
    }

    public static void printSorted(ArrayList<Command> commands, int presize){

        if(!(commands == null)){
            int cs = commands.size();

            System.out.println("Number of missed Tickets: " + (presize - commands.size()));

            Ticket [] tickets = new Ticket[cs];
            String  [] keys = new String[cs];
            for (int j = 0; j < cs; j++) {
                int point = -1;
                int numb = cs+1;
                for (int i = 0; i < cs-j; i++) {
                    Command com = commands.get(i);
                    if(numb > (int)com.getSecondArgument()){
                        numb = (int) com.getSecondArgument();
                        point = i;
                    }
                }
                tickets[j] = (Ticket) commands.get(point).getThirdArgument();
                keys[j] = commands.get(point).getFirstArgument();
                commands.remove(point);
            }
            for (int i = 0; i < cs; i++) {
                System.out.println(keys[i]);
                tickets[i].writeTicket();
            }
        }else {
            System.out.println("Doesn't receive any Tickets! \nCheck the WEB!");
        }
    }

    protected static int getId(){
        Scanner inn = new Scanner(System.in);
        System.out.print("Enter Id:\n>");
        String arg = inn.nextLine();
        int num;
        try{
            num = Integer.parseInt(arg);
            if(num > 0){
                return num;
            }else {
                System.out.println("Wrong sign");
                return getId();
            }
        }catch (Exception e){
            System.out.println("Wrong variable");
            return getId();
        }
    }

}
