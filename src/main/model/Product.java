package model;

public class Product {

    String name;
    private float cost;

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

    @Override
    public String toString() {
        return "Product{" + "name='" + name + '\'' + ", cost=" + cost + '}';
    }
}
