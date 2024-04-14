package GUI;

import input.Restaurant;
import input.dishes.Dish;
import input.table.Table;
import input.user.User;
import myRestaurant.MyRestaurant;
import myRestaurant.myDishes.*;
import myRestaurant.mySystemUsers.*;
import myRestaurant.myTables.MyTable;
import myRestaurant.myTables.MyTables;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XML {

    public static void readFromXML(List<MyUser> myUsersList, List<MyTable> myTablesList, List<MyDish> myDishesList, List<MyDish> myAppetizersList, List<MyDish> myMaincourseList, List<MyDish> myDessertsList, MyRestaurant myyRestaurant) throws JAXBException {


        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Restaurant restaurant = (Restaurant) unmarshaller.unmarshal(new File("src\\input.xml"));

        List<User> usersInputList = new ArrayList<>();
        usersInputList = restaurant.getUsers().getUsers();

        List<Dish> dishesInputList = new ArrayList<>();
        dishesInputList = restaurant.getDishes().getDishes();

        List<Table> tablesInputList = new ArrayList<>();
        tablesInputList = restaurant.getTables().getTables();


        HashMap<User, Table> clientsReservedTable = new HashMap<User, Table>();
        clientsReservedTable = restaurant.getClientsReservedTable();


        for (User newUser : usersInputList) {
            if (newUser.getRole().equals("Client")) {
                myUsersList.add(new Client(newUser.getName(), newUser.getUsername(), newUser.getPassword()));
            }
            else if (newUser.getRole().equals("Manager")) {
                myUsersList.add(new Manager(newUser.getName(), newUser.getUsername(), newUser.getPassword()));
            } else if (newUser.getRole().equals("Waiter")) {
                myUsersList.add(new Waiter(newUser.getName(), newUser.getUsername(), newUser.getPassword()));
            } else if (newUser.getRole().equals("Cooker")) {
                myUsersList.add(new Cook(newUser.getName(), newUser.getUsername(), newUser.getPassword()));
            }
        }


        for (Dish newDish : dishesInputList) {
            if (newDish.getType().equals("appetizer")) {
                myDishesList.add(new Appetizer(newDish.getName(), newDish.getPrice()));
                myAppetizersList.add(new Appetizer(newDish.getName(), newDish.getPrice()));
            } else if (newDish.getType().equals("main_course")) {
                myDishesList.add(new Maincourse(newDish.getName(), newDish.getPrice()));
                myMaincourseList.add(new Maincourse(newDish.getName(), newDish.getPrice()));
            } else if (newDish.getType().equals("dessert")) {
                myDishesList.add(new Dessert(newDish.getName(), newDish.getPrice()));
                myDessertsList.add(new Dessert(newDish.getName(), newDish.getPrice()));
            }
        }


        for (Table newTable : tablesInputList) {
            myTablesList.add(new MyTable(newTable.getNumber(), newTable.getNumberOfSeats(), newTable.isSmoking(), newTable.isReserved()));
        }


        for(User newUser : clientsReservedTable.keySet())
        {
            Client newClient = new Client(newUser.getName(), newUser.getUsername(), newUser.getPassword());
            newClient.setMoneyPaid(newUser.getMoneyPaid());

            for(Dish oldDish : newUser.getChosenDishes().keySet())
            {
                if (oldDish.getType().equals("appetizer"))
                    newClient.getChosenDishes().put(new Appetizer(oldDish.getName(), oldDish.getPrice()), newUser.getChosenDishes().get(oldDish));

                else if (oldDish.getType().equals("main_course"))
                    newClient.getChosenDishes().put(new Maincourse(oldDish.getName(), oldDish.getPrice()), newUser.getChosenDishes().get(oldDish));
                else if (oldDish.getType().equals("dessert"))
                    newClient.getChosenDishes().put(new Dessert(oldDish.getName(), oldDish.getPrice()), newUser.getChosenDishes().get(oldDish));
            }
            myyRestaurant.getClientsReservedTable().put(newClient, new MyTable(clientsReservedTable.get(newUser).getNumber(), clientsReservedTable.get(newUser).getNumberOfSeats(), clientsReservedTable.get(newUser).isSmoking(), clientsReservedTable.get(newUser).isReserved()));
        }
        myyRestaurant.setTotalMoneyEarned(restaurant.getTotalMoneyEarned());
    }

    public static void saveToXML(List<MyUser> myUsersList, List<MyTable> myTablesList, List<MyDish> myDishesList, List<MyDish> myAppetizersList, List<MyDish> myMaincourseList, List<MyDish> myDessertsList, MyRestaurant myyRestaurant) throws JAXBException {

        double totalMoneyEarned= 0;
        for(MyUser user : myUsersList)
        {
            if(user instanceof Client)
            {
                totalMoneyEarned +=  ((Client)user).getMoneyPaid();
            }
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(MyRestaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        MyRestaurant myRestaurant = new MyRestaurant();


        MyUsers myUsers = new MyUsers();
        for(MyUser myUser : myUsersList)
        {
            if(myUser instanceof Client)
                myUser.setRole("Client");
            else if (myUser instanceof Manager)
                myUser.setRole("Manager");
            else if (myUser instanceof Waiter)
                myUser.setRole("Waiter");
            else if (myUser instanceof Cook)
                myUser.setRole("Cooker");
        }
        myUsers.setUsers(myUsersList);


        MyTables myTables = new MyTables();
        myTables.setTables(myTablesList);

        MyDishes myDishes = new MyDishes();
        for(MyDish myDish : myDishesList)
        {
            if(myDish instanceof Appetizer)
                myDish.setType("appetizer");
            else if (myDish instanceof Maincourse)
                myDish.setType("main_course");
            else if(myDish instanceof Dessert)
                myDish.setType("dessert");
        }
        myDishes.setDishes(myDishesList);


        HashMap<Client, MyTable> myMap = new HashMap<Client, MyTable>();

        for(Client myClient : myyRestaurant.getClientsReservedTable().keySet())
        {

            for(MyDish newDish : myClient.getChosenDishes().keySet())
            {
                if (newDish instanceof Appetizer)
                    newDish.setType("appetizer");
                else if (newDish instanceof Maincourse)
                    newDish.setType("main_course");
                else if (newDish instanceof Dessert)
                    newDish.setType("dessert");
            }

            myMap.put(myClient, myyRestaurant.getClientsReservedTable().get(myClient));
        }

        myRestaurant.setMyUsers(myUsers);
        myRestaurant.setMyTables(myTables);
        myRestaurant.setMyDishes(myDishes);
        myRestaurant.setClientsReservedTable(myMap);
        myRestaurant.setTotalMoneyEarned(totalMoneyEarned);

        marshaller.marshal(myRestaurant, new File("src\\input.xml"));

        myyRestaurant.getClientsReservedTable().clear();
        myyRestaurant.setTotalMoneyEarned(0.0);
        myTablesList.clear();
        myUsersList.clear();
        myDishesList.clear();
        myAppetizersList.clear();
        myMaincourseList.clear();
        myDessertsList.clear();
    }
}
