package model;

public class Product {

    private String name;
    private double cost;
    UsersList prodUsers = new UsersList();

    //Empty constructor
    public Product() {
    }

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
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
    public boolean setProdUsers(UsersList list) {
        for (User u : list.allUsers) {
            prodUsers.addUserToList(u);
        }
        return true;
    }


    /*
     * EFFECTS:
     *          returns the users of that specific product to the list of prodUsers
     *          IF the product is used by ALL users then the list is EMPTY, and null value is returned
     *
     */
    public UsersList getProdUsers() {
        if (prodUsers != null) {
            return prodUsers;
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
