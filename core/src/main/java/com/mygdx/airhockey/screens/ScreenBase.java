package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.airhockey.backend.Config;

public abstract class ScreenBase implements Screen {
    protected final Sound cheer = Gdx.audio.newSound(Gdx.files.internal("music/cheer.mp3"));
    protected static final Config config = Config.getInstance();
    transient TextureRegion backgroundTexture;

    transient Game game;
    transient SpriteBatch batch;
    transient Camera camera;
    transient Stage stage;

    protected static Sound sound;

    /**
     * Constructor.
     * @param game game which is used.
     * @param soundPath sound to use.
     * @param texturePath texture to use.
     */
    public ScreenBase(Game game, String soundPath, String texturePath) {
        this.sound = Gdx.audio.newSound(
                Gdx.files.internal(soundPath));
        backgroundTexture = new TextureRegion(new Texture(texturePath), 0, 0, 400, 400);
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage();
        this.sound.loop();

    }

    /**
     * Helps the renderer.
     */
    public void renderHelper() {
        Gdx.gl.glLineWidth(5);
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(
                backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.clear();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
