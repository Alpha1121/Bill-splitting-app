package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product p1;
    Product p2;
    User u1;
    User u2;
    UsersList list1;
    UsersList list2;

    @BeforeEach
    public void runBefore() {
        p1 = new Product();
        p2 = new Product("Milk", 100);
        u1 = new User("Amogh");
        u2 = new User("Mary");
        list1 = new UsersList();
        list2 = new UsersList();
    }

    @Test
    public void testSetNameGetNameSetCostGetCost() {
        p1.setName("Coffee");
        p1.setCost(100);

        assertEquals("Coffee", p1.getName());
        assertEquals(100, p1.getCost());


        //Test to check condition where cost is negative
        p2.setCost(-100);

        assertEquals("Milk", p2.getName());
        assertEquals(0, p2.getCost());


    }

    @Test
    public void testSetProdUsersGetProdUsers() {
        p1.setName("Coffee");
        p1.setCost(100);
        list1.addUserToList(u1);

        assertTrue(p1.setProdUsers(list1));


        list2 = p1.getProdUsers();
        u2 = list2.getUserFromList(0);
        assertEquals(u1,u2);

    }

    @Test
    public void testGetUserNames() {
        p1.setName("Coffee");
        p1.setCost(100);
        assertEquals(" All Users", p1.getUserNames());

        p2.setName("Milk");
        p2.setCost(100);
        list1.addUserToList(u1);
        list1.addUserToList(u2);
        p2.setProdUsers(list1);
        assertEquals(" 'Amogh'  'Mary' ", p2.getUserNames());
    }

    @Test
    public void testToString() {
        p1.setName("Coffee");
        p1.setCost(100);

        assertEquals("Product {name= 'Coffee', cost= '100.0', Shared between= All Users}", p1.toString());

        p2.setName("Milk");
        p2.setCost(100);
        list1.addUserToList(u1);
        list1.addUserToList(u2);
        p2.setProdUsers(list1);
        assertEquals("Product {name= 'Milk', cost= '100.0', Shared between= 'Amogh'  'Mary' }", p2.toString());

    }
}
