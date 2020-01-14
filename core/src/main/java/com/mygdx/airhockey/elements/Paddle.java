package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.movement.KeyboardController;
import com.mygdx.airhockey.movement.MovementController;

public class Paddle extends GameElement {
    private MovementController movementController;

    /**
     * Constructor for the paddle.
     * @param body of the paddle.
     * @param movementController of the paddle.
     */
    public Paddle(Body body, MovementController movementController) {
        super(body);
        this.movementController = movementController;
    }

    /**
     * Updates the paddle's velocity.
     */
    public void updateVelocity() {
        movementController.updateVelocity(this.body);
    }

    /**
     * Getter for movement controller.
     * @return movement controller of the paddle.
     */
    public MovementController getMovementController() {
        return movementController;
    }

    /**
     * Setter for movement control.
     * @param movementController to set.
     */
    public void setMovementController(KeyboardController movementController) {
        this.movementController = movementController;
    }
}


