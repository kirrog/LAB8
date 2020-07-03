package GUI.Localization.Resources;

import java.util.ListResourceBundle;

public class FinnishBundle extends ListResourceBundle {

    private Object[][] dictionary = new Object[][]
            {
                    {"Tickets", "liput"},
                    {"Login", "Kirjaudu sisään"},
                    {"Password", "Salasana"},
                    {"SignIn", "Kirjaudu sisään"},
                    {"Register", "Rekisteröidy"},
                    {"Registration", "Rekisteröinti"},
                    {"Repeat", "Toistaa"},
                    {"Mail", "posti"},
                    {"Table", "Pöytä"},
                    {"Graphic", "Graafinen"},
                    {"Commands", "komennot"},
                    {"Exit", "poistuminen"},
                    {"Ticket", "lippu"},
                    {"Coordinates", "koordinaatit"},
                    {"Venue", "tapahtumapaikka"},
                    {"Address", "Osoite"},
                    {"Town", "Kaupunki"},
                    {"Russian", "Venäjän kieli"},
                    {"Hungarian", "Unkarin kieli"},
                    {"Finnish", "Suomalainen"},
                    {"Spanish", "Espanja"},
                    {"(Guatemala)", "Guatemala"},
                    {"Insert", "Insert"},
                    {"Key", "Avain"},
                    {"Name", "Nimi"},
                    {"Price", "Hinta"},
                    {"Comment", "Kommentti"},
                    {"Refundable", "palauteta"},
                    {"Type", "Tyyppi"},
                    {"Capacity", "kapasiteetti"},
                    {"Zipcode", "Postinumero"},
                    {"Execute", "Suorittaa"},
                    {"Update", "Päivittää"},
                    {"Remove", "Poista"},
                    {"Script", "käsikirjoitus"},
                    {"Lower", "Alempi"},
                    {"Replace", "Korvata"},
                    {"If", "Jos"},
                    {"Greater", "suurempi"},
                    {"Filter", "Suodattaa"},
                    {"By", "mennessä"},
                    {"Client", "Asiakas"},

                    {"English", "Englanti"},
                    {"Spanish (Guatemala)", "Espanja (Guatemala)"}
            };

    @Override
    protected Object[][] getContents() {
        return dictionary;
    }
}
