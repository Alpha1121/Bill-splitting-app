package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Super Class Bill that nests the usersList and productsList in it
public class Bill {
    String name;
    UsersList usersList;
    ProductsList productsList;

    //MODIFIES: this
    //EFFECTS: constructor with single parameter(name)
    public Bill(String name) {
        this.name = name;
        usersList = new UsersList();
        productsList = new ProductsList();
    }

    public Bill(UsersList usersList, ProductsList productsList) {
        this.usersList = usersList;
        this.productsList = productsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UsersList getUsersList() {
        return usersList;
    }

    public ProductsList  getProductsList() {
        return productsList;
    }

    //MODIFIES: this
    //EFFECTS: adds user u to usersList
    public void putUserInList(User u) {
        usersList.addUserToList(u);
        System.out.println("Bill.putUserInList passed");
    }

    //MODIFIES: this
    //EFFECTS: adds product p to productsList
    public void putProductsList(Product p) {
        productsList.addProductToList(p);
    }

    //EFFECTS: returns a String representation of the bill
    public String toString() {
        String s1 = usersList.getAllUsers();
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

        jsonArray.put(usersList.toJson());

        jsonArray.put(productsList.toJson());

        return jsonArray;
    }


}
