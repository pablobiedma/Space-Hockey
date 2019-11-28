package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;

public class DatabaseControler {

    //Database information
    private static final String URL =
            "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_SEMgroup45?serverTimezone="
            + TimeZone.getDefault().getID();
    private static final String DB_USERNAME = "pu_SEMgroup45";
    private static final String DB_PASSWORD = "rVZRdo6MaZkz";

    /**
     * Checks if a user exists in the database.
     * @param username of the user.
     * @return true if exists, else false.
     */
    public boolean userExists(final String username) {
        Connection connection = setUpConnection();
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
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Returns hashed password of the user.
     * @param username of the user.
     * @return hashed password of the user.
     */
    public String getHashedPassword(final String username)  {
        assert userExists(username);
        Connection connection = setUpConnection();
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
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Adds a user to the database.
     * @param username of the user.
     * @param hashedPassword of the user.
     * @throws SQLException if prepared statement is invalid.
     */
    public void createUser(final String username, final String hashedPassword) {
        Connection connection = setUpConnection();
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
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets score of a user from the database.
     * @param username of the user.
     * @return the score of the user.
     */
    public int getScore(String username) {
        assert userExists(username);
        Connection connection = setUpConnection();
        try {
            String query = "select score from Score where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    return resultSet.getInt("Score");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * Updates the score of a user.
     * @param username of the user.
     */
    public void updateScore(String username, int score) {
        assert userExists(username);
        Connection connection = setUpConnection();
        try {
            String query = "update Score SET score = ? where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setInt(1,score);
                preparedStatement.setString(2, username);
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets up a connection with the database.
     * @return created Connetion object.
     */
    private Connection setUpConnection() {
        try {
            return DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
