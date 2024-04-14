package myRestaurant.myDishes;

import input.dishes.Dish;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "dishes")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyDishes {

    @XmlElement(name = "dish")
    private List<MyDish> myDishes = new ArrayList<>();

    public List<MyDish> getDishes() {
        return myDishes;
    }

    public void setDishes(List<MyDish> dishes) {
        this.myDishes = dishes;
    }


}