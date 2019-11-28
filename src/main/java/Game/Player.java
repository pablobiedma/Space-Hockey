package Game;

public class Player {
    private double points;
    private String username;

    /**
     * Constructor for player's class.
     * @param username for the player.
     * @param points of the player.
     */
    public Player(String username, double points) {
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
    public void setPoints(double points) {
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
}
