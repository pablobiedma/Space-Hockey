package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class Pitch extends GameElement {
    /**
     * Constructor for walls class.
     * @param body to create the walls in.
     */
    public Pitch(Sprite sprite, Body body) {
        super(sprite, body);
    }

    /**
     * Draws the pitch given a batch.
     * @param batch for drawing.
     */
    public void draw(Batch batch) {
        sprite.draw(batch);
    }
}
