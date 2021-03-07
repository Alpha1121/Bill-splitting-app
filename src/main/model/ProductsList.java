package model;

import java.util.ArrayList;
import java.util.List;

public class ProductsList {
    List<Product> productList = new ArrayList<Product>();
    double totalBalance;


    public void addProductToList(Product prod) {
        productList.add(prod);
        totalBalance += prod.getCost();
    }

    public Product getProduct(int ind) {
        return productList.get(ind);
    }

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




    public String getAllProducts() {
        String s = "";
        if (productList != null) {
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
