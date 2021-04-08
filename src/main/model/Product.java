package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product extends ProductsList {

    private String name;
    private double cost;
    UsersList listOfUsers = new UsersList();

    //Empty constructor
    public Product() {
    }

    //Constructor with name and cost as parameters
    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    //Constructor with name, cost and ProdUsers
    public Product(String name, double cost, UsersList usersList) {
        setName(name);
        setCost(cost);
        setListOfUsers(usersList);
    }

    public String getProdUsers() {
        return getUserNames();
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    //REQUIRES: cost >= 0
    public void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        } else {
            this.cost = 0;
        }
    }


    public double getCost() {
        return cost;
    }

    /*
     * MODIFIES: this
     * EFFECTS:
     *          adds the users of that specific product to the list of prodUsers
     *          IF the product is used by ALL users then the list is EMPTY.
     */
    public boolean setListOfUsers(UsersList list) {
        for (User u : list.allUsers) {
            listOfUsers.addUserToList(u);
        }
        return true;
    }


    /*
     * EFFECTS:
     *          returns the users of that specific product to the list of prodUsers
     *          IF the product is used by ALL users then the list is EMPTY, and null value is returned
     *
     */
    public UsersList getListOfUsers() {
        if (listOfUsers != null) {
            return listOfUsers;
        } else {
            return null;
        }
    }

    /*
     * EFFECTS:
     *          returns a string of all the users that use this product.
     *          IF the product is used by ALL users then the string "ALL USERS" is returned.
     *
     */
    public String getUserNames() {
        String s;
        if (listOfUsers.allUsers.size() != 0) {
            s = "";
            for (User u : listOfUsers.allUsers) {
                s += " '" + u.getName() + "' ";
            }
        } else {
            s = " All Users";
        }

        return s;
    }

    /*MODIFIES user.balance && product.users
     *EFFECT :
     *  makes a Integer List to store the serial numbers of Users entered by the USER
     *  accordingly adds those Users(using those serial numbers) to a list
     *  sends the list to Product.setProdUsers to set the Users using that product.
     *
     */
    public void split(Bill bill) {
        changeUsersBalance(bill, true);
    }

    private void changeUsersBalance(Bill bill, boolean split) {
        double splitCost = (getCost() / listOfUsers.getSize());

        for (int i = 0; i < listOfUsers.getSize(); i++) {
            User userFromProdUsersList = listOfUsers.getUserFromList(i);
            for (int i1 = 0; i1 < bill.getUsersList().getSize(); i1++) {
                User userFromUsersList = bill.usersList.getUserFromList(i1);

                if (userFromProdUsersList.getName().contains(userFromUsersList.getName())) {
                    if (split) {
                        userFromUsersList.addBalance(splitCost);
                        System.out.println("Added split balance under: " + userFromUsersList.getName());
                    } else {
                        userFromUsersList.deductBalance(splitCost);
                        System.out.println("Deducted split balance from: " + userFromUsersList.getName());

                    }
                }
            }
        }
    }

    //MODIFIES: bill.usersList
    //EFFECTS: Deducts balance from all prod Users who are in the bill.usersList
    public void deductBalanceFromUsers(Product prod, Bill bill) {
        changeUsersBalance(bill, false);
    }


    /*
     * EFFECTS: returns a string representation of the product
     */

    @Override
    public String toString() {

        return "name= '" + name + '\''
                + ",    cost= '" + cost + '\''
                + ",    Shared between=" + getProdUsers();

    }



    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cost", cost);

        if (listOfUsers.allUsers.size() == 0) {
            json.put("Users", "All Users");
        } else {
            JSONArray jsonArray = new JSONArray();
            for (User u : listOfUsers.allUsers) {
                JSONObject json1 = new JSONObject();
                json1.put("name", u.getName());
                jsonArray.put(json1);
            }
            json.put("listOfUsers",jsonArray);
        }

        return json;
    }
}
