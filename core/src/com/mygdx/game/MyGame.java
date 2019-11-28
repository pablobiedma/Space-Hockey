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
		//batch = new SpriteBatch();
		/*
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

     	//img = new Texture("badlogic.jpg");
		int Help_Guides = 12;
		int row_height = Gdx.graphics.getWidth() / 12;
		int col_width = Gdx.graphics.getWidth() / 12;

		Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

//		Label title = new Label("Buttons with Skins",mySkin);
//		title.setSize(Gdx.graphics.getWidth(),row_height*2);
//		title.setPosition(0,Gdx.graphics.getHeight()-row_height*2);
//		title.setAlignment(Align.center);
//		stage.addActor(title);
//
//		// Button
//		Button button1 = new Button(mySkin);
//		button1.setSize(col_width*4,row_height);
//		button1.setPosition(col_width,Gdx.graphics.getHeight()-row_height*3);
//		button1.addListener(new InputListener(){
//			@Override
//			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//				outputLabel.setText("Press a Button");
//			}
//			@Override
//			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//				outputLabel.setText("Pressed Button");
//				return true;
//			}
//		});
//		stage.addActor(button1);

		// Text Button
		Button button2 = new TextButton("Start game",mySkin);
		button2.setSize(col_width*4,row_height);
		button2.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*4);
		button2.addListener(new InputListener(){
			//add listener here
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				outputLabel.setText("Press button to start");
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
				stage = new Stage(new ScreenViewport());
				Gdx.input.setInputProcessor(stage);
				int Help_Guides = 12;
				int row_height = Gdx.graphics.getWidth() / 12;
				int col_width = Gdx.graphics.getWidth() / 12;

				//Puck thingy
				Texture puckTexture = new Texture("sprite/sprite_puck.png");
				Image puck = new Image(puckTexture);
				puck.setPosition(Gdx.input.getX(), Gdx.input.getY());
				puck.scaleBy(-0.8f);
				stage.addActor(puck);


				Button button3 = new TextButton("Quit game",mySkin);
				button3.setSize(col_width*4,row_height);
				button3.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*8);
				button3.addListener(new InputListener(){
					//add listener here
					@Override
					public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
						outputLabel.setText("Press button to quit");
					}
					@Override
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						//stage = new Stage(new ScreenViewport());
						System.exit(0);
						return true;
					}
				});
				stage.addActor(button3);
				return true;
			}
		});
		stage.addActor(button2);

//		// ImageButton
//		ImageButton button3 = new ImageButton(mySkin);
//		button3.setSize(col_width*4,(float)(row_height*2));
//		button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_off.png"))));
//		button3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_on.png"))));
//		button3.setPosition(col_width,Gdx.graphics.getHeight()-row_height*6);
//		button3.addListener(new InputListener(){
//			@Override
//			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//				outputLabel.setText("Press a Button");
//			}
//			@Override
//			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//				outputLabel.setText("Pressed Image Button");
//				return true;
//			}});
		//stage.addActor(button3);

//		//ImageTextButton
//		ImageTextButton button4 = new ImageTextButton("ImageText Btn",mySkin,"small");
//		button4.setSize(col_width*4,(float)(row_height*2));
//		button4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_off.png"))));
//		button4.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_on.png"))));
//		button4.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*6);
//		button4.addListener(new InputListener(){
//			@Override
//			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//				outputLabel.setText("Press a Button");
//			}
//			@Override
//			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//				outputLabel.setText("Pressed Image Text Button");
//				return true;
//			}
//		});
//		stage.addActor(button4);
//
		outputLabel = new Label("Press Start",mySkin);
		outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
		outputLabel.setPosition(0,row_height);
		outputLabel.setAlignment(Align.center);
		stage.addActor(outputLabel);

		 */
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
	}
}