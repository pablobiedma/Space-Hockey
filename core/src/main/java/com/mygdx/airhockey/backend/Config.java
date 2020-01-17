package com.mygdx.airhockey.backend;

import com.badlogic.gdx.Input;
import com.mygdx.airhockey.movement.KeySet;

public class Config {
    private static Config instance;

    public transient String bluePaddleTexturePath;
    public transient String redPaddleTexturePath;
    public transient String puckTexturePath;

    /**
     * Resolution and viewport parameters.
     */
    public transient float viewportSize;
    public transient float resolution;

    /**
     * Paddle parameters.
     */
    public transient float redPaddleX;
    public transient float bluePaddleX;
    public transient float paddleRadius;
    public transient float paddleDensity;
    public transient float paddleFriction;
    public transient float paddleRestitution;
    public transient float paddleSpeed;

    /**
     * Puck parameters.
     */
    public transient float puckRadius;
    public transient float puckDensity;
    public transient float puckFriction;
    public transient float puckRestitution;

    /**
     * Wall parameters.
     */
    public transient float wallHeight;
    public transient float wallWidth;
    public transient float goalWidth;
    public transient float goalDepth;

    /**
     * Key code sets for paddles (arrows for the right one, wasd for the left one).
     */
    public transient KeySet bluePaddleKeys;
    public transient KeySet redPaddleKeys;

    /**
     * Constructor for config singleton.
     */
    private Config() {
        bluePaddleTexturePath = "sprite/planet1.png";
        redPaddleTexturePath = "sprite/planet2.png";
        puckTexturePath = "sprite/puck.png";

        viewportSize = 50;
        resolution = 1000;

        redPaddleX = -viewportSize / 4;
        bluePaddleX = viewportSize / 4;
        paddleRadius = 1.5f;
        paddleDensity = 0.5f;
        paddleFriction = 0.1f;
        paddleRestitution = 0.1f;
        paddleSpeed = 20;

        puckRadius = 1f;
        puckDensity = 0.3f;
        puckFriction = 0.3f;
        puckRestitution = 0.9f;

        wallHeight = 11;
        wallWidth = 20;
        goalWidth = 4.5f;
        goalDepth = 2 * puckRadius;

        bluePaddleKeys = new KeySet(
            Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN);
        redPaddleKeys = new KeySet(
            Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S);
    }

    /**
     * Provides access to configuration data.
     * @return config object.
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
}
