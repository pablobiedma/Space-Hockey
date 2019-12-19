package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.game_backend.Config;
import com.mygdx.game.game_backend.GameOperator;

/**
 * Game screen class - implements the game screen functionality.
 */
public class GameScreen extends ApplicationAdapter implements Screen {
    Game game;
    GameOperator gameOperator;
    Label scoreLabel;
    Stage stage;
    SpriteBatch batch;
    World world;
    Box2DDebugRenderer debugRenderer;
    Camera camera;


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
        stage = new Stage();
        initializeUI();
    }

    private void initializeUI() {
        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        scoreLabel = new Label("5-5",mySkin);
        scoreLabel.setSize(100,20);
        scoreLabel.setPosition(450, 200);
        scoreLabel.setFontScale(5);
        scoreLabel.setColor(Color.RED);
        scoreLabel.setAlignment(Align.center);
        stage.addActor(scoreLabel);
    }

    @Override
    /**
     * Shows the view.
     */
    public void show() {
        batch = new SpriteBatch();
    }

    @Override
    /**
     * Renders the view.
     */
    public void render(float delta) {
        stage.clear();
        camera.update();
        world.step(1/60f, 6, 2);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //gameOperator.drawSprites(batch);
        gameOperator.updatePhysics();
        debugRenderer.render(world, camera.combined);
        stage.draw();
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
