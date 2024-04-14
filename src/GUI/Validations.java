package GUI;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import myRestaurant.MyRestaurant;
import myRestaurant.myDishes.MyDish;
import myRestaurant.mySystemUsers.MyUser;
import myRestaurant.myTables.MyTable;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.regex.Pattern;

public class Validations {
    public static boolean signUpValidation (TextField signUpName, TextField signUpUserName, PasswordField signUpPassword, Label signUpNameLabel, Label signUpPasswordLabel, Label signUpUsernameLabel, ToggleGroup signUpradioButtonsToggle, Label signUpRadioButtonsLabel, List<MyUser>  myUsersList) {

        int flag = 0;

        signUpNameLabel.setText("");
        signUpUsernameLabel.setText("");
        signUpPasswordLabel.setText("");
        signUpRadioButtonsLabel.setText("");

        String newUserName = signUpName.getText();
        String newUserUsername = signUpUserName.getText();
        String newUserPassword = signUpPassword.getText();

        for (MyUser userCompare : myUsersList) {
            if (userCompare.getName().equals(newUserName) && userCompare.getUsername().equals(newUserUsername) && userCompare.getPassword().equals(newUserPassword))
                flag = 1;
        }

        String nameError = "Name is required!";
        String usernameError = "Username is required!";
        String passwordError = "Password is required!";
        String roleError = "Role is required!";
        String repeatedUserError = "User already has an account! ";
        Color errorColor = Color.RED;


        if (newUserName.isEmpty() || newUserName.isEmpty() || newUserPassword.isEmpty() || signUpradioButtonsToggle.getSelectedToggle() == null || !isWord(newUserName) || flag == 1) {
            if (newUserName.isEmpty() || newUserName.isEmpty() || newUserPassword.isEmpty() || signUpradioButtonsToggle.getSelectedToggle() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("CAN'T LEAVE ANY FIELD EMPTY!");
                alert.show();

                if (newUserName.isEmpty() && newUserName.isEmpty() && newUserPassword.isEmpty() && signUpradioButtonsToggle.getSelectedToggle() == null) {
                    signUpNameLabel.setText(nameError);
                    signUpNameLabel.setTextFill(errorColor);
                    signUpUsernameLabel.setText(usernameError);
                    signUpNameLabel.setTextFill(errorColor);
                    signUpPasswordLabel.setText(passwordError);
                    signUpNameLabel.setTextFill(errorColor);
                    signUpNameLabel.setTextFill(errorColor);
                    signUpRadioButtonsLabel.setText(roleError);
                }
                if (newUserName.isEmpty()) {
                    signUpNameLabel.setText(nameError);
                    signUpNameLabel.setTextFill(errorColor);
                }
                if (newUserUsername.isEmpty()) {
                    signUpUsernameLabel.setText(usernameError);
                    signUpUsernameLabel.setTextFill(errorColor);
                }
                if (newUserPassword.isEmpty()) {
                    signUpPasswordLabel.setText(passwordError);
                    signUpPasswordLabel.setTextFill(errorColor);
                }
                if (signUpradioButtonsToggle.getSelectedToggle() == null) {
                    signUpRadioButtonsLabel.setText(roleError);
                    signUpRadioButtonsLabel.setTextFill(errorColor);
                }
            }
            if (flag == 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("USER EXISTS!");
                alert.show();
                signUpRadioButtonsLabel.setText(repeatedUserError);
                signUpRadioButtonsLabel.setTextFill(errorColor);
                signUpName.clear();
                signUpUserName.clear();
                signUpPassword.clear();
                signUpradioButtonsToggle.getSelectedToggle().setSelected(false);
            }
            if (!isWord(newUserName)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("INVALID NAME");
                alert.show();
                signUpName.clear();
                signUpNameLabel.setText(nameError);
                signUpNameLabel.setTextFill(errorColor);
            }
            return false;
        }
        return true;
    }

    public static boolean signUpValidationManager (TextField signUpNameManager, TextField signUpUserNameManager, PasswordField signUpPasswordManager, Label signUpNameLabelManager, Label signUpPasswordLabelManager, Label signUpUsernameLabelManager, ToggleGroup signUpradioButtonsToggleManager, Label signUpRadioButtonsLabelManager, List<MyUser> myUsersList)
    {
        int flag = 0;

        String newUserName = signUpNameManager.getText();
        String newUserUsername = signUpUserNameManager.getText();
        String newUserPassword = signUpPasswordManager.getText();

        for (MyUser userCompare : myUsersList) {
            if (userCompare.getName().equals(newUserName) && userCompare.getUsername().equals(newUserUsername) && userCompare.getPassword().equals(newUserPassword))
                flag = 1;
        }

        String nameError = "Name is required!";
        String usernameError = "Username is required!";
        String passwordError = "Password is required!";
        String roleError = "Role is required!";
        String repeatedUserError = "User already has an account! ";
        Color errorColor = Color.RED;

        if (newUserName.isEmpty() || newUserName.isEmpty() || newUserPassword.isEmpty() || signUpradioButtonsToggleManager.getSelectedToggle() == null || !isWord(newUserName) || flag == 1) {
            if (newUserName.isEmpty() || newUserName.isEmpty() || newUserPassword.isEmpty() || signUpradioButtonsToggleManager.getSelectedToggle() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("CAN'T LEAVE ANY FIELD EMPTY!");
                alert.show();
                if (newUserName.isEmpty() && newUserName.isEmpty() && newUserPassword.isEmpty() && signUpradioButtonsToggleManager.getSelectedToggle() == null) {
                    signUpNameLabelManager.setText(nameError);
                    signUpNameLabelManager.setTextFill(errorColor);
                    signUpUsernameLabelManager.setText(usernameError);
                    signUpNameLabelManager.setTextFill(errorColor);
                    signUpPasswordLabelManager.setText(passwordError);
                    signUpNameLabelManager.setTextFill(errorColor);
                    signUpNameLabelManager.setTextFill(errorColor);
                    signUpRadioButtonsLabelManager.setText(roleError);
                }
                if (newUserName.isEmpty()) {
                    signUpNameLabelManager.setText(nameError);
                    signUpNameLabelManager.setTextFill(errorColor);
                }
                if (newUserUsername.isEmpty()) {
                    signUpUsernameLabelManager.setText(usernameError);
                    signUpUsernameLabelManager.setTextFill(errorColor);
                }
                if (newUserPassword.isEmpty()) {
                    signUpPasswordLabelManager.setText(passwordError);
                    signUpPasswordLabelManager.setTextFill(errorColor);
                }
                if (signUpradioButtonsToggleManager.getSelectedToggle() == null) {
                    signUpRadioButtonsLabelManager.setText(roleError);
                    signUpRadioButtonsLabelManager.setTextFill(errorColor);
                }
            }
            if (flag == 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("USER EXISTS!");
                alert.show();
                signUpRadioButtonsLabelManager.setText(repeatedUserError);
                signUpRadioButtonsLabelManager.setTextFill(errorColor);
                signUpNameManager.clear();
                signUpUserNameManager.clear();
                signUpPasswordManager.clear();
                if(signUpradioButtonsToggleManager.getSelectedToggle()!=null)
                signUpradioButtonsToggleManager.getSelectedToggle().setSelected(false);

            }
            if (!isWord(newUserName)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("INVALID NAME");
                alert.show();
                signUpNameManager.clear();
                signUpNameLabelManager.setText(nameError);
                signUpNameLabelManager.setTextFill(errorColor);
            }
            return false;
        }
        return true;
    }
    public static boolean isWord (String string)
    {
        String regex = "^[\\p{L} .'-]+$";
        return Pattern.matches(regex, string);
    }
}
