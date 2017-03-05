package sample;

import crypto.Cesar;
import crypto.RSA;
import crypto.Vigenere;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

/**
 * Created by Thomas on 02/03/2017.
 */
public class Controller {
    private RSA rsa;

    public Controller() {
        rsa = new RSA();
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
        rsa.setMessage(rsaInput.getText());
        rsaOutput.setText(rsa.cryptage());
    }
    @FXML public void onDecryptRSAClicked() {

    }

    @FXML public void onCryptRSAClickedStranger() {
        rsa.setMessage(rsaInput.getText());
        rsaOutput.setText(rsa.cryptage());
    }
    @FXML public void onDecryptRSAClickedStranger() {

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
