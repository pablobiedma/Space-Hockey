package com.mygdx.airhockey.database.tables;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {
    String password = "password";
    String username = "test";
    transient String wrong = "wrong";
    transient User user = new User(username, password);
    transient User userSet = new User(username, password);

    @Test
    void getUsername() {
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertNotEquals(wrong, user.getUsername());
    }

    @Test
    void setUsername() {
        userSet.setUsername("abc");
        Assertions.assertEquals("abc", userSet.getUsername());
    }

    @Test
    void getPassword() {
        Assertions.assertEquals(password, user.getPassword());
    }

    @Test
    void setPassword() {
        userSet.setPassword("pass");
        Assertions.assertEquals("pass", userSet.getPassword());
    }

    @Test
    void testToString() {
        String string = "User{, Username='test', Password='password'}";
        Assertions.assertEquals(string, user.toString());
    }

    @Test
    void testHashCode() {
        User x = new User("aap", "noot");  // equals and hashCode check name field value
        User y = new User("aap", "noot");
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    void testEquals() {
        User userSame = new User(username, password);
        User userWrong = new User(wrong, wrong);
        Assertions.assertFalse(user.equals(userWrong));
        Assertions.assertTrue(user.equals(userSame));
        Assertions.assertTrue(user.equals(user));
        Score score = new Score("abc", 4, "def");
        Assertions.assertFalse(user.equals(score));
    }

}