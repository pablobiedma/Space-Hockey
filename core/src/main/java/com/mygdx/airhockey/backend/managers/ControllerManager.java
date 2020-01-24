package com.mygdx.airhockey.backend.managers;

import static com.mygdx.airhockey.backend.generators.BodyGenerator.makePaddle;
import static com.mygdx.airhockey.backend.generators.BodyGenerator.makePuck;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.movement.AiMovementController;
import com.mygdx.airhockey.movement.KeyboardController;
import com.mygdx.airhockey.movement.MovementController;





public class ControllerManager {
    private static Config config = Config.getInstance();
    private transient Paddle redPaddle;
    private transient Paddle bluePaddle;
    private transient Puck puck;

    /**
     * Basic constructor.
     * @param redPaddle redpaddle.
     * @param bluePaddle bluepaddle.
     * @param puck puck.
     */
    public ControllerManager(Paddle redPaddle, Paddle bluePaddle, Puck puck) {
        this.redPaddle = redPaddle;
        this.bluePaddle = bluePaddle;
        this.puck = puck;
    }

    /**
     * Overriden constructor.
     * @param world world to use.
     * @param multiplayer if its a mp game.
     */
    public ControllerManager(World world, boolean multiplayer) {
        this.puck = makePuck(world);
        this.redPaddle = makePaddle(world,
                config.redPaddleX,
                new KeyboardController(config.redPaddleKeys, Gdx.input));
        MovementController opponentController = new AiMovementController(puck);
        if (multiplayer) {
            opponentController = new KeyboardController(config.bluePaddleKeys, Gdx.input);
        }
        this.bluePaddle = makePaddle(world,
                config.bluePaddleX, opponentController);

        this.redPaddle = redPaddle;
        this.bluePaddle = bluePaddle;
        this.puck = puck;
    }

    public void updatePositions() {
        bluePaddle.updateVelocity();
        redPaddle.updateVelocity();
    }

    /**
     * Resets position of all elements to default.
     */
    public void resetPositions() {
        puck.resetPosition(0,0);
        bluePaddle.resetPosition(config.bluePaddleX, 0);
        redPaddle.resetPosition(config.redPaddleX, 0);
    }

    public void setRedPaddle(Paddle redPaddle) {
        this.redPaddle = redPaddle;
    }

    public Paddle getBluePaddle() {
        return bluePaddle;
    }

    public Paddle getRedPaddle() {
        return redPaddle;
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
}
