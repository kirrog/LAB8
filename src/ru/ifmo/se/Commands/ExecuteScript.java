package ru.ifmo.se.Commands;


import java.io.File;
import java.util.Scanner;


/**
 * This class execute file script
 */
public class ExecuteScript implements Execute {

    public static int numberOfProceses = 0;

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        try {
            File file = new File(string);
            if(file.exists() & file.isFile() &  file.canRead() & file.canExecute()){
                ExeClass exeClass = new ExeClass(string);
                numberOfProceses++;
                exeClass.start();
                numberOfProceses--;
            }else {
                if(!file.exists()){
                    System.out.println("This doesn't exists");
                }else if(!file.isFile()){
                    System.out.println("It isn't file");
                }else if(!file.canRead()){
                    System.out.println("Can't read it");
                }else if(!file.canExecute()){
                    System.out.println("Can't execute it");
                }
            }
        } catch(StackOverflowError error) {
            System.out.println("Bad recursion: " + numberOfProceses);
        }
    }
}
