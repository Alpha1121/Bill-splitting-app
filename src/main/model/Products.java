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

    @Override
    public String toString() {
        return "Products{" + "allProducts=" + allProducts + '}';
    }
}
