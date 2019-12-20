package com.mygdx.airhockey.database;

import com.mygdx.airhockey.database.tables.Score;
import com.mygdx.airhockey.database.tables.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mygdx.airhockey.statistics.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
//import org.mockito.*;

class DatabaseControllerTest {

    transient String test = "test";
    transient String password = "n4bQgYhMfWWaL+qgxVrQFaO/TxsrC4Is0V1sFbDwCgg=";

    @Mock
    transient PreparedStatement preparedStatement;

    @Mock
    transient ResultSet resultSet;

    @Mock
    transient ConnectionFactory connectionFactory;

    @Mock
    transient Connection conn;

    @InjectMocks
    transient DatabaseController databaseMethods;


    @BeforeEach
    void setupTestEnvironment() throws SQLException {
        MockitoAnnotations.initMocks(this);
        connectionFactory = Mockito.mock(ConnectionFactory.class);
        conn = Mockito.mock(Connection.class);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);
        databaseMethods = new DatabaseController(new ConnectionFactory());
        Mockito.when(connectionFactory.getConnection()).thenReturn(conn);
        Mockito.when(conn.prepareStatement(Matchers.anyString())).thenReturn(preparedStatement);
        Mockito.doReturn(resultSet).when(preparedStatement).executeQuery();
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(databaseMethods);
    }

    @Test
    void userExistsTest() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true);
        Assertions.assertTrue(databaseMethods.userExists("hello"));
        Mockito.when(resultSet.next()).thenReturn(false);
        Assertions.assertFalse(databaseMethods.userExists("----------"));
    }

    @Test
    void getUserTest() throws SQLException {
        User user = new User(test, password);

        Mockito.when(resultSet.getString("username")).thenReturn(test);
        Mockito.when(resultSet.getString("password")).thenReturn(password);

        Assertions.assertEquals(user, databaseMethods.getUser(test));
    }

    @Test
    public void getPasswordHashTest() throws SQLException {
        User user2 = new User(test,password);

        Mockito.when(resultSet.getString("password")).thenReturn(password);
        //Mockito.when(resultSet.next()).thenReturn(true);
        Assertions.assertEquals(user2.getPassword(), databaseMethods.getHashedPassword(test));
    }

    @Test
    public void createUserTest() throws SQLException {
        String username = test;
        String hashedPassword = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg";
        databaseMethods.createUser(username, hashedPassword);
        Mockito.when(resultSet.next()).thenReturn(true);
        Assertions.assertTrue(databaseMethods.userExists(username));
    }

    @Test
    void getConnection() {
        Assertions.assertNotNull(databaseMethods.getConnectionFactory());
    }

    @Test
    void setConnection() {
        databaseMethods.setConnectionFactory(null);
        Assertions.assertNull(databaseMethods.getConnectionFactory());
    }

    @Test
    void getPoints() throws SQLException {
        int points = 19;

        Mockito.when(resultSet.getInt(1)).thenReturn(points);
        Assertions.assertEquals(points, databaseMethods.getPoints(test));
    }

    @Test
    void updateScore() throws SQLException {
        int points = 21;

        Mockito.when(resultSet.getInt(1)).thenReturn(points);
        databaseMethods.updateScore("nick", points);
        Mockito.when(resultSet.next()).thenReturn(true);
        Assertions.assertEquals(points, databaseMethods.getPoints("nick"));
    }

    @Test
    void getScore() throws SQLException {
        final Score score = new Score(test, 19, test);

        Mockito.when(resultSet.getString("username")).thenReturn(test);
        Mockito.when(resultSet.getInt("score")).thenReturn(19);
        Mockito.when(resultSet.getString("chosen_name")).thenReturn(test);
        Mockito.when(resultSet.next()).thenReturn(true);
        Assertions.assertEquals(score, databaseMethods.getScore(test));
    }

    @Test
    void getTopNScores() throws SQLException {
        List<Player> players = new LinkedList<Player>();
        players.add(new Player("nick", 21));
        players.add(new Player("something", 19));
        players.add(new Player("test", 19));
        //Mockito.when(resultSet.getInt("score")).thenReturn(19);
        //Mockito.when(resultSet.getString("chosen_name")).thenReturn(test);
        //Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        //Mockito.when(resultSet.getString("chosen_name")).thenReturn(test);
        //databaseMethods.getTopNScores(3);
        //Mockito.verify(resultSet, Mockito.times(3)).getString("chosen_name");
        Assertions.assertEquals(players, databaseMethods.getTopNScores(3));
    }

}
