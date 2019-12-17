package com.mygdx.airhockey.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.mygdx.airhockey.database.DatabaseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class PlayerTest {
    @Mock
    private transient DatabaseController database;

    private transient Player player;

    @BeforeEach
    void setUp() {
        player = new Player("test", 0);
    }

    @Test
    void getPoints() {
        assertEquals(0, player.getPoints());
    }

    @Test
    void setPoints() {
        player.setPoints(100);
        assertEquals(100,player.getPoints());
    }

    @Test
    void getUsername() {
        assertEquals("test", player.getUsername());
    }

    @Test
    void setUsername() {
        player.setUsername("abc");
        assertEquals("abc", player.getUsername());
    }

    @Test
    void updatePoints() {
        player.updatePoints(50);
        assertEquals(50, player.getPoints());
    }

    @Test
    void updateDatabaseScore() {
        database = Mockito.mock(DatabaseController.class);
        player.updateDatabaseScore(database);
        verify(database, times(1)).updateScore(anyString(), anyInt());
    }
}