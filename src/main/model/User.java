package model;

public class User extends Users {
    private String name;
    private float balance;
    private float totalBalance;

    public User(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public void splitBalance(User... values) {
        int num = 0;
        for (User s : values) {
            num++;
        }
        float split = (totalBalance / num);

        for (User s : values) {
            s.balance += split;
        }

    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", balance=" + balance + '}';
    }
}
