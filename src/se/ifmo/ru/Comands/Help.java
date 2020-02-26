package se.ifmo.ru.Comands;

import java.util.concurrent.Executor;

public class Help implements Executor {

    @Override
    public void execute(Runnable command) {
        System.out.println("help - справка\n");
        System.out.println("info - вывод информации о коллекции\n");
        System.out.println("show - вывод элементов коллекции в строковом формате\n");
        System.out.println("insert key - добавить элемент с заданным ключём\n");
        System.out.println("update id - обновить элемент с заданым ключём\n");
        System.out.println("remove_key key - удалить элемент по ключу\n");
        System.out.println("clear - очистить коллекцию\n");
        System.out.println("save - сохранить коллекцию\n");
        System.out.println("execute_script file_name - считать из файла скрипт и исполнить\n");
        System.out.println("exit - завершение работы программы без сохранения в файл\n");
        System.out.println("remove_lower {element} - удалить из коллекции все элементы меньше чем заданный\n");
        System.out.println("replace_if_lower key {element} - заменить значение по ключу, если новое больше старого\n");
        System.out.println("remove_greater_key key - удалить все элементы, ключ которых превышает заданный\n");
        System.out.println("filter_by_venue venue - вывод всеъ элементов с заданным значением venue\n");
        System.out.println("print_descending - вывести элементы коллекции в порядке убывания\n");
        System.out.println("print_field_descending_type type - вывести значения поля type в порядке убывания\n");
    }

}
