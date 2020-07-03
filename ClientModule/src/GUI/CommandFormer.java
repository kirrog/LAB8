package GUI;

import Collection.*;
import DataBase.CriptoMaker;
import DataBase.TicketOwner;
import GUI.Tables.BaseTableModel;
import UI.AbstractCommand;
import UI.Commandes.*;
import WebRes.Command;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class CommandFormer {

    private static ArrayList<Ticket> ticketArrayList = new ArrayList<>();

    public static TicketOwner getTicketOwner() {
        return ticketOwner;
    }

    public static void setTicketOwner(TicketOwner ticketOwner) {
        CommandFormer.ticketOwner = ticketOwner;
    }

    static TicketOwner ticketOwner;

    public static String answer = "";
    public static boolean answerChanged = false;

    //private static ConcurrentLinkedQueue<Object> executeCommands = new

    static {
        Ticket t;
        ticketOwner = new TicketOwner(10, "Lex", new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, "rroogg76aacchh@gmail.com");
        t = new Ticket(10,
                "First",
                new Coordinates(12, 10l, 15.4d),
                ZonedDateTime.now(),
                120,
                "Comment",
                true,
                TicketType.BUDGETARY,
                new Venue(1, "start", 150, VenueType.MALL,
                        new Address(2, "fdhkkadifgahd;thahdg",
                                new Location(3, 4.4d, 5.4f, 7l, "Hey Yooo!"))),
                ticketOwner);
        t.setKey("Fir");
        ticketArrayList.add(t);
        t = new Ticket(2,
                "Second",
                new Coordinates(3, 75l, 25.6d),
                ZonedDateTime.now(),
                30,
                "Comment2",
                false,
                TicketType.CHEAP,
                new Venue(2, "anotherStart", 30, VenueType.LOFT,
                        new Address(3, "fdhkkadifgahd",
                                new Location(4, 8.4d, 5.4f, 8l, "Okey!"))),
                ticketOwner);
        t.setKey("Fir1");
        ticketArrayList.add(t);
        t = new Ticket(3,
                "Third",
                new Coordinates(5, 32l, 6.4d),
                ZonedDateTime.now(),
                20,
                "Comment3",
                true,
                TicketType.USUAL,
                new Venue(3, "nextStart", 70, VenueType.OPEN_AREA,
                        new Address(4, "thahdgojgcmsfkdjfwfs",
                                new Location(5, 4.7d, 5.4f, 9l, "Nope"))),
                ticketOwner);
        t.setKey("Fir2");
        ticketArrayList.add(t);
        t = new Ticket(4,
                "Fourth",
                new Coordinates(7, 16l, 17.3d),
                ZonedDateTime.now(),
                10,
                "Comment4",
                false,
                TicketType.VIP,
                new Venue(4, "5start", 50, VenueType.PUB,
                        new Address(5, "fdhkkadifweryuioopahdg",
                                new Location(6, 14.9d, 5.4f, 70l, "One more"))),
                ticketOwner);
        t.setKey("Fir3");
        ticketArrayList.add(t);
        t = new Ticket(8,
                "Eight",
                new Coordinates(8, 126l, 75.3d),
                ZonedDateTime.now(),
                15,
                "Comment5",
                false,
                TicketType.VIP,
                new Venue(5, "Hey", 150, VenueType.OPEN_AREA,
                        new Address(6, "fdhgkdgkuioopahdg",
                                new Location(7, 16.9d, 57.4f, 73l, "No more"))),
                ticketOwner);
        t.setKey("Fir4");
        ticketArrayList.add(t);
        t = new Ticket(5,
                "Fifth",
                new Coordinates(9, 136l, 27.3d),
                ZonedDateTime.now(),
                25,
                "Comment6",
                false,
                TicketType.VIP,
                new Venue(6, "Low", 250, VenueType.LOFT,
                        new Address(7, "fdhkkadifwekfjtbddsahdg",
                                new Location(8, 24.9d, 15.4f, 7l, "Any more"))),
                ticketOwner);
        t.setKey("Fir5");
        ticketArrayList.add(t);
        t = new Ticket(6,
                "Шестой",
                new Coordinates(10, 146l, 31.3d),
                ZonedDateTime.now(),
                31,
                "Comment7",
                true,
                TicketType.VIP,
                new Venue(7, "This", 350, VenueType.MALL,
                        new Address(8, "fdhkkljhgjderyuioopahdg",
                                new Location(9, 141.9d, 53.4f, 73l, "Soo more"))),
                ticketOwner);
        t.setKey("Fir6");
        ticketArrayList.add(t);
        t = new Ticket(7,
                "Седьмой",
                new Coordinates(11, 156l, 51.3d),
                ZonedDateTime.now(),
                26,
                "Comment8",
                true,
                TicketType.VIP,
                new Venue(8, "World", 450, VenueType.STADIUM,
                        new Address(9, "fdhkkadifweajdgfopahdg",
                                new Location(10, 14.94d, 5.24f, 73l, "And more"))),
                ticketOwner);
        t.setKey("Fir7");
        ticketArrayList.add(t);
    }

    public static void setServerStatus(int serStat) {
        switch (serStat) {
            case -1:
                serverStatus = "Server end receiving";
                break;
            case 0:
                serverStatus = "Not connected";
                break;
            case 1:
                serverStatus = "Connected";
                break;
            case 2:
                serverStatus = "Executing";
                break;
            case 3:
                serverStatus = "Bad Script";
                break;
            default:
                break;
        }
    }

    public static void setWebStatus(int serStat) {
        switch (serStat) {
            case 0:
                webStatus = "Sending";
                break;
            case 1:
                webStatus = "Receiving";
                break;
            default:
                break;
        }
    }

    public static synchronized boolean checkCondition() {
        CheckCondition cc = (CheckCondition) AbstractCommand.replies.get("check_condition");
        cc.check("check_condition", "");
        ArrayList<Command> ac = (ArrayList<Command>) cc.getResult();
        if (ac.size() > 0) {
            for (int i = 0; i < ac.size(); i++) {
                Command c = ac.get(i);
                switch (c.getFirstArgument()) {
                    case "Ins":
                        ManipulaterElements.addTicket((Ticket) (c.getThirdArgument()));
                        break;
                    case "Upd":
                        ManipulaterElements.updateTicket((Ticket) (c.getThirdArgument()));
                        break;
                    case "Rem":
                        ManipulaterElements.removeTicket((Ticket) (c.getThirdArgument()));
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
        return false;
    }

    public static synchronized String help() {
        Help help = (Help) AbstractCommand.replies.get("help");
        help.check("help", "");
        answerChanged = true;
        return null;
    }

    public static void setMissedTicketNumber(int i) {
        missedTicketNumber = i;
    }

    private static String serverStatus = "Not connected";
    private static String webStatus = "Sending";
    private static int ticketNumber = 0;
    private static int missedTicketNumber = 0;

    public static synchronized String info() {
        return "<html>"
                + "Server Status : " + serverStatus + "<br>"
                + "Web Status : " + webStatus + "<br>"
                + "Tickets Number : " + ticketNumber + "<br>"
                + "Version Time : " + CheckCondition.currentVersionTime + "<br>"
                + "Missed Tickets Number : " + missedTicketNumber + "<br>"
                + "</html>";
    }

    public static synchronized ArrayList<Ticket> show() {
        Show show = (Show) AbstractCommand.replies.get("show");
        show.check("show", "");
        ArrayList<Ticket> at = new ArrayList<>();
        ((ArrayList<Command>) (show.getResult())).stream().map(com -> (Ticket) (com.getThirdArgument())).forEach(ticket -> at.add(ticket));
        answerChanged = true;
        return at;
    }

    public static synchronized boolean insertKey(String key, Ticket ticket) {
        Insert insert = (Insert)AbstractCommand.replies.get("insert");
        insert.setArg(ticket);
        insert.check("insert", key);
        answerChanged = true;
        Ticket ticket1 = (Ticket) insert.getResult();
        if(ticket1 != null){
            ManipulaterElements.addTicket(ticket1);
            return true;
        }else {
            return false;
        }
    }

    public static synchronized boolean update(int id, Ticket ticket) {
        Update update = (Update)AbstractCommand.replies.get("update");
        update.setArgs(id, ticket);
        update.check("update", "");
        answerChanged = true;
        Ticket ticket1 = (Ticket) update.getResult();
        if(ticket1 != null){
            ManipulaterElements.updateTicket(ticket1);
            return true;
        }else {
            System.out.println("Not updating");
            return false;
        }
    }

    public static synchronized boolean removeKey(String key) {
        RemoveKey removeKey = (RemoveKey)AbstractCommand.replies.get("remove_key");
        removeKey.check("clear", key);
        int res = (int) (removeKey.getResult());
        answerChanged = true;
        if(res == 0){
            ManipulaterElements.removeTicket(BaseTableModel.getTicketLinkedList().stream().filter(ticket -> ticket.getKey().equals(key)).findFirst().get());
            return true;
        }
        return false;
    }

    public static synchronized boolean clear() {
        Clear clear = (Clear) AbstractCommand.replies.get("clear");
        clear.check("clear", "");
        answerChanged = true;
        BaseTableModel.getTicketLinkedList().stream()
                .filter(ticket -> ticket.getTowner().equals(ticketOwner))
                .forEach(ticket -> ManipulaterElements.removeTicket(ticket));
        return true;
    }

    public static synchronized String executeScript(String fileName) {
        ExecuteScript executeScript = (ExecuteScript) AbstractCommand.replies.get("execute_script");
        executeScript.check("execute_script", fileName);
        answerChanged = true;
        return (String) executeScript.getResult();
    }

    public static synchronized boolean exit() {
        System.out.println("Exit");
        return true;
    }

    public static synchronized int removeLower(Ticket ticket) {
        RemoveLower removeLower = (RemoveLower) AbstractCommand.replies.get("remove_lower");
        removeLower.setArg(ticket);
        removeLower.check("remove_lower", "");
        answerChanged = true;
        BaseTableModel.getTicketLinkedList().stream()
                .filter(ticket1 -> ticket1.compareTo(ticket) < 1)
                .forEach(ticket1 -> ManipulaterElements.removeTicket(ticket1));
        return (int)(removeLower.getResult());
    }

    public static synchronized boolean replaceIfLower(String key, Ticket ticket) {
        ReplaceIfLower replaceIfLower = (ReplaceIfLower) AbstractCommand.replies.get("replace_if_lower");
        replaceIfLower.setArt(key, ticket);
        replaceIfLower.check("replace_if_lower", key);
        answerChanged = true;
        int res = (int)(replaceIfLower.getResult());
        if(res == 1){
            ManipulaterElements.updateTicket(ticket);
            return true;
        }
        return false;
    }

    public static synchronized int removeGraterKey(String key) {
        RemoveGreaterKey removeGreaterKey = (RemoveGreaterKey) AbstractCommand.replies.get("remove_greater_key");
        removeGreaterKey.check("remove_greater_key", key);
        answerChanged = true;
        BaseTableModel
                .getTicketLinkedList()
                .stream()
                .filter(ticket -> ticket.getKey().compareTo(key) > 0)
                .forEach(ticket -> ManipulaterElements.removeTicket(ticket));
        return -1;
    }

    public static synchronized ArrayList<Ticket> filterByVenue(Venue venue) {
        FilterByVenue filterByVenue = (FilterByVenue) AbstractCommand.replies.get("filter_by_venue");
        filterByVenue.setArg(venue);
        filterByVenue.check("filter_by_venue", "");
        ArrayList<Ticket> at = new ArrayList<>();
        ((ArrayList<Command>) (filterByVenue.getResult())).stream().map(com -> (Ticket) (com.getThirdArgument())).forEach(ticket -> at.add(ticket));
        answerChanged = true;
        return at;
    }

    public static synchronized ArrayList<Ticket> printDescending() {
        PrintDescending printDescending = (PrintDescending) AbstractCommand.replies.get("print_descending");
        printDescending.check("print_descending", "");
        ArrayList<Ticket> at = new ArrayList<>();
        ((ArrayList<Command>) (printDescending.getResult())).stream().map(com -> (Ticket) (com.getThirdArgument())).forEach(ticket -> at.add(ticket));
        answerChanged = true;
        return at;
    }

    public static synchronized ArrayList<Ticket> printFiendDescendingType(String type) {
        PrintFieldDescending printFieldDescending = (PrintFieldDescending) AbstractCommand.replies.get("print_field_descending_type");
        printFieldDescending.check("print_field_descending_type", type);
        ArrayList<Ticket> at = new ArrayList<>();
        ((ArrayList<Command>) (printFieldDescending.getResult())).stream().map(com -> (Ticket) (com.getThirdArgument())).forEach(ticket -> at.add(ticket));
        answerChanged = true;
        return at;
    }

    public static synchronized int login(String name, char[] password, String mail) {
        TicketOwner tOwner = new TicketOwner(name, CriptoMaker.cripting(String.valueOf(password)), mail);
        Login login = (Login) AbstractCommand.replies.get("login");
        login.setTOwner(tOwner);
        login.check("login", "");
        int res = (int) login.getResult();
        if (res == 0) {
            ticketOwner = tOwner;
        }
        answerChanged = true;
        return res;
    }

    public static synchronized int register(String name, char[] password, String mail) {
        TicketOwner tOwner = new TicketOwner(name, CriptoMaker.cripting(String.valueOf(password)), mail);
        Register register = (Register) AbstractCommand.replies.get("register");
        register.setTOwner(tOwner);
        register.check("register", "");
        int res = (int) register.getResult();
        answerChanged = true;
        return res;
    }

    public static synchronized int reRegister(String name, char[] password, String mail) {
        int lo = login(name, password, mail);
        if (lo != 0) {
            return lo;
        }
        TicketOwner tOwner = new TicketOwner(name, CriptoMaker.cripting(String.valueOf(password)), mail);
        ChangeRegister chReg = (ChangeRegister) AbstractCommand.replies.get("change_register");
        chReg.setTOwner(tOwner);
        chReg.check("change_register", "");
        int res = (int) chReg.getResult();
        answerChanged = true;
        return res;
    }

    //0 - all right
    //-1 - wrong login
    //-2 - wrong password


}
