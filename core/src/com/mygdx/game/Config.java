package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.mygdx.game.movement.KeyCodeSet;

public final class Config {
    /**
     * Resolution and viewport parameters.
     */
    public static final float VIEWPORT_SIZE = 50;
    public static final float RESOLUTION = 1000;

    /**
     * Paddle parameters.
     */
    public static final float PADDLE_RADIUS = 1.5f;
    public static final float PADDLE_DENSITY = 0.5f;
    public static final float PADDLE_FRICTION = 0.1f;
    public static final float PADDLE_RESTITUTION = 0.1f;

    /**
     * Puck parameters.
     */
    public static final float PUCK_RADIUS = 1f;
    public static final float PUCK_DENSITY = 0.3f;
    public static final float PUCK_FRICTION = 0.05f;
    public static final float PUCK_RESTITUTION = 0.9f;
    public static final float PADDLE_SPPED = 15;

    /**
     * Wall parameters.
     */
    public static final float WALL_HEIGHT = 10;
    public static final float WALL_WIDTH = 20;
    public static final float WALL_THICKNESS = 0.2f;

    /**
     * Key code sets for paddles (arrows for the right one, wasd for the left one).
     */
    public static final KeyCodeSet BLUE_PADDLE_KEYS = new KeyCodeSet(Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN);
    public static final KeyCodeSet RED_PADDLE_KEYS = new KeyCodeSet(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S);
}
