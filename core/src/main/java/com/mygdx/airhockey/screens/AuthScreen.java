package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


abstract class AuthScreen extends ScreenBase {
    public static final int HEIGHT = 40;
    protected transient TextField txfUsername;
    protected transient TextField txfPassword;
    transient int rowHeight = Gdx.graphics.getWidth() / 12;
    transient int colWidth = Gdx.graphics.getWidth() / 12;

    /**
     * Constructor for login screen.
     *
     * @param g game of the login screen;
     */
    public AuthScreen(Game g, String soundPath) {
        super(g, soundPath, "background.gif");
        game = g;
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Creates button.
     */
    public void createBtn(final String text, ClickListener clickListener) {
        Skin skin = new Skin(Gdx.files.internal("Craftacular_UI_Skin/craftacular-ui.json"));
        TextButton btn = new TextButton(text, skin);
        TextButton goBack = new TextButton("Back", skin);
        goBack.setPosition(colWidth * 5, Gdx.graphics.getHeight() - rowHeight * 8);
        goBack.setSize(colWidth * 2, rowHeight - 20);
        btn.setPosition(colWidth * 4, Gdx.graphics.getHeight() - rowHeight * 6);
        btn.setSize(colWidth * 4, rowHeight);
        btn.addListener(clickListener);
        goBack.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {
                game.setScreen(new MenuScreen(game, false));
                return true;
            }
        });
        txfUsername = new TextField("", skin);
        txfUsername.setPosition(colWidth * 4, Gdx.graphics.getHeight() - rowHeight * 3);
        txfUsername.setSize(colWidth * 4, HEIGHT);
        stage.addActor(txfUsername);
        txfPassword = new TextField("", skin);
        txfPassword.setPosition(colWidth * 4, Gdx.graphics.getHeight() - rowHeight * 4);
        txfPassword.setSize(colWidth * 4, HEIGHT);
        txfPassword.setPasswordCharacter('*');
        txfPassword.setPasswordMode(true);
        stage.addActor(txfPassword);
        stage.addActor(btn);
        stage.addActor(goBack);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Renders the screen.
     * @param delta frequency.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        stage.act(delta);
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

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }


}
