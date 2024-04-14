package input.user;

import input.dishes.Dish;
import myRestaurant.myDishes.MyDish;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)

public class User{

    @XmlElement(name = "name")
    private String name;


    @XmlElement(name = "role")
    private String role;


    @XmlElement(name = "username")
    private String username;


    @XmlElement(name = "password")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //just for customer when unmarshalling his chosen dishes and his money paid
    @XmlElement (name = "chosenDishes")
    private HashMap<Dish, Integer> chosenDishes = new HashMap<Dish, Integer>();
    @XmlElement (name ="moneyPaid")
    private double moneyPaid;


    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public HashMap<Dish, Integer> getChosenDishes() {
        return chosenDishes;
    }

    public void setChosenDishes(HashMap<Dish, Integer> chosenDishes) {
        this.chosenDishes = chosenDishes;
    }


    /*public ArrayList<Dish> getChosenDishes() {
        return chosenDishes;
    }

    public void setChosenDishes(ArrayList<Dish> chosenDishes) {
        this.chosenDishes = chosenDishes;
    }*/


}

