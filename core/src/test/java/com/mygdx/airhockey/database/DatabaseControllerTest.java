package com.mygdx.airhockey.database;

import com.mygdx.airhockey.database.tables.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DatabaseControllerTest {

    transient String test = "test";
    transient String password = "n4bQgYhMfWWaL+qgxVrQFaO/TxsrC4Is0V1sFbDwCgg=";
    transient String nick = "nick";
    transient String score = "score";
    transient String pass = "password";

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
        databaseMethods = new DatabaseController(connectionFactory);
        Mockito.when(connectionFactory.getConnection()).thenReturn(conn);
        Mockito.when(conn.prepareStatement(Matchers.anyString())).thenReturn(preparedStatement);
        Mockito.doReturn(resultSet).when(preparedStatement).executeQuery();
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

    }

    @AfterEach
    void stop() throws SQLException {
        conn.close();
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
        Mockito.verify(resultSet,Mockito.times(2)).next();
    }

    @Test
    void getUserTest() throws SQLException {
        User user = new User(test, password);

        Mockito.when(resultSet.getString("username")).thenReturn(test);
        Mockito.when(resultSet.getString(pass)).thenReturn(password);

        Assertions.assertEquals(user, databaseMethods.getUser(test));
        Mockito.verify(resultSet,Mockito.times(1)).getString("username");
        Mockito.verify(resultSet,Mockito.times(1)).getString(pass);
    }

    @Test
    public void getPasswordHashTest() throws SQLException {
        User user = new User(test,password);

        Mockito.when(resultSet.getString(pass)).thenReturn(password);

        Assertions.assertEquals(user.getPassword(), databaseMethods.getHashedPassword(test));
        Mockito.verify(resultSet,Mockito.times(1)).getString(pass);
    }

    @Test
    public void createUserTest() throws SQLException {
        String username = test;
        String hashedPassword = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg";
        databaseMethods.createUser(username, hashedPassword);
        Mockito.verify(preparedStatement,Mockito.times(1)).setInt(2, 0);
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

        Mockito.when(resultSet.getInt(score)).thenReturn(points);
        Assertions.assertEquals(points, databaseMethods.getPoints(test));
        Mockito.verify(resultSet, Mockito.times(1)).getInt(score);
    }

    @Test
    void updateScore() throws SQLException {
        int points = 21;

        Mockito.when(resultSet.getInt(score)).thenReturn(points);
        databaseMethods.updateScore(nick, points);
        Mockito.when(resultSet.next()).thenReturn(true);
        Assertions.assertEquals(points, databaseMethods.getPoints(nick));

        Mockito.verify(preparedStatement,Mockito.times(1)).setInt(1, points);
        Mockito.verify(preparedStatement,Mockito.times(1)).setString(2,nick);
        Mockito.verify(preparedStatement,Mockito.times(1)).execute();
    }

    @Test
    void getScore() throws SQLException {
        databaseMethods.getScore(test);
        Mockito.verify(resultSet, Mockito.times(1)).getString("username");
        Mockito.verify(resultSet, Mockito.times(1)).getInt(score);
        Mockito.verify(resultSet, Mockito.times(1)).getString("chosen_name");
    }

    @Test
    void getTopNScores() throws SQLException {
        boolean t = true;
        boolean f = false;
        Mockito.when(resultSet.next()).thenReturn(t).thenReturn(t).thenReturn(t).thenReturn(f);
        databaseMethods.getTopNScores(3);

        Mockito.verify(resultSet,Mockito.times(4)).next();
        Mockito.verify(resultSet, Mockito.times(3)).getString("chosen_name");
        Mockito.verify(resultSet, Mockito.times(3)).getInt(score);
    }

}
