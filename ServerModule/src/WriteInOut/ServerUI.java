package WriteInOut;

import Commands.*;
import DataBase.ThreadResurses;
import WebRes.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class ServerUI {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerUI.class);
    public Hashtable<String, ServerExecutable> replies = new Hashtable<String, ServerExecutable>();

    //private static Scanner scan = new Scanner(System.in);

    private ThreadResurses thres;

    public Command getCom() {
        return com;
    }

    Command com = new Command();

    private boolean haveClient = true;

    public boolean logined = false;

    public ServerUI(ThreadResurses tr) {
        thres = tr;
        tr.serverUI = this;
        replies.put("clear", new Clear(tr));
        replies.put("execute_script", new ExecuteScript(tr));
        replies.put("exit", new Exit(tr));
        replies.put("filter_by_venue", new FilterByVenue(tr));
        replies.put("help", new Help(tr));
        replies.put("info", new Info(tr));
        replies.put("insert", new Insert(tr));
        replies.put("print_descending", new PrintDescending(tr));
        replies.put("print_field_descending", new PrintFieldDescending(tr));
        replies.put("remove_greater_key", new RemoveGreaterKey(tr));
        replies.put("remove_key", new RemoveKey(tr));
        replies.put("remove_lower", new RemoveLower(tr));
        replies.put("replace_if_lower", new ReplaceIfLower(tr));
        replies.put("show", new Show(tr));
        replies.put("update", new Update(tr));
        replies.put("register", new Register(tr));
        replies.put("change_register", new ChangeRegister(tr));
        replies.put("login", new Login(tr));
        replies.put("check_condition", new CheckCondition(thres));
    }

    public void start() {
        while (isHaveClient()) {
            try {
//                if(System.in.available() != 0){
//                    String str = scan.nextLine();
//                    if(str.equals("exit")){
//                        com.setFirstArgument("Server end receiving");
//                        com.setNameOfCommand(thres.receiver.receive().getNameOfCommand());
//                        thres.sender.send(com);
//                        log.info("Server end receiving");
//                        return;
//                    }else {
//                        System.out.println("Wrong command");
//                    }
//                } else {
                com = thres.receiver.receive();
                if ("Test".equals(com.getNameOfCommand())) {
                    thres.sender.send(com);
                    continue;
                }
//                if(com.getNameOfCommand() == null){
//                    com = thres.receiver.receive();
//                }
                thres.sender.send(com);
                replies.get(com.getNameOfCommand()).answer(com);
//}
//            } catch (IOException e) {
//                log.info("", e);
            } catch (NullPointerException e) {
                log.info("Null command received", e);
            }
        }
    }

    public void startFromScript(String fileName, Command com) {
        boolean notExit = true;
        int i = 0;
        try (Scanner scan = new Scanner(new File(fileName))) {
//            Locale l = scan.locale();
//            String s = null;
//            while (scan.hasNextLine()){
//                s = scan.nextLine();
//            }
//            if(!"exit".equals(s)){
//                thres.receiver.receive();
//                Command command1 = new Command();
//                command1.setNameOfCommand("exit");
//                thres.sender.send(command1);
//            }
            while (notExit) {
                i++;
                if (scan.hasNextLine()) {
                    String command = scan.nextLine();
                    log.info(i + " command: " + command);
                    String arguments = "";
                    if (command.contains(" ")) {
                        arguments = command.substring(command.indexOf(" ") + 1);
                        command = command.substring(0, command.indexOf(" "));
                    }
                    if (command.isEmpty()) {
                        com.setFirstArgument(com.getFirstArgument() + "Wrong entering at " + i + " command!");
                        log.info("Wrong entering at " + i + " command!");
                        break;
                    }
                    if (arguments.contains(" ")) {
                        com.setFirstArgument(com.getFirstArgument() + "Too many arguments at " + i + " command!\nWill work only with first");
                        log.info("Too many arguments at " + i + " command!\nWill work only with first");
                        arguments = arguments.substring(0, arguments.indexOf(" "));
                    }

                    if (command.equals("exit") | !scan.hasNextLine() | !replies.containsKey(command)) {
                        notExit = false;
                        thres.receiver.receive();
                        Command command1 = new Command();
                        command1.setNameOfCommand("exit");
                        thres.sender.send(command1);
                    } else {
                        if (replies.containsKey(command)) {
                            replies.get(command).talk(arguments, scan);
                        } else {
                            com.setFirstArgument(com.getFirstArgument() + "This is not command in file!");
                            log.info("This is not command in file!");
                            i--;
                        }
                    }
                } else {
                    com.setFirstArgument(com.getFirstArgument() + "Script" + " complete at " + (i - 1) + " command!");
                    log.info("Script" + " complete at " + (i - 1) + " command!");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            log.info("Script", e);
        }

    }

    public boolean isHaveClient() {
        return haveClient;
    }

    public void setHaveClient(boolean haveClient) {
        this.haveClient = haveClient;
    }

//    public boolean checkTowner(TicketOwner ticketOwner){
//        if(thres.owners.containsKey(ticketOwner.getId())){
//            thres.ticketOwner = ticketOwner;
//            return true;
//        }else {
//            return false;
//        }
//    }
}
