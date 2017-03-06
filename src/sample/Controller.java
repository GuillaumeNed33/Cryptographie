package sample;

import crypto.Cesar;
import crypto.DiffieHellman;
import crypto.RSA;
import crypto.Vigenere;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * Created by Thomas on 02/03/2017.
 */
public class Controller {
    private RSA rsa;
    private DiffieHellman dh;

    @FXML Label DHAlicePublicKey;

     public Controller() {
        rsa = new RSA();
        dh = new DiffieHellman();
    }

    /*** CESAR ***/
    @FXML
    TextArea cesarInput;
    @FXML
    TextArea cesarOutput;
    @FXML
    TextField cesarDecalage;

    @FXML public void onCesarCryptClicked() {
        if(!Objects.equals(cesarInput.getText(), "")) {
            Cesar cesar = new Cesar(cesarInput.getText(),Integer.parseInt(cesarDecalage.getText()));
            cesarOutput.setText(cesar.crypt(false));
        }
    }

    @FXML public void onCesarDecryptClicked() {
        if(!Objects.equals(cesarOutput.getText(), "")) {
            Cesar cesar = new Cesar(cesarOutput.getText(),Integer.parseInt(cesarDecalage.getText()));
            cesarInput.setText(cesar.decrypt());
        }
    }

    /*** DIFFIE HELLMAN ***/
    @FXML TextField DHBobKeyInput;
    @FXML Button dhcalculateBtn;
    @FXML TextArea DHSharedKeyOutput;

    @FXML public void onDiffieHellmanBobClicked() {
        DHAlicePublicKey.setText(dh.getAlicePublicNumber().toString());
        BigInteger key;
        SecureRandom rnd = new SecureRandom();
        key = BigInteger.valueOf(rnd.nextInt(Integer.MAX_VALUE));
        DHBobKeyInput.setText(key.toString());
        dh.setBobNumber(key);
        dhcalculateBtn.setDisable(false);
    }

    @FXML public void onDHCalculateClicked() {
        DHSharedKeyOutput.setText(dh.calculateKey());
        System.out.println("cc");

    }

    /*** RSA ***/
    @FXML
    TextArea rsaInput;
    @FXML
    TextArea rsaOutput;
    @FXML
    TextField rsaPublicKey_Stanger;
    @FXML
    TextField rsaPrivateKey_Stanger;
    @FXML
    TextField rsaPublicKey;
    @FXML
    TextField rsaPrivateKey;

    @FXML public void onCryptRSAClicked() {
        if(!Objects.equals(rsaPublicKey.getText(), "")) {
         /*   if(!rsaPublicKey.getText().contains("[0-9]+") && !rsaPublicKey.getText().contains("=") && !rsaPublicKey.getText().contains("{") && !rsaPublicKey.getText().contains("}")) {
                rsaOutput.setText("Erreur au niveau de votre clé publique. (Syntaxe requise : {key=value})");
          }
                      else {*/
                rsa.setYourPubKey(rsaPublicKey.getText());
                rsa.setMessage(rsaInput.getText());
                rsaOutput.setText(rsa.cryptage(false));
            //}
        }

    }
    @FXML public void onDecryptRSAClicked() {
        if(!Objects.equals(rsaPrivateKey.getText(), "")) {
            if(!rsaPrivateKey.getText().matches("[0-9]+")) {
                rsaInput.setText("Erreur au niveau de votre clé privée. (Syntaxe requise : NUMBER)");
            }
            else {
                rsa.setYourPrivKey(rsaPrivateKey.getText());
                rsa.setMessageCrypt(rsaOutput.getText());
                rsaInput.setText(rsa.decryptage(false));
            }
        }

    }
    @FXML public void onCryptRSAClickedStranger() {
        if(!Objects.equals(rsaPublicKey_Stanger.getText(), "")) {
           /* if(!rsaPublicKey_Stanger.getText().matches("[0-9]+") && !rsaPublicKey_Stanger.getText().contains("=") && !rsaPublicKey_Stanger.getText().contains("{") && !rsaPublicKey_Stanger.getText().contains("}")) {
                rsaOutput.setText("Erreur au niveau de la clé publique étrangère. (Syntaxe requise : {key=value})");
            }
            else {*/
                rsa.setMessage(rsaInput.getText());
                rsaOutput.setText(rsa.cryptage(true));
           // }
        }
    }
    @FXML public void onDecryptRSAClickedStranger() {
        if(!Objects.equals(rsaPrivateKey_Stanger.getText(), "")) {
            if(!rsaPrivateKey_Stanger.getText().matches("[0-9]+")) {
                rsaInput.setText("Erreur au niveau de la clé privée étrangère. (Syntaxe requise : NUMBER)");
            }
            else {
                rsa.setMessageCrypt(rsaOutput.getText());
                rsaInput.setText(rsa.decryptage(true));
            }
        }

    }
    @FXML public void GenerateRSAKeys() {
        rsaPrivateKey_Stanger.setText(rsa.getPrivKey().toString());
        rsaPublicKey_Stanger.setText(rsa.getPubKey().toString());
    }

    /*** VIGENERE ***/
    @FXML
    TextArea vigenereInput;
    @FXML
    TextArea vigenereOutput;
    @FXML
    TextField vigenereCle;

    @FXML public void onCryptVigenereClicked() {
        Vigenere vi = new Vigenere(vigenereInput.getText(), vigenereCle.getText());
        vigenereOutput.setText(vi.vigenereCrypt());
    }
    @FXML public void onDecryptVigenereClicked() {
        Vigenere vi = new Vigenere(vigenereOutput.getText(), vigenereCle.getText());
        vigenereInput.setText(vi.vigenereDecrypt());
    }
}
