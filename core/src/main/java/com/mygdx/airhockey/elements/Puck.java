package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class Puck extends GameElement {

    /**
     * Constructor for puck.
     * @param sprite of the puck.
     * @param body of the puck.
     */
    public Puck(Body body) {
        super(body);
    }
}
