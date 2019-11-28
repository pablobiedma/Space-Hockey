package database;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

//BIG TO-DO -> create mock tests, these tests here are useless in the long run//
class DBControllerTest {

    @Test
    void userExistsTest() throws SQLException {
        assertFalse(DBController.userExists("----------"));
        assertTrue(DBController.userExists("hello"));
    }

    @Test
    public void getPasswordHashTest() throws SQLException {
        assertEquals("LPJNul+wow4m6DsqxbninhsWHlwfp0JecwQzYpOLmCQ=", DBController.getHashedPassword("hello"));
    }

    @Test
    public void createUserTest() throws SQLException {
        String username = "test";
        String hashedPassword = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg";
        DBController.createUser(username, hashedPassword);
        assertTrue(DBController.userExists(username));
    }
}
