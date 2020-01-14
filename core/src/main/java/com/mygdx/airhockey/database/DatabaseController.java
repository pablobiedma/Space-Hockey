package com.mygdx.airhockey.database;

import com.mygdx.airhockey.database.tables.Score;
import com.mygdx.airhockey.database.tables.User;
import com.mygdx.airhockey.statistics.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DatabaseController {
    private ConnectionFactory connectionFactory;


    transient Score score = null;
    transient User userDefine = null;
    transient int points = 0;
    transient String password = null;

    /**
     * Constructor for database controller.
     *
     * @param connectionFactory for getting connections with the database.
     */
    public DatabaseController(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Checks if a user exists in the database.
     *
     * @param username of the user.
     * @return true if exists, else false.
     */
    public boolean userExists(final String username) {
        try {
            String query = "select Username from User where Username = ?";
            Connection connection = connectionFactory.getConnection();
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
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves user from database.
     * @param username name of the user.
     * @return user to retrieve.
     * @throws Exception for sql statement
     */
    public User getUser(String username) {
        assert (userExists(username));
        try {
            String sql = "SELECT username , password FROM User WHERE username = ?";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet =  preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    userDefine = new User(resultSet.getString("username"),
                            resultSet.getString("password"));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDefine;
    }


    /**
     * Returns hashed password of the user.
     *
     * @param username of the user.
     * @return hashed password of the user.
     */
    public String getHashedPassword(final String username) {
        assert userExists(username);
        try {
            String query = "select password from User where username = ?";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    password = resultSet.getString("password");
                    return password;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * Adds a user to the database.
     *
     * @param username       of the user.
     * @param hashedPassword of the user.
     */
    public void createUser(final String username, final String hashedPassword) {
        try {
            String queryUser = "insert into User (Username, Password) VALUES (?,?)";
            String queryScore = "insert into Score (username, score, chosen_name) VALUES (?,?,?)";
            Connection connection = connectionFactory.getConnection();
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
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets score of a user from the database.
     * @param username of the user.
     * @return the score of the user.
     */
    public Score getScore(String username) {
        assert userExists(username);
        try {
            String query = "select * from Score where username = ?";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    score = new Score(resultSet.getString("username"),
                            resultSet.getInt("score"), resultSet.getString("chosen_name"));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }


    /**
     * Updates the score of a user.
     *
     * @param username of the user.
     */
    public void updateScore(String username, int score) {
        assert userExists(username);
        try {
            String query = "update Score SET score = ? where Username = ?";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setInt(1, score);
                preparedStatement.setString(2, username);
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves score of a certain user.
     * @param username of user
     * @return score of game
     * @throws Exception from sql
     */
    public int getPoints(String username) {
        assert (userExists(username));
        try {
            String sql = "SELECT score FROM Score WHERE username = ?";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try {
                preparedStatement.setString(1,username);
                ResultSet resultSet =  preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    points = resultSet.getInt("score");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }


    /**
     * Gets score of all users from the database.
     *
     * @return the list of player objects, containing usernames and scores.
     */
    public List<Player> getTopNScores(int n) {
        List<Player> players = new LinkedList<Player>();
        try {
            String query =
                    "SELECT username, score, chosen_name FROM Score ORDER BY score DESC LIMIT = ?";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    while (resultSet.next()) {
                        String username = resultSet.getString("chosen_name");
                        int score = resultSet.getInt("score");
                        Player player = new Player(username,score);
                        players.add(player);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Getter for connection.
     * @return connection with the database.
     */
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * Setter for connection.
     * @param connectionFactory value to set.
     */
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
