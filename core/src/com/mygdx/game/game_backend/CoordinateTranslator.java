package com.mygdx.game.game_backend;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Class used to translate Viewport coordinates to pixel coordinates. Needed for positioning the sprites.
 */
public class CoordinateTranslator {
    /**
     * Translates coordinate x.
     * @param s sprite for which the translation is performed.
     * @param x coordinate.
     * @return resulting coordinate.
     */
    public static float translateX(Sprite s, float x) {
        return Config.RESOLUTION / 2 + x / Config.VIEWPORT_SIZE * Config.RESOLUTION - s.getWidth() / 2;
    }

    /**
     * Translates coordinate y.
     * @param s sprite for which the translation is performed.
     * @param y coordinate.
     * @return resulting coordinate.
     */
    public static float translateY(Sprite s, float y) {
        return Config.RESOLUTION / 2 + y / Config.VIEWPORT_SIZE * Config.RESOLUTION - s.getHeight() / 2;
    }

    /**
     * Translates size to pixel resolution.
     * @param size to translate.
     * @return the new size in the pixel coordinates.
     */
    public static float translateSize(float size) {
        return 2 * size / Config.VIEWPORT_SIZE * Config.RESOLUTION;
    }
}
