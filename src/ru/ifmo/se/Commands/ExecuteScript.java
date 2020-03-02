package ru.ifmo.se.Commands;


import java.io.File;
import java.util.Scanner;


/** This class execute file*/
public class ExecuteScript implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
            File file = new File(string);
            ExeClass exeClass = new ExeClass(file);
            exeClass.start(true);
    }
}
