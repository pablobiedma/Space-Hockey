package com.mygdx.game.movement;

public class KeyCodeSet {
    private int keyCodeUp;
    private int keyCodeDown;
    private int keyCodeLeft;
    private int keyCodeRight;

    /**
     * Constructor for KeyCodeSet.
     * @param keyCodeLeft code of the button for moving left.
     * @param keyCodeRight code of the button for moving right.
     * @param keyCodeUp code of the button for moving up.
     * @param keyCodeDown code of the button for moving down.
     */
    public KeyCodeSet (int keyCodeLeft, int keyCodeRight, int keyCodeUp, int keyCodeDown) {
        this.keyCodeLeft = keyCodeLeft;
        this.keyCodeRight = keyCodeRight;
        this.keyCodeUp = keyCodeUp;
        this.keyCodeDown = keyCodeDown;
    }

    /**
     * Getter for key code up.
     * @return the key code of up button.
     */
    public int getKeyCodeUp() {
        return keyCodeUp;
    }

    /**
     * Getter for key code down.
     * @return the key code of down button.
     */
    public int getKeyCodeDown() {
        return keyCodeDown;
    }

    /**
     * Getter for key code left.
     * @return the key code of left button.
     */
    public int getKeyCodeLeft() {
        return keyCodeLeft;
    }

    /**
     * Getter for key code right.
     * @return the key code of right button.
     */
    public int getKeyCodeRight() {
        return keyCodeRight;
    }
}
