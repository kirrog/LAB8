package ru.ifmo.se.Commands;

import ru.ifmo.se.WriteInOut.InFileParser;

import java.io.*;
import java.util.Scanner;

import static ru.ifmo.se.Main.*;


/** This class save collection in file*/
public class Save implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        if (justTerm.programState[7]){
            File file = new File(justTerm.RWfiles[2]);
            File logs = new File("src/ru/ifmo/se/Parser/logs.json");

            try (FileOutputStream outputStream = new FileOutputStream(logs)) {
                try (BufferedOutputStream bos = new BufferedOutputStream(outputStream)) {
                    String str = ("{\n\t\"FileName\": \"" + justTerm.RWfiles[2] + "\"\n}");
                    byte[] buffer = str.getBytes();
                    bos.write(buffer, 0, buffer.length);
                }catch (IOException e){
                    System.out.println("There are problems with updating logs.json while writing");
                }
            } catch (IOException e) {
                System.out.println("There are problems with updating logs.json while opening");
            }

            try {
                file.delete();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Can't create file when write!");
            }

            InFileParser IFP = new InFileParser(file);
            IFP.saveInFile();
        }else{
            System.out.println("You choose file in which you can't write");
        }
    }
}
