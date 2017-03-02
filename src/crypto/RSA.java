package crypto;

/**
 * Created by Guillaume on 02/03/2017.
 */


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RSA {
    private static final int SIZE = 256; //512bits
    private static HashMap<BigInteger,BigInteger> pubKey = new HashMap<BigInteger,BigInteger>();
    private static BigInteger privKey;
    private static final BigInteger p = new BigInteger(SIZE, 100, new Random());
    private static final BigInteger q = new BigInteger(SIZE, 100, new Random());
    private static final BigInteger n = new BigInteger(p.multiply(q).toString());
    private static BigInteger phI;
    private static BigInteger e;
    private static BigInteger d;

    public static void main(String args [])
    {
        InitRSA();
        createKeys();

        /*** Cryptage d'un message sous forme de nombre ***/
        BigInteger msgNumber = new BigInteger("1278678766831202614894846515151111111118797898797678678766876898797678678766876");
        ArrayList<byte[]> parseMsg = parseData(msgNumber); //Decoupage du message en plusieurs message de taille max le nombre de bit utilise pour les clefs

        ArrayList<BigInteger>msg_CrypteNum = new ArrayList<BigInteger>();
        ArrayList<BigInteger>msg_DecrypteNum = new ArrayList<BigInteger>();

        String msgNumberCrypt = "";
        for (byte[] b : parseMsg) { //Cryptage de chaque morceau de message
            msg_CrypteNum.add(cryptMessage(new BigInteger(b)));
            msgNumberCrypt += cryptMessage(new BigInteger(b)).toString(); //Variable utilisé uniquement pour l'affichage
        }
        for (BigInteger data : msg_CrypteNum) {
            msg_DecrypteNum.add(decryptMessage(data));
        }
        String messageFinalNumber = "";
        for (BigInteger data : msg_DecrypteNum) {
            messageFinalNumber+= data.toString();
        }


        /*** Cryptage d'un message sous forme d'une chaine de caractere ***/
        String msgString = "message fonctionnel";
        byte [] data = msgString.getBytes();
        BigInteger[] parseMsgString = parseData(msgString);
        BigInteger msgStringCrypt = cryptMessage(new BigInteger(msgString.getBytes()));
        BigInteger msgStringDecrypt = decryptMessage(msgStringCrypt);
        String decoded = new String(msgStringDecrypt.toByteArray());

        showInformations(msgNumber, msgNumberCrypt, messageFinalNumber, msgString, msgStringCrypt, decoded);
    }

    private static ArrayList<byte[]> parseData(BigInteger msg) {
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

    private static void showInformations(BigInteger msgNumber, String msgNumberCrypt, String msgNumberDecrypt,
                                         String msgString, BigInteger msgStringCrypt, String msgStringDecrypt) {
        System.out.println("p       : " + p);
        System.out.println("q       : " + q);
        System.out.println("n       : " + n);
        System.out.println("phI     : " + phI);
        System.out.println("e       : " + e);
        System.out.println("d       : " + d + "\n");
        System.out.println("public  : " + pubKey);
        System.out.println("private : " + privKey + "\n");
        System.out.println("msg     : " + msgNumber);
        System.out.println("crypt   : " + msgNumberCrypt);
        System.out.println("decrypt : " + msgNumberDecrypt + "\n");
        System.out.println("msg     : " + msgString);
        System.out.println("crypt   : " + msgStringCrypt);
        System.out.println("decrypt : " + msgStringDecrypt + "\n");
    }

    private static void InitRSA() {
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

    private static void createKeys() {
        pubKey.put(e,n);
        privKey = d;
    }

    private static BigInteger decryptMessage(BigInteger msg) {
        return msg.modPow(d, n); //msg^d mod n
    }

    private static BigInteger cryptMessage(BigInteger msg) {
        return msg.modPow(e, n); //msg^e mod n
    }
}
