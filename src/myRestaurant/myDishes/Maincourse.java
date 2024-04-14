package myRestaurant.myDishes;

public class Maincourse extends MyDish {

    public Maincourse(String name, double price) {
        super(name, price);
        setTaxes(0.15);
    }
}
