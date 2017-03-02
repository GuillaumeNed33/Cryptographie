package crypto;

/**
 * Created by Guillaume on 02/03/2017.
 */
public class Cesar {

    private static final int ALPHABET_LENGTH = 26;

    public static void main(String args []) {
        String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=1; i<=ALPHABET_LENGTH;i++) {
            String chiffre = crypt(alpha,i);
            String dechiff = decrypt(chiffre,i);
            System.out.println(chiffre + "  ---->  "+dechiff);
        }
 ;
    }

    public static String crypt(String msg, int decalage) {
        
        StringBuilder sb = new StringBuilder(msg.length());
        for (char c : msg.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(decaleChar(c, decalage, 'a'));
            } else if (c >= 'A' && c <= 'Z') {
                sb.append(decaleChar(c, decalage, 'A'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static char decaleChar(char cara, int decalage, char caractereBase) {
        int base = caractereBase;
        if (decalage < 0) {
            base += ALPHABET_LENGTH - 1;
        }
        return (char) (((cara) - base + decalage) % ALPHABET_LENGTH + base);
    }

    public static String decrypt(String msg,int decalage) {
        return crypt(msg,-decalage);
    }
}
