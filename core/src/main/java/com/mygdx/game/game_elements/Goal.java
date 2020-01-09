package com.mygdx.game.game_elements;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.game_backend.Config;

public class Goal {
    private int score;
    private Rectangle collisionBox;
    /**
     * Sets score to 0.
     */
    public Goal(float x, float y) {
        score = 0;
        collisionBox = new Rectangle(x,
                y,
                Config.WALL_THICKNESS,
                Config.VIEWPORT_SIZE);
        collisionBox.setWidth(.8f);
        collisionBox.setHeight(Config.WALL_HEIGHT - 0.5f);
    }


    public Rectangle getCollisionBox() {
        return this.collisionBox;
    }

    /**
     * Increments score.
     */
    public void incrementScore() {
        this.score++;
    }

    /**
     * Gets score.
     * @return score
     */
    public int getScore() {
        return score;
    }
}
