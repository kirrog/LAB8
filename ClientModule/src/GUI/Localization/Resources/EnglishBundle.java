package GUI.Localization.Resources;

import java.util.ListResourceBundle;

public class EnglishBundle extends ListResourceBundle {

    private Object[][] dictionary = new Object[][]{
            {"Tickets", "Tickets"},
            {"Login", "Login"},
            {"Password", "Password"},
            {"SignIn", "SignIn"},
            {"Register", "Register"},
            {"Registration", "Registration"},
            {"Repeat", "Repeat"},
            {"Mail", "Mail"},
            {"Table", "Table"},
            {"Graphic", "Graphic"},
            {"Commands", "Commands"},
            {"Exit", "Exit"},
            {"Ticket", "Ticket"},
            {"Coordinates", "Coordinates"},
            {"Venue", "Venue"},
            {"Address", "Address"},
            {"Town", "Town"},
            {"Russian", "Russian"},
            {"Hungarian", "Hungarian"},
            {"Finnish", "Finnish"},
            {"Spanish", "Spanish"},
            {"(Guatemala)", "(Guatemala)"},
            {"Insert", "Insert"},
            {"Key", "Key"},
            {"Name", "Name"},
            {"Price", "Price"},
            {"Comment", "Comment"},
            {"Refundable", "Refundable"},
            {"Type", "Type"},
            {"Capacity", "Capacity"},
            {"Zipcode", "Zipcode"},
            {"Execute", "Execute"},
            {"Update", "Update"},
            {"Remove", "Remove"},
            {"Script", "Script"},
            {"Lower", "Lower"},
            {"Replace", "Replace"},
            {"If", "If"},
            {"Greater", "Greater"},
            {"Filter", "Filter"},
            {"By", "By"},
            {"Client", "Client"},

            {"English", "English"},
            {"Spanish (Guatemala)", "Spanish (Guatemala)"}
    };

    @Override
    protected Object[][] getContents() {
        return dictionary;
    }
}
