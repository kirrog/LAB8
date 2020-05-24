package Commands;

import Collection.Ticket;
import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class AbstractCommand implements Execute, ServerExecutable {

    protected String name;

    public String getName() {
        return name;
    }

    protected ThreadResurses tr;

    public void setTr(ThreadResurses thrres) {
        tr = thrres;
    }

    protected Command com;

    @Override
    public void answer(Command command) {
        com = command;
        exe();
    }

    @Override
    public void send(ArrayList<Command> commands) {
        com.setSecondArgument(commands.size());
        tr.sender.send(com);
        for (int i = 0; i < commands.size(); i++) {
            tr.sender.send(commands.get(i));
        }
    }

    public void sort(ArrayList<Command> commands) {
        int cs = commands.size();
        System.out.println(cs);
        if (cs == 0) {
            return;
        }
        Command[] cos = new Command[cs];
        for (int i = 0; i < cs; i++) {
            cos[i] = commands.get(i);
        }
        for (int i = 0; i < cs - 1; i++) {
            for (int j = i + 1; j < cs; j++) {
                int b = ((Ticket) cos[i].getThirdArgument()).compareTo((Ticket) cos[j].getThirdArgument());
                if (b < 0) {
                    Command c = cos[i];
                    cos[i] = cos[j];
                    cos[j] = c;
                }
            }
            cos[i].setSecondArgument(i);
        }
        cos[cs - 1].setSecondArgument(cs - 1);
        commands.clear();
        commands.addAll(Arrays.asList(cos).subList(0, cs));
    }

    @Override
    public void talk(String str, Scanner scanner) {
        com = tr.receiver.receive();
        com.setNameOfCommand(name);
        tr.sender.send(com);
        setArgs(str, scanner);
        exe();
    }

    protected abstract void setArgs(String str, Scanner scanner);

}
