package Commands;

import DataBase.ThreadResurses;

import java.util.Scanner;

/** This interface provide methods for execution*/
public interface Execute {
    void execute(String string, Scanner scan, ExeClass eCla);
    void setTr(ThreadResurses tr);
}
