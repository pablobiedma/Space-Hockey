package com.mygdx.airhockey.database.tables;

import java.util.Objects;

public class Score {
    int gameId;
    private String username;
    private int points;
    private String chosenName;

    /**
     * Default constructor for score.
     * @param username name of user
     * @param points score of player
     * @param chosenName name player chose for score board
     */
    public Score(int gameId, String username, int points, String chosenName) {
        this.gameId = gameId;
        this.username = username;
        this.points = points;
        this.chosenName = chosenName;
    }


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getChosenName() {
        return chosenName;
    }

    public void setChosenName(String chosenName) {
        this.chosenName = chosenName;
    }

    @Override
    public String toString() {
        return "Score{"
                + "GameId='" + gameId + '\''
                + ", Username='" + username + '\''
                + ", Score='" + points + '\''
                + ", Chosen name='" + chosenName + '\''
                + '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(gameId, username, points, chosenName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return equalsHelp(score);
    }

    /**
     * Helping method for equals method.
     * @param score the score to be compared.
     * @return true if equal, false otherwise.
     */
    private boolean equalsHelp(Score score) {
        return gameId == score.getGameId()
                && username.equals(score.getUsername())
                && points == score.getPoints()
                && chosenName.equals(score.getChosenName());
    }
}
