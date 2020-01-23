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
        Mockito.verify(conn, Mockito.times(2)).close();
    }

    @Test
    void getUserTest() throws SQLException {
        User user = new User(test, password);

        Mockito.when(resultSet.getString("username")).thenReturn(test);
        Mockito.when(resultSet.getString(pass)).thenReturn(password);

        Assertions.assertEquals(user, databaseMethods.getUser(test));
        Mockito.verify(resultSet,Mockito.times(1)).getString("username");
        Mockito.verify(resultSet,Mockito.times(1)).getString(pass);
        Mockito.verify(conn, Mockito.times(2)).close();
    }

    @Test
    public void getPasswordHashTest() throws SQLException {
        User user = new User(test,password);

        Mockito.when(resultSet.getString(pass)).thenReturn(password);

        Assertions.assertEquals(user.getPassword(), databaseMethods.getHashedPassword(test));
        Mockito.verify(resultSet,Mockito.times(1)).getString(pass);
        Mockito.verify(conn, Mockito.times(2)).close();
    }

    @Test
    public void createUserTest() throws SQLException {
        String username = test;
        String hashedPassword = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg";
        databaseMethods.createUser(username, hashedPassword);
        Mockito.verify(preparedStatement,Mockito.times(2)).setString(1, username);
        Mockito.when(resultSet.next()).thenReturn(true);
        Assertions.assertTrue(databaseMethods.userExists(username));
        Mockito.verify(conn, Mockito.times(3)).close();

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
    void addScore() throws SQLException {
        float points = 21;

        Mockito.when(resultSet.getFloat(score)).thenReturn(points);
        databaseMethods.addScore(nick, points, nick);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.verify(preparedStatement,Mockito.times(1)).setFloat(2, points);
        Mockito.verify(preparedStatement,Mockito.times(2)).setString(1,nick);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3,nick);
        Mockito.verify(preparedStatement,Mockito.times(1)).execute();
        Mockito.verify(conn,Mockito.times(2)).close();
    }

    @Test
    void getScore() throws SQLException {
        databaseMethods.getPersonalTopScore(test);
        Mockito.verify(conn, Mockito.times(2)).close();
    }

    @Test
    void getPoints() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true)
                .thenReturn(true).thenReturn(false);
        databaseMethods.getPoints(test);

        Mockito.verify(resultSet,Mockito.times(4)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getFloat(score);
        Mockito.verify(conn, Mockito.times(2)).close();
    }

    @Test
    void getTopNScores() throws SQLException {
        boolean t = true;
        boolean f = false;
        Mockito.when(resultSet.next()).thenReturn(t).thenReturn(t).thenReturn(t).thenReturn(f);
        databaseMethods.getTopNScores(3);

        Mockito.verify(resultSet,Mockito.times(4)).next();
        Mockito.verify(resultSet, Mockito.times(3)).getString("chosen_name");
        Mockito.verify(resultSet, Mockito.times(3)).getFloat(score);
        Mockito.verify(conn, Mockito.times(1)).close();
    }

}
