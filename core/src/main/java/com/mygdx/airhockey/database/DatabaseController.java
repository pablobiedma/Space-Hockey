package com.mygdx.airhockey.database;

import com.mygdx.airhockey.database.tables.Score;
import com.mygdx.airhockey.database.tables.User;
import com.mygdx.airhockey.statistics.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseController {
    private ConnectionFactory connectionFactory;

    transient Score score = null;
    transient User userDefine = null;
    transient int points = 0;
    transient String password = null;
    transient boolean exists = false;

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
                    exists = resultSet.next();
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
        return exists;
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
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);
            try {
                preparedStatementUser.setString(1, username);
                preparedStatementUser.setString(2, hashedPassword);
                if (!userExists(username)) {
                    preparedStatementUser.execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatementUser.close();
                //                preparedStatementScore.close();
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
    public int getPersonalTopScore(String username) {
        assert userExists(username);
        try {
            String query = "select * from Score where username = ? ORDER BY score DESC";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    score = new Score(resultSet.getInt("game_id"), resultSet.getString("username"),
                            resultSet.getInt("score"), resultSet.getString("chosen_name"));
                    score.getPoints();
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
        return score.getPoints();
    }


    /**
     * Adds score to the database.
     * @param username username of current player.
     * @param score score of the last game.
     * @param chosenName name player has chosen for the leaderboard.
     */
    public void addScore(String username, int score, String chosenName) {
        assert userExists(username);
        try {
            String query =
                    " insert into Score (username, score, chosen_name) VALUES (?,?,?)";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, score);
                preparedStatement.setString(3,chosenName);
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
    public List<Integer> getPoints(String username) {
        assert (userExists(username));
        List<Integer> scores = new ArrayList<>();
        try {
            String sql = "SELECT score FROM Score WHERE username = ? ORDER BY score DESC";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try {
                preparedStatement.setString(1,username);
                ResultSet resultSet =  preparedStatement.executeQuery();
                try {
                    while (resultSet.next()) {
                        points = resultSet.getInt("score");
                        scores.add(points);
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
        return scores;
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
                    "SELECT username, score, chosen_name FROM Score ORDER BY score DESC LIMIT ?";
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setInt(1,n);
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
