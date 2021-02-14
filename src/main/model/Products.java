package model;

import java.util.ArrayList;
import java.util.List;

public class Products {
    List<Product> allProducts = new ArrayList<Product>();

    public void addProductToList(Product prod) {
        allProducts.add(prod);
    }

    public void removeProductFromList(Product prod) {
        if (allProducts.contains(prod)) {
            allProducts.remove(prod);
        }
    }

    public void getAllProducts() {
        System.out.println("sr. no. |     Name     |     cost     |");
        int i = 1;
        for (Product p : allProducts) {
            System.out.println(" " + i + ".  " + p.toString());
            i++;
        }
    }
}
