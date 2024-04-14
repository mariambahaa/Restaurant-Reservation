package myRestaurant.myDishes;

public class Appetizer extends MyDish {


    public Appetizer(String name, double price) {
        super(name, price);
        setTaxes(0.1);
    }

}
