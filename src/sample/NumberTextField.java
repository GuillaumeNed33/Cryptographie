package sample;

import javafx.scene.control.TextField;
/**
 * Created by Guillaume on 03/03/2017.
 */
public class NumberTextField extends TextField {

    public NumberTextField() {
        this.setPromptText("Enter only numbers");
    }

    @Override
    public void replaceText(int i, int il, String string) {
        if(string.matches("[0-9]") || string.isEmpty()) {
            super.replaceText(i,il,string);
        }
    }

    @Override
    public void replaceSelection(String string) {
       super.replaceSelection(string);
    }
}
