package GUI.Localization.Resources;

import java.util.ListResourceBundle;

public class RussianBundle extends ListResourceBundle {

    private Object[][] dictionary = new Object[][]{
            {"Tickets", "Билеты"},
            {"Login", "Логин"},
            {"Password", "Пароль"},
            {"SignIn", "Войти"},
            {"Register", "Зарегистрироваться"},
            {"Registration", "Регистрация"},
            {"Repeat", "Повторить"},
            {"Mail", "Почта"},
            {"Table", "Таблица"},
            {"Graphic", "Графика"},
            {"Commands", "Команды"},
            {"Exit", "Выход"},
            {"Ticket", "Билет"},
            {"Coordinates", "Координаты"},
            {"Venue", "Место встречи"},
            {"Address", "Адрес"},
            {"Town", "Город"},
            {"Russian", "Русский"},
            {"Hungarian", "Венгерский"},
            {"Finnish", "Финский"},
            {"Spanish", "Испанский"},
            {"(Guatemala)", "Гватемала"},
            {"Insert", "Добавить"},
            {"Key", "Ключ"},
            {"Name", "Имя"},
            {"Price", "Цена"},
            {"Comment", "Комментарий"},
            {"Refundable", "Возмещаемый"},
            {"Type", "Тип"},
            {"Capacity", "Вместимость"},
            {"Zipcode", "Зипкод"},
            {"Execute", "Исполнять"},
            {"Update", "Обновить"},
            {"Remove", "Удалить"},
            {"Script", "Скрипт"},
            {"Lower", "Меньше"},
            {"Replace", "Заменить"},
            {"If", "Если"},
            {"Greater", "Больше"},
            {"Filter", "Отфильтровать"},
            {"By", "По"},
            {"Client", "Клиент"},

            {"English", "Английский"},
            {"Spanish (Guatemala)", "Испанский (Гватемала)"}
    };

    @Override
    protected Object[][] getContents() {
        return dictionary;
    }
}
