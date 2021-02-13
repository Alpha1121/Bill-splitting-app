package model;

import java.util.ArrayList;
import java.util.List;

public class Users {
    List<User> allUsers = new ArrayList<User>();

    public void addUserToList(User u) {
        allUsers.add(u);
    }

    public void removeUserFromList(User u) {
        if (allUsers.contains(u)) {
            allUsers.remove(u);
        }
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
