package myRestaurant;

import myRestaurant.myDishes.MyDishes;
import myRestaurant.mySystemUsers.Client;
import myRestaurant.mySystemUsers.MyUsers;
import myRestaurant.myTables.MyTable;
import myRestaurant.myTables.MyTables;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.Hashtable;

@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyRestaurant {

    @XmlElement(name = "users")
    private MyUsers myUsers = null;

    @XmlElement(name = "tables")
    private MyTables myTables = null;

    @XmlElement(name = "dishes")
    private MyDishes myDishes = null;

    @XmlElement(name = "totalMoneyEarned")
    private double totalMoneyEarned;

    @XmlElement (name = "clientsReservedTable")
    private HashMap<Client, MyTable> clientsReservedTable = new HashMap<Client, MyTable>();

    public HashMap<Client, MyTable> getClientsReservedTable() {
        return clientsReservedTable;
    }

    public void setClientsReservedTable(HashMap<Client, MyTable> clientsReservedMyTable) {
        this.clientsReservedTable = clientsReservedMyTable;
    }

    public double getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned(double totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }


    public MyUsers getMyUsers () {
            return myUsers;
        }

        public void setMyUsers (MyUsers myUsers){
            this.myUsers = myUsers;
        }

        public MyTables getMyTables () {
            return myTables;
        }

        public void setMyTables (MyTables myTables){
            this.myTables = myTables;
        }

        public MyDishes getMyDishes () {
            return myDishes;
        }

        public void setMyDishes (MyDishes myDishes){
            this.myDishes = myDishes;
        }
    }
