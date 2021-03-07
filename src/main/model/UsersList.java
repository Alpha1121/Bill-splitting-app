package model;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    List<User> allUsers = new ArrayList<User>();

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
}
