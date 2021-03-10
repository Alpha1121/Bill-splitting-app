package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsList {
    String name;
    List<Product> productList;
    double totalBalance;

    public ProductsList() {
        productList = new ArrayList<Product>();
    }

    public ProductsList(String name) {
        this.name = name;
        productList = new ArrayList<Product>();
    }

    //EFFECT: Adds prod to the productList and also increases the totalBalance by the amount of the product
    public void addProductToList(Product prod) {
        productList.add(prod);
        totalBalance += prod.getCost();
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
            s = "[ No Products to show ]";
        }

        return s;
    }




    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Product p : productList) {
            jsonArray.put(p.toJson());
        }

        jsonObject.put("Products", jsonArray);
        return jsonObject;
    }


}
