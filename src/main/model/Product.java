package model;

public class Product {

    String name;
    private double cost;
    UsersList prodUsers = new UsersList();

    public Product() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setProdUsers(User u) {
        prodUsers.addUserToList(u);
    }

    public UsersList getProdUsers() {
        if (prodUsers != null) {
            return prodUsers;
        } else {
            return null;
        }
    }

    public String getUserNames() {
        String s;
        if (prodUsers.allUsers.size() != 0) {
            s = "";
            for (User u : prodUsers.allUsers) {
                s += " \'" + u.getName() + "\' ";
            }
        } else {
            s = " All Users";
        }

        return s;
    }



    @Override
    public String toString() {

        return "Product {"
                + "name= '" + name + '\''
                + ", cost= '" + cost + '\''
                + ", Shared between=" + getUserNames()
                + '}';

    }
}
