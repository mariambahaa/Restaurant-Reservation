package myRestaurant.myTables;

import input.table.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)

public class MyTables {

    @XmlElement(name = "table")
    List<MyTable> myTables = new ArrayList<>();

    public List<MyTable> getTables() {
        return myTables;
    }

    public void setTables(List<MyTable> tables) {
        this.myTables = tables;
    }


}