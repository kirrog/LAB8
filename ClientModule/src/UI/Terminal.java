package UI;

import WebRes.Command;

import java.util.Scanner;

import static Starter.ClientMain.sender;

public class Terminal {

    public static boolean logined = false;

    Scanner inner = new Scanner(System.in);

    public void startFromConsole() {
        boolean notExit = true;

        while (notExit) {
            System.out.print("Enter command\n> ");
            String enCom = null;

            while (notExit) {
                enCom = inner.nextLine();
                if (enCom.isEmpty()) {
                    System.out.print("> ");
                } else {
                    break;
                }
            }

            String arguments = "";

            if (enCom.contains(" ")) {
                arguments = enCom.substring(enCom.indexOf(" ") + 1);
                enCom = enCom.substring(0, enCom.indexOf(" "));
            }
            if (enCom.isEmpty()) {
                System.out.println("Wrong entering");
                continue;
            }
            if (arguments.contains(" ")) {
                System.out.println("Too many arguments\nWill work only with first");
                arguments = arguments.substring(0, arguments.indexOf(" "));
            }

            if (enCom.equals("exit")) {
                Command com = new Command();
                com.setNameOfCommand("exit");
                sender.send(com);
                notExit = false;
            } else {
                if (CommandMaker.chooseMethod(enCom,arguments)) {
                    System.out.println("Start!");
                } else {
                    System.out.println("Can't find this command!");
                }
            }
        }
    }



}
