package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }



    // EFFECTS: reads workroom from file and returns it;
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


    // EFFECTS: parses workroom from JSON object and returns it
    private Bill parseBill(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Bill bill = new Bill(name);
        addBill(bill, jsonObject);
        return bill;
    }


    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom

    private void addBill(Bill bill, JSONObject jsonObject) {
        JSONArray jsonArray1 = jsonObject.getJSONArray("Users");
        JSONArray jsonArray2 = jsonObject.getJSONArray("Products");

        for (Object json : jsonArray1) {
            JSONObject jsonObject1 = (JSONObject) json;
            addUser(bill, jsonObject1);
        }

        for (Object json : jsonArray2) {
            JSONObject jsonObject2 = (JSONObject) json;
            addProduct(bill, jsonObject2);
        }
    }



    private void addUser(Bill bill, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double balance = jsonObject.getDouble("Balance Owed");
        User u = new User(name,balance);
        bill.putUserList(u);
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addProduct(Bill bill, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double cost = jsonObject.getDouble("cost");

        JSONArray jsonArray = jsonObject.getJSONArray("listOfUsers");
        for (Object json : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) json;
            addProdUser(bill, jsonObject1);
        }


    }

    private void addProdUser(Bill bill, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User u = new User(name);
        
    }
}
