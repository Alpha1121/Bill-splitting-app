package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Houses all the products which have attributes like name, cost and users(of the product)
public class ProductsList {
    String name;
    List<Product> productList;
    UsersList usersList;
    double totalBalance;

    public ProductsList() {
        productList = new ArrayList<Product>();
    }

    public ProductsList(String name) {
        this.name = name;
        productList = new ArrayList<Product>();
    }

    // EFFECT: Adds prod to the productList and also increases the totalBalance by
    // the amount of the product
    public void addProductToList(Product prod) {
        addProdUserToList(prod);

        productList.add(prod);
        totalBalance += prod.getCost();
    }

    // MODIFIES: This
    // EFFECT: adds users to usersList.
    private void addProdUserToList(Product p) {
        boolean exists = false;
        System.out.println("Adding users to prodUserList");
        if (p.getListOfProdUsers() != null) {
            for (int i = 0; i < p.getListOfProdUsers().getSize(); i++) {
                User u = p.getListOfProdUsers().getUserFromList(i);
                if (usersList == null) {
                    break;
                } else {
                    for (int j = 0; j < usersList.getSize(); j++) {
                        System.out.println("reached here for " + p.getName());
                        User u1 = usersList.getUserFromList(j);
                        if (u.equals(u1)) {
                            exists = true;
                        }
                    }
                    if (!exists) {
                        usersList.addUserToList(u);
                    }
                }
            }
        } else {
            System.out.println("No users to add");
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return productList.size();
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

    public boolean removeProductFromList(Product prod, Bill bill) {
        if (productList.contains(prod)) {
            productList.remove(prod);
            totalBalance -= prod.getCost();
            prod.deductBalanceFromUsers(prod, bill);
            return true;
        } else {
            return false;
        }
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    /*
     * EFFECTS: returns a string representation of all the products in the
     * ProductsList
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

        jsonObject.put("ProductsList", jsonArray);

        return jsonObject;
    }

}
