package se.ifmo.ru.Collection;

import java.util.Random;

public class IdGenerator {
    static final Random random = new Random();

    public static int toGenerate() {
        return random.nextInt();
    }
}
