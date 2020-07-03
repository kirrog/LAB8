package GUI.Localization.Resources;

import java.util.ListResourceBundle;

public class HungarianBundle extends ListResourceBundle {

    private Object[][] dictionary = new Object[][]{
            {"Tickets", "Jegyek"},
            {"Login", "Belépés"},
            {"Password", "Jelszó"},
            {"SignIn", "Bejelentkezés"},
            {"Register", "Regisztráció"},
            {"Registration", "Bejegyzés"},
            {"Repeat", "Ismétlés"},
            {"Mail", "Posta"},
            {"Table", "asztal"},
            {"Graphic", "Grafikus"},
            {"Commands", "parancsok"},
            {"Exit", "Kijárat"},
            {"Ticket", "Jegy"},
            {"Coordinates", "Koordináták"},
            {"Venue", "Helyszín"},
            {"Address", "Cím"},
            {"Town", "Város"},
            {"Russian", "orosz"},
            {"Hungarian", "Magyar"},
            {"Finnish", "finn"},
            {"Spanish", "spanyol"},
            {"(Guatemala)", "Guatemala"},
            {"Insert", "Insert"},
            {"Key", "Kulcs"},
            {"Name", "Név"},
            {"Price", "Ár"},
            {"Comment", "Megjegyzés"},
            {"Refundable", "visszatérítendő"},
            {"Type", "típus"},
            {"Capacity", "Kapacitás"},
            {"Zipcode", "Irányítószám"},
            {"Execute", "kivégez"},
            {"Update", "frissítés"},
            {"Remove", "Vegye ki"},
            {"Script", "Forgatókönyv"},
            {"Lower", "Alsó"},
            {"Replace", "Cserélje"},
            {"If", "Ha"},
            {"Greater", "Greater"},
            {"Filter", "Szűrő"},
            {"By", "Által"},
            {"Client", "Ügyfél"},

            {"English", "angol"},
            {"Spanish (Guatemala)", "Spanyol (Guatemala)"}
    };

    @Override
    protected Object[][] getContents() {
        return dictionary;
    }
}
