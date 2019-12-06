package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.backend.CoordinateTranslator;

public abstract class DynamicObject {
    Sprite sprite;
    Body body;

    public DynamicObject(Sprite sprite, Body body) {
        this.sprite = sprite;
        this.body = body;
    }

    /**
     * Constructor for dynamic object.
     * @param world for creating the object.
     * @param texturePath for object's texture.
     * @param size of the object's texture.
     * @param x position of the object.
     * @param y position of the object.
     */
    public DynamicObject(World world, String texturePath, float size, float x, float y) {
        this.sprite = createSprite(new Texture(texturePath), size);
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
     * @param texture for the sprite.
     * @param size of the sprite's texture.
     * @return created sprite.
     */
    public Sprite createSprite(Texture texture, float size) {
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
    public Body createBody(World world, float x, float y) {
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
    public FixtureDef createFixtureDef(
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

    /**
     * Getter for sprite.
     * @return sprite of the object.
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Sets a sprite to a given object.
     * @param sprite to set.
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Getter for body.
     * @return body of the object.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Setter for body.
     * @param body value to set.
     */
    public void setBody(Body body) {
        this.body = body;
    }
}
