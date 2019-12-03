package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGame;

public class MenuScreen implements Screen {
    private Stage stage;
    private Game game;

    public MenuScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //img = new Texture("badlogic.jpg");
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Text Button
        Button startGameButton = new TextButton("Start game",mySkin);
        startGameButton.setSize(col_width*4,row_height);
        startGameButton.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*4);
        startGameButton.addListener(new InputListener(){
            //add listener here
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //outputLabel.setText("Press button to start");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen());
                return true;
            }
        });
        stage.addActor(startGameButton);

        //Puck thingy
        Button button3 = new TextButton("Close",mySkin);
        button3.setSize(col_width*4,row_height);
        button3.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*6);
        button3.addListener(new InputListener(){
            //add listener here
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //stage = new Stage(new ScreenViewport());
                System.exit(0);
                return true;
            }
        });
        stage.addActor(button3);

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
        /*
        outputLabel = new Label("Press Start",mySkin);
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);

         */

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
