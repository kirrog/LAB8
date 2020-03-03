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
    //10 - last session file
    //11 - default file


    public static ZonedDateTime getHashCreationDate() {
        return hashCreationDate;
    }

    public static void setHashCreationDate(ZonedDateTime hashCreationDate) {
        Main.hashCreationDate = hashCreationDate;
    }

    public static void main(String[] args) {

        fileRWName = readLogs();
        Scanner scan = new Scanner(System.in);
        File workFile = new File(fileRWName);
        File defaulted = new File("Tickets.json");
        File checkFile = null;

        if (args.length > 0){
            checkFile = new File(args[args.length-1]);
        }else{
            System.out.println("You didn't wrote any file!");
        }


        boolean[] ps = new boolean[16];
        for (boolean s : ps) {
            s = false;
        }



        if(workFile.isFile() & workFile.exists()){
            System.out.println("Last session file found");
            programState[10] = true;
        }else {
            System.out.println("Can't find last session file");
        }
        if(defaulted.isFile() & defaulted.exists()){
            System.out.println("Default file found");
            programState[11] = true;
        }else {
            System.out.println("Can't find default file");
        }
        if(! (checkFile == null)){
            if (checkFile.isFile() & checkFile.exists()){
                System.out.println("Argument file found");
                ps = writeFileStatus(args[args.length-1]);
            }
        }else{
            System.out.println("Argument didn't find");
        }

        boolean end = true;
        while (end) {
            boolean[] cs = showCommands(ps);
            System.out.println("Enter only one digit. Chooooooose your command:");
            String com = scan.nextLine();

            switch (com.charAt(0)) {
                case '1':
                    if (cs[0]) {
                        try {
                            checkFile.createNewFile();
                            ps = writeFileStatus(checkFile.getAbsolutePath());
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
                        workFile = checkFile;
                    } else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '4':
                    if(cs[3]){
                        workFile = defaulted;
                        end = false;
                    }else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '5':
                    if(cs[4]){
                        workFile = new File(fileRWName);
                        end = false;
                    }else {
                        System.out.println("You can't use this command!");
                    }
                    break;
                case '6':
                    end = false;
                    break;
                default:
                    System.out.println("Wrong command digit!");
                    break;
            }
        }
        if (!(workFile == null)){
            fileRWName = workFile.getAbsolutePath();
            programState = writeFileStatus(fileRWName);
        }else{
            System.out.println("Houston we have problems with previous or default file");
        }

        if (ps[6]) {
            JsonParser jPars = new JsonParser(workFile);
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

    private static boolean[] writeFileStatus(String jStr) {

        File f = new File(jStr);

        boolean[] ps;

        String[] strings = splitPathInArr(f);
        ps = writeDirStatus(strings);

        ps[10] = programState[10];
        ps[11] = programState[11];

        if (!(f == null)){
            if (f.isFile()) {


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
            }else{
                System.out.println("It's not a file!!!");
                ps[9] = true;
            }
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
        boolean[] cs = new boolean[5];
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

        if (ps[11]){
            System.out.println("4 - Exit with default file (Tickets.json) and start working");
            cs[3] = ps[11];
        }
        if (ps[10]){
            System.out.println("5 - Exit with last session file (" + fileRWName + ") and start working");
            cs[4] = ps[10];
        }

        System.out.println("6 - Continue without collection");

        return cs;
    }
}
