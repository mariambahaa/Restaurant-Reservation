package myRestaurant.mySystemUsers;
import myRestaurant.MyRestaurant;
import myRestaurant.myTables.MyTable;
import myRestaurant.myDishes.MyDish;
import myRestaurant.payment.PaymentMethod;
import org.omg.PortableInterceptor.INACTIVE;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;


public class Client extends MyUser {

    //Table table;
    @XmlElement(name = "chosenDishes")
    private HashMap<MyDish, Integer> chosenDishes = new HashMap<MyDish, Integer>();


    @XmlTransient
    private PaymentMethod paymentMethod;

    @XmlElement(name = "moneyPaid")
    private double moneyPaid;

    public Client(String name, String username, String password) {
        super(name, username, password);
    }


    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }


    public HashMap<MyDish, Integer> getChosenDishes() {
        return chosenDishes;
    }

    public void setChosenDishes(HashMap<MyDish, Integer> chosenDishes) {
        this.chosenDishes = chosenDishes;
    }


    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public double checkout() {

        this.moneyPaid = 0;
        for (MyDish dish : chosenDishes.keySet()) {
            this.moneyPaid += dish.getFinalPrice() * chosenDishes.get(dish);
        }
        return this.moneyPaid;
    }


    public void buy(MyDish dish, Integer quantity) {

        int flag = 0;
        int repeatedQuantity = 0;
        MyDish repeatedDish = null;

        for (MyDish myDish : this.chosenDishes.keySet())
        {
            if(dish.getName().equals(myDish.getName()))
            {
                flag = 1;
                repeatedDish = myDish;
                repeatedQuantity = chosenDishes.get(myDish);
            }
        }

        if (flag == 1) {
            this.chosenDishes.replace(repeatedDish, repeatedQuantity, quantity + repeatedQuantity);
        }
        else
            this.chosenDishes.put(dish, quantity);
    }


    public void remove(MyDish dish)
    {
        chosenDishes.remove(dish);
    }

}
