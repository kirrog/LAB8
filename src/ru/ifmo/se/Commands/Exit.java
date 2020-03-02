package ru.ifmo.se.Commands;

import java.util.Scanner;


/** This class ends program without save*/
public class Exit implements Execute {

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("Something goes wrong!!!");
    }
}
