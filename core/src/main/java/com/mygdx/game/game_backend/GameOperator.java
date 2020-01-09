package com.mygdx.game.game_backend;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.game_elements.Paddle;
import com.mygdx.game.game_elements.Pitch;
import com.mygdx.game.game_elements.Puck;
import com.mygdx.game.game_elements.Walls;
import com.mygdx.game.game_elements.Goal;

/**
 * Class that handles the backend of the game.
 */
public class GameOperator {
    Pitch pitch;
    Paddle redPaddle;
    Paddle bluePaddle;
    Puck puck;
    Walls walls;

    Goal goal1;
    Goal goal2;


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

        walls = new Walls(world, Config.WALL_WIDTH, Config.WALL_HEIGHT, Config.WALL_THICKNESS);
        pitch = new Pitch();

        goal1 = new Goal(-Config.WALL_WIDTH - 1,
                -Config.WALL_HEIGHT / 2);
        goal2 = new Goal(Config.WALL_WIDTH + 1,
                -Config.WALL_HEIGHT / 2);
    }

    /**
     * Updates physics of the game.
     */
    public void updatePhysics() {
        bluePaddle.updateVelocity();
        redPaddle.updateVelocity();


        handleCollisions();
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

    /**
     * Gets score of one of the goals.
     * @param goalId 1 or 2
     * @return the score of given goal.
     */
    public int getScores(int goalId) {
        if (goalId == 1) {
            return goal1.getScore();
        }
        return goal2.getScore();
    }

    /**
     * Handles collision between puck and goals.
     */
    public void handleCollisions() {
        if (Intersector.overlaps(puck.createCollider(), goal1.getCollisionBox())
                || Intersector.overlaps(puck.createCollider(), goal2.getCollisionBox())) {
            if (Intersector.overlaps(puck.createCollider(), goal1.getCollisionBox())) {
                goal2.incrementScore();
            }

            if (Intersector.overlaps(puck.createCollider(), goal2.getCollisionBox())) {
                goal1.incrementScore();
            }

            puck.stopAndSetToMiddle();
            redPaddle.stopAndSetToPosition(-Config.WALL_WIDTH + 3f, 0);
            bluePaddle.stopAndSetToPosition(Config.WALL_WIDTH - 3f, 0);
        }
    }
}
