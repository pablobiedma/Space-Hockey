package com.mygdx.game.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.game_backend.Config;

public class MovementController {
    private KeyCodeSet keycodes;
    private Body body;

    /**
     * Constructor for movement controller.
     * @param body to control the movement of.
     * @param keycodes to steer the movement.
     */
    public MovementController(Body body, KeyCodeSet keycodes) {
        this.body = body;
        this.keycodes = keycodes;
    }

    /**
     * Updates velocity of the object
     */
    public void updateVelocity() {
        if (touchesMiddleLine()) {
            if(body.getPosition().x > 0) { ;
                body.setLinearVelocity(Config.PADDLE_SPEED,0);
            } else {
                body.setLinearVelocity(-Config.PADDLE_SPEED,0);
            }

        } else {
            float vertical = 0;
            float horizontal = 0;

            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeLeft())) {
                horizontal -= Config.PADDLE_SPEED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeRight())) {
                horizontal += Config.PADDLE_SPEED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeUp())) {
                vertical += Config.PADDLE_SPEED;
            }
            if (Gdx.input.isKeyPressed(keycodes.getKeyCodeDown())) {
                vertical -= Config.PADDLE_SPEED;
            }
            body.setLinearVelocity(horizontal, vertical);
        }

    }

    /**
     * Checks if the paddle touches the middle line.
     * @return true if it does, else false.
     */
    private boolean touchesMiddleLine() {
        Circle c = new Circle();
        c.setPosition(body.getPosition().x, body.getPosition().y);
        c.setRadius(Config.PADDLE_RADIUS);
        Rectangle border = new Rectangle(-Config.WALL_THICKNESS/2,-Config.VIEWPORT_SIZE/2,Config.WALL_THICKNESS, Config.VIEWPORT_SIZE);
        if(Intersector.overlaps(c, border)){
            return true;
        } else {
            return false;
        }
    }
}
