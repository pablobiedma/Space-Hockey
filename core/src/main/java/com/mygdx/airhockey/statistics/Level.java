package com.mygdx.airhockey.statistics;

public class Level {
    //constants regarding scoring and game rules
    private static final int MAX_GOALS = 7;
    private static final double POINTS_PER_GOAL_SCORED = 200;
    private static final double POINTS_PER_GOAL_CONCEDED = -100;
    private static final double POINTS_PER_WIN = 500;
    private static final double POINTS_PER_LOSS = -300;
    //fields
    private double score = 0;
    private boolean started;
    private boolean finished;
    private int leftGoal = 0;
    private int rightGoals = 0;

    private Player player;

    /**
     * Constructor. Creates a new Level.
     */
    public Level(Player player) {
        this.started = false;
        this.finished = false;
        this.player = player;
    }

    /**
     * Starts the game.
     */
    public void start() {
        started = true;
    }

    /**
     * Getter for player.
     * @return player for the level.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter for the player.
     * @param player value to set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Getter for score.
     * @return score for current level.
     */
    public double getScore() {
        return score;
    }

    /**
     * Setter for score.
     * @param score value to set.
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Tells us if the game is started.
     * @return true if it is, else false.
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Tells us if the game is finished.
     * @return true if finished, else false.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Getter for player goals.
     * @return player's score.
     */
    public int getLeftGoals() {
        return leftGoal;
    }

    /**
     * Getter for ai goals.
     * @return ai's score.
     */
    public int getRightGoals() {
        return rightGoals;
    }

    /**
     * Setter for game in progress.
     * @param started value to set.
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * Setter for finished.
     * @param finished value to set.
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Setter for player goals.
     * @param leftGoal value to set.
     */
    public void setLeftGoal(int leftGoal) {
        this.leftGoal = leftGoal;
    }

    /**
     * Setter for aiGoals.
     * @param rightGoals value to set.
     */
    public void setRightGoals(int rightGoals) {
        this.rightGoals = rightGoals;
    }

    /**
     * Increases the players score.
     */
    public void goalLeft() {
        leftGoal++;
        score += POINTS_PER_GOAL_SCORED;
        checkIfFinished();
    }

    /**
     * Increases the ai score.
     */
    public void goalRight() {
        rightGoals++;
        score += POINTS_PER_GOAL_CONCEDED;
        checkIfFinished();
    }

    /**
     * Checks if the game is finished.
     * If yes, changes the values of inProgress and finished fields.
     */
    private void checkIfFinished() {
        if (leftGoal >= MAX_GOALS) {
            score += POINTS_PER_WIN;
            finalizeGame();
        } else if (rightGoals >= MAX_GOALS) {
            score += POINTS_PER_LOSS;
            finalizeGame();
        }
    }

    /**
     * Finalizes the game.
     * Updates database, updates player points.
     */
    private void finalizeGame() {
        //        player.addDatabaseScore(score);
        // Maciek needs to adjust that, that's why it is not deleted.
        finished = true;
    }

    public int getLeftGoal() {
        return leftGoal;
    }
}

