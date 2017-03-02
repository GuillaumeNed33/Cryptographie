package sample;

import crypto.Cesar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.util.Objects;

/**
 * Created by Thomas on 02/03/2017.
 */
public class Controller {
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
}
