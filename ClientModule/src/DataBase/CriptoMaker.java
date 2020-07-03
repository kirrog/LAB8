package DataBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class CriptoMaker {

    private static Random random = new Random();

    public static byte[] cripting(String string){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            return md.digest(string.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateSalt(String n){
        char[] chars = "abcdefghijklmnopqrstuvwxyz:;\"\'[}{]".toCharArray();
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random(n.hashCode());
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

}
