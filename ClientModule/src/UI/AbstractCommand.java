package UI;

import Collection.Ticket;
import GUI.CommandFormer;
import GUI.ManipulaterElements;
import UI.Commandes.*;
import WebRes.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import static Starter.ClientMain.receiver;
import static Starter.ClientMain.sender;

public abstract class AbstractCommand implements MesSendable {

    protected Command command = new Command();

    public static Hashtable<String, AbstractCommand> replies = new Hashtable<String, AbstractCommand>();

    static {
        replies.put("clear", new Clear());
        replies.put("execute_script", new ExecuteScript());
        replies.put("filter_by_venue", new FilterByVenue());
        replies.put("help", new Help());
        replies.put("info", new Info());
        replies.put("insert", new Insert());
        replies.put("print_descending", new PrintDescending());
        replies.put("print_field_descending_type", new PrintFieldDescending());
        replies.put("remove_greater_key", new RemoveGreaterKey());
        replies.put("remove_key", new RemoveKey());
        replies.put("remove_lower", new RemoveLower());
        replies.put("replace_if_lower", new ReplaceIfLower());
        replies.put("show", new Show());
        replies.put("update", new Update());
        replies.put("exit", new Exit());
        replies.put("register", new Register());
        replies.put("change_register", new ChangeRegister());
        replies.put("login", new Login());
        replies.put("check_condition", new CheckCondition());
    }

    @Override
    public void check(String command, String arg) {
        this.command.setNameOfCommand(command);
        send(command);
    }

    private int recurs = 3;
    private int current = 0;

    @Override
    public void send(String str) {
        Command com;
        try {
            sender.send(command);
            com = receiver.receive();
            CommandFormer.setServerStatus(2);
            printMes(com);
            receive();
        } catch (IOException e) {
            CommandFormer.setServerStatus(0);
            if (current < recurs) {
                current++;
                this.send(str);
            }else {
                current = 0;
            }
        }
    }

    public String send() throws IOException {
        Command com = new Command();
        sender.send(com);
        com = receiver.receive();
        printMes(com);
        String string = com.getNameOfCommand();
        replies.get(string).receive();
        return string;
    }

    private void printMes(Command com) {
        String string = com.getFirstArgument();
        if (string != null) {
            if (string.contains("Server end receiving")) {
                CommandFormer.setServerStatus(-1);
            }
            CommandFormer.answer = com.getFirstArgument();
        }
    }

    public static void printSorted(ArrayList<Command> commands, int presize) {

        if (!(commands == null)) {
            int cs = commands.size();

            for (int i = 0; i < cs; i++) {
                if (commands.get(i) == null) {
                    commands.remove(i);
                }
            }

            CommandFormer.setMissedTicketNumber(presize - commands.size());

            Ticket[] tickets = new Ticket[cs];
            String[] keys = new String[cs];
            for (int j = 0; j < cs; j++) {
                int point = -1;
                int numb = cs + 1;
                for (int i = 0; i < cs - j; i++) {
                    Command com = commands.get(i);
                    if (numb > (int) com.getSecondArgument()) {
                        numb = (int) com.getSecondArgument();
                        point = i;
                    }
                }
                tickets[j] = (Ticket) commands.get(point).getThirdArgument();
                keys[j] = commands.get(point).getFirstArgument();
                commands.remove(point);
            }
            ManipulaterElements.clear();
            for (int i = 0; i < cs; i++) {
                ManipulaterElements.addTicket(tickets[i]);
            }
        } else {
            CommandFormer.setServerStatus(0);
        }
    }

}
