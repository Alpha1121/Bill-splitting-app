package model;

import java.util.ArrayList;
import java.util.List;

public class Users {
    List<User> allUsers = new ArrayList<User>();

    public void addUserToList(User u) {
        allUsers.add(u);
    }


    public void removeUserFromList(int i) {
        if (allUsers.contains(allUsers.get(i))) {
            allUsers.remove(allUsers.get(i));
        }
    }

    public void getAllUsers() {
        System.out.println("sr. no. |     Name     |     balance     |");
        int i = 1;
        for (User u : allUsers) {
            System.out.println(" " + i + ".  " + u.toString());
            i++;
        }
    }
}
