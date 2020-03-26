package ru.ifmo.se.Collection;

import java.util.Date;

/** This class generate id*/
public class IdGenerator {

    public static int toGenerate() {

        //String letters = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        int count = 256;
        Date date = new Date();
        long intval = date.getTime();
        int result = 0;
        for (int i = 0; i < 3; i++) {
            int last = (int)intval % count;
            intval = (intval - last) / count;
            result *= 1000;
            result += last;
        }
        return result;
    }
}
