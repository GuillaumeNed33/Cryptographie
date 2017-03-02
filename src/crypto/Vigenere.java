package crypto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Guillaume on 02/03/2017.
 */
public class Vigenere {
    public static void main(String args[]) {
        String message = "chateau de princesse"; //ne prend pas en compte les accents et autre caracteres speciaux
        String cle = "afezttafezttafeztt";

        HashMap<Character, Float> freq = frequence(message); //Map de frequence des lettres
        // seeContains(freq);
        String message_crypt = vigenereCrypt(message, cle);
        String message_decrypt = vigenereDecrypt(message_crypt, cle);

        System.out.println("Message initial : " + message);
        System.out.println("Clé : " + cle + "\nlongueur cle : " + cleLength(cle, freq));
        System.out.println("IC : " + indiceCoincidence(cle, freq));
        System.out.println("Message Crypté : " + message_crypt);
        System.out.println("Message Décrypté : " + message_decrypt);
    }

    public static void seeContains(HashMap<Character, Float> map) {
        Set clefs = map.keySet();
        Iterator it = clefs.iterator();

        while (it.hasNext()) {
            Object cle = it.next();
            Object valeur = map.get(cle);
            System.out.println("Cle : " + cle + "\nValeur : " + valeur + "\n\n");
        }
    }

    public static HashMap<Character, Float> frequence(String texte) {
        HashMap<Character, Float> freq = new HashMap<Character, Float>();
        texte = texte.toLowerCase();

        for (int i = 97; i < 123; i++) {
            freq.put(((char) i), 0.f);
        }
        for (int i = 0; i < texte.length(); i++) {
            if (texte.charAt(i) != ' ')
                freq.put(texte.charAt(i), freq.get(texte.charAt(i)) + 1);
        }
        Float somme = 0.f;
        Set clefs = freq.keySet();
        Iterator it = clefs.iterator();
        while (it.hasNext()) {
            Object cle = it.next();
            Object valeur = freq.get(cle);
            if ((Float) valeur != 0.0) {
                freq.put((Character) cle, ((Float) valeur / texte.length()));
            }
            somme += (Float) valeur / texte.length();
        }
        //Somme = 1
        return freq;
    }

    public static String vigenereCrypt(String message, String cle) {
        message = message.toLowerCase();
        String newMessage=message;

        for (int j = 0; j < message.length() ; ++j)
        {
            if ((int)message.charAt(j)!=32)
            {
                char c;
                if ((message.charAt(j) + (cle.charAt(j% cle.length()) -96) <= 122))
                    c = (char)(message.charAt(j) + (cle.charAt(j%cle.length())) -96);
                else
                    c = (char)(message.charAt(j) + ((cle.charAt(j%cle.length())) -96-26));

                newMessage = replaceCharAt(newMessage, j, c);
            }
        }
        return newMessage;
    }

    public static String vigenereDecrypt(String message, String cle) {

        String newMessage = message;

        for (int j = 0; j < message.length() ; ++j)
        {
            if ((int)message.charAt(j)!=32)
            {
                if (message.charAt(j)-cle.charAt(j%cle.length())+96 >= 97) {
                    char c = (char)(message.charAt(j) - ((cle.charAt(j%cle.length())) -96));
                    newMessage = replaceCharAt(newMessage, j, c);
                }
                else {
                    char c = (char)(message.charAt(j) - ((cle.charAt(j%cle.length())) -96-26));
                    newMessage = replaceCharAt(newMessage, j, c);
                }
            }
        }
        return newMessage;
    }

    public static float indiceCoincidence(String cle, HashMap<Character, Float> map) {
        //Methodes des indices de coïncidence.
        float IC = 0.f;
        for (int i = 97; i < 123; i++) {
            float numerateur = (map.get((char) i)*cle.length())*((map.get((char) i)*cle.length())-1);
            float denominateur = cle.length() * (cle.length()-1);
            IC += numerateur/denominateur;
        }
        return IC;
    }

    private static int cleLength(String cle, HashMap<Character, Float> freq) {
        int nb=1;
        char first = cle.charAt(0);
        boolean fin = false;
        int j=1;
        while (!fin &&  j < cle.length())
        {
            if(cle.charAt(j) == first)
                fin = true;
            else {
                j++;
                nb++;
            }
        }
        return nb;
    }


    public static String casserVigenere(String msg) {
        return null;
    }

    public static String replaceCharAt(String s, int pos, char c) {
        char [] tab = s.toCharArray();
        tab[pos] = c;
        return new String(tab);
    }
}
