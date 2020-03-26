package ru.ifmo.se.Commands;

import ru.ifmo.se.Collection.*;
import ru.ifmo.se.WriteInOut.Terminal;
import ru.ifmo.se.WriteInOut.TicketReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class contains methods for execution commands
 */
public class ExeClass {

    private static HashMap<String, Execute> hashMap = new HashMap<String, Execute>();

    static {
        hashMap.put("help", new Help());
        hashMap.put("clear", new Clear());
        hashMap.put("execute_script", new ExecuteScript());
        hashMap.put("filter_by_venue", new FilterByVenue());
        hashMap.put("info", new Info());
        hashMap.put("insert", new Insert());
        hashMap.put("print_descending", new PrintDescending());
        hashMap.put("print_fiend_descending", new PrintFieldDescending());
        hashMap.put("remove_greater_key", new RemoveGreaterKey());
        hashMap.put("remove_key", new RemoveKey());
        hashMap.put("remove_lower", new RemoveLower());
        hashMap.put("replace_if_lower", new ReplaceIfLower());
        hashMap.put("save", new Save());
        hashMap.put("show", new Show());
        hashMap.put("update", new Update());
    }

    private Scanner inner;

    private boolean isFile = false;

    public boolean isFile() {
        return isFile;
    }

    private String status = "";

    public ExeClass() {
        inner = new Scanner(System.in);
    }

    public ExeClass(String str) {
        try {
            inner = new Scanner(new File(str));
            isFile = true;
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading script");
            Terminal.writeFileStatus(new File(str));
        }
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
                break;
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
                    System.out.println("Wrong entering at " + i + " string!");
                    break;
                }
                if (arguments.contains(" ")) {
                    System.out.println("Too many arguments at " + i + " string!\nWill work only with first");
                    arguments = arguments.substring(0, arguments.indexOf(" "));
                }

                if (command.equals("exit")) {
                    notExit = false;
                } else {
                    if (!(inner.hasNextLine())) {
                        System.out.println("Script complete!");
                        break;
                    } else {
                        if (hashMap.containsKey(command)) {
                            hashMap.get(command).execute(arguments, inner, this);
                        } else {
                            System.out.println("This is not command in file!");
                        }
                    }
                }
            } else {
                System.out.println("Script complete!");
                break;
            }
        }
    }

    boolean isNotRight = true;

    public Ticket getTicket() {
        return new TicketReader(inner,isFile).getTicket();
    }
    ///////////////////
    public Venue getVenue(){
        return new TicketReader(inner,isFile).getVenue();
    }
}
