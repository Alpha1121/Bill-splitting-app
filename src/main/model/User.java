package model;

public class User extends UsersList {
    private String name;
    private double balance;


    //EFFECT: Constructor that creates User object with the name provided in the parameter.
    public User(String name) {
        setName(name);
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

    /* REQUIRES: balance >= 0
     *
     * MODIFIES this
     *
     * EFFECTS: deducts amount from the owed balance of the user
     */
    public void deductBalance(double amount) {
        if (amount >= 0) {
            this.balance -= amount;
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
}
