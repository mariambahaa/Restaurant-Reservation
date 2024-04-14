package input;

import input.dishes.Dishes;
import input.table.Table;
import input.table.Tables;
import input.user.User;
import input.user.Users;
import myRestaurant.mySystemUsers.Client;
import myRestaurant.myTables.MyTable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlRootElement (name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {

    @XmlElement( name = "users")
    private Users users = null;

    @XmlElement(name = "tables")
    private Tables tables = null;

    @XmlElement(name = "dishes")
    private Dishes dishes;

    @XmlElement (name = "clientsReservedTable")
    private HashMap<User, Table> clientsReservedTable = new HashMap<User, Table>();

    public double getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned(double totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }

    @XmlElement(name = "totalMoneyEarned")
    private double totalMoneyEarned;

    public HashMap<User, Table> getClientsReservedTable() {
        return clientsReservedTable;
    }

    public void setClientsReservedTable(HashMap<User, Table> clientsReservedTable) {
        this.clientsReservedTable = clientsReservedTable;
    }


    /*private double totalMoneyEarned = 0;
    public double getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned(double totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }*/


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

}

