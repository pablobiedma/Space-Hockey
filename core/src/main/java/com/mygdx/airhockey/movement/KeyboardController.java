package com.mygdx.airhockey.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.Config;

public class KeyboardController implements MovementController {
    private KeySet keycodes;

    private Input input;
    private static Config config = Config.getInstance();

    /**
     * Constructor for movement controller.
     *
     * @param keycodes to steer the movement.
     */
    public KeyboardController(KeySet keycodes, Input input) {
        this.keycodes = keycodes;
        this.input = input;
    }

    /**
     * Updates velocity of the object.
     */
    public void updateVelocity(Body body) {
        if (MovementController.touchesMiddleLine(body)) {
            if (body.getPosition().x > 0) {
                body.setLinearVelocity(config.paddleSpeed, 0);
            } else {
                body.setLinearVelocity(-config.paddleSpeed, 0);
            }

        } else {
            updateVelocityKeyPresses(body);
        }

    }

    /**
     * determines how to velocity should be updated based on key pressed.
     */
    private void updateVelocityKeyPresses(Body body) {
        float vertical = 0;
        float horizontal = 0;

        if (input.isKeyPressed(keycodes.getKeyCodeLeft())) {
            horizontal -= config.paddleSpeed;
        }
        if (input.isKeyPressed(keycodes.getKeyCodeRight())) {
            horizontal += config.paddleSpeed;
        }
        if (input.isKeyPressed(keycodes.getKeyCodeUp())) {
            vertical += config.paddleSpeed;
        }
        if (input.isKeyPressed(keycodes.getKeyCodeDown())) {
            vertical -= config.paddleSpeed;
        }
        body.setLinearVelocity(horizontal, vertical);
    }

    /**
     * Getter for keycode set.
     *
     * @return the controls of movement controler.
     */
    public KeySet getKeycodes() {
        return keycodes;
    }

    /**
     * Setter for keycode set.
     *
     * @param keycodes to set.
     */
    public void setKeycodes(KeySet keycodes) {
        this.keycodes = keycodes;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

}
