package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(0, level.getPlayerGoals());
    }

    @Test
    void getAiGoals() {
        assertEquals(0, level.getAiGoals());
    }

    @Test
    void goalFor() {
        level.goalFor();
        assertEquals(1, level.getPlayerGoals());
    }

    @Test
    void goalAgainst() {
        level.goalAgainst();
        assertEquals(1, level.getAiGoals());
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
        for(int i = 0; i < 7; i++) {
            level.goalFor();
        }
        assertTrue(level.isFinished());
    }

    @Test
    void aiWins() {
        for(int i = 0; i < 7; i++) {
            level.goalAgainst();
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
        level.setPlayerGoals(5);
        assertEquals(level.getPlayerGoals(), 5);
    }

    @Test
    void setAiGoals() {
        level.setAiGoals(5);
        assertEquals(level.getAiGoals(),5);
    }

    @Test
    void start() {
        level.start();
        assertTrue(level.isStarted());
    }
}
