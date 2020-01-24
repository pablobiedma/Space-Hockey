package com.mygdx.airhockey.backend.generators;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.movement.MovementController;

public class BodyGenerator {
    private static Config config = Config.getInstance();

    /**
     * Creates a circular body.
     *
     * @param world to create in.
     * @param x     position of the body.
     * @param y     position of the body.
     * @return created body.
     */
    public static Body createBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        return world.createBody(bodyDef);
    }

    /**
     * creates a pitch body.
     * @param world to create in.
     * @return created body.
     */
    public static final Pitch makePitch(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        Body pitchBody = world.createBody(bodyDef);

        float[] shape = {
            -config.wallWidth, config.wallHeight,
            config.wallWidth, config.wallHeight,
            config.wallWidth, config.goalWidth,
            config.wallWidth + config.goalDepth, config.goalWidth,
            config.wallWidth + config.goalDepth, -config.goalWidth,
            config.wallWidth, -config.goalWidth,
            config.wallWidth, -config.wallHeight,
            -config.wallWidth, -config.wallHeight,
            -config.wallWidth, -config.goalWidth,
            -config.wallWidth - config.goalDepth, -config.goalWidth,
            -config.wallWidth - config.goalDepth, config.goalWidth,
            -config.wallWidth, config.goalWidth,
        };

        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(shape);
        pitchBody.createFixture(chainShape, 0);
        return new Pitch(pitchBody);
    }

    /**
     * Creates the paddle.
     * @param world world to use.
     * @param posX position for paddle.
     * @param movementController controller to attach to.
     * @return the newly made paddle.
     */
    public static final Paddle makePaddle(World world,
                            float posX, MovementController movementController) {
        Body paddleBody = createBody(world, posX, 0);
        FixtureDef paddleFixtureDef = createFixtureDef(new CircleShape(), config.paddleRadius,
                config.paddleDensity, config.paddleFriction, config.paddleRestitution);
        paddleBody.createFixture(paddleFixtureDef);
        return new Paddle(paddleBody, movementController);
    }

    /**
     * Makes the puck.
     * @param world world to use.
     * @return the newly made puck.
     */
    public static final Puck makePuck(World world) {
        Body puckBody = createBody(world, 0, 0);
        FixtureDef puckFixtureDef = createFixtureDef(new CircleShape(), config.puckRadius,
                config.puckDensity, config.puckFriction, config.puckRestitution);
        puckBody.createFixture(puckFixtureDef);
        return new Puck(puckBody);
    }

    /**
     * Creates a fixture definition.
     *
     * @param shape       of the fixture.
     * @param radius      of the fixture.
     * @param density     of the fixture.
     * @param friction    of the fixture.
     * @param restitution of the fixture.
     * @return created fixture definition.
     */
    public static FixtureDef createFixtureDef(
            CircleShape shape, float radius, float density, float friction, float restitution) {
        shape.setRadius(radius);
        FixtureDef res = new FixtureDef();
        res.shape = shape;
        res.density = density;
        res.friction = friction;
        res.restitution = restitution;
        shape.dispose();
        return res;
    }
}
