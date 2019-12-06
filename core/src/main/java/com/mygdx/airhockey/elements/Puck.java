package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.backend.Config;

public class Puck extends DynamicObject {

    /**
     * Constructor for puck.
     * @param sprite of the puck.
     * @param body of the puck.
     */
    public Puck(Sprite sprite, Body body) {
        super(sprite, body);
    }

    /**
     * Constructor for puck.
     * @param world to construct in.
     * @param texturePath of the puck's texture.
     * @param size of the puck's texture.
     * @param x position of the puck.
     * @param y position of the puck.
     */
    public Puck(World world, String texturePath, float size, float x, float y) {
        super(world, texturePath, size, x, y);
        FixtureDef fixtureDef = createFixtureDef(new CircleShape(), Config.PUCK_RADIUS,
                Config.PUCK_DENSITY, Config.PUCK_FRICTION, Config.PUCK_RESTITUTION);
        body.createFixture(fixtureDef);
    }
}
