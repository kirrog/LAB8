package ru.ifmo.se.WriteInOut;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Terminal {

    public static String[] RWfiles = new String[]{"Tickets.json", readLogs(), ""};
    //default
    //last session
    //from argument

    public static boolean[] programState = initialize();
    //0 - dir of file exists
    //1 - dir of file readable
    //2 - dir of file writeable
    //3 - dir of file executable
    //4 - Splitter is '/' of file exists
    //5 - file exists
    //6 - file readable
    //7 - file writeable
    //8 - file executable
    //9 - entered isn't file (example: dir)
    //10 - last session file
    //11 - default file
    //12 - argument file

    private String[] args;

    private boolean full = true;

    public Terminal(String[] argss) {
        this.args = argss;
    }

    public void start() {
        Scanner scan = new Scanner(System.in);

        RWfiles[1] = readLogs();

        File workFile = new File(RWfiles[1]);
        File defaulted = new File(RWfiles[0]);
        File checkFile = null;

        if (args.length > 0) {
            RWfiles[2] = args[args.length - 1];
            checkFile = new File(RWfiles[2]);
        }

        boolean[] ps = initialize();

        if (workFile.isFile() & workFile.exists()) {
            System.out.println("Last session file found");
            programState[11] = true;
        } else {
            System.out.println("Can't find last session file");
        }
        if (defaulted.isFile() & defaulted.exists()) {
            System.out.println("Default file found");
            programState[10] = true;
        } else {
            System.out.println("Can't find default file");
        }


        if (!(checkFile == null)) {
            if (checkFile.isFile() & checkFile.exists()) {
                System.out.println("Argument file found");
                System.out.println("Argument file status: ");
                programState[12] = true;
                ps = writeFileStatus(RWfiles[2]);
            }
        } else {
            System.out.println("Argument file didn't found");
            if (programState[10]) {
                System.out.println("Default file status: ");
                ps = writeFileStatus(defaulted.getAbsolutePath());
            } else if (programState[11]) {
                System.out.println("Last session file status: ");
                ps = writeFileStatus(workFile.getAbsolutePath());
            }
        }

        boolean end = true;
        while (end) {
            boolean[] cs = showCommands(ps);
            System.out.print("Enter only one digit. Choose your command:\n>");
            String com = scan.nextLine();

            switch (com.charAt(0)) {
                case '1':
                    if (cs[0]) {
                        try {
                            checkFile.createNewFile();
                            ps = writeFileStatus(checkFile.getAbsolutePath());
                        } catch (IOException e) {
                            System.out.println("Can't create file!");
                        }
                    } else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '2':
                    System.out.print("Enter file name: \n>");
                    String string = scan.nextLine();
                    checkFile = new File(string);
                    RWfiles[2] = string;
                    ps = writeFileStatus(RWfiles[2]);
                    break;
                case '3':
                    if (cs[2]) {
                        end = false;
                        workFile = checkFile;
                    } else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '4':
                    if (cs[3]) {
                        workFile = defaulted;
                        end = false;
                    } else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '5':
                    if (cs[4]) {
                        workFile = new File(RWfiles[1]);
                        end = false;
                    } else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '6':
                    end = false;
                    programState[13] = true;
                    break;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }

        if (!((workFile == null) | programState[13])) {
            RWfiles[2] = workFile.getAbsolutePath();
            programState = writeFileStatus(RWfiles[2]);
            if (workFile.canRead()) {
                JsonParser jPars = new JsonParser(workFile);
                jPars.parse();
            }
        } else {
            System.out.println("Program work without file");
        }
    }

    private static String readLogs() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/ru/ifmo/se/Parser/logs.json"));
            String string = scanner.nextLine();
            string += scanner.nextLine();
            string += scanner.nextLine();
            try {
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(string);
                return (String) jsonObject.get("FileName");
            } catch (ParseException e) {
                System.out.println("Trouble with logs " + string);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Troubles with src/ru/ifmo/se/Parser/logs.json");
        }
        return "Tickets.json";
    }

    public static boolean[] writeFileStatus(String jStr) {

        RWfiles[2] = jStr;

        File f = new File(jStr);

        boolean[] ps;

        String[] strings = splitPathInArr(f);
        ps = writeDirStatus(strings);

        ps[10] = programState[10];
        ps[11] = programState[11];
        ps[12] = programState[12];

        if (f.exists()) {
            if (ps[0]) {
                System.out.print("File status: ");

                if (f.isFile()) {
                    System.out.print("exists: ");
                    ps[5] = true;
                    if (f.canRead()) {
                        System.out.print("r");
                        ps[6] = true;
                    } else {
                        System.out.print("-");
                        ps[6] = false;
                    }
                    if (f.canWrite()) {
                        System.out.print("w");
                        ps[7] = true;
                    } else {
                        System.out.print("-");
                        ps[7] = false;
                    }
                    if (f.canExecute()) {
                        System.out.println("x");
                        ps[8] = true;
                    } else {
                        System.out.println("-");
                        ps[8] = false;
                    }

                } else {
                    System.out.println("not a file");
                    ps[5] = false;
                }
            }
        } else {
            System.out.println("It isn't exist!!!");
            ps[9] = true;
        }

        return ps;
    }

    private static boolean[] writeDirStatus(String[] strings) {

        String string = "";
        String splitter = "" + strings[0].charAt(strings[0].length() - 1);
        boolean[] ps = new boolean[16];

        for (boolean booleans : ps) {
            booleans = false;
        }

        if (splitter == "/") {
            string = "/";
            ps[4] = true;
        }

        for (int i = 0; i < strings.length - 1; i++) {
            string = string + splitter + strings[i];
        }

        File dir = new File(string);

        System.out.print("Dir status: ");

        if (dir.exists()) {
            System.out.print("exists: ");
            ps[0] = true;
            if (dir.canRead()) {
                System.out.print("r");
                ps[1] = true;
            } else {
                System.out.print("-");
                ps[1] = false;
            }
            if (dir.canWrite()) {
                System.out.print("w");
                ps[2] = true;
            } else {
                System.out.print("-");
                ps[2] = false;
            }
            if (dir.canExecute()) {
                System.out.println("x");
                ps[3] = true;
            } else {
                System.out.println("-");
                ps[3] = false;
            }
        } else {
            System.out.println("not exists");
            ps[0] = false;
        }

        return ps;
    }

    private static String[] splitPathInArr(File f) {
        String[] strings = null;
        if (f.getAbsolutePath().split("\\\\").length > f.getAbsolutePath().split("/").length) {
            strings = f.getAbsolutePath().split("\\\\");
            strings[0] += "\\";
        } else {
            strings = f.getAbsolutePath().split("/");
            strings[0] += "/";
        }

        return strings;
    }

    private static boolean[] showCommands(boolean[] ps) {
        // dir exists read write exe, isLinux, file exists  read write exe
        // create new file
        // exit from program
        // choose another file
        boolean[] cs = new boolean[5];
        for (boolean s : cs) {
            s = false;
        }

        if (ps[0] & ps[2] & !ps[5] & (!(RWfiles[2].equals(RWfiles[1]))) & (!(RWfiles[2].equals(RWfiles[0])))) {
            cs[0] = true;
            System.out.println("1 - Create new file");
        }

        System.out.println("2 - Choose another file");
        cs[1] = true;

        if ((ps[7] | ps[6]) & !ps[9] & (!(RWfiles[2].equals(RWfiles[1]))) & (!(RWfiles[2].equals(RWfiles[0])))) {
            System.out.println("3 - Save as main file new one and start working");
            if (!ps[6]) {
                System.out.println("\tWe can't get collection from it, only write in!");
            }
            if (!ps[7]) {
                System.out.println("\tWe wouldn't save whe collection!");
            }
            cs[2] = true;
        }

        if (ps[10]) {
            System.out.println("4 - Exit with default file (Tickets.json) and start working");
            cs[3] = ps[10];
        }

        if (ps[11] & (!(RWfiles[1].equals("Tickets.json")))) {
            System.out.println("5 - Exit with last session file (" + RWfiles[1] + ") and start working");
            cs[4] = ps[11];
        }

        System.out.println("6 - Continue without collection");

        return cs;
    }

    private static boolean[] initialize(){
        boolean[] array = new boolean[16];

        for (boolean s : array) {
            s = false;
        }

        return array;
    }
}
