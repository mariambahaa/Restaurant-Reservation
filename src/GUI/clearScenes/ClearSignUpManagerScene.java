package GUI.clearScenes;
import javafx.scene.control.*;

public class ClearSignUpManagerScene {

    public static void clearSignUpManager (TextField signUpNameManager, TextField signUpUserNameManager, PasswordField signUpPasswordManager, Label signUpNameLabelManager, Label signUpPasswordLabelManager, Label signUpUsernameLabelManager, CheckBox showPasswordCheckBoxManager, ToggleGroup signUpradioButtonsToggleManager) {

        if(!signUpNameManager.getText().isEmpty())
        signUpNameManager.clear();
        if(!signUpUserNameManager.getText().isEmpty())
        signUpUserNameManager.clear();
        if(!signUpPasswordManager.getText().isEmpty())
        signUpPasswordManager.clear();
        signUpNameLabelManager.setText("");
        signUpPasswordLabelManager.setText("");
        signUpUsernameLabelManager.setText("");
        if(showPasswordCheckBoxManager.isSelected())
        showPasswordCheckBoxManager.setSelected(false);
        if(signUpradioButtonsToggleManager.getSelectedToggle()!=null)
        signUpradioButtonsToggleManager.getSelectedToggle().setSelected(false);

    }
}
