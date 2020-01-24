package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class Utilities {
    /**
     * Initializes a label.
     * @param style for the label.
     * @param x position of the label.
     * @param y position for the label.
     * @param fontScale of the font.
     * @param color of the font.
     * @return initialized label.
     */
    public static Label initLabel(String skinPath, String style, float x, float y,
                            float fontScale, Color color) {
        Skin skin = new Skin(Gdx.files.internal(skinPath));
        Label label = new Label("", skin, style);
        label.setPosition(x, y);
        label.setFontScale(fontScale);
        label.setColor(color);
        label.setAlignment(Align.center);
        return label;
    }
}
