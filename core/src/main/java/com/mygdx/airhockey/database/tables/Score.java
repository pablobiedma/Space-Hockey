package com.mygdx.airhockey.database.tables;

import java.util.Objects;

public class Score {
    private String username;
    private int points;
    private String chosenName;

    /**
     * Default constructor for score.
     * @param username name of user
     * @param points score of player
     * @param chosenName name player chose for score board
     */
    public Score(String username, int points, String chosenName) {
        this.username = username;
        this.points = points;
        this.chosenName = chosenName;
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
                + ", Username='" + username + '\''
                + ", Score='" + points + '\''
                + ", Chosen name='" + chosenName + '\''
                + '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(username, points, chosenName);
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
        return username.equals(score.getUsername())
                && points == score.getPoints()
                && chosenName.equals(score.getChosenName());
    }

}