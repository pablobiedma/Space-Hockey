package com.mygdx.game.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.Config;

public class MovementController {
    private KeyCodeSet keycodes;
    private Body body;
    private boolean isOnTheLeft;



    public MovementController(Body body, KeyCodeSet keycodes, boolean isOnTheLeft) {
        this.body = body;
        this.keycodes = keycodes;
        this.isOnTheLeft = isOnTheLeft;
    }

    public void updateVelocity() {
        if (crossedHalf()) {
            if (isOnTheLeft) {
                body.setLinearVelocity(-Config.PADDLE_SPPED,0);
            } else {
                body.setLinearVelocity(Config.PADDLE_SPPED,0);
            }

        } else {
            float vertical = 0;
            float horizontal = 0;

            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeLeft())) {
                horizontal -= Config.PADDLE_SPPED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeRight())) {
                horizontal += Config.PADDLE_SPPED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeUp())) {
                vertical += Config.PADDLE_SPPED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeDown())) {
                vertical -= Config.PADDLE_SPPED;
            }
            body.setLinearVelocity(horizontal, vertical);
        }

    }

    private boolean crossedHalf() {
        if (isOnTheLeft && body.getPosition().x >= 0 || !isOnTheLeft && body.getPosition().x <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
