package myRestaurant.mySystemUsers;

import myRestaurant.myDishes.MyDish;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)

public abstract class MyUser {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "password")
    private String password;

    //just for xml marshalling
    @XmlElement(name = "role")
    private String role;


    public MyUser()
    {}

    public MyUser(String name, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        ArrayList<MyDish> chosenDishes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
