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

    ProductsList productsList1;
    ProductsList productsList2;

    @BeforeEach
    public void runBefore() {
        p1 = new Product("Coffee", 100);
        p2 = new Product("Milk", 50);

        u1 = new User("Amogh");
        u2 = new User("Morty");
        u3 = new User("Rick");

        usersList1.addUserToList(u1);
        usersList1.addUserToList(u2);

        usersList2.addUserToList(u2);
        usersList2.addUserToList(u3);

        p1.setProdUsers(usersList1);
        p2.setProdUsers(usersList2);

        productsList1 = new ProductsList();
        productsList2 = new ProductsList();
    }

    @Test
    public void testAddProductToListGetProduct() {
        productsList1.addProductToList(p1);

        assertEquals(p1, productsList1.getProduct(0));

    }

    @Test
    public void testRemoveProductFromList() {
        productsList1.addProductToList(p1);
        productsList1.addProductToList(p2);
        assertTrue(productsList1.removeProductFromList(p2));
    }

    @Test
    public void testGetTotalBalance() {
        productsList1.addProductToList(p1);
        productsList1.addProductToList(p2);
        assertEquals(150.00, productsList1.getTotalBalance());

        productsList1.removeProductFromList(p2);
        assertEquals(100.00, productsList1.getTotalBalance());
    }

    @Test
    public void testGetAllProducts() {
        productsList1.addProductToList(p1);
        productsList1.addProductToList(p2);

        String s = productsList1.getAllProducts();

        assertEquals(" 1.  Product {name= 'Coffee', cost= '100.0', Shared between= All Users}\n" +
                " 2.  Product {name= 'Milk', cost= '50.0', Shared between= All Users}\n", s);
    }

}
