package com.mygdx.airhockey.backend;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.elements.Walls;

/**
 * Class that handles the backend of the game.
 */
public class GameOperator {
    Pitch pitch;
    Paddle redPaddle;
    Paddle bluePaddle;
    Puck puck;
    Walls walls;

    /**
     * Constructor for game operator.
     * @param pitch pitch for the game.
     * @param redPaddle in the game.
     * @param bluePaddle in the game.
     * @param puck in the game.
     * @param walls in the game.
     */
    public GameOperator(Pitch pitch, Paddle redPaddle, Paddle bluePaddle, Puck puck, Walls walls) {
        this.pitch = pitch;
        this.redPaddle = redPaddle;
        this.bluePaddle = bluePaddle;
        this.puck = puck;
        this.walls = walls;
    }

    /**
     * Set's up a new game.
     */
    public GameOperator(World world) {
        redPaddle = new Paddle(world, Config.RED_PADDLE_TEXTURE_PATH,
                CoordinateTranslator.translateSize(2 * Config.PADDLE_RADIUS),
                Config.RED_PADDLE_X, 0, Config.RED_PADDLE_KEYS);

        bluePaddle = new Paddle(world, Config.BLUE_PADDLE_TEXTURE_PATH,
                CoordinateTranslator.translateSize(2 * Config.PADDLE_RADIUS),
                Config.BLUE_PADDLE_X, 0, Config.BLUE_PADDLE_KEYS);
        puck = new Puck(world, Config.PUCK_TEXTURE_PATH,
                CoordinateTranslator.translateSize(2 * Config.PUCK_RADIUS), 0, 0);

        walls = new Walls(world, Config.WALL_WIDTH, Config.WALL_HEIGHT, Config.WALL_THICKNESS);
        pitch = new Pitch();
    }

    /**
     * Updates physics of the game.
     */
    public void updatePhysics() {
        bluePaddle.updateVelocity();
        redPaddle.updateVelocity();
    }

    /**
     * Draws sprites on a batch.
     * @param batch to draw on.
     */
    public void drawSprites(Batch batch) {
        batch.begin();

        pitch.draw(batch);
        puck.draw(batch);
        redPaddle.draw(batch);
        bluePaddle.draw(batch);

        batch.end();
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Paddle getRedPaddle() {
        return redPaddle;
    }

    public void setRedPaddle(Paddle redPaddle) {
        this.redPaddle = redPaddle;
    }

    public Paddle getBluePaddle() {
        return bluePaddle;
    }

    public void setBluePaddle(Paddle bluePaddle) {
        this.bluePaddle = bluePaddle;
    }

    public Puck getPuck() {
        return puck;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    public Walls getWalls() {
        return walls;
    }

    public void setWalls(Walls walls) {
        this.walls = walls;
    }
}
