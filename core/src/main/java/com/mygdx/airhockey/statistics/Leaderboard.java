package com.mygdx.airhockey.statistics;

import com.mygdx.airhockey.database.DatabaseController;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<Player> players;

    /**
     *Instantiates a leaderboard.
     * @param database to read the scores from.
     * @param n number of top scores you want to retrieve
     */
    public Leaderboard(DatabaseController database, int n) {
        players = database.getTopNScores(n);
        for (Player player : players) {
            System.out.println(player.getUsername() + " " + player.getPoints());
        }
    }

    ///**
    // * Produces a leaderboard.
    // * @param n size of the leaderboard.
    // * @return a list of players.
    // */
    //public List<Player> makeLeaderboard(int n) {
    //    //players.sort(Comparator.comparing(Player::getPoints).reversed());
    //    return players.subList(0, Math.min(players.size(), n));
    //}

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
