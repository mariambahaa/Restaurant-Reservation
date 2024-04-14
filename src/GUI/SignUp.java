package GUI;

import javafx.scene.control.*;
import myRestaurant.MyRestaurant;
import myRestaurant.myDishes.MyDish;
import myRestaurant.mySystemUsers.*;
import myRestaurant.myTables.MyTable;

import javax.xml.bind.JAXBException;
import java.util.List;


public class SignUp {

    public static void signUp (TextField signUpName, TextField signUpUserName, PasswordField signUpPassword, RadioButton [] signUpRadioButtons, List<MyUser> myUsersList, List<MyTable> myTablesList, List<MyDish> myDishesList, List<MyDish> myAppetizersList, List<MyDish> myMaincourseList, List<MyDish> myDessertsList, MyRestaurant myyRestaurant) {

        String newUserName = signUpName.getText();
        String newUserUsername = signUpUserName.getText();
        String newUserPassword = signUpPassword.getText();

        signUpName.clear();
        signUpUserName.clear();
        signUpPassword.clear();

        if (signUpRadioButtons[0].isSelected()) {
            Client newClient = new Client(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newClient);
        } else if (signUpRadioButtons[1].isSelected()) {
            PremiumClient newPremiumClient = new PremiumClient(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newPremiumClient);
        } else if (signUpRadioButtons[2].isSelected()) {
            Manager newManager = new Manager(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newManager);
        } else if (signUpRadioButtons[3].isSelected()) {
            Waiter newWaiter = new Waiter(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newWaiter);
        } else if (signUpRadioButtons[4].isSelected()) {
            Cook newCook = new Cook(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newCook);
        }

        try {
            XML.saveToXML(myUsersList, myTablesList, myDishesList, myAppetizersList, myMaincourseList, myDessertsList, myyRestaurant);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static void signUpManager (TextField signUpNameManager, TextField signUpUserNameManager, PasswordField signUpPasswordManager, RadioButton [] signUpRadioButtonsManager, List<MyUser> myUsersList, List<MyTable> myTablesList, List<MyDish> myDishesList, List<MyDish> myAppetizersList, List<MyDish> myMaincourseList, List<MyDish> myDessertsList, MyRestaurant myyRestaurant) {

        String newUserName = signUpNameManager.getText();
        String newUserUsername = signUpUserNameManager.getText();
        String newUserPassword = signUpPasswordManager.getText();

        signUpNameManager.clear();
        signUpUserNameManager.clear();
        signUpPasswordManager.clear();

        if (signUpRadioButtonsManager[0].isSelected()) {
            Client newClient = new Client(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newClient);
        } else if (signUpRadioButtonsManager[1].isSelected()) {
            PremiumClient newPremiumClient = new PremiumClient(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newPremiumClient);
        } else if (signUpRadioButtonsManager[2].isSelected()) {
            Manager newManager = new Manager(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newManager);
        } else if (signUpRadioButtonsManager[3].isSelected()) {
            Waiter newWaiter = new Waiter(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newWaiter);
        } else if (signUpRadioButtonsManager[4].isSelected()) {
            Cook newCook = new Cook(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newCook);
        }

        try {
            XML.saveToXML(myUsersList, myTablesList, myDishesList, myAppetizersList, myMaincourseList, myDessertsList, myyRestaurant);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
