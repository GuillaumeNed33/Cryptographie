package crypto;

/**
 * Created by Guillaume on 02/03/2017.
 */


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RSA {
    private static final int SIZE = 1024;
    private static HashMap<BigInteger,BigInteger> pubKey = new HashMap<BigInteger,BigInteger>();
    private static BigInteger privKey;
    private static final BigInteger p = new BigInteger(SIZE, 100, new Random());
    private static final BigInteger q = new BigInteger(SIZE, 100, new Random());
    private static final BigInteger n = new BigInteger(p.multiply(q).toString());
    private static BigInteger phI;
    private static BigInteger e;
    private static BigInteger d;
    private BigInteger msgStringCrypt = null;
    private String message=null;

    private BigInteger yourPrivKey;
    private HashMap<BigInteger,BigInteger> yourPubKey = new HashMap<BigInteger,BigInteger>();
    private BigInteger cle;

    public void setYourPrivKey(String b) {this.yourPrivKey = new BigInteger(b);}

    public void setYourPubKey(String b) {
        cle = new BigInteger(b.split("\\{")[1].split("=")[0]);

        String value = b.split("\\{")[1].split("=")[1].split("}")[0];

        this.yourPubKey.put(cle,new BigInteger(value));
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageCrypt(String message) {
        this.msgStringCrypt = new BigInteger(message);
    }

    public RSA() {
        InitRSA();
        createKeys();
    }

    public String cryptage(boolean stranger) {
        if(stranger) {
            return cryptMessage(new BigInteger(message.getBytes())).toString();
        }
        else
            return cryptMessage2(new BigInteger(message.getBytes())).toString();
    }

    public String decryptage(boolean stranger) {
        if(stranger) {
            return new String(decryptMessage(msgStringCrypt).toByteArray());
        }
        else
            return new String(decryptMessage2(msgStringCrypt).toByteArray());
    }

    private ArrayList<byte[]> parseData(BigInteger msg) {
        ArrayList<byte[]> data = new ArrayList<byte[]>();
        if(msg.toByteArray().length > SIZE/4) { //depassement du nombre de bit avec un codage de 1 caractere sur 4bits
            int dataSize = (msg.toByteArray().length / (SIZE/4))+1;
            int index=0, k = 0;

            for(int j=0; j<dataSize ; j++) {
                byte[] tmp;
                if(j==dataSize-1)
                    tmp = new byte[ (msg.toByteArray().length)-((dataSize-1)*(SIZE/4))];
                else
                    tmp = new byte[SIZE/4];
                data.add(tmp);
            }

            byte[] tab = msg.toByteArray();
            for (int i = 0; i < tab.length; i++) {
                data.get(k)[index] =  tab[i];
                index++;
                if (index%(SIZE/4)==0 && index >= (SIZE/4)) {
                    index = 0;
                    k++;
                }
            }
            return data;
        }
        else {
            data.add(msg.toByteArray());
            return data;
        }
    }
    private static BigInteger[] parseData(String msg) {
        BigInteger [] data;
        if(msg.getBytes().length > SIZE/4) {

        }
        else {
            data = new BigInteger[]{new BigInteger(msg.getBytes())};
            return data;
        }
        return null;
    }

    private void InitRSA() {
        phI = p.subtract(BigInteger.ONE); //(p-1)
        phI = phI.multiply( q.subtract( BigInteger.ONE)); // multiply by (q-1)

        e = new BigInteger (SIZE, 100, new Random());

        while ( (e.compareTo(BigInteger.ONE) <= 0) && //e > 1
                (e.compareTo(phI) >= 0) && // e < phI
                (e.gcd(phI).equals(BigInteger.ONE)) ) { //pgcd(e, phI) = 1
            e = new BigInteger (SIZE, 100, new Random());
        }
        d =  e.modInverse(phI); // ed = 1 mod phI
    }

    public static HashMap<BigInteger, BigInteger> getPubKey() {
        return pubKey;
    }

    public static BigInteger getPrivKey() {
        return privKey;
    }

    private void createKeys() {
        pubKey.put(e,n);
        privKey = d;
    }

    private BigInteger decryptMessage(BigInteger msg) {
        return msg.modPow(d, n); //msg^d mod n
    }

    private BigInteger cryptMessage(BigInteger msg) {
        return msg.modPow(e, n); //msg^e mod n
    }

    private BigInteger decryptMessage2(BigInteger msg) {
        return msg.modPow(yourPrivKey, yourPubKey.get(cle)); //msg^d mod n
    }

    private BigInteger cryptMessage2(BigInteger msg) {
        return msg.modPow(cle, yourPubKey.get(cle)); //msg^e mod n
    }
}
