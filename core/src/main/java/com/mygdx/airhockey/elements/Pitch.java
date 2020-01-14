package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class Pitch extends GameElement {
    /**
     * Constructor for walls class.
     * @param body to create the walls in.
     */
    public Pitch(Body body) {
        super(body);
    }
}
