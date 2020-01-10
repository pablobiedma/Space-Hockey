package com.mygdx.airhockey.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mygdx.airhockey.database.DatabaseController;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class LeaderboardTest {



    private transient DatabaseController databaseController =
            Mockito.mock(DatabaseController.class);
    transient Leaderboard leaderboard = null;

    @Test
    void makeLeaderboard() {
        leaderboard = new Leaderboard(databaseController,3);
        leaderboard.getPlayers();
        Mockito.verify(databaseController, Mockito.times(1)).getTopNScores(3);
    }

    @Test
    void getPlayers() {
        leaderboard = new Leaderboard(databaseController,3);
        assertNotNull(leaderboard.getPlayers());
    }

    @Test
    void setPlayers() {
        List<Player> players = new LinkedList<>();
        leaderboard = new Leaderboard(databaseController,3);

        leaderboard.setPlayers(players);
        assertEquals(players, leaderboard.getPlayers());
    }
}