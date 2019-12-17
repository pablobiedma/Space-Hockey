package com.mygdx.airhockey.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DatabaseConnector {
    //Database information
    private static String URL =
            "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_SEMgroup45?serverTimezone="
                    + TimeZone.getDefault().getID();
    private static String DB_USERNAME = "pu_SEMgroup45";
    private static String DB_PASSWORD = "rVZRdo6MaZkz";

    /**
     * Sets up a connection with the com.mygdx.airhockey.database.
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

    /**
     * Changes the com.mygdx.airhockey.database credentials.
     * @param url of the com.mygdx.airhockey.database.
     * @param username used for login.
     * @param password used for login.
     */
    public static void setCredentials(String url, String username, String password) {
        URL = url;
        DB_USERNAME = username;
        DB_PASSWORD = password;
    }

    /**
     * Resets the com.mygdx.airhockey.database credentials.
     */
    public static void resetCredentials() {
        URL = "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_SEMgroup45?serverTimezone="
                + TimeZone.getDefault().getID();
        DB_USERNAME = "pu_SEMgroup45";
        DB_PASSWORD = "rVZRdo6MaZkz";
    }
}
