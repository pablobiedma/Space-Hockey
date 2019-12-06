package com.mygdx.game.game_backend;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.game_elements.Paddle;
import com.mygdx.game.game_elements.Pitch;
import com.mygdx.game.game_elements.Puck;
import com.mygdx.game.game_elements.Walls;

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
     * Set's up a new game.
     * @param world in which the game will be set up.
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

        walls = new Walls (world, Config.WALL_WIDTH, Config.WALL_HEIGHT, Config.WALL_THICKNESS);
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
}
