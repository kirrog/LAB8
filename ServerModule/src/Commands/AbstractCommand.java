package Commands;

import Collection.Ticket;
import Starter.Main;
import Web.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public abstract class AbstractCommand implements Execute,ServerExecutable {

    protected String name;
    public String getName() {
        return name;
    }

    public static Hashtable<String, ServerExecutable > replies = new Hashtable<String, ServerExecutable>();

    static {
        replies.put("clear", new Clear());
        replies.put("execute_script", new ExecuteScript());
        replies.put("exit", new Exit());
        replies.put("filter_by_venue", new FilterByVenue());
        replies.put("help",new Help());
        replies.put("info", new Info());
        replies.put("insert",new Insert());
        replies.put("print_descending", new PrintDescending());
        replies.put("print_field_descending", new PrintFieldDescending());
        replies.put("remove_greater_key", new RemoveGreaterKey());
        replies.put("remove_key", new RemoveKey());
        replies.put("remove_lower", new RemoveLower());
        replies.put("replace_if_lower", new ReplaceIfLower());
        replies.put("show", new Show());
        replies.put("update",new Update());
    }

    Command com;

    @Override
    public void answer(Command command){
        com = command;
        exe();
    }

    @Override
    public void send(ArrayList<Command> commands){
        com.setSecondArgument(commands.size());
        Main.sender.send(com);
        for (int i = 0; i < commands.size(); i++) {
            Main.sender.send(commands.get(i));
        }
    }

    public void sort(ArrayList<Command> commands){
        int cs = commands.size();
        System.out.println(cs);
        if(cs == 0){
            return;
        }
        Command [] cos = new Command[cs];
        for (int i = 0; i < cs; i++) {
            cos[i] = commands.get(i);
        }
        for (int i = 0; i < cs-1; i++) {
            for (int j = i+1; j < cs; j++) {
                int b = ((Ticket)cos[i].getThirdArgument()).compareTo((Ticket)cos[j].getThirdArgument());
                if(b < 0){
                    Command c = cos[i];
                    cos[i] = cos[j];
                    cos[j]=c;
                }
            }
            cos[i].setSecondArgument(i);
        }
        cos[cs-1].setSecondArgument(cs-1);
        commands.clear();
        commands.addAll(Arrays.asList(cos).subList(0, cs));
    }

    @Override
    public void talk (String str, Scanner scanner){
        try {
            com = Main.receiver.receive();
            com.setNameOfCommand(getName());
            Main.sender.send(com);
            setArgs(str,scanner);
            exe();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void setArgs(String str, Scanner scanner);

}
