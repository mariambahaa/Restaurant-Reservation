package myRestaurant.mySystemUsers;

import myRestaurant.myDishes.MyDish;
import myRestaurant.mySystemUsers.Client;

public class PremiumClient extends Client {

    public PremiumClient(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public double checkout() {
        return (super.checkout() - 0.2*super.checkout());
    }
}
