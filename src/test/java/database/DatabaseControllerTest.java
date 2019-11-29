package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DatabaseControllerTest {
    private transient DatabaseController database;
    private transient Connection connection;

    @BeforeEach
    void setupTestEnvironment() {
        connection = Mockito.mock(Connection.class);
        database = new DatabaseController(DatabaseConnector.setUpConnection());
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

    @Test
    void getConnection() {
        assertNotNull(database.getConnection());
    }

    @Test
    void setConnection() {
        database.setConnection(null);
        assertNull(database.getConnection());
    }
}
