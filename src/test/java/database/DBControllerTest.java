package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBControllerTest {
    private transient DBController database;
    @BeforeEach
    void setupTestEnvironment() {
        database = new DBController();
    }

    @Test
    void constructorTest() {
        assertNotNull(database);
    }

    @Test
    void userExistsTest() throws SQLException {
        assertFalse(database.userExists("----------"));
        assertTrue(database.userExists("hello"));
    }

    @Test
    public void getPasswordHashTest() throws SQLException {
        assertEquals("LPJNul+wow4m6DsqxbninhsWHlwfp0JecwQzYpOLmCQ=",
                database.getHashedPassword("hello"));
    }

    @Test
    public void createUserTest() throws SQLException {
        String username = "test";
        String hashedPassword = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg";
        database.createUser(username, hashedPassword);
        assertTrue(database.userExists(username));
    }
}
