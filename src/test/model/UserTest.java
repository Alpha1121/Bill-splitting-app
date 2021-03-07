package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User u1;
    User u2;

    @BeforeEach
    public void setup() {
        u1 = new User("Amogh");
        u2 = new User("Matt");
    }

    @Test
    void testConstructor() {
        assertEquals("Amogh", u1.getName());
        assertEquals("Matt", u2.getName());

    }

    @Test
    void testAddBalance() {
        u1.addBalance(4.49);
        assertEquals(4.49, u1.getBalance());

        u2.addBalance(-12);
        assertEquals(0, u2.getBalance());
    }

    @Test
    void testDeductBalance() {
        u1.addBalance(100);
        u1.deductBalance(50);
        assertEquals(50, u1.getBalance());

        u2.addBalance(100);
        u2.deductBalance(-40);
        assertEquals(100, u2.getBalance());
    }

    @Test
    public void userToString() {
        u1.setName("Amogh");
        u1.addBalance(100);

        assertEquals(u1.toString(), "User {" + "name='" + u1.getName() + '\'' + ", Balance Owed=" + u1.getBalance() + '}');


        u2.setName("Bleh");
        u2.addBalance(-100);
        u2.addBalance(10);
        assertEquals(u1.toString(), "User {" + "name='" + u1.getName() + '\'' + ", Balance Owed=" + u1.getBalance() + '}');
    }


}