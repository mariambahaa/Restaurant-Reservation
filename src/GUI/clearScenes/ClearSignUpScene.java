package GUI.clearScenes;
import javafx.scene.control.*;

public class ClearSignUpScene {

    public static void clearSignUp (TextField signUpName, TextField signUpUserName, PasswordField signUpPassword, Label signUpNameLabel, Label signUpPasswordLabel, Label signUpUsernameLabel, CheckBox showPasswordCheckBox, ToggleGroup signUpradioButtonsToggle) {
       if(!signUpName.getText().isEmpty())
        signUpName.clear();
        if(!signUpUserName.getText().isEmpty())
        signUpUserName.clear();
        if(!signUpPassword.getText().isEmpty())
        signUpPassword.clear();
        signUpNameLabel.setText("");
        signUpPasswordLabel.setText("");
        signUpUsernameLabel.setText("");
        if(showPasswordCheckBox.isSelected())
        showPasswordCheckBox.setSelected(false);
        if(signUpradioButtonsToggle.getSelectedToggle()!=null)
        signUpradioButtonsToggle.getSelectedToggle().setSelected(false);
    }
}
