package crypto;

/**
 * Created by Guillaume on 02/03/2017.
 */
public class Cesar {

    private static final int ALPHABET_LENGTH = 26;

    private int decalage = 0;
    private String msg = null;

    public Cesar(String message, int decal) {
        msg = message;
        decalage = decal;
    }

    public String crypt(boolean decrypt) {
        if(decrypt)
            decalage = -decalage;

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

    private char decaleChar(char cara, int decalage, char caractereBase) {
        int base = caractereBase;
        if (decalage < 0) {
            base += ALPHABET_LENGTH - 1;
        }
        return (char) (((cara) - base + decalage) % ALPHABET_LENGTH + base);
    }

    public String decrypt() {
        return crypt(true);
    }
}
