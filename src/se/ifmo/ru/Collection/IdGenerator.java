package se.ifmo.ru.Collection;

import java.util.Date;
import java.util.Random;


public class IdGenerator {
    static final Random random = new Random();

    public static int toGenerate() {

        //String letters = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        int count = 256;
        Date date = new Date();
        long intval = date.getTime();
        int result = 0;
        for (int i = 0; i < 5; i++) {
            int last = (int)intval % count;
            intval = (intval - last) / count;
            result *= 1000;
            result += last;
        }
        return result;
    }
}
