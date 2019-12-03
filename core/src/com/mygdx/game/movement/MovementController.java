package com.mygdx.game.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementController {
    private KeyCodeSet keycodes;
    private Body body;
    private boolean isOnTheLeft;

    private final static int SPEED = 12;

    public MovementController(Body body, KeyCodeSet keycodes, boolean isOnTheLeft) {
        this.body = body;
        this.keycodes = keycodes;
        this.isOnTheLeft = isOnTheLeft;
    }

    public void updateVelocity() {
        if (crossedHalf()) {
            if (isOnTheLeft) {
                body.setLinearVelocity(-SPEED,0);
            } else {
                body.setLinearVelocity(SPEED,0);
            }

        } else {
            float vertical = 0;
            float horizontal = 0;

            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeLeft())) {
                horizontal -= SPEED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeRight())) {
                horizontal += SPEED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeUp())) {
                vertical += SPEED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeDown())) {
                vertical -= SPEED;
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
