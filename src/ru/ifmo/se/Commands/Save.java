package ru.ifmo.se.Commands;

import ru.ifmo.se.Parser.InFileParser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static ru.ifmo.se.Main.fileRWName;


/** This class save collection in file*/
public class Save implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        File file = new File(fileRWName);

        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Can't create file when write!");
        }

        InFileParser IFP = new InFileParser(file);
        IFP.saveInFile();
    }
}
