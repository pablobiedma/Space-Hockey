package database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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
            if(connection != null) connection.close();
        }
    }
}