package Commands;


import DataBase.ThreadResurses;
import WebRes.Command;

import java.util.ArrayList;
import java.util.Scanner;


/** This class print all commands*/
public class Help extends AbstractCommand {

    public Help(){
        name = "help";
    }

    public Help(ThreadResurses threadResurses){
        name = "help";
        tr = threadResurses;
    }

    @Override
    public void execute(String string, Scanner scan, ExeClass eCla) {
        System.out.println("help - справка");
        System.out.println("info - вывод информации о коллекции");
        System.out.println("show - вывод элементов коллекции в строковом формате");
        System.out.println("insert key - добавить элемент с заданным ключём; Must login");
        System.out.println("update id - обновить элемент с заданым ключём; Must login");
        System.out.println("remove_key key - удалить элемент по ключу; Must login");
        System.out.println("clear - очистить коллекцию; Must login");
        System.out.println("execute_script file_name - считать из файла скрипт и исполнить; Must login");
        System.out.println("exit - завершение работы программы без сохранения в файл");
        System.out.println("remove_lower {element} - удалить из коллекции все элементы меньше чем заданный; Must login");
        System.out.println("replace_if_lower key {element} - заменить значение по ключу, если новое больше старого; Must login");
        System.out.println("remove_greater_key key - удалить все элементы, ключ которых превышает заданный; Must login");
        System.out.println("filter_by_venue venue - вывод всеъ элементов с заданным значением venue");
        System.out.println("print_descending - вывести элементы коллекции в порядке убывания");
        System.out.println("print_field_descending_type type - вывести значения поля type в порядке убывания");
        System.out.println("login - войти в систему");
        System.out.println("register - зарегистрироваться");
        System.out.println("change_register - сменить значения аккаунта; Must login");
    }

    @Override
    public void exe() {
        com.setFirstArgument(
                "help - справка"+ "\n" +
                "info - вывод информации о коллекции"+ "\n" +
                "show - вывод элементов коллекции в строковом формате"+ "\n" +
                "insert key - добавить элемент с заданным ключём; Must login"+ "\n" +
                "update id - обновить элемент с заданым ключём; Must login"+ "\n" +
                "remove_key key - удалить элемент по ключу; Must login"+ "\n" +
                "clear - очистить коллекцию; Must login"+ "\n" +
                "execute_script file_name - считать из файла скрипт и исполнить; Must login"+ "\n" +
                "exit - завершение работы программы"+ "\n" +
                "remove_lower {element} - удалить из коллекции все элементы меньше чем заданный; Must login"+ "\n" +
                "replace_if_lower key {element} - заменить значение по ключу, если новое больше старого; Must login"+ "\n" +
                "remove_greater_key key - удалить все элементы, ключ которых превышает заданный; Must login"+ "\n" +
                "filter_by_venue venue - вывод всеъ элементов с заданным значением venue"+ "\n" +
                "print_descending - вывести элементы коллекции в порядке убывания"+ "\n" +
                "print_field_descending_type type - вывести значения поля type в порядке убывания"+ "\n" +
                "login - войти в систему" + "\n" + "register - зарегистрироваться" + "\n" + "change_register - сменить значения аккаунта; Must login"
        );
        send(null);
    }

    @Override
    public void send(ArrayList<Command> commands) {
        tr.sender.send(com);
    }

    @Override
    protected void setArgs(String str, Scanner scanner) {}
}
