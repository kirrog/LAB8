package Commands;

import Collection.Ticket;
import Collection.Venue;
import DataBase.ThreadResurses;
import DataBase.TicketOwner;
import WriteInOut.Terminal;
import WriteInOut.TicketReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains methods for execution commands
 */
public class ExeClass {

    private static HashMap<String, Execute> hashMap = new HashMap<String, Execute>();

    private ThreadResurses thres;

    private Scanner inner;

    public boolean logined = false;

    private boolean isFile = false;

    public boolean isFile() {
        return isFile;
    }

    private String string = "";

    {
        hashMap.put("help", new Help(thres));
        hashMap.put("clear", new Clear(thres));
        hashMap.put("execute_script", new ExecuteScript(thres));
        hashMap.put("filter_by_venue", new FilterByVenue(thres));
        hashMap.put("info", new Info(thres));
        hashMap.put("insert", new Insert(thres));
        hashMap.put("print_descending", new PrintDescending(thres));
        hashMap.put("print_field_descending_type", new PrintFieldDescending(thres));
        hashMap.put("remove_greater_key", new RemoveGreaterKey(thres));
        hashMap.put("remove_key", new RemoveKey(thres));
        hashMap.put("remove_lower", new RemoveLower(thres));
        hashMap.put("replace_if_lower", new ReplaceIfLower(thres));
        hashMap.put("show", new Show(thres));
        hashMap.put("update", new Update(thres));
        hashMap.put("login", new Login(thres));
        hashMap.put("register", new Register(thres));
        hashMap.put("change_register", new ChangeRegister(thres));
    }

    public ExeClass(ThreadResurses tr) {
        thres = tr;
        inner = new Scanner(System.in);
        hashMap.forEach((string, execute)-> execute.setTr(tr));
    }

    public ExeClass(String str, ThreadResurses tr) {
        thres = tr;
        try {
            inner = new Scanner(new File(str));
            isFile = true;
            string = str;
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading script: " + str);
            Terminal.writeFileStatus(new File(str));
        }
        hashMap.forEach((string, execute)-> execute.setTr(tr));
    }

    public void start() {
        if(isFile){
            startFromScript();
        }else {
            startFromConsole();
        }
    }

    private void startFromConsole() {
        boolean notExit = true;

        while (notExit) {
            System.out.print("Enter command\n> ");
            String command = null;
            while (notExit) {
                command = inner.nextLine();
                if (command.isEmpty()) {
                    System.out.print("> ");
                } else {
                    break;
                }
            }
            String arguments = "";
            if (command.contains(" ")) {
                arguments = command.substring(command.indexOf(" ") + 1);
                command = command.substring(0, command.indexOf(" "));
            }
            if (command.isEmpty()) {
                System.out.println("Wrong entering");
                continue;
            }
            if (arguments.contains(" ")) {
                System.out.println("Too many arguments\nWill work only with first");
                arguments = arguments.substring(0, arguments.indexOf(" "));
            }

            if (command.equals("exit")) {
                notExit = false;
            } else {
                if (hashMap.containsKey(command)) {
                    hashMap.get(command).execute(arguments, inner, this);
                } else {
                    System.out.println("Can't find this command!");
                }
            }
        }
    }

    private void startFromScript() {
        boolean notExit = true;
        int i = 0;

        while (notExit) {
            i++;
            if(inner.hasNextLine()) {
                String command = inner.nextLine();

                String arguments = "";
                if (command.contains(" ")) {
                    arguments = command.substring(command.indexOf(" ") + 1);
                    command = command.substring(0, command.indexOf(" "));
                }
                if (command.isEmpty()) {
                    System.out.println("Wrong entering at " + i + " command!");
                    break;
                }
                if (arguments.contains(" ")) {
                    System.out.println("Too many arguments at " + i + " command!\nWill work only with first");
                    arguments = arguments.substring(0, arguments.indexOf(" "));
                }

                if (command.equals("exit")) {
                    notExit = false;
                } else {
                    if (hashMap.containsKey(command)) {
                        hashMap.get(command).execute(arguments, inner, this);
                    } else {
                        System.out.println("This is not command in file!");
                        i--;
                    }
                }
            } else {
                System.out.println("Script" +  " complete at " + (i-1) +" command!");
                break;
            }
        }
    }

    boolean isNotRight = true;

    public TicketOwner getTicketOwner(boolean reReg, boolean login) {
        return new TicketReader(inner,isFile).getTicketOwner(reReg, login);
    }

    public Ticket getTicket() {
        return new TicketReader(inner,isFile).getTicket();
    }
    ///////////////////
    public Venue getVenue(){
        return new TicketReader(inner,isFile).getVenue();
    }
}
