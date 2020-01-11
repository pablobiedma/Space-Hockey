package com.mygdx.airhockey.backend;

import com.badlogic.gdx.Input;
import com.mygdx.airhockey.movement.KeyCodeSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static String configPath = "./config.properties";
    private static Config instance;

    public transient String bluePaddleTexturePath;
    public transient String redPaddleTexturePath;
    public transient String puckTexturePath;
    public transient String pitchTexturePath;

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
    public transient KeyCodeSet bluePaddleKeys;
    public transient KeyCodeSet redPaddleKeys;

    /**
     * Constructor for config singleton.
     */
    private Config(String configPath) {
        bluePaddleTexturePath = "sprite/blue-paddle.png";
        redPaddleTexturePath = "sprite/red-paddle.png";
        puckTexturePath = "sprite/puck.png";
        pitchTexturePath = "sprite/pitch.png";

        viewportSize = 50;
        resolution = 1100;

        redPaddleX = -viewportSize / 4;
        bluePaddleX = viewportSize / 4;
        paddleRadius = 1.5f;
        paddleDensity = 0.5f;
        paddleFriction = 0.1f;
        paddleRestitution = 0.1f;
        paddleSpeed = 15;

        puckRadius = 1f;
        puckDensity = 0.3f;
        puckFriction = 0.05f;
        puckRestitution = 0.9f;

        wallHeight = 11;
        wallWidth = 20;
        goalWidth = 4.5f;
        goalDepth = 2 * puckRadius;

        bluePaddleKeys = new KeyCodeSet(
            Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN);
        redPaddleKeys = new KeyCodeSet(
            Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S);
        //try (InputStream input = Thread.currentThread()
        //        .getContextClassLoader().getResourceAsStream(configPath)) {
        //    Properties properties = new Properties();
        //
        //    if (input == null) {
        //        System.out.println("Sorry, unable to find config.properties");
        //        return;
        //    }
        //    properties.load(input);
        //
        //    bluePaddleTexturePath = properties.getProperty("BLUE_PADDLE_TEXTURE_PATH");
        //    redPaddleTexturePath = properties.getProperty("RED_PADDLE_TEXTURE_PATH");
        //    puckTexturePath = properties.getProperty("PUCK_TEXTURE_PATH");
        //    pitchTexturePath = properties.getProperty("PITCH_TEXTURE_PATH");
        //    viewportSize = Integer.parseInt(properties.getProperty("VIEWPORT_SIZE"));
        //    resolution = Integer.parseInt(properties.getProperty("RESOLUTION"));
        //    redPaddleX = -viewportSize / 4;
        //    bluePaddleX = viewportSize / 4;
        //
        //    paddleRadius = Float.parseFloat(properties.getProperty("PADDLE_RADIUS"));
        //    paddleDensity = Float.parseFloat(properties.getProperty("PADDLE_DENSITY"));
        //    paddleFriction = Float.parseFloat(properties.getProperty("PADDLE_FRICTION"));
        //    paddleRestitution = Float.parseFloat(properties.getProperty("PADDLE_RESTITUTION"));
        //    paddleSpeed = Float.parseFloat(properties.getProperty("PADDLE_SPEED"));
        //
        //    puckRadius = Float.parseFloat(properties.getProperty("PUCK_RADIUS"));
        //    puckDensity = Float.parseFloat(properties.getProperty("PUCK_DENSITY"));
        //    puckFriction = Float.parseFloat(properties.getProperty("PUCK_FRICTION"));
        //    puckRestitution = Float.parseFloat(properties.getProperty("PUCK_RESTITUTION"));
        //
        //    wallHeight = Float.parseFloat(properties.getProperty("WALL_HEIGHT"));
        //    wallWidth = Float.parseFloat(properties.getProperty("WALL_WIDTH"));
        //
        //    bluePaddleKeys = new KeyCodeSet(Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT,
        //            Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN);
        //    redPaddleKeys = new KeyCodeSet(Input.Keys.A, Input.Keys.D,
        //    Input.Keys.W, Input.Keys.S);
        //
        //} catch (IOException ex) {
        //    ex.printStackTrace();
        //}
    }

    /**
     * Provides access to configuration data.
     * @return config object.
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config(configPath);
        }
        return instance;
    }
}
