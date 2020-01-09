package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private TextureRegion backgroundTexture;

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
        backgroundTexture = new TextureRegion(new Texture("blue.jpg"), 0, 0, 2048, 563);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Button startGameButton = new TextButton("Login",mySkin);
        startGameButton.setSize(colWidth * 4,rowHeight);
        startGameButton.setPosition(colWidth * 4,Gdx.graphics.getHeight() - rowHeight * 6);
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen.this.game.setScreen(new LoginScreen(MenuScreen.this.game));
                return true;
            }
        });
        stage.addActor(startGameButton);

        Button button4 = new TextButton("Sign Up",mySkin);
        button4.setSize(colWidth * 4,rowHeight);
        button4.setPosition(colWidth * 4,Gdx.graphics.getHeight() - rowHeight * 8);
        button4.addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen.this.game.setScreen(new SignupScreen(MenuScreen.this.game));
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
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
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
