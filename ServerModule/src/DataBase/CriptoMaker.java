package DataBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptoMaker {

    private static String paper = "@NotHack";

    public static byte[] cripting(String string){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            return md.digest(string.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saltAndPaper(TicketOwner to, String salt){
        byte [] password = to.getPassword();
        StringBuilder sb = new StringBuilder();
        char [] chars = new char[password.length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) password[i];
        }
        sb = sb.append(chars);
        String pas = paper + sb.toString() + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            password = md.digest(pas.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        to.setPassword(password);
    }

}
