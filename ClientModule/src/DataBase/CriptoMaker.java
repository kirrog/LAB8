package DataBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptoMaker {

    public static byte[] cripting(String string){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            return md.digest(string.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
