package myRestaurant.payment;

public interface PaymentMethod {

    void pay(double amount);
    double getBalance();
}
