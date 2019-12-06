package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private Stage stage;
    private Game game;

    public MenuScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Button startGameButton = new TextButton("Login",mySkin);
        startGameButton.setSize(col_width*4,row_height);
        startGameButton.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*4);
        startGameButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LoginScreen(game));
                return true;
            }
        });
        stage.addActor(startGameButton);

        //Puck thingy
        Button button3 = new TextButton("Close",mySkin);
        button3.setSize(col_width*2,row_height-20);
        button3.setPosition(col_width*5,Gdx.graphics.getHeight()-row_height*8);
        button3.addListener(new InputListener(){
            //add listener here
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return true;
            }
        });
        stage.addActor(button3);
        Button button4 = new TextButton("Sign Up",mySkin);
        button4.setSize(col_width*4,row_height);
        button4.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*6);
        button4.addListener(new InputListener(){
            //add listener here
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return true;
            }
        });
        stage.addActor(button4);
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