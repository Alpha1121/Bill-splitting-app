package model;

import org.json.JSONObject;

//Users that would be using the SplittingApp to split the bill b/w each other
public class User extends UsersList {
    private String name;
    private double balance;


    //EFFECT: Constructor that creates User object with the name provided in the parameter.
    public User(String name) {
        setName(name);
    }

    public User(String name, double balance) {
        setName(name);
        addBalance(balance);
    }

    public void setName(String name) {
        this.name = name;
    }

    //EFFECT returns the name of users
    public String getName() {
        return name;
    }


    /* REQUIRES: balance >= 0
     *
     * MODIFIES this
     *
     * EFFECTS: Adds amount to the owed balance of the user
     */
    public void addBalance(double amount) {
        if (amount >= 0) {
            this.balance += amount;
        }
    }

    /* REQUIRES: balance >= amount and amount>=0
     *
     * MODIFIES this
     *
     * EFFECTS: deducts amount from the owed balance of the user
     */
    public void deductBalance(double amount) {
        if (amount >= 0) {
            if (balance >= amount) {
                this.balance -= amount;
            } else {
                balance = 0;
            }
        }
    }



    //EFFECT returns the owed balance of a user
    public double getBalance() {
        return balance;
    }



    /*
     * EFFECTS: returns a string representation of the User
     */
    @Override
    public String toString() {
        return ("User {" + "name='" + name + '\'' + ", Balance Owed=" + getBalance() + '}');
    }



    public JSONObject userToJson() {
        JSONObject json = new JSONObject();
        json.put("Balance Owed", balance);
        json.put("name", name);
        return json;
    }
}
