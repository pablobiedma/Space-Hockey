package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screens.MenuScreen;

public class MyGame extends com.badlogic.gdx.Game {
	static public Skin gameSkin;
	@Override
	public void create () {
		gameSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
	}
}