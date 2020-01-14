package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGame extends com.badlogic.gdx.Game {
    public static Skin gameSkin;

    @Override
    public void create() {
        gameSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        this.setScreen(new MenuScreen(this, true));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}