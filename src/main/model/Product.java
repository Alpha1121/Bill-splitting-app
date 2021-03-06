package model;

public class Product {

    String name;
    private float cost;
    UsersList users;

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

    public float getCost() {
        return cost;
    }

    public void setUsers(User... user) {
        users = new UsersList();
        for (User u : user) {
            users.addUserToList(u);
        }

    }

    @Override
    public String toString() {
        String s = null;
        for (int i = 0; i < users.getSize(); i++) {
            s = "Product{" + "name='" + name + '\'' + ", cost=" + cost + ", Shared between='" + users.getUser(i) + '}';
        }
        return s;
    }
}
