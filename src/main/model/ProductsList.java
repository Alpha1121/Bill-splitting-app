package model;

import java.util.ArrayList;
import java.util.List;

public class ProductsList {
    List<Product> productList = new ArrayList<Product>();
    double totalBalance;

    //EFFECT: Adds prod to the productList and also increases the totalBalance by the amount of the product
    public void addProductToList(Product prod) {
        productList.add(prod);
        totalBalance += prod.getCost();
    }

    public Product getProduct(int ind) {
        return productList.get(ind);
    }

    /*
     * EFFECTS:
     * returns true if productList contains prod and is successfully removed
     * else returns false.
     */
    public boolean removeProductFromList(Product prod) {
        if (productList.contains(prod)) {
            productList.remove(prod);
            totalBalance -= prod.getCost();
            return true;
        } else {
            return false;
        }
    }


    public double getTotalBalance() {
        return totalBalance;
    }


    /*
     * EFFECTS: returns a string representation of all the products in the ProductsList
     */
    public String getAllProducts() {
        String s = "";
        if (productList.size() != 0) {
            int i = 1;
            for (Product p : productList) {
                s += (" " + i + ".  " + p.toString() + "\n");
                i++;
            }
        } else {
            s = "No Products to show";
        }

        return s;
    }
}
