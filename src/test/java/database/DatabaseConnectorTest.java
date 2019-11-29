package database;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DatabaseConnectorTest {

    @Test
    void setUpConnection() throws SQLException {
        Connection connection = DatabaseConnector.setUpConnection();
        try {
            assertNotNull(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Test
    void setUpConnectionWrongCredentials() throws SQLException {
        DatabaseConnector.setCredentials("wrong.connection", "user", "password");
        Connection connection = DatabaseConnector.setUpConnection();
        try {
            assertNull(connection);
            DatabaseConnector.resetCredentials();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}