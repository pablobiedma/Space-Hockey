package com.mygdx.game.game_elements;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.game_backend.Config;
import com.mygdx.game.movement.KeyCodeSet;
import com.mygdx.game.movement.MovementController;

public class Paddle extends DynamicObject {
    private MovementController movementController;

    /**
     * Constructor for the Paddle class.
     * @param world to construct in.
     * @param texturePath of the paddle's texture.
     * @param size of the paddle's texture.
     * @param x position of the paddle.
     * @param y position of the paddle.
     * @param keyCodeSet for moving the paddle.
     */
    public Paddle(World world, String texturePath, float size, float x, float y, KeyCodeSet keyCodeSet) {
        super(world, texturePath, size, x, y);
        this.movementController = new MovementController(body, keyCodeSet);
        FixtureDef fixtureDef = getFixtureDef(new CircleShape(), Config.PADDLE_RADIUS,
                Config.PADDLE_DENSITY, Config.PADDLE_FRICTION, Config.PADDLE_RESTITUTION);
        body.createFixture(fixtureDef);
    }

    /**
     * Updates the paddle's velocity.
     */
    public void updateVelocity() {
        movementController.updateVelocity();
    }

    public void stopAndSetToPosition(float x, float y) {
        body.setLinearVelocity(0,0);
        body.setTransform(x, y
                , body.getAngle());

    }
}
