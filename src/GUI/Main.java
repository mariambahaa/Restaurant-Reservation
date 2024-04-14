package GUI;

import GUI.clearScenes.ClearLoginScene;
import GUI.clearScenes.ClearSignUpManagerScene;
import GUI.clearScenes.ClearSignUpScene;
import GUI.clientDetailsScenes.clientCookScene;
import GUI.clientDetailsScenes.clientManagerScene;

import GUI.clientDetailsScenes.clientWaiterScene;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import myRestaurant.MyRestaurant;
import myRestaurant.myDishes.*;
import myRestaurant.payment.Cash;
import myRestaurant.payment.Visa;
import org.controlsfx.control.CheckListView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import myRestaurant.myTables.MyTable;
import myRestaurant.mySystemUsers.*;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;


public class Main extends Application {


    List<MyUser> myUsersList = new ArrayList<>();
    List<MyDish> myDishesList = new ArrayList<>();
    List<MyDish> myAppetizersList = new ArrayList<>();
    List<MyDish> myMaincourseList = new ArrayList<>();
    List<MyDish> myDessertsList = new ArrayList<>();
    List<MyTable> myTablesList = new ArrayList<>();

    MyUser currentUser = null;

    HashMap<Client, MyTable> clientMyTableHashMap = new HashMap<Client, MyTable>();
    MyRestaurant myyRestaurant = new MyRestaurant();

    TextField loginUsernameField = new TextField();
    PasswordField loginPasswordField = new PasswordField();
    Label showLoginPasswordLabel = new Label("");
    CheckBox showloginPasswordCheckBox = new CheckBox("Show Password");

    Button addAllDishes = new Button();
    Button checkoutButton = new Button();

    TextField signUpName = new TextField();
    TextField signUpUserName = new TextField();
    PasswordField signUpPassword = new PasswordField();
    TextField showPassword = new TextField();
    RadioButton [] signUpRadioButtons;
    ToggleGroup signUpradioButtonsToggle = new ToggleGroup();
    Label signUpNameLabel;
    Label signUpUsernameLabel;
    Label signUpPasswordLabel;
    Label signUpRadioButtonsLabel;
    CheckBox showPasswordCheckBox;

    TextField signUpNameManager = new TextField();
    TextField signUpUserNameManager = new TextField();
    PasswordField signUpPasswordManager = new PasswordField();
    TextField showPasswordManager = new TextField();
    RadioButton [] signUpRadioButtonsManager;
    ToggleGroup signUpradioButtonsToggleManager = new ToggleGroup();
    Label signUpNameLabelManager;
    Label signUpUsernameLabelManager;
    Label signUpPasswordLabelManager;
    Label signUpRadioButtonsLabelManager;
    CheckBox showPasswordCheckBoxManager;


    ObservableList<String> checkboxAppetizerName = FXCollections.observableArrayList();
    ObservableList<String> checkboxMaincourseName = FXCollections.observableArrayList();
    ObservableList<String> checkboxDessertName = FXCollections.observableArrayList();

    ListView <String> appetizersPriceList;
    ListView <String> maincoursePriceList;
    ListView <String> dessertsPriceList;

    List <String> priceAppetizer;
    List <String> priceMaincourse;
    List <String> priceDessert;

    CheckListView <String> checkboxAppetizer;
    CheckListView <String> checkboxMaincourse;
    CheckListView <String> checkboxDessert;

    TextField [] quantityAppetizersText;
    TextField [] quantityMaincourseText;
    TextField [] quantityDessertsText;


    ObservableList<Integer> selectedAppetizerIndices;
    ObservableList<Integer> selectedMaincourseIndices;
    ObservableList<Integer> selectedDessertIndices;

    Button backButtonManager4=new Button("Back");
    Button backButtonCook2=new Button("Back");
    Button backButtonWaiter2=new Button("Back");

    Font titleFont = new Font("verdana", 60);
    Font subtitleFont = new Font("verdana", 30);
    Font checkBoxFont = new Font("verdana", 15);
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = stage;
        //loginScene
        GridPane gridpane = new GridPane();
        Image backgroundImage = null;
        try {
            backgroundImage = new Image(new FileInputStream("src\\GUI\\images\\background.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BackgroundSize backgroundSize = new BackgroundSize(500, 500, true, true, true, true);
        BackgroundImage backgroundImage1 = new BackgroundImage(backgroundImage, null, null, null, backgroundSize);
        gridpane.setBackground(new Background(backgroundImage1));
        gridpane.setAlignment(Pos.CENTER);
        Text title = new Text("Welcome!");
        Text loginUsername = new Text("Username: ");
        Text loginPassword = new Text("Password: ");
        title.setFont(titleFont);
        loginUsername.setFont(subtitleFont);
        loginPassword.setFont(subtitleFont);
        //TextField loginUsernameField = new TextField();
        loginUsernameField.setPromptText("username");
        //PasswordField loginPasswordField = new PasswordField();
        loginPasswordField.setPromptText("password");
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        //Label showLoginPasswordLabel = new Label("");
       //CheckBox showloginPasswordCheckBox = new CheckBox("Show Password");
        showloginPasswordCheckBox.setTextFill(Color.WHITE);
        showloginPasswordCheckBox.setFont(checkBoxFont);
        showloginPasswordCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                showLoginPasswordLabel.setText(newValue ? loginPasswordField.getText() : " ");
            }
        });
        gridpane.setMinSize(500, 500);
        gridpane.setPadding(new Insets(10, 10, 10, 10));
        gridpane.setVgap(10);
        gridpane.setHgap(10);
        gridpane.add(title, 0, 0);
        gridpane.add(loginUsername, 0, 1);
        gridpane.add(loginUsernameField, 0, 2);
        gridpane.add(loginPassword, 0, 3);
        gridpane.add(loginPasswordField, 0, 4);
        gridpane.add(loginButton, 2, 5);
        gridpane.add(signUpButton, 2, 6);
        gridpane.add(showLoginPasswordLabel,0, 5 );
        gridpane.add(showloginPasswordCheckBox,0, 6);
        Scene loginScene = new Scene(gridpane);

        Text welcome = new Text("Welcome!");
        welcome.setFont(new Font("Arial", 35));

        Text signUpNameText = new Text("Enter name: ");
        Text signUpUsernameText = new Text("Enter username: ");
        Text signUpPasswordText = new Text("Enter password: ");

        signUpName = new TextField();
        signUpUserName = new TextField();
        signUpPassword = new PasswordField();

        signUpNameLabel = new Label();
        signUpUsernameLabel = new Label();
        signUpPasswordLabel = new Label();
        signUpRadioButtonsLabel = new Label();
        Label showPassword = new Label("");

        signUpRadioButtons = new RadioButton[5];
        signUpRadioButtons[0] = new RadioButton("Client");
        signUpRadioButtons[1] = new RadioButton("Premium Client");
        signUpRadioButtons[2] = new RadioButton("Manager");
        signUpRadioButtons[3] = new RadioButton("Waiter");
        signUpRadioButtons[4] = new RadioButton("Cook");

        signUpradioButtonsToggle = new ToggleGroup();
        signUpRadioButtons[0].setToggleGroup(signUpradioButtonsToggle);
        signUpRadioButtons[1].setToggleGroup(signUpradioButtonsToggle);
        signUpRadioButtons[2].setToggleGroup(signUpradioButtonsToggle);
        signUpRadioButtons[3].setToggleGroup(signUpradioButtonsToggle);
        signUpRadioButtons[4].setToggleGroup(signUpradioButtonsToggle);

        Button finishSignUp = new Button("Done ");
        showPasswordCheckBox = new CheckBox("Show Password");

        showPasswordCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                showPassword.setText(newValue ? signUpPassword.getText() : " ");
            }
        });

        HBox hBoxSignUpRadioButtons = new HBox(signUpRadioButtons);
        VBox vBoxSignUp = new VBox(signUpNameText, signUpName, signUpNameLabel, signUpUsernameText, signUpUserName,signUpUsernameLabel, signUpPasswordText, signUpPassword,showPassword, signUpPasswordLabel, hBoxSignUpRadioButtons, signUpRadioButtonsLabel, showPasswordCheckBox, finishSignUp);
        vBoxSignUp.setSpacing(10);
        vBoxSignUp.setPadding(new Insets(10,10,10,10));

        BorderPane signUpBorderpane = new BorderPane();
        signUpBorderpane.setTop(welcome);
        signUpBorderpane.setCenter(vBoxSignUp);

        Button backButtonSignUp=new Button("Back");
        signUpBorderpane.setRight(backButtonSignUp);

        Scene signUpScene = new Scene(signUpBorderpane, 600,500);
        //signUp scene


        //customerScene2
        BorderPane customer2 = new BorderPane();
        Line line = new Line(200, 0, 200, 1000);
        Image reserveImage = null;
        try {
            reserveImage = new Image(new FileInputStream("src\\GUI\\images\\restaurant.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView reserveImageView = new ImageView(reserveImage);
        reserveImageView.setFitWidth(30);
        reserveImageView.setFitHeight(30);
        VBox vBoxButtons = new VBox();
        VBox vBox2 = new VBox();
        HBox hBox = new HBox();
        vBoxButtons.setMinWidth(150);
        vBoxButtons.setSpacing(10);
        vBoxButtons.setPadding(new Insets(10,10,10,10));
        Button profileButton = new Button("PROFILE");
        Button reservationButton = new Button("RESERVATION", reserveImageView);
        Button menuButton = new Button("ADD DISHES");
        reservationButton.setStyle("-fx-base: coral;");
        reservationButton.setContentDisplay(ContentDisplay.TOP);
        vBoxButtons.getChildren().addAll(profileButton, reservationButton,menuButton);
        vBoxButtons.setSpacing(10);
        vBoxButtons.setMargin(profileButton, new Insets(50, 10, 10, 10));
        vBoxButtons.setMargin(reservationButton, new Insets(10, 10, 10, 10));
        vBoxButtons.setMargin(menuButton, new Insets(10, 10, 50, 10));
        hBox.getChildren().addAll(vBoxButtons, line);
        Text clientProfileInfo = new Text();
        Text reserveInfo = new Text();
        clientProfileInfo.setFont(new Font("verdana", 40));
        vBox2.getChildren().addAll(clientProfileInfo, reserveInfo);
        customer2.setLeft(hBox);
        customer2.setCenter(vBox2);
        customer2.setMargin(clientProfileInfo, new Insets(10, 10, 10, 10));
        Button customerBackButton1=new Button("Logout");
        customer2.setRight(customerBackButton1);
        Scene customerScene2 = new Scene(customer2, 600, 400);


        //customerScene3
        Text tableNumberLabel = new Text();
        Label tableSeatsMissingLabel = new Label();
        Label typeOfTableMissingLabel = new Label();
        BorderPane customer3 = new BorderPane();
        Text tableTypeText = new Text("Choose the type of table you want");
        tableTypeText.setFont(new Font("Arial", 20));
        Text smokingText = new Text("Smoking or no smoking");
        smokingText.setFont(new Font("Arial", 15));
        Text seatsNumberText = new Text("Choose the number of seats you want");
        seatsNumberText.setFont(new Font("Arial", 15));
        RadioButton smokingButton = new RadioButton("Smoking");
        RadioButton noSmokingButton = new RadioButton("No Smoking");

        ToggleGroup smokingButtons = new ToggleGroup();
        smokingButton.setToggleGroup(smokingButtons);
        noSmokingButton.setToggleGroup(smokingButtons);

        Image image = null;
        try {
            image = new Image(new FileInputStream("src\\seats.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView seatImageView = new ImageView(image);
        seatImageView.setFitHeight(5);
        seatImageView.setFitWidth(5);

        Text seatsText = new Text("Enter number of seats you need");
        TextField seatsTextField = new TextField();
        seatsTextField.setPromptText("number of seats");
         seatsTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    seatsTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        Text finishReservationText = new Text("Finished reservation? Click here ");
        finishReservationText.setFont(new Font("Arial", 15));
        Button reserveButton = new Button("Reserve");
        VBox reservationVBox = new VBox(tableTypeText, smokingText, smokingButton, noSmokingButton,typeOfTableMissingLabel, seatsText, seatsTextField,tableSeatsMissingLabel);
        reservationVBox.setSpacing(20);
        reservationVBox.setPadding(new Insets(50,10,50,10));
        HBox reserveHBox = new HBox(finishReservationText, reserveButton);
        VBox wholeReservationVBox = new VBox(reservationVBox,reserveHBox);
        wholeReservationVBox.setMinWidth(150);
        wholeReservationVBox.setSpacing(10);
        wholeReservationVBox.setPadding(new Insets(50,10,50,10));
        customer3.setLeft(wholeReservationVBox);
        customer3.setTop(tableNumberLabel);
        Button customerBackButton2=new Button("Back");
        customer3.setRight(customerBackButton2);
        Scene customerScene3 = new Scene(customer3, 800, 800);

        //menu
        GridPane gridpaneMenu = new GridPane();
        final VBox vBoxAppetizer = new VBox();
        final VBox vBoxMaincourse=new VBox();
        final VBox vBoxDessert=new VBox();
        final VBox vBoxAppetizerPrice=new VBox();
        final VBox vBoxMaincoursePrice=new VBox();
        final VBox vBoxDessertPrice=new VBox();
        final VBox vBoxQuantityAppetizer=new VBox();
        final VBox vBoxQuantityMaincourse=new VBox();
        final VBox vBoxQuantityDessert=new VBox();
        Text menuTitle = new Text("Food Menu");
        menuTitle.setFont(new Font("Arial", 80));
        Text appetizerTitle=new Text("Appetizers");
        menuTitle.setFont(new Font("Arial", 60));
        Text maincourseTitle=new Text("Maincourse");
        menuTitle.setFont(new Font("Arial", 60));
        Text dessertTitle=new Text("Dessert");
        menuTitle.setFont(new Font("Arial", 60));

        Text taxesText = new Text("Taxes\nAppetizers --> 10%\nMaincourse --> 15%\nDesserts --> 20%\n" );
        taxesText.setFont(new Font("verdana", 15));

        gridpaneMenu.setStyle("-fx-background-color: beige");

        checkboxAppetizerName = FXCollections.observableArrayList("Greek Salad", "Fried Potatoes");
        checkboxMaincourseName = FXCollections.observableArrayList("Grilled Chicken", "Mushroom Soup", "Beef Steak");
        checkboxDessertName = FXCollections.observableArrayList("Molten Cake", "Apple Pie");

        checkboxAppetizer = new CheckListView<>(checkboxAppetizerName);
        checkboxMaincourse = new CheckListView<>(checkboxMaincourseName);
        checkboxDessert = new CheckListView<>(checkboxDessertName);

        checkboxAppetizer.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        checkboxMaincourse.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        checkboxDessert.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        quantityAppetizersText = new TextField[checkboxAppetizerName.size()];
        quantityMaincourseText = new TextField[checkboxMaincourseName.size()];
        quantityDessertsText = new TextField[checkboxDessertName.size()];


        for (int i=0; i<checkboxAppetizerName.size(); i++) {
            TextField textField = new TextField();
            textField.setText("0");
            quantityAppetizersText[i] = textField;
            int finalI = i;
            quantityAppetizersText[i].textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        quantityAppetizersText[finalI].setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            vBoxQuantityAppetizer.getChildren().add(textField);
        }
        for (int i=0; i<checkboxMaincourseName.size(); i++) {

            TextField textField = new TextField();
            textField.setText("0");
            quantityMaincourseText[i] = textField;
            int finalI = i;
            quantityMaincourseText[i].textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        quantityMaincourseText[finalI].setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            vBoxQuantityMaincourse.getChildren().add(textField);
        }
        for (int i=0; i<checkboxDessertName.size(); i++) {
            quantityDessertsText[i] = new TextField();
            quantityDessertsText[i].setText("0");
            int finalI = i;
            quantityDessertsText[i].textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        quantityDessertsText[finalI].setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            vBoxQuantityDessert.getChildren().add(quantityDessertsText[i]);
        }

        appetizersPriceList = new ListView<>();
        maincoursePriceList = new ListView<>();
        dessertsPriceList = new ListView<>();

        priceAppetizer = new ArrayList<String>();
        priceAppetizer.add("35");
        priceAppetizer.add("30");
        priceMaincourse = new ArrayList<String>();
        priceMaincourse.add("75");
        priceMaincourse.add("60");
        priceMaincourse.add("80");
        priceDessert = new ArrayList<String>();
        priceDessert.add("50");
        priceDessert.add("60");

        appetizersPriceList.getItems().addAll(priceAppetizer);
        maincoursePriceList.getItems().addAll(priceMaincourse);
        dessertsPriceList.getItems().addAll(priceDessert);

        vBoxAppetizer.setMinWidth(150);
        vBoxMaincourse.setMinWidth(150);
        vBoxDessert.setMinWidth(150);
        vBoxAppetizerPrice.setMinWidth(30);
        vBoxMaincoursePrice.setMinWidth(30);
        vBoxDessertPrice.setMinWidth(30);

        vBoxAppetizer.getChildren().addAll(checkboxAppetizer);
        vBoxMaincourse.getChildren().addAll(checkboxMaincourse);
        vBoxDessert.getChildren().addAll(checkboxDessert);
        vBoxAppetizer.setPadding(new Insets(10,10,10,10));
        vBoxAppetizer.setSpacing(10);
        vBoxMaincourse.setPadding(new Insets(10,10,10,10));
        vBoxMaincourse.setSpacing(10);
        vBoxDessert.setPadding(new Insets(10,10,10,10));
        vBoxDessert.setSpacing(10);

        vBoxAppetizerPrice.getChildren().addAll(appetizersPriceList);
        vBoxMaincoursePrice.getChildren().addAll(maincoursePriceList);
        vBoxDessertPrice.getChildren().addAll(dessertsPriceList);

        vBoxAppetizerPrice.setPadding(new Insets(10,10,10,10));
        vBoxAppetizerPrice.setSpacing(10);
        vBoxMaincoursePrice.setPadding(new Insets(10,10,10,10));
        vBoxMaincoursePrice.setSpacing(10);
        vBoxDessertPrice.setPadding(new Insets(10,10,10,10));
        vBoxDessertPrice.setSpacing(10);

        gridpaneMenu.setAlignment(Pos.CENTER);
        gridpaneMenu.setPadding(new Insets(5));
        gridpaneMenu.setHgap(5);
        gridpaneMenu.setVgap(5);
        gridpaneMenu.setPadding(new Insets(10,10,10,10));
        gridpaneMenu.add(vBoxAppetizer,0,2);
        gridpaneMenu.add(vBoxMaincourse,3,2);
        gridpaneMenu.add(vBoxDessert,6,2);
        gridpaneMenu.add(vBoxAppetizerPrice,1,2);
        gridpaneMenu.add(vBoxMaincoursePrice,4,2);
        gridpaneMenu.add(vBoxDessertPrice,7,2);
        vBoxQuantityAppetizer.setMinWidth(50);
        vBoxQuantityMaincourse.setMinWidth(50);
        vBoxQuantityDessert.setMinWidth(50);
        vBoxQuantityAppetizer.setPadding(new Insets(10,10,10,10));
        vBoxQuantityAppetizer.setSpacing(10);
        vBoxQuantityMaincourse.setPadding(new Insets(10,10,10,10));
        vBoxQuantityMaincourse.setSpacing(10);
        vBoxQuantityDessert.setPadding(new Insets(10,10,10,10));
        vBoxQuantityDessert.setSpacing(10);
        gridpaneMenu.add(vBoxQuantityAppetizer,2,2);
        gridpaneMenu.add(vBoxQuantityMaincourse,5,2);
        gridpaneMenu.add(vBoxQuantityDessert,8,2);

        Image moltenCake = new Image(new FileInputStream("src\\GUI\\images\\moltenCake.png"));
        ImageView moltenCakeImageView = new ImageView(moltenCake);
        moltenCakeImageView.setFitHeight(100);
        moltenCakeImageView.setFitWidth(100);
        Image grilledChicken = new Image(new FileInputStream("src\\GUI\\images\\grilledChicken.png"));
        ImageView grilledChickenImageView = new ImageView(grilledChicken);
        grilledChickenImageView.setFitHeight(100);
        grilledChickenImageView.setFitWidth(100);
        Image greekSalad = new Image(new FileInputStream("src\\GUI\\images\\greekSalad.png"));
        ImageView greekSaladImageView = new ImageView(greekSalad);
        greekSaladImageView.setFitHeight(100);
        greekSaladImageView.setFitWidth(100);

        //checkout
        addAllDishes.setText("Click to add dishes first!");
        addAllDishes.setMinWidth(100);
        checkoutButton.setText("Checkout");

        gridpaneMenu.add(menuTitle,4,0);
        gridpaneMenu.add(appetizerTitle, 0, 1);
        gridpaneMenu.add(maincourseTitle, 3, 1);
        gridpaneMenu.add(dessertTitle, 6, 1);
        gridpaneMenu.add(taxesText, 6,3);
        gridpaneMenu.add(addAllDishes, 6,4);
        gridpaneMenu.add(checkoutButton, 7,5);
        gridpaneMenu.add(greekSaladImageView, 1,4);
        gridpaneMenu.add(grilledChickenImageView, 4,4);
        gridpaneMenu.add(moltenCakeImageView, 7,4);

        Button customerBackButton3=new Button("Back");
        gridpaneMenu.add(customerBackButton3,8,3);

        Scene menuScene=new Scene(gridpaneMenu,1800,1800);

        //checkout client scene
        BorderPane borderpaneCheckout=new BorderPane();
        borderpaneCheckout.setPadding(new Insets(10, 20, 10, 20));

        Text titleText=new Text("Checkout");
        titleText.setFont(titleFont);
        Text totalMoney = new Text();
        totalMoney.setFont(new Font("verdana", 20));
        borderpaneCheckout.setTop(titleText);
        titleText.setTextAlignment(TextAlignment.CENTER);

        Text paymentText=new Text("Choose your payment method");
        paymentText.setFont(subtitleFont);

        ToggleGroup paymentToggle = new ToggleGroup();
        RadioButton cash = new RadioButton("Cash");
        cash.setToggleGroup(paymentToggle);
        RadioButton visa = new RadioButton("Visa");
        visa.setToggleGroup(paymentToggle);
        Label cashLabel = new Label();
        TextField visaCardNumber=new TextField();
        visaCardNumber.setPromptText("card number");
        Button finishCardNumberEntry=new Button("Submit Card Number");

        paymentToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {

                if (paymentToggle.getSelectedToggle() == cash) {
                    ((Client) currentUser).setPaymentMethod(new Cash(((Client) currentUser).getMoneyPaid()));
                    cashLabel.setText("Your Total Money is\n" + ((Client) currentUser).getMoneyPaid());

                } else if (paymentToggle.getSelectedToggle() == visa) {
                    cashLabel.setText("Your Total Money is\n" + ((Client) currentUser).getMoneyPaid());
                    VBox vboxText=new VBox();
                    vboxText.getChildren().addAll(visaCardNumber,finishCardNumberEntry,cashLabel);
                    borderpaneCheckout.setCenter(vboxText);

                    // force the field to be numeric only
                    visaCardNumber.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue,
                                            String newValue) {
                            if (!newValue.matches("\\d*")) {
                                visaCardNumber.setText(newValue.replaceAll("[^\\d]", ""));
                            }
                        }
                    });
                }
            }
        });
                    //submit button
                    Button doneButton = new Button();
                    doneButton.setText("Done");
                    Button backButtonCheckout = new Button("back");
                    VBox vBoxCentreCheckout = new VBox(totalMoney, paymentText, cash, visa, cashLabel);
                    vBoxCentreCheckout.setSpacing(10);
                    vBoxCentreCheckout.setPadding(new Insets(20, 10, 10, 10));
                    borderpaneCheckout.setRight(backButtonCheckout);
                    borderpaneCheckout.setBottom(doneButton);
                    borderpaneCheckout.setCenter(vBoxCentreCheckout);
                    borderpaneCheckout.setCenter(vBoxCentreCheckout);
                    Scene checkoutScene = new Scene(borderpaneCheckout, 600, 600);

                    //managerScene2
                    BorderPane manager2 = new BorderPane();
                    Text managerInfo = new Text();
                    managerInfo.setFont(new Font("Arial", 30));
                    Image managerImage = null;
                    try {
                        managerImage = new Image(new FileInputStream("src\\GUI\\images\\manager.png"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    ImageView managerImageView = new ImageView(managerImage);
                    managerImageView.setFitWidth(100);
                    managerImageView.setFitHeight(100);
                    Button managerClientButton = new Button("Today's information");
                    Button managerStatisticsButton = new Button("Today's total money");
                    Button addClients = new Button("Add Users");
                    Button managerBackButton1 = new Button("Logout");
                    VBox managerButtons = new VBox(managerClientButton, managerStatisticsButton, addClients, managerBackButton1);
                    managerButtons.setSpacing(10);
                    managerButtons.setPadding(new Insets(10, 10, 10, 10));
                    HBox managerHbox = new HBox(managerImageView, managerInfo);
                    manager2.setTop(managerHbox);
                    manager2.setCenter(managerButtons);
                    Scene managerScene2 = new Scene(manager2, 500, 500);

                    //statistics scene
                    Text totalMoneyEarnedTitle = new Text("Total Money earned today : \n");
                    Text totalMoneyEarned = new Text();
                    Button managerBackButton2 = new Button("back");
                    totalMoneyEarned.setFont(subtitleFont);
                    BorderPane statisticsManagerBorderPane = new BorderPane();
                    statisticsManagerBorderPane.setTop(totalMoneyEarnedTitle);
                    statisticsManagerBorderPane.setCenter(totalMoneyEarned);
                    statisticsManagerBorderPane.setRight(managerBackButton2);
                    Scene statisticsScene = new Scene(statisticsManagerBorderPane, 500, 500);

                    Text managerWelcome = new Text("Welcome!");
                    managerWelcome.setFont(new Font("Arial", 35));

                    Text signUpNameTextManager = new Text("Enter name: ");
                    Text signUpUsernameTextManager = new Text("Enter username: ");
                    Text signUpPasswordTextManager = new Text("Enter password: ");

                    signUpNameManager = new TextField();
                    signUpUserNameManager = new TextField();
                    signUpPasswordManager = new PasswordField();

                    signUpNameLabelManager = new Label();
                    signUpUsernameLabelManager = new Label();
                    signUpPasswordLabelManager = new Label();
                    signUpRadioButtonsLabelManager = new Label();
                    Label showPasswordManager = new Label("");

                    signUpRadioButtonsManager = new RadioButton[5];
                    signUpRadioButtonsManager[0] = new RadioButton("Client");
                    signUpRadioButtonsManager[1] = new RadioButton("Premium Client");
                    signUpRadioButtonsManager[2] = new RadioButton("Manager");
                    signUpRadioButtonsManager[3] = new RadioButton("Waiter");
                    signUpRadioButtonsManager[4] = new RadioButton("Cook");

                    signUpradioButtonsToggleManager = new ToggleGroup();
                    signUpRadioButtonsManager[0].setToggleGroup(signUpradioButtonsToggleManager);
                    signUpRadioButtonsManager[1].setToggleGroup(signUpradioButtonsToggleManager);
                    signUpRadioButtonsManager[2].setToggleGroup(signUpradioButtonsToggleManager);
                    signUpRadioButtonsManager[3].setToggleGroup(signUpradioButtonsToggleManager);
                    signUpRadioButtonsManager[4].setToggleGroup(signUpradioButtonsToggleManager);

                    Button finishAddingSignUpManager = new Button("Add ");
                    showPasswordCheckBoxManager = new CheckBox("Show Password");

                    showPasswordCheckBoxManager.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            showPasswordManager.setText(newValue ? signUpPasswordManager.getText() : " ");
                        }
                    });

                    HBox hBoxSignUpRadioButtonsManager = new HBox(signUpRadioButtonsManager);
                    VBox vBoxSignUpManager = new VBox(signUpNameTextManager, signUpNameManager, signUpNameLabelManager, signUpUsernameTextManager, signUpUserNameManager, signUpUsernameLabelManager, signUpPasswordTextManager, signUpPasswordManager, showPasswordManager, signUpPasswordLabelManager, hBoxSignUpRadioButtonsManager, signUpRadioButtonsLabelManager, showPasswordCheckBoxManager, finishAddingSignUpManager);
                    vBoxSignUpManager.setSpacing(10);
                    vBoxSignUpManager.setPadding(new Insets(10, 10, 10, 10));

                    BorderPane signUpBorderpaneManager = new BorderPane();
                    signUpBorderpaneManager.setTop(welcome);
                    signUpBorderpaneManager.setCenter(vBoxSignUpManager);

                    Button backButtonSignUpManager = new Button("Back");
                    signUpBorderpaneManager.setRight(backButtonSignUpManager);

                    Scene signUpSceneManager = new Scene(signUpBorderpaneManager, 600, 500);

                    //waiterScene2
                    BorderPane waiter2 = new BorderPane();
                    Text waiterInfo = new Text();
                    waiterInfo.setFont(subtitleFont);
                    Button waiterButton = new Button("Today's customers");
                    Button backButtonWaiter = new Button("Logout");
                    waiter2.setRight(backButtonWaiter);
                    waiter2.setCenter(waiterInfo);
                    waiter2.setBottom(waiterButton);
                    waiter2.setMargin(waiterInfo, new Insets(10, 10, 10, 10));
                    Scene waiterScene2 = new Scene(waiter2,700,300);

                    //cookScene2
                    BorderPane cook2 = new BorderPane();
                    Text cookInfo = new Text();
                    cookInfo.setFont(subtitleFont);
                    Button cookButton = new Button("Dishes here");
                    Button backButtonCook = new Button("Logout");
                    cook2.setCenter(cookInfo);
                    cook2.setBottom(cookButton);
                    cook2.setRight(backButtonCook);
                    Scene cookScene2 = new Scene(cook2,700,300);

                    //button actions
                    loginButton.setOnAction(e -> {

                        myUsersList.clear();
                        myDishesList.clear();
                        myTablesList.clear();
                        myAppetizersList.clear();
                        myMaincourseList.clear();
                        myDessertsList.clear();
                        myyRestaurant.getClientsReservedTable().clear();

                        try {
                            XML.readFromXML(myUsersList, myTablesList, myDishesList, myAppetizersList, myMaincourseList, myDessertsList, myyRestaurant);
                        } catch (JAXBException ex) {
                            ex.printStackTrace();
                        }

                        String usernameCompare = loginUsernameField.getText();
                        String passwordCompare = loginPasswordField.getText();
                        ClearLoginScene.clearLogin(loginUsernameField, loginPasswordField, showLoginPasswordLabel, showloginPasswordCheckBox);

                        for (int i = 0; i < myUsersList.size(); i++) {
                            if (usernameCompare.equals(myUsersList.get(i).getUsername()) && passwordCompare.equals(myUsersList.get(i).getPassword())) {
                                currentUser = myUsersList.get(i);
                                break;
                            }
                        }
                         if (currentUser == null) {
                            Text errorText = new Text("INVALID!");
                            errorText.setFont(new Font("verdana", 50));
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("NOT A SYSTEM USER!");
                            alert.show();
                            gridpane.add(errorText, 2, 6);
                            errorText.setText("");
                        } else {
                            if (currentUser instanceof Client) {
                                clientProfileInfo.setText("Welcome " + currentUser.getName() + "\nPlease click to reserve");
                                for (Client client : myyRestaurant.getClientsReservedTable().keySet()) {
                                    if (client.getName().equals(currentUser.getName())) {
                                        currentUser = client;
                                        String first = "Your ordered dishes are: \n";
                                        String second = "";
                                        for (MyDish dish : client.getChosenDishes().keySet()) {
                                            second += dish.getName();
                                            second += "\nQuantity: ";
                                            second += client.getChosenDishes().get(dish);
                                            second += "\n";
                                        }
                                        String third = "\nMoney paid = " + ((Client) currentUser).getMoneyPaid() + "L.E";
                                        reserveInfo.setText(first + second + third);
                                        reserveInfo.setFont(new Font("verdana", 20));
                                        break;
                                    }
                                }
                                primaryStage.setScene(customerScene2);
                            } else if (currentUser instanceof Manager) {
                                managerInfo.setText("Welcome " + currentUser.getName() + "\nPlease click to choose");
                                primaryStage.setScene(managerScene2);
                            } else if (currentUser instanceof Waiter) {
                                waiterInfo.setText("Welcome " + currentUser.getName() + "\nPlease click to see today's information");
                                primaryStage.setScene(waiterScene2);
                            } else if (currentUser instanceof Cook) {
                                cookInfo.setText("Welcome " + currentUser.getName() + "\nPlease click to see today's dishes");
                                primaryStage.setScene(cookScene2);
                            }
                        }
                    });

                    signUpButton.setOnAction(e -> {

                        try {
                            XML.readFromXML(myUsersList, myTablesList, myDishesList, myAppetizersList, myMaincourseList, myDessertsList, myyRestaurant);
                        } catch (JAXBException ex) {
                            ex.printStackTrace();
                        }
                        primaryStage.setScene(signUpScene);

                    });
                    finishSignUp.setOnAction(e -> {
                        if (!Validations.signUpValidation( signUpName,  signUpUserName,  signUpPassword,  signUpNameLabel,  signUpPasswordLabel,  signUpUsernameLabel, signUpradioButtonsToggle,signUpRadioButtonsLabel,myUsersList))
                            primaryStage.setScene(signUpScene);
                        else {
                            SignUp.signUp( signUpName,  signUpUserName,  signUpPassword, signUpRadioButtons,  myUsersList, myTablesList,  myDishesList, myAppetizersList, myMaincourseList, myDessertsList,  myyRestaurant);
                            ClearSignUpScene.clearSignUp(signUpName, signUpUserName, signUpPassword, signUpNameLabel, signUpPasswordLabel, signUpUsernameLabel, showPasswordCheckBox, signUpradioButtonsToggle);
                            primaryStage.setScene(loginScene);
                        }
                    });
                    profileButton.setOnAction(action -> {
                        primaryStage.setScene(customerScene2);
                    });
                    reservationButton.setOnAction(action -> {
                        primaryStage.setScene(customerScene3);
                    });

                    menuButton.setOnAction(e -> {
                        primaryStage.setScene(menuScene);
                    });

                    reserveButton.setOnAction(event -> {

                        int numberOfSeats;
                        String numberOfSeatsString = seatsTextField.getText();
                        numberOfSeats = Integer.parseInt(numberOfSeatsString);
                        MyTable chosenTable = new MyTable();
                        MyTable reservedTable = null;

                        if (smokingButtons.getSelectedToggle().equals(null) || numberOfSeats == 0 || numberOfSeatsString.trim().equals("")) {
                            if (smokingButtons.getSelectedToggle().equals(null)) {
                                Alert unselectedSmoking = new Alert(Alert.AlertType.ERROR);
                                typeOfTableMissingLabel.setText("Type of table required!");
                                typeOfTableMissingLabel.setTextFill(Color.RED);
                                unselectedSmoking.setTitle("NOT CHOSEN TABLE TYPE");
                                unselectedSmoking.setContentText("Must choose type of table!\n (smoking or not)");
                                unselectedSmoking.show();
                            }

                            if (numberOfSeatsString.equals("")) {
                                tableSeatsMissingLabel.setText("Number of seats required!");
                                tableSeatsMissingLabel.setTextFill(Color.RED);
                            }
                            if (numberOfSeats == 0) {
                                tableSeatsMissingLabel.setText("Invalid seat number!");
                                tableSeatsMissingLabel.setTextFill(Color.RED);
                                smokingButtons.getSelectedToggle().setSelected(false);
                                Alert invalidSeatNumber = new Alert(Alert.AlertType.ERROR);
                                invalidSeatNumber.setTitle("INVALID SEAT NUMBER");
                                invalidSeatNumber.setContentText("INVALID SEAT NUMBER");
                                invalidSeatNumber.show();
                            }
                            seatsTextField.clear();
                            primaryStage.setScene(customerScene3);
                        } else {

                            if (smokingButtons.getSelectedToggle() == smokingButton) {
                                chosenTable.setSmoking(true);
                            } else {
                                chosenTable.setSmoking(false);
                            }
                            numberOfSeats = Integer.parseInt(seatsTextField.getText());
                            chosenTable.setNumberOfSeats(numberOfSeats);
                            reservedTable = chosenTable.isAvailable(myTablesList);
                        }

                        if (reservedTable != null) {
                            myyRestaurant.getClientsReservedTable().put((Client) currentUser, reservedTable);
                            primaryStage.setScene(menuScene);
                        } else {

                            ButtonType okButton = new ButtonType("Reserve another table", ButtonBar.ButtonData.YES);
                            ButtonType cancelButton = new ButtonType("Cancel Reservation", ButtonBar.ButtonData.CANCEL_CLOSE);
                            Alert choicesConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                            choicesConfirmation.setTitle("RESERVATION CONFIRMATION");
                            choicesConfirmation.setContentText("Click OK to reserve another table\nClick cancel to cancel reservation");
                            choicesConfirmation.getButtonTypes().setAll(okButton, cancelButton);
                            Optional<ButtonType> result = choicesConfirmation.showAndWait();
                            if (result.get() == okButton) {
                                smokingButtons.getSelectedToggle().setSelected(false);
                                seatsTextField.clear();
                                primaryStage.setScene(customerScene3);
                            } else if (result.get() == cancelButton) {
                                loginUsernameField.clear();
                                loginPasswordField.clear();
                                primaryStage.setScene(loginScene);
                            }
                        }
                        smokingButtons.getSelectedToggle().setSelected(false);
                        seatsTextField.clear();
                    });

                  addAllDishes.setOnAction(actionEvent ->
                  {
                      selectedAppetizerIndices = checkboxAppetizer.getCheckModel().getCheckedIndices();
                      selectedMaincourseIndices = checkboxMaincourse.getCheckModel().getCheckedIndices();
                      selectedDessertIndices = checkboxDessert.getCheckModel().getCheckedIndices();

                      for (int i = 0; i < myAppetizersList.size(); i++) {
                          try {
                              if (selectedAppetizerIndices.contains(i)) {
                                  int quantity = Integer.parseInt(quantityAppetizersText[i].getText());
                                  if (quantity == 0) {
                                        Alert negativeQuantity = new Alert(Alert.AlertType.ERROR);
                                        negativeQuantity.setContentText("NO QUANTITY ENTERED!");
                                        negativeQuantity.show();
                                        quantityAppetizersText[i].setText("0");
                                        primaryStage.setScene(menuScene);
                                    } else {
                                        ((Client) currentUser).buy(myAppetizersList.get(i), quantity);
                                    }
                                }
                            } catch (NullPointerException e1) {
                                System.out.println("NULL POINTER EXCEPTION");
                            }
                        }
                        for (int i = 0; i < myMaincourseList.size(); i++) {
                            try {
                                if (selectedMaincourseIndices.contains(i)) {
                                    int quantity = Integer.parseInt(quantityMaincourseText[i].getText());
                                    if (quantity == 0) {
                                        Alert negativeQuantity = new Alert(Alert.AlertType.ERROR);
                                        negativeQuantity.setContentText("NO QUANTITY ENTERED!");
                                        negativeQuantity.show();
                                        quantityMaincourseText[i].setText("0");
                                        primaryStage.setScene(menuScene);
                                    } else {
                                        ((Client) currentUser).buy(myMaincourseList.get(i), quantity);
                                    }
                                }
                            } catch (NullPointerException e1) {
                                System.out.println("NULL POINTER EXCEPTION");
                            }
                        }

                        for (int i = 0; i < myDessertsList.size(); i++) {
                            try {
                                if (selectedDessertIndices.contains(i)) {
                                    int quantity = Integer.parseInt(quantityDessertsText[i].getText());
                                    if (quantity == 0) {
                                        Alert negativeQuantity = new Alert(Alert.AlertType.ERROR);
                                        negativeQuantity.setContentText("NO QUANTITY ENTERED!");
                                        negativeQuantity.show();
                                        quantityDessertsText[i].setText("0");
                                        primaryStage.setScene(menuScene);
                                    } else {
                                        ((Client) currentUser).buy(myDessertsList.get(i), quantity);
                                    }
                                }
                            } catch (NullPointerException e1) {
                                System.out.println("NULL POINTER EXCEPTION");
                            }
                        }
                        clearMenu();
                    });

               finishCardNumberEntry.setOnAction(event -> {
                    ((Client) currentUser).setPaymentMethod(new Visa(Integer.parseInt(visaCardNumber.getText())));
                    visaCardNumber.clear();
               });

                    checkoutButton.setOnAction(e -> {
                        ((Client) currentUser).checkout();
                        String clientDishes = "";
                        List<String> clientDishesNames = new ArrayList<>();
                        int i = 0;
                        for (MyDish clientDish : ((Client) currentUser).getChosenDishes().keySet()) {
                            if (i == 0) clientDishes += "Your ordered dishes are: ";
                            clientDishes += clientDish.getName();
                            clientDishes += "   X";
                            clientDishes += ((Client) currentUser).getChosenDishes().get(clientDish);
                            clientDishes += "\n";
                            clientDishesNames.add(clientDishes);
                            i++;
                        }

                        totalMoney.setText(clientDishes + Double.toString(((Client) currentUser).getMoneyPaid()));
                        primaryStage.setScene(checkoutScene);
                    });

                    managerClientButton.setOnAction(e -> {
                        primaryStage.setScene(clientManagerScene.clientManagerScene(myyRestaurant.getClientsReservedTable(), backButtonManager4));
                    });


                    managerStatisticsButton.setOnAction(e -> {
                        totalMoneyEarned.setText(Double.toString(myyRestaurant.getTotalMoneyEarned()) + "LE");
                        primaryStage.setScene(statisticsScene);

                    });

                    addClients.setOnAction(e -> {
                        primaryStage.setScene(signUpSceneManager);
                    });


                    finishAddingSignUpManager.setOnAction(e -> {
                        if (!Validations.signUpValidationManager( signUpNameManager,  signUpUserNameManager,  signUpPasswordManager,  signUpNameLabelManager,  signUpPasswordLabelManager,  signUpUsernameLabelManager,  signUpradioButtonsToggleManager,  signUpRadioButtonsLabelManager, myUsersList))
                            primaryStage.setScene(signUpSceneManager);
                        else {
                            SignUp.signUpManager( signUpNameManager,  signUpUserNameManager,  signUpPasswordManager, signUpRadioButtonsManager, myUsersList,myTablesList, myDishesList, myAppetizersList, myMaincourseList, myDessertsList, myyRestaurant);
                            ClearSignUpManagerScene.clearSignUpManager(signUpNameManager, signUpUserNameManager, signUpPasswordManager, signUpNameLabelManager, signUpPasswordLabelManager, signUpUsernameLabelManager, showPasswordCheckBoxManager, signUpradioButtonsToggleManager);
                            primaryStage.setScene(managerScene2);
                        }
                    });

                    doneButton.setOnAction(e -> {

                        try {
                            XML.saveToXML(myUsersList, myTablesList, myDishesList, myAppetizersList, myMaincourseList, myDessertsList, myyRestaurant);
                        } catch (JAXBException ex) {
                            ex.printStackTrace();
                        }

                        primaryStage.setScene(loginScene);

                    });
                    cookButton.setOnAction(e -> {
                        primaryStage.setScene(clientCookScene.clientCookScene(myyRestaurant.getClientsReservedTable(), backButtonCook2));
                    });
                    waiterButton.setOnAction(e -> {
                        primaryStage.setScene(clientWaiterScene.clientWaiterScene(myyRestaurant.getClientsReservedTable(), backButtonWaiter2));

                    });
                    //back Buttons
                    backButtonCook.setOnAction(action -> {
                        primaryStage.setScene(loginScene);
                    });

                    backButtonWaiter.setOnAction(action -> {
                        ClearLoginScene.clearLogin(loginUsernameField, loginPasswordField, showLoginPasswordLabel, showPasswordCheckBox);
                        primaryStage.setScene(loginScene);
                    });

                    backButtonCook2.setOnAction(action -> {
                        primaryStage.setScene(cookScene2);
                    });
                    backButtonWaiter2.setOnAction(action -> {
                        primaryStage.setScene(waiterScene2);
                    });

                    managerBackButton1.setOnAction(action -> {
                        ClearLoginScene.clearLogin(loginUsernameField, loginPasswordField, showLoginPasswordLabel, showPasswordCheckBox);
                        primaryStage.setScene(loginScene);
                    });

                    managerBackButton2.setOnAction(action -> {
                        primaryStage.setScene(managerScene2);
                    });

                    backButtonManager4.setOnAction(action -> {
                        primaryStage.setScene(managerScene2);
                    });

                    customerBackButton1.setOnAction(action -> {
                        ClearLoginScene.clearLogin(loginUsernameField, loginPasswordField, showLoginPasswordLabel, showPasswordCheckBox);
                        primaryStage.setScene(loginScene);
                    });
                    customerBackButton2.setOnAction(action -> {
                        primaryStage.setScene(customerScene2);
                    });
                    customerBackButton3.setOnAction(action -> {
                        primaryStage.setScene(customerScene3);
                    });
                    backButtonCheckout.setOnAction(action -> {
                        primaryStage.setScene(menuScene);
                    });
                    backButtonSignUp.setOnAction(action -> {
                        ClearSignUpScene.clearSignUp(signUpName, signUpUserName, signUpPassword, signUpNameLabel, signUpPasswordLabel, signUpUsernameLabel, showPasswordCheckBox, signUpradioButtonsToggle);
                        primaryStage.setScene(loginScene);
                    });

                    backButtonSignUpManager.setOnAction(e -> {
                        //clearSignUpManager();
                        primaryStage.setScene(managerScene2);
                    });

                    primaryStage.setTitle("RESTAURANT");
                    primaryStage.setScene(loginScene);
                    primaryStage.show();
                }
                private void clearMenu () {

                    for (int i = 0; i < myAppetizersList.size(); i++) {
                        if (selectedAppetizerIndices.contains(i)) {
                            checkboxAppetizer.getCheckModel().clearCheck(i);
                            quantityAppetizersText[i].setText("0");
                        }
                    }
                    for (int i = 0; i < myMaincourseList.size(); i++) {
                        if (selectedMaincourseIndices.contains(i)) {
                            checkboxMaincourse.getCheckModel().clearCheck(i);
                            quantityMaincourseText[i].setText("0");
                        }
                    }
                    for (int i = 0; i < myDessertsList.size(); i++) {
                        if (selectedDessertIndices.contains(i)) {
                            checkboxDessert.getCheckModel().clearCheck(i);
                            quantityDessertsText[i].setText("0");
                        }
                    }
                }

                public static void main (String[]args){
                    launch(args);
                }
}
