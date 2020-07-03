package Commands;

import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CheckCondition extends AbstractCommand {

    public static ZonedDateTime lastChange = ZonedDateTime.now();

    private static HashMap<ZonedDateTime, Ticket> ticketsIns = new HashMap<>();
    private static HashMap<ZonedDateTime, Ticket> ticketsUpd = new HashMap<>();
    private static HashMap<ZonedDateTime, Ticket> ticketsRem = new HashMap<>();

    private static HashMap<ThreadResurses, ZonedDateTime> currentVersions = new HashMap<>();

    public CheckCondition(ThreadResurses threadResurses) {
        name = "check_condition";
        tr = threadResurses;
        currentVersions.put(tr, ZonedDateTime.now());
    }

    public void closeSynchronisation(){
        currentVersions.remove(tr);
    }

    private static void setLastChange() {
        lastChange = ZonedDateTime.now();
    }

    public static synchronized void addTicket(Ticket ticket) {
        ticketsIns.put(ZonedDateTime.now(), ticket);
        setLastChange();
    }

    public static synchronized void updateTicket(Ticket ticket) {
        ticketsUpd.put(ZonedDateTime.now(), ticket);
        setLastChange();
    }

    public static synchronized void removeTicket(Ticket ticket) {
        ticketsRem.put(ZonedDateTime.now(), ticket);
        setLastChange();
    }


    @Override
    public synchronized void exe() {
        ZonedDateTime zdt = (ZonedDateTime) com.getThirdArgument();
        if (zdt.compareTo(lastChange) < 0) {
            com.setNameOfCommand(name);
            com.setFirstArgument("Prev version");
            com.setThirdArgument(lastChange);
            ArrayList<Command> commandArrayList = new ArrayList<>();
            ZonedDateTime userVersionTime = currentVersions.get(tr);
            ticketsIns
                    .keySet()
                    .stream()
                    .filter(ticketTime -> ticketTime.compareTo(userVersionTime) > 0)
                    .forEach(ticketTime -> {
                        Command c = new Command();
                        c.setNameOfCommand(name);
                        c.setFirstArgument("Ins");
                        c.setThirdArgument(ticketsIns.get(ticketTime));
                    });
            ticketsUpd
                    .keySet()
                    .stream()
                    .filter(ticketTime -> ticketTime.compareTo(userVersionTime) > 0)
                    .forEach(ticketTime -> {
                        Command c = new Command();
                        c.setNameOfCommand(name);
                        c.setFirstArgument("Upd");
                        c.setThirdArgument(ticketsUpd.get(ticketTime));
                    });
            ticketsRem
                    .keySet()
                    .stream()
                    .filter(ticketTime -> ticketTime.compareTo(userVersionTime) > 0)
                    .forEach(ticketTime -> {
                        Command c = new Command();
                        c.setNameOfCommand(name);
                        c.setFirstArgument("Rem");
                        c.setThirdArgument(ticketsRem.get(ticketTime));
                    });
            send(commandArrayList);
        } else {
            com.setFirstArgument("Last version");
            com.setSecondArgument(0);
            tr.sender.send(com);
        }
        currentVersions.replace(tr, lastChange);
        ZonedDateTime min = currentVersions.values().stream().sorted().findFirst().get();
        ticketsIns.keySet().stream().filter(zonedDateTime -> zonedDateTime.compareTo(min) < 0).forEach(zonedDateTime -> ticketsIns.remove(zonedDateTime));
        ticketsUpd.keySet().stream().filter(zonedDateTime -> zonedDateTime.compareTo(min) < 0).forEach(zonedDateTime -> ticketsIns.remove(zonedDateTime));
        ticketsRem.keySet().stream().filter(zonedDateTime -> zonedDateTime.compareTo(min) < 0).forEach(zonedDateTime -> ticketsIns.remove(zonedDateTime));
    }


    @Override
    protected void setArgs(String str, Scanner scanner) {
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
    }
}
