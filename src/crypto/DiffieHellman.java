package crypto;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {
    private BigInteger PrimeModulus, generator, alicePrivateNumber, alicePublicNumber, bobPublicNumber, SecretKey;
    private static final int bitLength = 512;
    public DiffieHellman() {
        SecureRandom rnd = new SecureRandom();
        PrimeModulus = BigInteger.probablePrime(bitLength,rnd);
        generator = BigInteger.valueOf(rnd.nextInt(Integer.MAX_VALUE));
        alicePrivateNumber = BigInteger.valueOf(rnd.nextInt(Integer.MAX_VALUE));
        System.out.println("gene" + generator.toString());
        alicePublicNumber = PrimeModulus.modPow(alicePrivateNumber,generator);
        System.out.println("alice" + alicePublicNumber.toString());

    }

    public BigInteger getPrimeModulus() {
        return PrimeModulus;
    }

    public BigInteger getGenerator() {
        return generator;
    }

    public BigInteger getSecretKey() { return SecretKey;}

    public BigInteger getAlicePublicNumber() { return alicePublicNumber; };

    public void setBobNumber(BigInteger b) {
        bobPublicNumber = b;
    }

    public String calculateKey() {
        SecretKey = bobPublicNumber.modPow(alicePrivateNumber, generator);
        return SecretKey.toString();
    }
}
