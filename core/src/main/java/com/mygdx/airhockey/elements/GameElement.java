package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.CoordinateTranslator;

public abstract class GameElement {
    Body body;

    /**
     * Constructor for game element.
     * @param body of the object.
     */
    public GameElement(Body body) {
        this.body = body;
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
