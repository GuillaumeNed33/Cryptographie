package crypto;

import java.math.BigInteger;
import java.security.SecureRandom;

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

    private static final int bitLength = 512;


    public DiffieHellman() {
        SecureRandom rnd = new SecureRandom();
        p = BigInteger.probablePrime(bitLength, rnd);
        g = BigInteger.probablePrime(bitLength, rnd);
        a = new BigInteger("1234567"); //choisi par moi
    }
    public String calculateKey(BigInteger b) {
        gModPowA =  g.modPow(a,p);
        gModPowB =  new BigInteger("1210796"); //Valeur envoyé par M. Leothaud
        cle = gModPowB.modPow(a,p);
        return cle.toString();
    }

}
