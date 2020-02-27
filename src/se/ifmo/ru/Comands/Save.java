package se.ifmo.ru.Comands;

import se.ifmo.ru.Parser.InFileParser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static se.ifmo.ru.Main.fileOutPutName;

public class Save implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        File file = new File(fileOutPutName);

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
