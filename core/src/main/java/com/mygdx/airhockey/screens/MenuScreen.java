package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    private transient Stage stage;
    private transient Game game;
    private static final TextureRegion backgroundTexture = new TextureRegion(new Texture("arcade.png"), 0, 0, 900, 900);;
    private transient Sound initialSound;
    private static final Sound backgroundSound = Gdx.audio.newSound(Gdx.files.internal("music/open-space.mp3"));;


    /**
     * Instantiates a menu screen.
     * @param game to initialize the screen with.
     */
    public MenuScreen(Game game, boolean playSound) {
        this.game = game;
        this.initialSound = initialSound;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int rowHeight = Gdx.graphics.getWidth() / 12;
        int colWidth = Gdx.graphics.getWidth() / 12;

        if(playSound) {
            backgroundSound.loop();
        }

        Skin mySkin = new Skin(Gdx.files.internal("Craftacular_UI_Skin/craftacular-ui.json"));
        Button startGameButton = new TextButton("Log in",mySkin);
        Label logo = new Label("SPACE HOCKEY", mySkin);
        logo.setFontScale(1.75f);
        logo.setAlignment(Align.center);
        logo.setWidth(1000);
        logo.setPosition(0, Gdx.graphics.getHeight() - rowHeight * 2);
        logo.setColor(Color.GOLD);

        Label title = new Label("Air Hockey made with love by Group 45", mySkin);
        title.setPosition(colWidth * 2, Gdx.graphics.getHeight() - rowHeight * 11);
        startGameButton.setSize(colWidth * 3,rowHeight);
        startGameButton.setPosition(colWidth * 4.5f,Gdx.graphics.getHeight() - rowHeight * 9);
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen.this.game.setScreen(new LoginScreen(MenuScreen.this.game, backgroundSound));
                return true;
            }
        });
        stage.addActor(startGameButton);
        stage.addActor(logo);
        stage.addActor(title);

        Button button4 = new TextButton("Sign Up",mySkin);
        button4.setSize(colWidth * 3,rowHeight);
        button4.setPosition(colWidth * 4.5f,Gdx.graphics.getHeight() - rowHeight * 8);
        button4.addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen.this.game.setScreen(new SignupScreen(MenuScreen.this.game, backgroundSound));
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
        stage.getBatch().draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
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
