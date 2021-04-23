package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Houses all the Users which have attributes like name and balance owed.
public class UsersList {
    String name;
    List<User> allUsers;
    ProductsList pl;

    public UsersList() {
        allUsers = new ArrayList<User>();
        pl = new ProductsList();
    }

    public UsersList(String name) {
        this.name = name;
        allUsers = new ArrayList<User>();
        pl = new ProductsList();
    }

    public void setName(String name) {
        this.name =  name;
    }

    public String getName() {
        return name;
    }

    public void addProduct() {

    }

    // Returns the productslist
    public ProductsList getProductsList() {
        return pl;
    }

    //MODIFIES this
    public void addUserToList(User u) {
        allUsers.add(u);
    }

    //REQUIRES: ind >= 0
    //EFFECTS: returns the User object from the allUsers List, according to the index entered in the parameter.
    public User getUserFromList(int ind) {
        if (ind >= 0) {
            return allUsers.get(ind);
        } else {
            return null;
        }
    }

    //REQUIRES: ind >= 0
    //EFFECTS: returns the name of the User according to the index entered in the parameter.
    public String getUserName(int ind) {
        if (ind >= 0) {
            return getUserFromList(ind).getName();
        } else {
            return null;
        }
    }

    //REQUIRES: ind >= 0
    //EFFECTS: removes the User object from bill.usersList according to the index entered in the parameter.
    public boolean removeUserFromList(int ind) {
        if (allUsers.contains(allUsers.get(ind))) {
            allUsers.remove(allUsers.get(ind));
            return true;
        } else {
            return false;
        }
    }


    //MODIFIES: UsersList bill.usersList
    //EFFECTS: removes the User u from bill.usersList
    public boolean removeUserFromList(User u) {
        if (allUsers.contains(u)) {
            allUsers.remove(u);
            return true;
        } else {
            return false;
        }
    }

    public int getSize() {
        return allUsers.size();
    }


    /*
     * EFFECTS: returns a string representation of all users in the UsersList
     */
    public String getAllUsers() {

        String s = "";
        if (allUsers.size() != 0) {
            int i = 1;
            for (User u : allUsers) {
                s += " " + i + ".  " + u.toString() + "\n";
                i++;
            }
        } else {
            s = "[ No Users to show ]";
        }

        return s;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (User user : allUsers) {
            jsonArray.put(user.userToJson());
        }

        jsonObject.put("UsersList",jsonArray);

        return jsonObject;
    }


}
