package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsListTest {
    Product p1;
    Product p2;

    User u1;
    User u2;
    User u3;

    UsersList usersList1;
    UsersList usersList2;

    ProductsList list1;
    ProductsList list2;

    @BeforeEach
    public void runBefore() {
        p1 = new Product("Coffee", 100);
        p2 = new Product("Milk", 50);

        u1 = new User("Amogh");
        u2 = new User("Morty");
        u3 = new User("Rick");

        list1 = new ProductsList();
        list2 = new ProductsList();
    }

    @Test
    public void testAddProductToListGetProduct() {
        list1.addProductToList(p1);

        assertEquals(p1, list1.getProduct(0));

    }

    @Test
    public void testRemoveProductFromList() {
        list1.addProductToList(p1);
        list1.addProductToList(p2);
        assertTrue(list1.removeProductFromList(p2));
    }

    @Test
    public void testGetTotalBalance() {
        list1.addProductToList(p1);
        list1.addProductToList(p2);
        assertEquals(150.00, list1.getTotalBalance());

        list1.removeProductFromList(p2);
        assertEquals(100.00, list1.getTotalBalance());
    }

    @Test
    public void testGetAllProducts() {
        list1.addProductToList(p1);
        list1.addProductToList(p2);

        String s = list1.getAllProducts();

        assertEquals(" 1.  Product {name= 'Coffee', cost= '100.0', Shared between= All Users}\n" +
                " 2.  Product {name= 'Milk', cost= '50.0', Shared between= All Users}\n", s);
    }

}
