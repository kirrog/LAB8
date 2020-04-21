package Commands;


import Starter.Main;
import Web.Command;

import java.util.ArrayList;
import java.util.Scanner;


/** This class print all commands*/
public class Help extends AbstractCommand {


    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("help - справка");
        System.out.println("info - вывод информации о коллекции");
        System.out.println("show - вывод элементов коллекции в строковом формате");
        System.out.println("insert key - добавить элемент с заданным ключём");
        System.out.println("update id - обновить элемент с заданым ключём");
        System.out.println("remove_key key - удалить элемент по ключу");
        System.out.println("clear - очистить коллекцию");
        System.out.println("save - сохранить коллекцию");
        System.out.println("execute_script file_name - считать из файла скрипт и исполнить");
        System.out.println("exit - завершение работы программы без сохранения в файл");
        System.out.println("remove_lower {element} - удалить из коллекции все элементы меньше чем заданный");
        System.out.println("replace_if_lower key {element} - заменить значение по ключу, если новое больше старого");
        System.out.println("remove_greater_key key - удалить все элементы, ключ которых превышает заданный");
        System.out.println("filter_by_venue venue - вывод всеъ элементов с заданным значением venue");
        System.out.println("print_descending - вывести элементы коллекции в порядке убывания");
        System.out.println("print_field_descending_type type - вывести значения поля type в порядке убывания");
    }

    @Override
    public void exe() {
        com.setFirstArgument(
                "help - справка"+ "\n" +
                "info - вывод информации о коллекции"+ "\n" +
                "show - вывод элементов коллекции в строковом формате"+ "\n" +
                "insert key - добавить элемент с заданным ключём"+ "\n" +
                "update id - обновить элемент с заданым ключём"+ "\n" +
                "remove_key key - удалить элемент по ключу"+ "\n" +
                "clear - очистить коллекцию"+ "\n" +
                "execute_script file_name - считать из файла скрипт и исполнить"+ "\n" +
                "exit - завершение работы программы"+ "\n" +
                "remove_lower {element} - удалить из коллекции все элементы меньше чем заданный"+ "\n" +
                "replace_if_lower key {element} - заменить значение по ключу, если новое больше старого"+ "\n" +
                "remove_greater_key key - удалить все элементы, ключ которых превышает заданный"+ "\n" +
                "filter_by_venue venue - вывод всеъ элементов с заданным значением venue"+ "\n" +
                "print_descending - вывести элементы коллекции в порядке убывания"+ "\n" +
                "print_field_descending_type type - вывести значения поля type в порядке убывания"
        );
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        Main.sender.send(com);
    }
}
