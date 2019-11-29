package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DatabaseConnector {
    //Database information
    private static final String URL =
            "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_SEMgroup45?serverTimezone="
                    + TimeZone.getDefault().getID();
    private static final String DB_USERNAME = "pu_SEMgroup45";
    private static final String DB_PASSWORD = "rVZRdo6MaZkz";

    /**
     * Sets up a connection with the database.
     * @return created Connetion object.
     */
    public static Connection setUpConnection() {
        try {
            return DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
