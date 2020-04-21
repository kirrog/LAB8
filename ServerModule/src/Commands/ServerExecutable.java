package Commands;

import Web.Command;

import java.util.ArrayList;

public interface ServerExecutable {
    void answer(Command command);
    void exe();
    void send(ArrayList<Command> commands);
}
