package myRestaurant.payment;

public class Visa implements PaymentMethod{

    private int cardNumber;

    public Visa(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        // Connect to bank
        //try{
        //}catch
        // check card validity
        // check enough balance
        // pay
    }

    @Override
    public double getBalance() {
        // Connect to bank
        // check card validity
        // check enough balance
        // return balance
        return 0;
    }
}
