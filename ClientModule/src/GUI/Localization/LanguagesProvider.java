package GUI.Localization;

//Contains answers for choosing language

import GUI.JMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.util.*;

public class LanguagesProvider {

    private static JMainFrame jmf;

    public static void setJmf(JMainFrame jmff) {
        jmf = jmff;
    }

    private static String[] localesStrings = new String[]{
            "Russian",
            "Finnish",
            "Hungarian",
            "Spanish (Guatemala)",
            "English"
    };

    private static String[] loc;

    private static HashMap<String, Locale> locales = new HashMap<String, Locale>(localesStrings.length);
    private static HashMap<String, String> bundles = new HashMap<String, String>(localesStrings.length);
    public static JComboBox<String> locations;
    public static JComboBox<String> locationsL;
    public static JComboBox<String> locationsR;
    static String workLanguage = "English";
    static{
        locales.put("Russian", new Locale("ru", "RU"));
        bundles.put("Russian", "GUI.Localization.Resources.RussianBundle");
        locales.put("Finnish", new Locale("fi","FI"));
        bundles.put("Finnish", "GUI.Localization.Resources.FinnishBundle");
        locales.put("Hungarian", new Locale("hu","HU"));
        bundles.put("Hungarian", "GUI.Localization.Resources.HungarianBundle");
        locales.put("Spanish (Guatemala)", new Locale("es","GT"));
        bundles.put("Spanish (Guatemala)", "GUI.Localization.Resources.SpanishGuatBundle");
        locales.put("English", new Locale("en", "US"));
        bundles.put("English", "GUI.Localization.Resources.EnglishBundle");
        loc = localesStrings.clone();
        locations = new JComboBox<String>(loc);
        locationsL = new JComboBox<String>(loc);
        locationsR = new JComboBox<String>(loc);
        locations.setSelectedItem(workLanguage);
        locationsL.setSelectedItem(workLanguage);
        locationsR.setSelectedItem(workLanguage);

        ActionListener aacl = new ActionListener() {

            int i = 0;
            boolean reset = false;

            @Override
            public synchronized void actionPerformed(ActionEvent e) {

                if(reset) {
                    return;
                }

                JComboBox box = (JComboBox) e.getSource();
                int selected = box.getSelectedIndex();
                if(selected < 0 || selected > (localesStrings.length - 1)){
                    return;
                }
                workLanguage = localesStrings[selected];

                reset = true;
                setWorkLanguage(workLanguage);
                translate(locations);
                locations.setSelectedIndex(selected);
                translate(locationsL);
                locationsL.setSelectedIndex(selected);
                translate(locationsR);
                locationsR.setSelectedIndex(selected);
                i=0;
                reset = false;

            }

            private void translate(JComboBox<String> locationsBox){
                locationsBox.removeAllItems();
                for (int j = 0; j < localesStrings.length; j++) {
                    locationsBox.insertItemAt(translateBoxLabel(localesStrings[j]), j);
                }
            }
        };
        locations.addActionListener(aacl);
        locationsL.addActionListener(aacl);
        locationsR.addActionListener(aacl);
    }

    private static void setWorkLanguage(String string){
        TimeAdapter.currentLanguage = string;
        jmf.translate();
    }

    static String adaptTime(ZonedDateTime zdt){
        return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL ,locales.get(workLanguage)).format(Date.from(zdt.toInstant()));
        // адаптирует время под текущий формат
    }

    public static String translateBoxLabel(String string){
        ResourceBundle rb = ResourceBundle.getBundle(bundles.get(workLanguage));
        return  rb.getString(string);
    }

    public static String adaptPhrase(String phrase){
        String result = phrase;
        if (!workLanguage.equals("English") && result != null){
            String[] strs = result.split(" ");
            result = "";
            ResourceBundle rb = ResourceBundle.getBundle(bundles.get(workLanguage));
            for (String s : strs){
                String co ="";
                if(s.endsWith(";")){
                    co = ";";
                    s = s.substring(0,s.length()-1);
                }
                try {
                    result += rb.getString(s) + co + " ";
                }catch (MissingResourceException e){
                    result += s + co  + " ";
                }
            }
        }
        return result;
    }



    public static String[] translateLine(String[] strs){
        StringBuilder strB = new StringBuilder();
        for (String s : strs){
            strB.append(s + "; ");
        }
        //System.out.println(strB.toString());
        String[] result = adaptPhrase(strB.toString()).split(";");
        //System.out.println(Arrays.toString(result));
        return result;
    }

    public static String adaptNumber(Number i){
        return NumberFormat.getInstance(locales.get(workLanguage)).format(i);
    }

    public static Locale getCurrentLocale(){
        return locales.get(workLanguage);
    }
}
