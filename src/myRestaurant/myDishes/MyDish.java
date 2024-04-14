package myRestaurant.myDishes;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "dish")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class MyDish {

    @XmlElement (name = "name")
    private  String name;

    @XmlElement(name = "price")
    private double price;

    @XmlTransient
    private double finalPrice;

  //just for xml marshalling, not needed in the project except when saving
    @XmlElement(name = "type")
    private String type;

    @XmlTransient
    private double taxes;

    public MyDish()
    {}

    public MyDish(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }


    public void setFinalPrice() {
        this.price += price * taxes;
    }

    public double getFinalPrice() {
        return (this.price + this.price * taxes);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
