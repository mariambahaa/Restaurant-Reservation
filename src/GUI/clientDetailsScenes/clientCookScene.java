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

public class clientCookScene {

    public static Scene clientCookScene(HashMap<Client, MyTable> clientMyTableHashMap, Button backButtonCook2) {

        Label[] titleClient = new Label[clientMyTableHashMap.size()];
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
        info.getChildren().add(backButtonCook2);
        Scene cookScene2 = new Scene(info,600,600);
        return cookScene2;
    }
}
