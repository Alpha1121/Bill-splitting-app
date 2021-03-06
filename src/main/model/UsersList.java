package model;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    List<User> allUsers = new ArrayList<User>();

    public User getUser(int ind) {
        return allUsers.get(ind - 1);
    }

    public String getUserName(int ind) {
        return getUser(ind).getName();
    }

    public void addUserToList(User u) {
        allUsers.add(u);
    }


    public void removeUserFromList(int i) {
        if (allUsers.contains(allUsers.get(i))) {
            allUsers.remove(allUsers.get(i));
        }
    }

    public int getSize() {
        return allUsers.size();
    }


    public void getAllUsers() {
        int i = 1;
        for (User u : allUsers) {
            System.out.println(" " + i + ".  " + u.toString());
            i++;
        }
    }
}
