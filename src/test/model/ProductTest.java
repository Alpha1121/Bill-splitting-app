package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product p1;
    Product p2;
    User u1;
    User u2;

    @BeforeEach
    public void runBefore() {
        p1 = new Product();
        p2 = new Product();
        u1 = new User("Amogh");
        u2 = new User("Mary");
    }

    @Test
    public void testSetNameGetNameSetCostGetCost() {
        p1.setName("Coffee");
        p1.setCost(100);

        assertEquals("Coffee", p1.getName());
        assertEquals(100, p1.getCost());


        //Test to check condition where cost is negative
        p2.setName("Milk");
        p2.setCost(-100);

        assertEquals("Milk", p2.getName());
        assertEquals(0, p2.getCost());


    }

    @Test
    public void testSetProdUsersGetProdUsers() {
        p1.setName("Coffee");
        p1.setCost(100);
        p1.setProdUsers(u1);
    }
}
