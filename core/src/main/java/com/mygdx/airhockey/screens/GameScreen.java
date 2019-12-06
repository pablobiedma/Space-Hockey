package com.mygdx.airhockey.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.backend.GameOperator;

/**
 * Game screen class - implements the game screen functionality.
 */
public class GameScreen extends ApplicationAdapter implements Screen {
    transient Game game;
    transient GameOperator gameOperator;
    transient SpriteBatch batch;
    transient World world;
    transient Box2DDebugRenderer debugRenderer;
    transient Camera camera;

    /**
     * Constructor for game screen class. Creates the game screen object.
     */
    public GameScreen(Game game) {
        this.game = game;
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        gameOperator = new GameOperator(world);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Config.VIEWPORT_SIZE, Config.VIEWPORT_SIZE);
    }

    /**
     * Shows the view.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
    }

    /**
     * Renders the view.
     */
    @Override
    public void render(float delta) {
        camera.update();
        world.step(1 / 60f, 6, 2);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameOperator.drawSprites(batch);
        gameOperator.updatePhysics();
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void create() {

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
        world.dispose();
    }
}
