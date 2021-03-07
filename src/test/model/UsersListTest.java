package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UsersListTest {
    UsersList list1;
    UsersList list2;
    User u1;

    @BeforeEach
    public void runBefore() {
        list1 = new UsersList();
        list2 = new UsersList();
        u1 = new User("Amogh");
    }


    @Test
    public void testAddUserToListGetUserFromList() {
        list1.addUserToList(u1);
        list2.addUserToList(u1);

        assertEquals(list1.getUserFromList(0), list2.getUserFromList(0));
    }

    @Test
    public void testGetUserName() {
        list1.addUserToList(u1);
        assertEquals("Amogh", list1.getUserName(0));
    }

    @Test
    public void testRemoveUser() {
        list1.addUserToList(u1);
        assertTrue(list1.removeUserFromList(0));
    }

    @Test
    public void testGetSize() {
        list1.addUserToList(u1);
        list1.addUserToList(u1);

        assertEquals(2, list1.getSize());
    }

    @Test
    public void testGetAllUsers() {
        list1.addUserToList(u1);
        list1.addUserToList(u1);

        String s = "";
        int i = 1;
        for (User u : list1.allUsers) {
            s += " " + i + ".  " + u.toString() + "\n";
            i++;
        }

        assertEquals(s, list1.getAllUsers());
    }
}
