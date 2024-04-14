package GUI.clientDetailsScenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import myRestaurant.myDishes.MyDish;
import myRestaurant.mySystemUsers.Client;
import myRestaurant.myTables.MyTable;
import java.util.HashMap;

public class clientManagerScene {

    public static Scene clientManagerScene(HashMap<Client, MyTable> clientMyTableHashMap, Button backButtonManager4) {

        Label[] titleClient = new Label[clientMyTableHashMap.size()];
        String dishInformation = "";
        Label [] dishesInformation = new Label[clientMyTableHashMap.size()];
        Label [] tableInformation = new Label[clientMyTableHashMap.size()];
        Label [] moneyInformation = new Label[clientMyTableHashMap.size()];

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
            moneyInformation[i] = new Label("Money paid by customer = " + myClient.getMoneyPaid() + "LE");

            info.getChildren().addAll(titleClient[i]);
            info.getChildren().addAll(dishesInformation[i]);
            info.getChildren().addAll(tableInformation[i]);
            info.getChildren().addAll(moneyInformation[i]);

            i++;
        }

        info.getChildren().add(backButtonManager4);
        Scene managerScene = new Scene(info,600,600);
        return managerScene;
    }
}
