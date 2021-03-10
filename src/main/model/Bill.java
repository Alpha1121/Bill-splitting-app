package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Bill {
    String name;
    ArrayList<Object> bill = new ArrayList<Object>();
    UsersList usersList;
    ProductsList productsList;

    public Bill(String name) {
        usersList = new UsersList();
        productsList = new ProductsList();
    }

    public Bill(UsersList usersList, ProductsList productsList) {
        this.usersList = usersList;
        this.productsList = productsList;

        bill.add(usersList);
        bill.add(productsList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void putUserList(User u) {
        usersList.addUserToList(u);
        bill.add(usersList);
    }

    public void putProductsList(Product p, UsersList u) {
        ProductsList productsList = new ProductsList();
        productsList.addProductToList(p);
        bill.add(productsList);
    }

    public String toString() {
        UsersList usersList = (UsersList) bill.get(0);
        String s1 = usersList.getAllUsers();

        ProductsList productsList = (ProductsList) bill.get(1);
        String s2 = productsList.getAllProducts();

        String s3 = s1 + "\n" + s2;

        return s3;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("bill", billToJson());
        return json;
    }


    // EFFECTS: returns things in this UsersList as a JSON array
    private JSONArray billToJson() {
        JSONArray jsonArray = new JSONArray();

        UsersList usersList = (UsersList) bill.get(0);
        jsonArray.put(usersList.toJson());

        ProductsList productsList = (ProductsList) bill.get(1);
        jsonArray.put(productsList.toJson());

        return jsonArray;
    }


}
