package myRestaurant.myTables;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyTable {

    @XmlElement(name = "number")
    private int number;

    @XmlElement(name = "number_of_seats")
    private int numberOfSeats;

    @XmlElement(name = "smoking")
    private boolean smoking;

    @XmlElement(name = "isReserved")
    private boolean isReserved;

    public MyTable() {}

    public MyTable(int number, int numberOfSeats, boolean smoking, boolean isReserved)
    {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.smoking = smoking;
        this.isReserved  = isReserved;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public MyTable isAvailable(List <MyTable> myTables)
    {
        boolean available = false;
        MyTable reservedTable = null;
        sortTablesWithSeats(myTables);
        for(int i = 0; i<myTables.size(); i++)
        {
            if(myTables.get(i).isSmoking() == this.smoking  && myTables.get(i).getNumberOfSeats() >= this.numberOfSeats)
                {
                    if(!myTables.get(i).isReserved()){
                        available = true;
                        reservedTable = myTables.get(i);
                        myTables.get(i).setReserved(true);
                        break;
                    }
                }
        }
        return reservedTable;
    }
    public void sortTablesWithSeats(List<MyTable> myTables)
    {
        Collections.sort(myTables, new Comparator<MyTable>() {
            @Override
            public int compare(MyTable table1, MyTable table2) {
                if (table1.getNumberOfSeats() != table2.getNumberOfSeats())
                    return table1.getNumberOfSeats() - table2.getNumberOfSeats();
                else return 0;
            }
        });
    }
}
