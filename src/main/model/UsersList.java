package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    String name;
    List<User> allUsers;

    public UsersList() {
        allUsers = new ArrayList<User>();
    }

    public UsersList(String name) {
        this.name = name;
        allUsers = new ArrayList<User>();
    }

    public void setName(String name) {
        this.name =  name;
    }

    public String getName() {
        return name;
    }


    //MODIFIES this
    public void addUserToList(User u) {
        allUsers.add(u);
    }

    //REQUIRES: ind >= 0
    //EFFECTS: returns the User object according to the index entered in the parameter.
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
    //EFFECTS: removes the User object according to the index entered in the parameter.
    public boolean removeUserFromList(int ind) {
        if (allUsers.contains(allUsers.get(ind))) {
            allUsers.remove(allUsers.get(ind));
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
//
//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("name", name);
//        json.put("allUsers", getAllUsers());
//        return json;
//    }



    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (User user : allUsers) {
            jsonArray.put(user.userToJson());
        }

        jsonObject.put("Users",jsonArray);

        return jsonObject;
    }


}
