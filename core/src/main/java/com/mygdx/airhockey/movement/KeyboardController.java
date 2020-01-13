package com.mygdx.airhockey.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.Config;

public class KeyboardController implements MovementController {
    private KeyCodeSet keycodes;
    private static Config config = Config.getInstance();

    /**
     * Constructor for movement controller.
     *
     * @param keycodes to steer the movement.
     */
    public KeyboardController(KeyCodeSet keycodes) {
        this.keycodes = keycodes;
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
            float vertical = 0;
            float horizontal = 0;

            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeLeft())) {
                horizontal -= config.paddleSpeed;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeRight())) {
                horizontal += config.paddleSpeed;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeUp())) {
                vertical += config.paddleSpeed;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeDown())) {
                vertical -= config.paddleSpeed;
            }
            body.setLinearVelocity(horizontal, vertical);
        }

    }

    /**
     * Getter for keycode set.
     *
     * @return the controls of movement controler.
     */
    public KeyCodeSet getKeycodes() {
        return keycodes;
    }

    /**
     * Setter for keycode set.
     *
     * @param keycodes to set.
     */
    public void setKeycodes(KeyCodeSet keycodes) {
        this.keycodes = keycodes;
    }

}
