package GUI.clearScenes;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class ClearLoginScene {

    public static void clearLogin(TextField loginUsernameField, PasswordField loginPasswordField, Label showLoginPasswordLabel, CheckBox showloginPasswordCheckBox)
    {
        if(!loginUsernameField.getText().isEmpty())
        loginUsernameField.clear();
        if(!loginPasswordField.getText().isEmpty())
        loginPasswordField.clear();
        showLoginPasswordLabel.setText("");
        if(showloginPasswordCheckBox.isSelected())
        showloginPasswordCheckBox.setSelected(false);
    }
}
