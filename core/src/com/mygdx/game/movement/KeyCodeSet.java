package com.mygdx.game.movement;

import com.badlogic.gdx.physics.box2d.Body;

public class KeyCodeSet {
    private int keyCodeUp;
    private int keyCodeDown;
    private int keyCodeLeft;
    private int keyCodeRight;

    public KeyCodeSet (int keyCodeLeft, int keyCodeRight, int keyCodeUp, int keyCodeDown) {
        this.keyCodeLeft = keyCodeLeft;
        this.keyCodeRight = keyCodeRight;
        this.keyCodeUp = keyCodeUp;
        this.keyCodeDown = keyCodeDown;
    }

    public int getKeyCodeUp() {
        return keyCodeUp;
    }

    public int getKeyCodeDown() {
        return keyCodeDown;
    }

    public int getKeyCodeLeft() {
        return keyCodeLeft;
    }

    public int getKeyCodeRight() {
        return keyCodeRight;
    }
}
