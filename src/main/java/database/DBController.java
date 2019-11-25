package database;
import java.sql.*;

public class DBController {

    /**
     * URL of the database
     */
    private static final String URL = "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_SEMgroup45?" +
            "user=pu_SEMgroup45&password=rVZRdo6MaZkz";

    /**
     * Checks if a user exists in the database.
     * @param username of the user.
     * @return true if exists, else false.
     * @throws SQLException if prepared statement is wrong.
     */
    public static boolean userExists(final String username) throws SQLException {
        Connection connection = DriverManager.getConnection(URL);

        String query = "select Username from User where Username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        connection.close();
        preparedStatement.close();
        return resultSet.next();
    }

    /**
     * Returns hashed password of the user.
     * @param username of the user.
     * @return hashed password of the user.
     * @throws SQLException if prepared statement is invalid.
     */
    public static String getHashedPassword(final String username) throws SQLException {
        assert userExists(username);
        Connection connection = DriverManager.getConnection(URL);
        String query = "select Password from User where Username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        resultSet.close();
        connection.close();
        preparedStatement.close();
        return resultSet.getString("Password");
    }

    /**
     * Adds a user to the database.
     * @param username of the user.
     * @param hashedPassword of the user.
     * @throws SQLException if prepared statement is invalid.
     */
    public static void createUser(final String username, final String hashedPassword) throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        String query = "insert into User (Username, Password) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, hashedPassword);
        if (!userExists(username)) {
            preparedStatement.execute();
        }

        preparedStatement.close();
        connection.close();
    }
}
