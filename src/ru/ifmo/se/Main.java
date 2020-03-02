package ru.ifmo.se;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.ifmo.se.Collection.Ticket;
import ru.ifmo.se.Commands.ExeClass;
import ru.ifmo.se.Parser.JsonParser;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    public static int hashSize = 1000;

    public static Hashtable<String, Ticket> TicketsHashTable = new Hashtable<String, Ticket>(hashSize);

    static ZonedDateTime hashCreationDate = ZonedDateTime.now();

    public static String fileRWName = "Test.json";

    public static boolean[] programState;
    //0 - dir of file exists
    //1 - dir of file readable
    //2 - dir of file writeable
    //3 - dir of file executable
    //4 - Splitter is '/' of file exists
    //5 - file of file exists
    //6 - file of file readable
    //7 - file of file writeable
    //8 - file of file executable

    public static ZonedDateTime getHashCreationDate() {
        return hashCreationDate;
    }

    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
        Main.hashCreationDate = hashCreationDate;
    }

    public static void main(String[] args) {

        fileRWName = readLogs();

        File f = new File(fileRWName);

        boolean[] ps = new boolean[9];

        for (boolean s : ps) {
            s = false;
        }

        if (f.exists()) {
            System.out.println("Opened file from previous session: " + fileRWName);
            ps = writeFileStatus(fileRWName);
        }

        Scanner scan = new Scanner(System.in);
        String filename = scan.nextLine();
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
                    end = false;

                    break;
                case '4':
                    end = false;
                    break;
                default:
                    System.out.println("Wrong command digit!");
                    break;
            }
        }

        JsonParser jPars = new JsonParser(f);
        jPars.parse();
        ExeClass eCla = new ExeClass();
        eCla.start(false);

    }

    private static String readLogs() {
        Scanner scanner = new Scanner("ru/ifmo/se//Parser/logs.json");
        String string = scanner.nextLine();
        string = scanner.nextLine();
        string = scanner.nextLine();
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(string);
            return (String) jsonObject.get("FileName");
        } catch (ParseException e) {
            System.out.println("Trouble with logs " + string);
        }

        return "Test.json";
    }

    private static boolean[] writeFileStatus(String jStr) {

        File f = new File(jStr);

        String[] strings = splitPathInArr(f);
        boolean[] ps = writeDirStatus(strings);

        if (ps[0]) {
            if (f.exists()) {
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

        if(ps[6]){
            System.out.println("We wouldn't have collection at start with this file!");
        }
        if (ps[7]){
            System.out.println("We wouldn't have possibility to save collection");
        }

        return ps;
    }

    private static boolean[] writeDirStatus(String[] strings) {

        String string = "";
        String splitter = "" + strings[0].charAt(strings[0].length() - 1);
        boolean[] ps = new boolean[9];

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
        for (boolean s : cs){
            s = false;
        }

        if (ps[0] & ps[2]) {
            cs[0] = true;
            System.out.println("1 - Create new file");

        }
        System.out.println("2 - Choose another file");

        if (ps[7]){
            System.out.println("3 - Save new file and start working");
            cs[2] = true;
        }

        System.out.println("4 - Exit with default file and start working");

        return cs;
    }
}
