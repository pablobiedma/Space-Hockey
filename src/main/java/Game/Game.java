package Game;

public class Game {
    private Level level;
    private Player player;

    /**
     * Constructor for game.
     * @param player that plays the game.
     */
    public Game(Player player) {
        this.player = player;
        this.level = new Level();
    }

    /**
     * Getter for level.
     * @return current game's level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Setter for level.
     * @param level value to set.
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Getter for player.
     * @return player that plays the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter for player.
     * @param player value to set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
