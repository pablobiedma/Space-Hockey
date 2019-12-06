package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.backend.CoordinateTranslator;

public class Pitch {
    private Sprite sprite;

    /**
     * Initializes the pitch sprite.
     */
    public Pitch() {
        Texture pitchTexture = new Texture(Config.PITCH_TEXTURE_PATH);
        sprite = new Sprite(pitchTexture);
        float pitchWidth = 2 * Config.WALL_WIDTH / Config.VIEWPORT_SIZE * Config.RESOLUTION;
        float pitchHeight = 2 * Config.WALL_HEIGHT / Config.VIEWPORT_SIZE * Config.RESOLUTION;

        sprite.setSize(pitchWidth, pitchHeight);
        sprite.setPosition(
                CoordinateTranslator.translateX(sprite, 0),
                CoordinateTranslator.translateY(sprite, 0));
    }

    /**
     * Draws the pitch given a batch.
     * @param batch for drawing.
     */
    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
