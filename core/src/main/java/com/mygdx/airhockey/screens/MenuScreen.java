package com.mygdx.airhockey.screens;

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
    private transient Stage stage;
    private transient Game game;

    /**
     * Instantiates a menu screen.
     * @param game to initialize the screen with.
     */
    public MenuScreen(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int rowHeight = Gdx.graphics.getWidth() / 12;
        int colWidth = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Button startGameButton = new TextButton("Login",mySkin);
        startGameButton.setSize(colWidth * 4,rowHeight);
        startGameButton.setPosition(colWidth * 4,Gdx.graphics.getHeight() - rowHeight * 4);
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen.this.game.setScreen(new LoginScreen(MenuScreen.this.game));
                return true;
            }
        });
        stage.addActor(startGameButton);

        //Puck thingy
        Button button3 = new TextButton("Close",mySkin);
        button3.setSize(colWidth * 2,rowHeight - 20);
        button3.setPosition(colWidth * 5,Gdx.graphics.getHeight() - rowHeight * 8);
        button3.addListener(new InputListener() {
            //add listener here
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button3);
        Button button4 = new TextButton("Sign Up",mySkin);
        button4.setSize(colWidth * 4,rowHeight);
        button4.setPosition(colWidth * 4,Gdx.graphics.getHeight() - rowHeight * 6);
        button4.addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
