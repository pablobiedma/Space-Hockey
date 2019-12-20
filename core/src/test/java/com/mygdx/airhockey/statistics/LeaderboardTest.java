package com.mygdx.airhockey.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mygdx.airhockey.database.DatabaseController;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

class LeaderboardTest {

    private transient DatabaseController database;
    private transient Leaderboard leaderboard;
    private transient List<Player> top3;

    @BeforeEach
    void setUp() {
        List<Player> players = new LinkedList<Player>();
        players.add(new Player("test1", 10));
        players.add(new Player("test2", 20));
        players.add(new Player("test3", 30));
        //players.add(new Player("test4", 40));



        database = Mockito.mock(DatabaseController.class);
        Mockito.when(database.getTopNScores(Matchers.anyInt())).thenReturn(players);
        leaderboard = new Leaderboard(database, 3);
        top3 = leaderboard.getPlayers();

    }

    @Test
    void makeLeaderboard() {
        assertEquals(top3.size(), 3);
    }

    //@Test
    //void makeLeaderboardTooLarge() {
    //    List<Player> top5 = leaderboard.makeLeaderboard(5);
    //    assertEquals(top5.size(), 3);
    //}

    //@Test
    //void makeLeaderboardTopScoreCheck() {
    //    assertEquals(30, top3.get(0).getPoints());
    //    assertEquals("test4", top3.get(0).getUsername());
    //}

    @Test
    void getPlayers() {
        assertNotNull(leaderboard.getPlayers());
    }

    @Test
    void setPlayers() {
        List<Player> players = new LinkedList<>();
        leaderboard.setPlayers(players);
        assertEquals(players, leaderboard.getPlayers());
    }
}