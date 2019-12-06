package com.mygdx.game.game_elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.game_backend.CoordinateTranslator;

public abstract class DynamicObject {
    Sprite sprite;
    Body body;

    /**
     * Constructor for dynamic object.
     * @param world for creating the object.
     * @param texturePath for object's texture.
     * @param size of the object's texture.
     * @param x position of the object.
     * @param y position of the object.
     */
    public DynamicObject(World world, String texturePath, float size, float x, float y) {
        this.sprite = createSprite(texturePath, size);
        this.body = createBody(world, x,y);
    }

    /**
     * Draws the object, given a batch.
     * @param batch used for drawing.
     */
    public void draw(Batch batch) {
        sprite.setPosition(
                CoordinateTranslator.translateX(sprite, body.getPosition().x),
                CoordinateTranslator.translateY(sprite, body.getPosition().y));
        sprite.draw(batch);
    }

    /**
     * Creates a sprite.
     * @param texturePath for the sprite.
     * @param size of the sprite's texture.
     * @return created sprite.
     */
    private Sprite createSprite(String texturePath, float size) {
        Texture texture = new Texture(texturePath);
        Sprite sprite = new Sprite(texture);
        sprite.setSize(size, size);
        return sprite;
    }

    /**
     * Creates a circular body.
     * @param world to create in.
     * @param x position of the body.
     * @param y position of the body.
     * @return created body.
     */
    private Body createBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        return world.createBody(bodyDef);
    }

    /**
     * Creates a fixture definition.
     * @param shape of the fixture.
     * @param radius of the fixture.
     * @param density of the fixture.
     * @param friction of the fixture.
     * @param restitution of the fixture.
     * @return created fixture definition.
     */
    protected FixtureDef getFixtureDef(CircleShape shape, float radius, float density, float friction, float restitution) {
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
