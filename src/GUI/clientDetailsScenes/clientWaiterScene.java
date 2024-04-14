package GUI.clientDetailsScenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import myRestaurant.mySystemUsers.Client;
import myRestaurant.myTables.MyTable;

import java.util.HashMap;

public class clientWaiterScene {
    public static Scene clientWaiterScene(HashMap<Client, MyTable> clientMyTableHashMap, Button backButtonWaiter2) {

        Label[] titleClient = new Label[clientMyTableHashMap.size()];
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

        info.getChildren().addAll(backButtonWaiter2);
        Scene waiterScene2 = new Scene(info,600,600);
        return waiterScene2;
    }
}
