package ru.ifmo.se;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.ifmo.se.Collection.Ticket;
import ru.ifmo.se.Commands.ExeClass;
import ru.ifmo.se.Parser.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    public static int hashSize = 1000;

    public static Hashtable<String, Ticket> TicketsHashTable = new Hashtable<String, Ticket>(hashSize);

    static ZonedDateTime hashCreationDate = ZonedDateTime.now();

    public static String fileRWName = "Tickets.json";

    public static boolean[] programState;
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

    public static ZonedDateTime getHashCreationDate() {
        return hashCreationDate;
    }

    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
        Main.hashCreationDate = hashCreationDate;
    }

    public static void main(String[] args) {

        fileRWName = readLogs();
        Scanner scan = new Scanner(System.in);
        File f = new File(fileRWName);

        boolean[] ps = new boolean[9];

        for (boolean s : ps) {
            s = false;
        }

        if (f.exists()) {
            System.out.println("Opened file from previous session: " + fileRWName);
            ps = writeFileStatus(fileRWName);
        }



        boolean end = true;
        while (end) {
            File checkFile = f;
            boolean[] cs = showCommands(ps);
            System.out.println("Enter only one digit. Chooooooose your command:");
            String com = scan.nextLine();

            switch (com.charAt(0)) {
                case '1':
                    if (cs[0]) {
                        try {
                            checkFile.createNewFile();
                        } catch (IOException e) {
                            System.out.println("Can't create file");
                        }
                    } else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '2':
                    System.out.println("Enter file name: ");
                    String string = scan.nextLine();
                    checkFile = new File(string);
                    ps = writeFileStatus(string);
                    break;
                case '3':
                    if (cs[2]) {
                        end = false;
                        f = checkFile;
                    }
                    break;
                case '4':
                    end = false;
                    break;
                default:
                    System.out.println("Wrong command digit!");
                    break;
            }
        }

        if (ps[6]) {
            JsonParser jPars = new JsonParser(f);
            jPars.parse();
        } else {
            System.out.println("Can't get collection because of permission denied!");
        }

        ExeClass eCla = new ExeClass();
        eCla.start(false);


    }

    private static String readLogs() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/ru/ifmo/se/Parser/logs.json"));
        } catch (FileNotFoundException e) {
            System.out.println("Troubles with src/ru/ifmo/se/Parser/logs.json");
        }
        String string = scanner.nextLine();
        string += scanner.nextLine();
        string += scanner.nextLine();

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(string);
            System.out.println(jsonObject);
            return (String) jsonObject.get("FileName");
        } catch (ParseException e) {
            System.out.println("Trouble with logs " + string);
        }

        return "Tickets.json";
    }

    private static boolean[] writeFileStatus(String jStr) {

        File f = new File(jStr);

        boolean[] ps = new boolean[10];
        for (boolean s : ps) {
            s = false;
        }

        if (f.isFile()){
            String[] strings = splitPathInArr(f);
            ps = writeDirStatus(strings);

            if (ps[0]) {
                if (f.exists()){

                    ps[5] = true;
                    if (f.canRead()) {
                        System.out.println("Can read! ");
                        ps[6] = true;
                    } else {
                        System.out.println("Can't read! ");
                        ps[6] = false;
                    }
                    if (f.canWrite()) {
                        System.out.println("Can write! ");
                        ps[7] = true;
                    } else {
                        System.out.println("Can't write! ");
                        ps[7] = false;
                    }
                    if (f.canExecute()) {
                        System.out.println("Can execute! ");
                        ps[8] = true;
                    } else {
                        System.out.println("Can't execute! ");
                        ps[8] = false;
                    }

                } else {
                    System.out.println("File doesn't exists!");
                    ps[5] = false;
                }
            } else {
                System.out.println("Dir doesn't exist");
            }
        }else{
            System.out.println("It's not a file!!!");
            ps[9] = true;
        }
        return ps;
    }

    private static boolean[] writeDirStatus(String[] strings) {

        String string = "";
        String splitter = "" + strings[0].charAt(strings[0].length() - 1);
        boolean[] ps = new boolean[10];

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


        if (dir.exists()) {
            System.out.println("Dir exists!");
            ps[0] = true;
            if (dir.canRead()) {
                System.out.println("Can read Dir!");
                ps[1] = true;
            } else {
                System.out.println("Can't read Dir! ");
                ps[1] = false;
            }
            if (dir.canWrite()) {
                System.out.println("Can write Dir! ");
                ps[2] = true;
            } else {
                System.out.println("Can't write Dir! ");
                ps[2] = false;
            }
            if (dir.canExecute()) {
                System.out.println("Can execute Dir! ");
                ps[3] = true;
            } else {
                System.out.println("Can't execute Dir! ");
                ps[3] = false;
            }
        } else {
            System.out.println("Dir doesn't exists or permission denied!");
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
        boolean[] cs = new boolean[4];
        for (boolean s : cs) {
            s = false;
        }

        if (ps[0] & ps[2] & !ps[5]) {
            cs[0] = true;
            System.out.println("1 - Create new file");

        }

        System.out.println("2 - Choose another file");
        cs[1] = true;

        if ((ps[7] | ps[6]) & ! ps[9]) {
            System.out.println("3 - Save as main file new one and start working");
            if (!ps[6]) {
                System.out.println("\tWe cant get collection from it, only write in!");
            }
            if (!ps[7]) {
                System.out.println("\tWe wouldn't save whe collection!");
            }
            cs[2] = true;
        }

        System.out.println("4 - Exit with default file (Tickets.json) and start working");
        cs[3] = true;

        return cs;
    }
}
