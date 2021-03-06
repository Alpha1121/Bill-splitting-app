package model;

public class User extends UsersList {
    private String name;
    private float balance;

    public User(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addBalance(float splitBalance) {
        this.balance += splitBalance;
    }

    public float getBalance() {
        return balance;
    }


    @Override
    public String toString() {
        return ("User {" + "name='" + name + '\'' + ", Balance Owed=" + balance + '}');
    }
}
