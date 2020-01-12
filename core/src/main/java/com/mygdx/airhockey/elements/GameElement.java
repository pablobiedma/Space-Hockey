package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.CoordinateTranslator;

public abstract class GameElement {
    Sprite sprite;
    Body body;

    /**
     * Constructor for game element.
     * @param sprite of the object.
     * @param body of the object.
     */
    public GameElement(Sprite sprite, Body body) {
        this.sprite = sprite;
        this.body = body;
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

    /**
     * Moves the body to a different position.
     * @param x desired x coordinate.
     * @param y desired y coordinate.
     */
    public void resetPosition(float x, float y) {
        body.setTransform(x, y, body.getAngle());
        body.setLinearVelocity(0, 0);
    }
}
