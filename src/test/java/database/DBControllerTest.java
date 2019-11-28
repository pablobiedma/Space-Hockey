package database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

//BIG TO-DO -> create mock tests, these tests here are useless in the long run//
class DBControllerTest {
    @Test
    void userExistsTest() throws SQLException {
        assertTrue(DBController.userExists("mstyczen"));
        assertFalse(DBController.userExists("hello"));
    }

    @Test
    public void getPasswordHashTest() throws SQLException {
        assertEquals("XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg", DBController.getHashedPassword("mstyczen"));
    }

    @Test
    public void createUserTest() throws SQLException {
        String username = "test";
        String hashedPassword = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg";
        DBController.createUser(username, hashedPassword);
        assertTrue(DBController.userExists(username));
    }
}
