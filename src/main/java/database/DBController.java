package database;
import java.sql.*;
import java.util.TimeZone;

public class DBController {

    /**
     * URL of the database
     */
    private static final String URL = "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_SEMgroup45?serverTimezone=" + TimeZone.getDefault().getID();
    private static final String DB_USERNAME = "pu_SEMgroup45";
    private static final String DB_PASSWORD = "rVZRdo6MaZkz";

    /**
     * Checks if a user exists in the database.
     * @param username of the user.
     * @return true if exists, else false.
     * @throws SQLException if prepared statement is wrong.
     */
    public static boolean userExists(final String username) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        try {
            String query = "select Username from User where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    return resultSet.next();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                preparedStatement.close();
            }

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.close();
        }
    }

    /**
     * Returns hashed password of the user.
     * @param username of the user.
     * @return hashed password of the user.
     * @throws SQLException if prepared statement is invalid.
     */
    public static String getHashedPassword(final String username) throws SQLException {
        assert userExists(username);
        Connection connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        try {
            String query = "select Password from User where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    return resultSet.getString("Password");
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.close();
        }
    }

    /**
     * Adds a user to the database.
     * @param username of the user.
     * @param hashedPassword of the user.
     * @throws SQLException if prepared statement is invalid.
     */
    public static void createUser(final String username, final String hashedPassword) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        try {
            String queryUser = "insert into User (Username, Password) VALUES (?,?)";
            String queryScore = "insert into Score (username, score, chosen_name) VALUES (?,?,?)";
            PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);
            PreparedStatement preparedStatementScore = connection.prepareStatement(queryScore);
            try {
                preparedStatementUser.setString(1, username);
                preparedStatementUser.setString(2, hashedPassword);
                preparedStatementScore.setString(1, username);
                preparedStatementScore.setInt(2, 0);
                preparedStatementScore.setString(3, username);
                if (!userExists(username)) {
                    preparedStatementUser.execute();
                    preparedStatementScore.execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatementUser.close();
                preparedStatementScore.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

    }
}
