package crypto;

import java.math.BigInteger;

/**
 * Created by Guillaume on 02/03/2017.
 */
public class DiffieHellman {
    private static BigInteger p;
    private static BigInteger g;
    private static BigInteger a;
    private static BigInteger b;
    private static BigInteger gModPowA;
    private static BigInteger gModPowB;
    private static BigInteger cle;

    private static final int SIZE = 256;

    public static void main(String args []) {
        initHellman();
    }

    private static void initHellman() {
        p = new BigInteger("9293363");
        g = new BigInteger("3488143");
        a = new BigInteger("1234567"); //choisi par moi

        gModPowA =  g.modPow(a,p);
        gModPowB =  new BigInteger("1210796"); //Valeur envoyé par M. Leothaud
        cle = gModPowB.modPow(a,p);
        System.out.println(cle);
    }
}
