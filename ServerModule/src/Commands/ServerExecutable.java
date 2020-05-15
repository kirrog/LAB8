package Commands;

import WebRes.Command;

import java.util.ArrayList;
import java.util.Scanner;

public interface ServerExecutable {
    void answer(Command command);
    void exe();
    void send(ArrayList<Command> commands);
    void talk(String str, Scanner scanner);
}
