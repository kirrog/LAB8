package ru.ifmo.se.Commands;


import java.io.File;
import java.util.Scanner;

import static ru.ifmo.se.Commands.ExeClass.numberOfProceses;


/** This class execute file*/
public class ExecuteScript implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
            if(numberOfProceses < 20){
                File file = new File(string);
                ExeClass exeClass = new ExeClass(file);
                numberOfProceses++;
                exeClass.start(true);
                numberOfProceses--;
            }else {
                System.out.println("You can't break limit of execution processes (only 20)!!!");
            }

    }
}
