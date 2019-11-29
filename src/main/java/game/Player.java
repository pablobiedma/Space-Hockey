package game;

import database.DatabaseController;

public class Player {
    private int points;
    private String username;

    /**
     * Constructor for player.
     * @param username for the player.
     * @param points the player has.
     */
    public Player(String username, int points) {
        this.username = username;
        this.points = points;
    }

    /**
     * Getter for points.
     * @return points of the user.
     */
    public double getPoints() {
        return points;
    }

    /**
     * Setter for points.
     * @param points value to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Getter for username.
     * @return username of the player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     * @param username value to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Updates user's points.
     * @param amount to add to current score.
     */
    public void updatePoints(double amount) {
        points += amount;
    }

    /**
     * Updates the score in the database.
     * @param database to update.
     */
    public void updateDatabaseScore(DatabaseController database) {
        database.updateScore(username, points);
    }
}
