package myRestaurant.payment;



public class Cash implements PaymentMethod {

    private double balance;

    public Cash(double balance) {
        this.balance = balance;
    }

    @Override
    public void pay(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Amount Paid Via Cash: " + amount);
        } else {
            System.out.println("Not enough balance");
        }
    }
    public double getBalance()
    {
        return balance;
    }
}
