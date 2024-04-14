package myRestaurant.mySystemUsers;

import input.user.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyUsers {

    @XmlElement(name = "user")
    private List<MyUser> myUsers = new ArrayList<>();

    public List<MyUser> getUsers() {
        return myUsers;
    }

    public void setUsers(List<MyUser> users) {
        this.myUsers = users;
    }
}

