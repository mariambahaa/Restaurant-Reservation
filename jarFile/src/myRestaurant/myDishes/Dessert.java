package myRestaurant.myDishes;

public class Dessert extends MyDish {

    public Dessert(String name, double price) {
        super(name, price);
        setTaxes(0.2);
    }
}
