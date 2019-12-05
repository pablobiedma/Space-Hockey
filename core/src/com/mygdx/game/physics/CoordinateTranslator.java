package com.mygdx.game.physics;

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
}
