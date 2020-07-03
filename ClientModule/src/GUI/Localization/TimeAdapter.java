package GUI.Localization;

//Will help with Tickets time when they received

import Collection.Ticket;
import GUI.WorkPage;

import java.time.ZonedDateTime;

public class TimeAdapter extends Thread{

    public static boolean work = true;
    static ZonedDateTime min = ZonedDateTime.now();
    static String currentLanguage;

    public static ZonedDateTime currentTime = ZonedDateTime.now();

    public static void adaptTicketTime(Ticket ticket){
        ZonedDateTime zdt = ticket.getCreationDate();
        if(min.compareTo(zdt) > 0){
            min = zdt;
        }
        // парсим под текущий LanguageProvider
    }

    public static String adaptTime(ZonedDateTime zonedDateTime){
        return LanguagesProvider.adaptTime(zonedDateTime);
    }

    @Override
    public void run() {
        while (work){
            currentTime = ZonedDateTime.now();
            WorkPage.setTime(adaptTime(currentTime));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
