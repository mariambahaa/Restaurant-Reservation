package GUI;

import com.jfoenix.controls.JFXButton;
import input.dishes.Dish;
import input.Restaurant;
import input.table.Table;
import input.user.User;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import myRestaurant.MyRestaurant;
import myRestaurant.myDishes.*;
import myRestaurant.myTables.MyTables;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import myRestaurant.myTables.MyTable;
import myRestaurant.mySystemUsers.*;
import sun.security.krb5.internal.crypto.Des;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Integer.parseInt;


public class Main extends Application {

    List<MyUser> myUsersList = new ArrayList<>();
    List<MyDish> myDishesList = new ArrayList<>();
    List<Appetizer> myAppetizersList = new ArrayList<Appetizer>();
    List<MyDish> myMaincourseList = new ArrayList<>();
    List<MyDish> myDessertsList = new ArrayList<>();
    List<MyTable> myTablesList = new ArrayList<>();

    MyUser currentUser = null;

    HashMap<Client, MyTable> clientMyTableHashMap = new HashMap<Client, MyTable>();
    MyRestaurant myyRestaurant = new MyRestaurant();

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


    TextField newDishName;
    TextField newDishPrice;
    Label newDishNameLabel;
    Label newDishPriceLabel;
    RadioButton [] newDishRadioButtons;
    ToggleGroup newDishRadioButtonsToggle;

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
        TextField loginUsernameField = new TextField();
        loginUsernameField.setPromptText("username");
        PasswordField loginPasswordField = new PasswordField();
        loginPasswordField.setPromptText("password");
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        Label showLoginPasswordLabel = new Label("");
        showLoginPasswordLabel.setTextFill(Color.WHITE);
        CheckBox showloginPasswordCheckBox = new CheckBox("Show Password");
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


        //signUp scene
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
        VBox vBoxSignUp = new VBox(signUpNameText, signUpName, signUpNameLabel, signUpUsernameText, signUpUserName,signUpUsernameLabel, signUpPasswordText, signUpPassword,showPassword, signUpPasswordLabel, hBoxSignUpRadioButtons,signUpRadioButtonsLabel, showPasswordCheckBox, finishSignUp);
        vBoxSignUp.setSpacing(10);
        vBoxSignUp.setPadding(new Insets(10,10,10,10));

        BorderPane signUpBorderpane = new BorderPane();
        signUpBorderpane.setTop(welcome);
        signUpBorderpane.setCenter(vBoxSignUp);

        Scene signUpScene = new Scene(signUpBorderpane, 600,500);



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
        Scene customerScene2 = new Scene(customer2, 600, 400);


        //customerScene3
        Text tableNumberLabel = new Text();
        Label tableNumberMissingLabel = new Label();
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
            image = new Image(new FileInputStream("src\\GUI\\images\\seats.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView seatImageView = new ImageView(image);
        seatImageView.setFitHeight(5);
        seatImageView.setFitWidth(5);

        Text seatsText = new Text("Enter number of seats you need");
        TextField seatsTextField = new TextField();
        seatsTextField.setPromptText("number of seats");

        Text finishReservationText = new Text("Finished reservation? Click here ");
        finishReservationText.setFont(new Font("Arial", 15));
        Button reserveButton = new Button("Reserve");
        VBox reservationVBox = new VBox(tableTypeText, smokingText, smokingButton, noSmokingButton, seatsText, seatsTextField);
        reservationVBox.setSpacing(20);
        reservationVBox.setPadding(new Insets(50,10,50,10));
        HBox reserveHBox = new HBox(finishReservationText, reserveButton);
        VBox wholeReservationVBox = new VBox(reservationVBox);
        wholeReservationVBox.setMinWidth(150);
        wholeReservationVBox.setSpacing(20);
        wholeReservationVBox.setPadding(new Insets(50,10,50,10));
        customer3.setLeft(wholeReservationVBox);
        customer3.setBottom(reserveHBox);
        customer3.setTop(tableNumberLabel);
        Scene customerScene3 = new Scene(customer3, 300, 300);



        //menuuuuuuuuuuu
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
            vBoxQuantityAppetizer.getChildren().add(textField);
        }

        for (int i=0; i<checkboxMaincourseName.size(); i++) {

            TextField textField = new TextField();
            textField.setText("0");
            quantityMaincourseText[i] = textField;
            vBoxQuantityMaincourse.getChildren().add(textField);
        }

        for (int i=0; i<checkboxDessertName.size(); i++) {
            quantityDessertsText[i] = new TextField();
            quantityDessertsText[i].setText("0");
            vBoxQuantityDessert.getChildren().add(quantityDessertsText[i]);
        }

        appetizersPriceList = new ListView<>();
        maincoursePriceList = new ListView<>();
        dessertsPriceList = new ListView<>();

        //final String[] priceAppetizer=new String[]{"35","30"};
        //final String[] priceMaincourse=new String[]{"75","60","80"};
        //final String[] priceDessert=new String[]{"50","60"};

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

        addAllDishes.setText("Click here to add all your dishes");
        addAllDishes.setMinWidth(60);
        checkoutButton.setText("Checkout");

        gridpaneMenu.add(menuTitle,4,0);
        gridpaneMenu.add(appetizerTitle, 0, 1);
        gridpaneMenu.add(maincourseTitle, 3, 1);
        gridpaneMenu.add(dessertTitle, 6, 1);
        gridpaneMenu.add(taxesText, 8,3);
        gridpaneMenu.add(addAllDishes, 8,4);
        gridpaneMenu.add(checkoutButton, 8,5);
        gridpaneMenu.add(greekSaladImageView, 1,4);
        gridpaneMenu.add(grilledChickenImageView, 4,4);
        gridpaneMenu.add(moltenCakeImageView, 7,4);


        Scene menuScene=new Scene(gridpaneMenu,1000,600);


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
        VBox vBoxCentreCheckout = new VBox( totalMoney, paymentText, cash, visa);
        vBoxCentreCheckout.setSpacing(10);
        vBoxCentreCheckout.setPadding(new Insets(20,10,10,10));

        //submit button
        Button doneButton=new Button();
        doneButton.setText("Done");
        borderpaneCheckout.setBottom(doneButton);
        borderpaneCheckout.setCenter(vBoxCentreCheckout);

        Scene checkoutScene = new Scene(borderpaneCheckout, 600, 600);



        //managerScene2
        BorderPane manager2 = new BorderPane();
        Text managerInfo = new Text();
        managerInfo.setFont(new Font("Arial",30));
        //Image managerImage = new Image(new FileInputStream("manager.png"));
       // ImageView managerImageView = new ImageView(managerImage);
       // managerImageView.setFitWidth(100);
       // managerImageView.setFitHeight(100);
        Button managerClientButton = new Button("Today's information");
        Button managerStatisticsButton = new Button("Today's statistics");
        Button addClients = new Button("Add Client");
        Button addDishes = new Button("Add Dish");
        Button managerBackButton1=new Button("logout");
        VBox managerButtons = new VBox(managerClientButton, managerStatisticsButton,addClients,addDishes,managerBackButton1);
        managerButtons.setSpacing(10);
        managerButtons.setPadding(new Insets(10,10,10,10));
       // HBox managerHbox = new HBox(managerImageView, managerInfo);
       //manager2.setTop(managerHbox);
        manager2.setCenter(managerButtons);
        Scene managerScene2 = new Scene(manager2,500,500);

        //statistics scene
        Text totalMoneyEarnedTitle = new Text("Total Money earned today : \n");
        Text totalMoneyEarned = new Text();
        totalMoneyEarned.setFont(subtitleFont);
        BorderPane statisticsManagerBorderPane = new BorderPane();
        statisticsManagerBorderPane.setTop(totalMoneyEarnedTitle);
        statisticsManagerBorderPane.setCenter(totalMoneyEarned);
        Scene statisticsScene = new Scene(statisticsManagerBorderPane, 500,500);


        //addDishes scene
        Text newDishTitle = new Text("Today's new dish!");
        newDishTitle.setFont(new Font("Arial", 35));

        Text newDishNameText = new Text("Enter name: ");
        Text newDishPriceText = new Text("Enter price: ");
        Text newDishTypeText = new Text("Choose type of dish: ");

        newDishName = new TextField();
        newDishPrice = new TextField();

        newDishNameLabel = new Label();
        newDishPriceLabel = new Label();
        signUpRadioButtonsLabel = new Label();

        newDishRadioButtons = new RadioButton[3];
        newDishRadioButtons[0] = new RadioButton("Appetizer");
        newDishRadioButtons[1] = new RadioButton("Maincourse");
        newDishRadioButtons[2] = new RadioButton("Dessert");


        newDishRadioButtonsToggle = new ToggleGroup();
        newDishRadioButtons[0].setToggleGroup(newDishRadioButtonsToggle);
        newDishRadioButtons[1].setToggleGroup(newDishRadioButtonsToggle);
        newDishRadioButtons[2].setToggleGroup(newDishRadioButtonsToggle);

        Button addNewDish = new Button("Done ");

        HBox hBoxDishesTypes = new HBox(newDishRadioButtons);
        VBox vBoxAddDishes = new VBox(newDishNameText, newDishName,newDishNameLabel, newDishPriceText, newDishPrice,newDishPriceLabel, newDishTypeText, hBoxDishesTypes,signUpRadioButtonsLabel,addNewDish);
        vBoxAddDishes.setSpacing(10);
        vBoxAddDishes.setPadding(new Insets(10,10,10,10));

        BorderPane addDishesBorderPane = new BorderPane();
        addDishesBorderPane.setTop(newDishTitle);
        addDishesBorderPane.setCenter(vBoxAddDishes);

        Scene addNewDishScene = new Scene(addDishesBorderPane, 600,500);



        //waiterScene2
        BorderPane waiter2 = new BorderPane();
        Text waiterInfo = new Text();
        waiterInfo.setFont(new Font("verdana", 50));
        Button waiterButton = new Button("Today's customers");
        waiter2.setCenter(waiterInfo);
        waiter2.setBottom(waiterButton);
        waiter2.setMargin(waiterInfo, new Insets(10, 10, 10, 10));
        Scene waiterScene2 = new Scene(waiter2);



        //cookScene2
        BorderPane cook2 = new BorderPane();
        Text cookInfo = new Text();
        Button cookButton = new Button("Dishes here");
        cook2.setCenter(cookInfo);
        cook2.setBottom(cookButton);
        Scene cookScene2 = new Scene(cook2);


        loginButton.setOnAction(e -> {
            try {
                readFromXML();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            String usernameCompare = loginUsernameField.getText();
            String passwordCompare = loginPasswordField.getText();

            loginUsernameField.clear();
            loginPasswordField.clear();

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
            }
            else {
                if (currentUser instanceof Client) {
                    clientProfileInfo.setText("Welcome " + currentUser.getName() + "\nPlease click to reserve");
                    for(Client client : myyRestaurant.getClientsReservedTable().keySet())
                    {
                        if(client.getName().equals(currentUser.getName()))
                        {
                            currentUser = client;
                            String first = "Your ordered dishes are: \n";
                            String second = "";
                            for(MyDish dish :  client.getChosenDishes().keySet()) {
                                second += dish.getName();
                                second += "\nQuantity: ";
                                second += client.getChosenDishes().get(dish);
                                second += "\n";
                            }
                            String third = "\nMoney paid = " + ((Client)currentUser).getMoneyPaid() + "L.E";
                            reserveInfo.setText(first+second+third);
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

        signUpButton.setOnAction(e ->{
            primaryStage.setScene(signUpScene);
        });

        finishSignUp.setOnAction(e -> {
                    try {
                        readFromXML();
                    } catch (JAXBException ex) {
                        ex.printStackTrace();
                    }
                    if(!signUpValidation())
                        primaryStage.setScene(signUpScene);
                    else {
                      signUp();
                    primaryStage.setScene(loginScene);
                    }
        });

        profileButton.setOnAction(action -> {
            primaryStage.setScene(customerScene2);
        });

        reservationButton.setOnAction(action -> {
            primaryStage.setScene(customerScene3);
        });

        menuButton.setOnAction(e ->{
        primaryStage.setScene(menuScene);
        });

        reserveButton.setOnAction(event -> {
            int numberOfSeats;
            numberOfSeats = parseInt(seatsTextField.getText());
            MyTable chosenTable = new MyTable();
            MyTable reservedTable = null;

            if (smokingButtons.getSelectedToggle() == null || numberOfSeats<0) {
                if (smokingButtons.getSelectedToggle() == null) {
                    Alert unselectedSmoking = new Alert(Alert.AlertType.ERROR);
                    unselectedSmoking.setTitle("NOT CHOSEN TABLE TYPE");
                    unselectedSmoking.setContentText("Must choose type of table!\n (smoking or not)");
                    unselectedSmoking.show();
                }

                if (numberOfSeats < 0) {
                    smokingButtons.getSelectedToggle().setSelected(false);
                    Alert negativeSeats = new Alert(Alert.AlertType.ERROR);
                    negativeSeats.setTitle("NEGATIVE SEAT NUMBER");
                    negativeSeats.setContentText("CANT ADD A NEGATIVE NUMBER OF SEATS");
                    negativeSeats.show();
                }
                seatsTextField.clear();
                primaryStage.setScene(customerScene2);
            }
            else {
                if (smokingButtons.getSelectedToggle() == smokingButton) {
                    chosenTable.setSmoking(true);
                } else {
                    chosenTable.setSmoking(false);
                }
                chosenTable.setNumberOfSeats(numberOfSeats);
                 reservedTable = chosenTable.isAvailable(myTablesList);
            }

            if( reservedTable != null)
            {
                myyRestaurant.getClientsReservedTable().put((Client)currentUser, reservedTable);
                primaryStage.setScene(menuScene);

            }
            else {
                ButtonType okButton = new ButtonType("Reserve another table", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Cancel Reservation", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert choicesConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                choicesConfirmation.setTitle("RESERVATION CONFIRMATION");
                choicesConfirmation.setContentText("Click OK to reserve another table\nClick cancel to cancel reservation");
                choicesConfirmation.getButtonTypes().setAll(okButton, cancelButton);
                Optional<ButtonType> result = choicesConfirmation.showAndWait();
                if (result.get() == okButton){
                    primaryStage.setScene(customerScene3);
                }
                else if (result.get() == cancelButton) {
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
                       if (quantity < 0) {
                           Alert negativeQuantity = new Alert(Alert.AlertType.ERROR);
                           negativeQuantity.setContentText("CANT ADD A NEGATIVE QUANTITY");
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
                       if (quantity < 0) {
                           Alert negativeQuantity = new Alert(Alert.AlertType.ERROR);
                           negativeQuantity.setContentText("CANT ADD A NEGATIVE QUANTITY");
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
                       if (quantity < 0) {
                           Alert negativeQuantity = new Alert(Alert.AlertType.ERROR);
                           negativeQuantity.setContentText("CANT ADD A NEGATIVE QUANTITY");
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

       checkoutButton.setOnAction( e -> {

           ((Client)currentUser).checkout();

           String clientDishes = "";
           List<String> clientDishesNames = new ArrayList<>();

           int i = 0;

           for(MyDish clientDish : ((Client)currentUser).getChosenDishes().keySet() )
           {
               if (i == 0) clientDishes += "Your ordered dishes are: ";
               clientDishes += clientDish.getName();
               clientDishes += "   X";
               clientDishes += ((Client)currentUser).getChosenDishes().get(clientDish);
               clientDishes += "\n";
               clientDishesNames.add(clientDishes);
               i++;
           }


           //clientDishes += Double.toString(((Client)currentUser).getMoneyPaid());
           totalMoney.setText(clientDishes + Double.toString(((Client)currentUser).getMoneyPaid()));
           primaryStage.setScene(checkoutScene);

       });

       managerClientButton.setOnAction(e -> {
           primaryStage.setScene(clientManagerScene(myyRestaurant.getClientsReservedTable()));
        });

       managerStatisticsButton.setOnAction( e -> {
           totalMoneyEarned.setText(Double.toString(myyRestaurant.getTotalMoneyEarned()));
           primaryStage.setScene(statisticsScene);
       });

       addClients.setOnAction( e ->{
           primaryStage.setScene(signUpScene);
           signUpValidation();
           signUp();
       });

       doneButton.setOnAction( e-> {

           try {
               saveToXML();
           } catch (JAXBException ex) {
               ex.printStackTrace();
           }

           loginUsernameField.clear();
           loginPasswordField.clear();
           primaryStage.setScene(loginScene);

       });

       cookButton.setOnAction(e -> {
           primaryStage.setScene(clientCookScene(myyRestaurant.getClientsReservedTable()));
       });

       waiterButton.setOnAction( e -> {
           primaryStage.setScene(clientWaiterScene(myyRestaurant.getClientsReservedTable()));
       });

        addDishes.setOnAction( e ->{
            primaryStage.setScene(addNewDishScene);
        });

        primaryStage.setTitle("RESTAURANT");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void readFromXML() throws JAXBException {

        //InputStream inStream = new FileInputStream( "input.xml" );
        //String fileName = "input.xml";
        //File xmlFile = new File(fileName);

        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Restaurant restaurant = (Restaurant) unmarshaller.unmarshal(new File("src\\input.xml"));

        List<User> usersInputList = new ArrayList<>();
        usersInputList = restaurant.getUsers().getUsers();

        //List<MyUser> myUsersList = new ArrayList<>();

        List<Dish> dishesInputList = new ArrayList<>();
        dishesInputList = restaurant.getDishes().getDishes();

        //List<MyDish> myDishesList = new ArrayList<>();

        List<Table> tablesInputList = new ArrayList<>();
        tablesInputList = restaurant.getTables().getTables();

        //List<MyTable> myTablesList = new ArrayList<>();

        HashMap<User, Table> clientsReservedTable = new HashMap<User, Table>();
        clientsReservedTable = restaurant.getClientsReservedTable();


        for (User newUser : usersInputList) {
            if (newUser.getRole().equals("Client")) {
                Client newClient = new Client(newUser.getName(), newUser.getUsername(), newUser.getPassword());
                newClient.setMoneyPaid(newUser.getMoneyPaid());
                myUsersList.add(newClient);
            }
            else if (newUser.getRole().equals("Manager")) {
                myUsersList.add(new Manager(newUser.getName(), newUser.getUsername(), newUser.getPassword()));
            } else if (newUser.getRole().equals("Waiter")) {
                myUsersList.add(new Waiter(newUser.getName(), newUser.getUsername(), newUser.getPassword()));
            } else if (newUser.getRole().equals("Cooker")) {
                myUsersList.add(new Cook(newUser.getName(), newUser.getUsername(), newUser.getPassword()));
            }
        }


        for (Dish newDish : dishesInputList) {
            if (newDish.getType().equals("appetizer")) {
                myDishesList.add(new Appetizer(newDish.getName(), newDish.getPrice()));
                myAppetizersList.add(new Appetizer(newDish.getName(), newDish.getPrice()));
            } else if (newDish.getType().equals("main_course")) {
                myDishesList.add(new Maincourse(newDish.getName(), newDish.getPrice()));
                myMaincourseList.add(new Maincourse(newDish.getName(), newDish.getPrice()));
            } else if (newDish.getType().equals("dessert")) {
                myDishesList.add(new Dessert(newDish.getName(), newDish.getPrice()));
                myDessertsList.add(new Dessert(newDish.getName(), newDish.getPrice()));
            }
        }

        for (Table newTable : tablesInputList) {
            myTablesList.add(new MyTable(newTable.getNumber(), newTable.getNumberOfSeats(), newTable.isSmoking(), newTable.isReserved()));
        }

        for(User newUser : clientsReservedTable.keySet())
        {
            Client newClient = new Client(newUser.getName(), newUser.getUsername(), newUser.getPassword());
            newClient.setMoneyPaid(newUser.getMoneyPaid());
            for(Dish oldDish : newUser.getChosenDishes().keySet())
            {
                if (oldDish.getType().equals("appetizer"))
                    newClient.getChosenDishes().put(new Appetizer(oldDish.getName(), oldDish.getPrice()), newUser.getChosenDishes().get(oldDish));
                   // newClient.getChosenDishes().add(new Appetizer(oldDish.getName(), oldDish.getPrice()));
                else if (oldDish.getType().equals("main_course"))
                    newClient.getChosenDishes().put(new Maincourse(oldDish.getName(), oldDish.getPrice()), newUser.getChosenDishes().get(oldDish));
                else if (oldDish.getType().equals("dessert"))
                    newClient.getChosenDishes().put(new Dessert(oldDish.getName(), oldDish.getPrice()), newUser.getChosenDishes().get(oldDish));
            }
            myyRestaurant.getClientsReservedTable().put(newClient, new MyTable(clientsReservedTable.get(newUser).getNumber(), clientsReservedTable.get(newUser).getNumberOfSeats(), clientsReservedTable.get(newUser).isSmoking(), clientsReservedTable.get(newUser).isReserved()));
        }
        myyRestaurant.setTotalMoneyEarned(restaurant.getTotalMoneyEarned());
    }

    private void saveToXML() throws JAXBException {

        double totalMoneyEarned= 0;
        for(MyUser user : myUsersList)
        {
            if(user instanceof Client)
            {
               totalMoneyEarned +=  ((Client)user).getMoneyPaid();
            }
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(MyRestaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        MyRestaurant myRestaurant = new MyRestaurant();


        MyUsers myUsers = new MyUsers();
        for(MyUser myUser : myUsersList)
        {
            if(myUser instanceof Client)
                myUser.setRole("Client");
            else if (myUser instanceof Manager)
                myUser.setRole("Manager");
            else if (myUser instanceof Waiter)
                myUser.setRole("Waiter");
            else if (myUser instanceof Cook)
                myUser.setRole("Cooker");
        }
        myUsers.setUsers(myUsersList);


        MyTables myTables = new MyTables();
        myTables.setTables(myTablesList);

        MyDishes myDishes = new MyDishes();
        for(MyDish myDish : myDishesList)
        {
            if(myDish instanceof Appetizer)
                myDish.setType("appetizer");
            else if (myDish instanceof Maincourse)
                myDish.setType("main_course");
            else if(myDish instanceof Dessert)
                myDish.setType("dessert");
        }
        myDishes.setDishes(myDishesList);

        HashMap<Client, MyTable> myMap = new HashMap<Client, MyTable>();

        for(Client myClient : myyRestaurant.getClientsReservedTable().keySet())
        {
            //for(MyDish newDish : myClient.getChosenDishes())

            for(MyDish newDish : myClient.getChosenDishes().keySet())
            {
                if (newDish instanceof Appetizer)
                    newDish.setType("appetizer");
                else if (newDish instanceof Maincourse)
                    newDish.setType("main_course");
                else if (newDish instanceof Dessert)
                    newDish.setType("dessert");
            }

            myMap.put(myClient, myyRestaurant.getClientsReservedTable().get(myClient));
        }

        myRestaurant.setMyUsers(myUsers);
        myRestaurant.setMyTables(myTables);
        myRestaurant.setMyDishes(myDishes);
        myRestaurant.setClientsReservedTable(myMap);
        myRestaurant.setTotalMoneyEarned(totalMoneyEarned);

        marshaller.marshal(myRestaurant, new File("src\\input.xml"));

        myyRestaurant.getClientsReservedTable().clear();
        myyRestaurant.setTotalMoneyEarned(0.0);
        myTablesList.clear();
        myUsersList.clear();
        myDishesList.clear();
        myAppetizersList.clear();
        myMaincourseList.clear();
        myDessertsList.clear();
    }

    private boolean signUpValidation() {

        String newUserName = signUpName.getText();
        String newUserUsername = signUpUserName.getText();
        String newUserPassword = signUpPassword.getText();

        String nameError = "Name is required!";
        String usernameError = "Username is required!";
        String passwordError = "Password is required!";
        String roleError = "Role is required!";
        Color errorColor = Color.RED;

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
            }  if (newUserName.isEmpty()) {
                signUpNameLabel.setText(nameError);
                signUpNameLabel.setTextFill(errorColor);
            }  if (newUserUsername.isEmpty()) {
                signUpUsernameLabel.setText(usernameError);
                signUpUsernameLabel.setTextFill(errorColor);
            }  if (newUserPassword.isEmpty()) {
                signUpPasswordLabel.setText(passwordError);
                signUpPasswordLabel.setTextFill(errorColor);
            }  if (signUpradioButtonsToggle.getSelectedToggle() == null) {
                signUpRadioButtonsLabel.setText(roleError);
                signUpRadioButtonsLabel.setTextFill(errorColor);
            }
            return false;
        }
        else return true;
    }
    private void signUp(){

        String newUserName = signUpName.getText();
        String newUserUsername = signUpUserName.getText();
        String newUserPassword = signUpPassword.getText();

        signUpName.clear();
        signUpUserName.clear();
        signUpPassword.clear();

        if(signUpRadioButtons[0].isSelected())
        {
            Client newClient = new Client(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newClient);
        }
        else if(signUpRadioButtons[1].isSelected())
        {
            PremiumClient newPremiumClient = new PremiumClient(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newPremiumClient);
        }
        else if(signUpRadioButtons[2].isSelected())
        {
            Manager newManager = new Manager(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newManager);
        }
        else if(signUpRadioButtons[3].isSelected())
        {
            Waiter newWaiter = new Waiter(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newWaiter);
        }
        else  if(signUpRadioButtons[4].isSelected())
        {
            Cook newCook = new Cook(newUserName, newUserUsername, newUserPassword);
            myUsersList.add(newCook);
        }
        try {
            saveToXML();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }


    private void clearMenu() {

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

    private Scene clientManagerScene(HashMap<Client, MyTable> clientMyTableHashMap) {

        Label [] titleClient = new Label[clientMyTableHashMap.size()];
        String dishInformation = "";
        Label [] dishesInformation = new Label[clientMyTableHashMap.size()];
        Label [] tableInformation = new Label[clientMyTableHashMap.size()];

        VBox info = new VBox();
        info.setSpacing(10);
        info.setPadding(new Insets(10,10,10,10));

        int i = 0;
        for (Client myClient : clientMyTableHashMap.keySet()) {

            dishInformation = "";

            titleClient[i] = new Label("Customer name : " + (i+1) + "\n"+ myClient.getName());
            titleClient[i].setFont(new Font("Arial", 30));

            for(MyDish dish : myClient.getChosenDishes().keySet()){
             dishInformation += ("\n Dish \n" + dish.getName() + " quantity \n" + myClient.getChosenDishes().get(dish));
            }

            tableInformation[i] = new Label("Table number\n " + clientMyTableHashMap.get(myClient).getNumber());

            dishesInformation[i] = new Label(dishInformation);
            info.getChildren().addAll(titleClient[i]);
            info.getChildren().addAll(dishesInformation[i]);
            info.getChildren().addAll(tableInformation[i]);
            i++;
        }

        Scene managerScene = new Scene(info,600,600);
        return managerScene;
    }
    private Scene clientWaiterScene(HashMap<Client, MyTable> clientMyTableHashMap) {

        Label [] titleClient = new Label[clientMyTableHashMap.size()];
        Label [] tableInformation = new Label[clientMyTableHashMap.size()];

        VBox info = new VBox();
        info.setSpacing(10);
        info.setPadding(new Insets(10,10,10,10));

        int i = 0;
        for (Client myClient : clientMyTableHashMap.keySet()) {

            titleClient[i] = new Label("Customer name : " + (i+1) + "\n" + myClient.getName());
            titleClient[i].setFont(new Font("Arial", 30));

            tableInformation[i] = new Label("Table number\n " + clientMyTableHashMap.get(myClient).getNumber());


            info.getChildren().addAll(titleClient[i]);
            info.getChildren().addAll(tableInformation[i]);

            i++;
        }

        Scene waiterScene2 = new Scene(info,600,600);
        return waiterScene2;
    }
    private Scene clientCookScene(HashMap<Client, MyTable> clientMyTableHashMap) {

        Label [] titleClient = new Label[clientMyTableHashMap.size()];
        String dishInformation = "";
        Label [] dishesInformation = new Label[clientMyTableHashMap.size()];
        Label [] tableInformation = new Label[clientMyTableHashMap.size()];

        VBox info = new VBox();
        info.setSpacing(10);
        info.setPadding(new Insets(10,10,10,10));

        int i = 0;
        for (Client myClient : clientMyTableHashMap.keySet()) {

            dishInformation = "";

            titleClient[i] = new Label("Customer name : " + (i+1) + "\n" + myClient.getName());
            titleClient[i].setFont(new Font("Arial", 30));

            for(MyDish dish : myClient.getChosenDishes().keySet()){
                dishInformation += ("\n Dish \n" + dish.getName() + " quantity \n" + myClient.getChosenDishes().get(dish));
            }
            tableInformation[i] = new Label("Table number\n " + clientMyTableHashMap.get(myClient).getNumber());

            dishesInformation[i] = new Label(dishInformation);
            info.getChildren().addAll(dishesInformation[i]);
            info.getChildren().addAll(tableInformation[i]);
            i++;
        }

        Scene cookScene2 = new Scene(info,600,600);
        return cookScene2;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
