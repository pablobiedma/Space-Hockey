package com.mygdx.airhockey.database.tables;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ScoreTest {
    int gameId = 4;
    String username = "aap";
    int points = 1;
    String chosenName = "noot";
    transient Score score = new Score(gameId, username, points, chosenName);
    transient Score scoreSet = new Score(gameId, username, points, chosenName);
    transient String wrong = "wrong";
    transient String mies = "mies";

    @Test
    void getGameId() {
        Assertions.assertEquals(gameId, score.getGameId());
    }

    @Test
    void setGameId() {
        scoreSet.setGameId(2);
        Assertions.assertEquals(2, scoreSet.getGameId());
    }

    @Test
    void getUsername() {
        Assertions.assertEquals(username, score.getUsername());
    }

    @Test
    void setUsername() {
        scoreSet.setUsername(mies);
        Assertions.assertEquals(mies, scoreSet.getUsername());
    }

    @Test
    void getPoints() {
        Assertions.assertEquals(points, score.getPoints());
    }

    @Test
    void setPoints() {
        scoreSet.setPoints(3);
        Assertions.assertEquals(3, scoreSet.getPoints());
    }

    @Test
    void getChosenName() {
        Assertions.assertEquals(chosenName, scoreSet.getChosenName());
    }

    @Test
    void setChosenName() {
        scoreSet.setChosenName(mies);
        Assertions.assertEquals(mies, scoreSet.getChosenName());
    }

    @Test
    void testToString() {
        String string = "Score{GameId='4', Username='aap', Score='1', Chosen name='noot'}";
        Assertions.assertEquals(string, score.toString());
    }

    @Test
    void testHashCode() {
        Score x = new Score(gameId, username,points, chosenName);
        Score y = new Score(gameId, username, points,chosenName);
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    void testEquals() {
        Score scoreSame = new Score(gameId, username, points, chosenName);
        Score scoreWrong = new Score(8, wrong,0, wrong);
        User user = new User("abc", "def");
        Assertions.assertFalse(score.equals(scoreWrong));
        Assertions.assertTrue(score.equals(scoreSame));
        Assertions.assertFalse(score.equals(user));
        Assertions.assertTrue(score.equals(score));
    }

}