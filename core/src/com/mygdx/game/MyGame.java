package com.mygdx.game;

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
import com.mygdx.game.screens.MenuScreen;

//public class Game extends ApplicationAdapter {
////	SpriteBatch batch;
////	Texture img;
//	private Stage stage;
//
//	Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
//
//
//	@Override
//	public void create () {
////		batch = new SpriteBatch();
////		img = new Texture("badlogic.jpg");
//		stage = new Stage(new ScreenViewport());
//		Gdx.input.setInputProcessor(stage);
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		stage.act();
//		stage.draw();
//	}
//
//	@Override
//	public void dispose () {
////		batch.dispose();
////		img.dispose();
//	}
//}
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