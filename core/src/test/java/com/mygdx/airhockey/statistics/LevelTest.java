package com.mygdx.airhockey.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LevelTest {

    private transient Level level;
    private transient Player player;

    @BeforeEach
    void setupTestEnvironment() {
        player = new Player("test", 0);
        level = new Level(player);
    }

    @Test
    void testConstructor() {
        assertNotNull(level);
    }

    @Test
    void isInProgress() {
        assertFalse(level.isStarted());
    }

    @Test
    void isFinished() {
        assertFalse(level.isFinished());
    }

    @Test
    void getPlayerGoals() {
        assertEquals(0, level.getLeftGoals());
    }

    @Test
    void getAiGoals() {
        assertEquals(0, level.getRightGoals());
    }

    @Test
    void goalFor() {
        level.goalLeft();
        assertEquals(1, level.getLeftGoals());
    }

    @Test
    void goalAgainst() {
        level.goalRight();
        assertEquals(1, level.getRightGoals());
    }

    @Test
    void getPlayer() {
        assertEquals(player, level.getPlayer());
    }

    @Test
    void setPlayer() {
        Player player2 = new Player("some_player", 10);
        level.setPlayer(player2);
        assertEquals(player2, level.getPlayer());
    }

    @Test
    void playerWins() {
        for (int i = 0; i < 7; i++) {
            level.goalLeft();
        }
        assertTrue(level.isFinished());
    }

    @Test
    void aiWins() {
        for (int i = 0; i < 7; i++) {
            level.goalRight();
        }
        assertTrue(level.isFinished());
    }

    @Test
    void setInProgress() {
        level.setStarted(false);
        assertFalse(level.isStarted());
    }

    @Test
    void setFinished() {
        level.setFinished(true);
        assertTrue(level.isFinished());
    }

    @Test
    void setPlayerGoals() {
        level.setLeftGoal(5);
        assertEquals(level.getLeftGoals(), 5);
    }

    @Test
    void setAiGoals() {
        level.setRightGoals(5);
        assertEquals(level.getRightGoals(),5);
    }

    @Test
    void start() {
        level.start();
        assertTrue(level.isStarted());
    }

    @Test
    void getScore() {
        assertEquals(0, level.getScore());
    }

    @Test
    void setScore() {
        level.setScore(1);
        assertEquals(1, level.getScore());
    }
}
