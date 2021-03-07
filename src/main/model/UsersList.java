package model;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    List<User> allUsers = new ArrayList<User>();

    public void addUserToList(User u) {
        allUsers.add(u);
    }

    public User getUserFromList(int ind) {
        return allUsers.get(ind);
    }

    public String getUserName(int ind) {
        return getUserFromList(ind).getName();
    }

    public boolean removeUserFromList(int i) {
        if (allUsers.contains(allUsers.get(i))) {
            allUsers.remove(allUsers.get(i));
            return true;
        } else {
            return false;
        }
    }

    public int getSize() {
        return allUsers.size();
    }


    public String getAllUsers() {
        String s = "";
        int i = 1;
        for (User u : allUsers) {
            s += " " + i + ".  " + u.toString() + "\n";
            i++;
        }

        return s;
    }
}
