package crypto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Guillaume on 02/03/2017.
 */
public class Vigenere {
    private String msg = null;
    private String cle = null;

    public Vigenere(String message, String cle) {
        msg = message;
        this.cle = cle;
    }

    private HashMap<Character, Float> frequence(String texte) {
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

    public String vigenereCrypt() {
        msg = msg.toLowerCase();
        String newMessage=msg;

        for (int j = 0; j < msg.length() ; ++j)
        {
            if ((int)msg.charAt(j)!=32)
            {
                char c;
                if ((msg.charAt(j) + (cle.charAt(j% cle.length()) -96) <= 122))
                    c = (char)(msg.charAt(j) + (cle.charAt(j%cle.length())) -96);
                else
                    c = (char)(msg.charAt(j) + ((cle.charAt(j%cle.length())) -96-26));

                newMessage = replaceCharAt(newMessage, j, c);
            }
        }
        return newMessage;
    }

    public String vigenereDecrypt() {

        String newMessage = msg;

        for (int j = 0; j < msg.length() ; ++j)
        {
            if ((int)msg.charAt(j)!=32)
            {
                if (msg.charAt(j)-cle.charAt(j%cle.length())+96 >= 97) {
                    char c = (char)(msg.charAt(j) - ((cle.charAt(j%cle.length())) -96));
                    newMessage = replaceCharAt(newMessage, j, c);
                }
                else {
                    char c = (char)(msg.charAt(j) - ((cle.charAt(j%cle.length())) -96-26));
                    newMessage = replaceCharAt(newMessage, j, c);
                }
            }
        }
        return newMessage;
    }

    private float indiceCoincidence(String cle, HashMap<Character, Float> map) {
        //Methodes des indices de coïncidence.
        float IC = 0.f;
        for (int i = 97; i < 123; i++) {
            float numerateur = (map.get((char) i)*cle.length())*((map.get((char) i)*cle.length())-1);
            float denominateur = cle.length() * (cle.length()-1);
            IC += numerateur/denominateur;
        }
        return IC;
    }

    private int cleLength(String cle, HashMap<Character, Float> freq) {
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


    public String casserVigenere(String msg) {
        return null;
    }

    private String replaceCharAt(String s, int pos, char c) {
        char [] tab = s.toCharArray();
        tab[pos] = c;
        return new String(tab);
    }
}
