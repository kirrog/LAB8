package se.ifmo.ru.Collection;

import java.util.Random;

import static se.ifmo.ru.Main.hashSize;

public class IdGenerator {
    static final Random random = new Random();

    public static int toGenerate() {
        return ((1+Math.abs(random.nextInt()))/hashSize);
    }
}
