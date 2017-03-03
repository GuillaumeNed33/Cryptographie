package sample;

import crypto.Cesar;
import crypto.RSA;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 * Created by Thomas on 02/03/2017.
 */
public class Controller {
    RSA rsa;
    Controller() {
        rsa = new RSA();
    }
    @FXML
    TextArea cesarInput;
    @FXML
    TextArea cesarOutput;
    @FXML
    TextField cesarDecalage;

    @FXML public void onCesarClicked() {
        if(!Objects.equals(cesarInput.getText(), "")) {
            Cesar cesar = new Cesar(cesarInput.getText(),2);
            cesarOutput.setText(cesar.crypt());
        }

    }
    @FXML
    TextArea rsaInput;
    @FXML
    TextArea rsaOutput;

    @FXML public void onCryptRSAClicked() {
        rsa.setMessage(rsaInput.getText());
        rsaOutput.setText(rsa.cryptage());
    }
    @FXML public void onDecryptRSAClicked() {

    }
}
