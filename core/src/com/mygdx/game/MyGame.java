package com.mygdx.game;

<<<<<<< HEAD
//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.Align;
//import com.badlogic.gdx.utils.Scaling;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.mygdx.game.screens.GameScreen;
=======
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
>>>>>>> Added a lot of game functionallity - collisions, paddle movement, puck movement, textures.
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