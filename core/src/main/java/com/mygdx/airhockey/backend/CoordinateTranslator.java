package com.mygdx.airhockey.backend;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Class used to translate Viewport coordinates to pixel coordinates.
 * Needed for positioning the sprites.
 */
public class CoordinateTranslator {
    private static Config config = Config.getInstance();

    /**
     * Translates coordinate x.
     * @param s sprite for which the translation is performed.
     * @param x coordinate.
     * @return resulting coordinate.
     */
    public static float translateX(Sprite s, float x) {
        return config.resolution / 2 + x / config.viewportSize
                * config.resolution - s.getWidth() / 2;
    }

    /**
     * Translates coordinate y.
     * @param s sprite for which the translation is performed.
     * @param y coordinate.
     * @return resulting coordinate.
     */
    public static float translateY(Sprite s, float y) {
        return config.resolution / 2 + y / config.viewportSize
                * config.resolution - s.getHeight() / 2;
    }

    /**
     * Translates size to pixel resolution.
     * @param size to translate.
     * @return the new size in the pixel coordinates.
     */
    public static float translateSize(float size) {
        return size / config.viewportSize * config.resolution;
    }
}
