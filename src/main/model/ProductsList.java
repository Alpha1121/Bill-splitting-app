package model;

import java.util.ArrayList;
import java.util.List;

public class ProductsList {
    List<Product> productList = new ArrayList<Product>();
    float totalBalance;


    public void addProductToList(Product prod) {
        productList.add(prod);
        totalBalance += prod.getCost();
    }

    public Product getProduct(int ind) {
        return productList.get(ind);
    }

    public void removeProductFromList(Product prod) {
        if (productList.contains(prod)) {
            productList.remove(prod);
            totalBalance -= prod.getCost();
        }
    }


    public float getTotalBalance() {
        return totalBalance;
    }




    public void getAllProducts() {
        int i = 1;
        for (Product p : productList) {
            System.out.println(" " + i + ".  " + p.toString());
            i++;
        }
    }
}
