package se.ifmo.ru.Comands;


import java.io.File;
import java.util.Scanner;

public class ExecuteScript implements Execute {

    @Override
    public void execute(String string, Scanner scan) {
        File file = new File(string);
        ExeClass eCla = new ExeClass(file);
        eCla.start(true);

    }
}
