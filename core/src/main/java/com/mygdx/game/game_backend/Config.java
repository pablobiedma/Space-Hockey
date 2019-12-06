package com.mygdx.game.game_backend;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.movement.KeyCodeSet;

public final class Config {
    /**
     * Texture paths
     */
    public static final String BLUE_PADDLE_TEXTURE_PATH = "sprite/blue-paddle.png";
    public static final String RED_PADDLE_TEXTURE_PATH = "sprite/red-paddle.png";
    public static final String PUCK_TEXTURE_PATH = "sprite/puck.png";
    public static final String PITCH_TEXTURE_PATH = "sprite/pitch.png";

    /**
     * Resolution and viewport parameters.
     */
    public static final float VIEWPORT_SIZE = 50;
    public static final float RESOLUTION = 1000;

    /**
     * Paddle parameters.
     */
    public static final float RED_PADDLE_X = -VIEWPORT_SIZE/4;
    public static final float BLUE_PADDLE_X = VIEWPORT_SIZE/4;
    public static final float PADDLE_RADIUS = 1.5f;
    public static final float PADDLE_DENSITY = 0.5f;
    public static final float PADDLE_FRICTION = 0.1f;
    public static final float PADDLE_RESTITUTION = 0.1f;
    public static final float PADDLE_SPEED = 15;

    /**
     * Puck parameters.
     */
    public static final float PUCK_RADIUS = 1f;
    public static final float PUCK_DENSITY = 0.3f;
    public static final float PUCK_FRICTION = 0.05f;
    public static final float PUCK_RESTITUTION = 0.9f;

    /**
     * Wall parameters.
     */
    public static final float WALL_HEIGHT = 11;
    public static final float WALL_WIDTH = 20;
    public static final float WALL_THICKNESS = 0.2f;

    /**
     * Key code sets for paddles (arrows for the right one, wasd for the left one).
     */
    public static final KeyCodeSet BLUE_PADDLE_KEYS = new KeyCodeSet(Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN);
    public static final KeyCodeSet RED_PADDLE_KEYS = new KeyCodeSet(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S);
}
