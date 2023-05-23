package persistence;

import model.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Bill from file and returns it;
    // throws IOException if an error occurs reading data from file

    public Bill readBill() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBill(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bill from JSON object and returns it
    private Bill parseBill(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Bill bill = new Bill(name);
        addBill(bill, jsonObject);
        return bill;
    }

    // MODIFIES: Bill
    // EFFECTS: parses usersList and productsList from JSON object and adds them to
    // the bill

    private void addBill(Bill bill, JSONObject jsonObject) {

        JSONArray billJsonArray = jsonObject.getJSONArray("bill");

        JSONObject usersJson = (JSONObject) billJsonArray.get(0);
        JSONArray usersArray = (JSONArray) usersJson.get("UsersList");

        JSONObject productsJson = (JSONObject) billJsonArray.get(1);
        JSONArray productsArray = (JSONArray) productsJson.get("ProductsList");

        for (Object json : usersArray) {
            JSONObject jsonObject1 = (JSONObject) json;
            addUser(bill, jsonObject1);
            // System.out.println("1 done");
        }

        for (Object json : productsArray) {
            JSONObject jsonObject2 = (JSONObject) json;
            // System.out.println("2 done");
            addProduct(bill, jsonObject2);
        }
    }

    // MODIFIES: bill.usersList
    // EFFECTS: parses User from JSON object and adds it to bill.usersList
    private void addUser(Bill bill, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double balance = jsonObject.getDouble("Balance Owed");
        User u = new User(name, balance);
        // System.out.println("addUser passed");
        bill.putUserInList(u);
    }

    // MODIFIES: bill.productsList
    // EFFECTS: parses Products from JSON object and adds it to bill.productsList
    // also parses Users from JSON object1 and adds it to
    // bill.productsList.product.listOfUsers
    private void addProduct(Bill bill, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double cost = jsonObject.getDouble("cost");
        UsersList prodUsers = new UsersList();
        System.out.println(name);
        if (jsonObject.has("Users")) {
            prodUsers = null;
        } else {
            JSONArray usersJson = jsonObject.getJSONArray("listOfUsers");
            for (Object json : usersJson) {
                JSONObject jsonObject1 = (JSONObject) json;
                // System.out.println("4 done");
                prodUsers.addUserToList(addProdUserToList(jsonObject1));
                // System.out.println("5 done");
            }
        }

        // add the read product with it's name, cost and users
        Product p = new Product(name, cost, prodUsers);
        bill.putProductsList(p);
    }

    // EFFECTS: parses Users from (JSON object) listOfUsers returns it
    private User addProdUserToList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User user = new User(name);

        return user;
    }
}
