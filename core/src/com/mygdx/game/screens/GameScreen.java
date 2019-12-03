package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Config;
import com.mygdx.game.movement.MovementController;

/**
 * Game screen class - implements the game screen functionality.
 */
public class GameScreen extends ApplicationAdapter implements Screen {
    SpriteBatch batch;
    Sprite redPaddleSprite;
    Sprite bluePaddleSprite;
    Sprite puckSprite;
    Sprite pitchSprite;
    Body redPaddleBody;
    Body bluePaddleBody;
    Body puckBody;
    MovementController bluePaddleController;
    MovementController redPaddleController;
    World world;
    Box2DDebugRenderer debugRenderer;
    Camera camera;
    Stage stage;

    public GameScreen() {
        Box2D.init();
        world = new World(new Vector2(0, 0), true);

        initializeSprites();
        initializeBodies(Config.VIEWPORT_SIZE);
        initializeFixtures();
        initializeWalls(Config.WALL_WIDTH, Config.WALL_HEIGHT, Config.WALL_THICKNESS);
        initializeMovementControllers();

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Config.VIEWPORT_SIZE, Config.VIEWPORT_SIZE);
    }

    private void initializeMovementControllers() {
        bluePaddleController = new MovementController(bluePaddleBody, Config.BLUE_PADDLE_KEYS, false);
        redPaddleController = new MovementController(redPaddleBody, Config.RED_PADDLE_KEYS, true);
    }

    private void initializeFixtures() {

        //initialize puck and paddles
        FixtureDef paddleFixtureDef = getFixtureDef(new CircleShape(), Config.PADDLE_RADIUS,
                Config.PADDLE_DENSITY, Config.PADDLE_FRICTION, Config.PADDLE_RESTITUTION);
        FixtureDef puckFixtureDef = getFixtureDef(new CircleShape(), Config.PUCK_RADIUS,
                Config.PUCK_DENSITY, Config.PUCK_FRICTION, Config.PUCK_RESTITUTION);

        redPaddleBody.createFixture(paddleFixtureDef);
        bluePaddleBody.createFixture(paddleFixtureDef);
        puckBody.createFixture(puckFixtureDef);
    }

    private FixtureDef getFixtureDef(CircleShape shape, float radius, float density, float friction, float restitution) {
        shape.setRadius(radius);

        FixtureDef res = new FixtureDef();
        res.shape = shape;
        res.density = density;
        res.friction = friction;
        res.restitution = restitution;
        shape.dispose();
        return res;
    }

    public void initializeBodies(float viewportSize) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(0, 0);
        puckBody  = world.createBody(bodyDef);

        bodyDef.position.set(viewportSize / 4, 0);
        bluePaddleBody  = world.createBody(bodyDef);

        bodyDef.position.set(-viewportSize / 4, 0);
        redPaddleBody = world.createBody(bodyDef);
    }

    /**
     * Initializes walls.
     */
    private void initializeWalls(float width, float height, float thickness) {
        initializeWall(0, -height,
                width, thickness);
        initializeWall(0, height,
                width, thickness);
        initializeWall(width, 0,
                thickness, height);
        initializeWall(-width, 0,
                thickness, height);
    }

    /**
     * Initializes a wall.
     * @param x position of the centre of the wall.
     * @param y position of the centre of the wall.
     * @param width of the wall.
     * @param height of the wall.
     */
    private void initializeWall(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        Body wallBody  = world.createBody(bodyDef);

        PolygonShape wall = new PolygonShape();
        wall.setAsBox(width, height);
        wallBody.createFixture(wall, 0);
    }


    /**
     * Initializes the sprites.
     */
    private void initializeSprites() {
        Texture bluePaddleTexture = new Texture("sprite/blue-paddle.png");
        bluePaddleSprite = new Sprite(bluePaddleTexture);
        float paddleSize = 2 * Config.PADDLE_RADIUS / Config.VIEWPORT_SIZE * Config.RESOLUTION;
        bluePaddleSprite.setSize(paddleSize, paddleSize);

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Texture redPaddleTexture = new Texture("sprite/red-paddle.png");
        redPaddleSprite = new Sprite(redPaddleTexture);
        redPaddleSprite.setSize(paddleSize, paddleSize);

        Texture puckTexture = new Texture("sprite/puck.png");
        puckSprite = new Sprite(puckTexture);
        float puckSize = 2 * Config.PUCK_RADIUS / Config.VIEWPORT_SIZE * Config.RESOLUTION;
        puckSprite.setSize(puckSize, puckSize);

        Texture pitchTexture = new Texture("sprite/pitch.png");
        pitchSprite = new Sprite(pitchTexture);
        float pitchWidth = 2 * Config.WALL_WIDTH / Config.VIEWPORT_SIZE * Config.RESOLUTION;
        float pitchHeight = 2 * Config.WALL_HEIGHT / Config.VIEWPORT_SIZE * Config.RESOLUTION;
        pitchSprite.setSize(pitchWidth, pitchHeight);
        pitchSprite.setPosition(
                translateCoordinateX(pitchSprite, 0),
                translateCoordinateY(pitchSprite, 0));
    }

    @Override
    /**
     * Shows the view.
     */
    public void show() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
    }

    @Override
    /**
     * Renders the view.
     */
    public void render(float delta) {
        camera.update();
        world.step(1/60f, 6, 2);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        pitchSprite.draw(batch);

        puckSprite.setPosition(translateCoordinateX(puckSprite, puckBody.getPosition().x), translateCoordinateY(puckSprite, puckBody.getPosition().y));
        puckSprite.draw(batch);

        redPaddleSprite.setPosition(translateCoordinateX(redPaddleSprite, redPaddleBody.getPosition().x),
                translateCoordinateY(redPaddleSprite, redPaddleBody.getPosition().y));
        redPaddleSprite.draw(batch);

        bluePaddleSprite.setPosition(translateCoordinateX(bluePaddleSprite, bluePaddleBody.getPosition().x),
                translateCoordinateY(bluePaddleSprite, bluePaddleBody.getPosition().y));
        bluePaddleSprite.draw(batch);

        batch.end();

        bluePaddleController.updateVelocity();
        redPaddleController.updateVelocity();
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

    private float translateCoordinateX(Sprite s, float x) {
        return Config.RESOLUTION / 2 + x / Config.VIEWPORT_SIZE * Config.RESOLUTION - s.getWidth() / 2;
    }

    private float translateCoordinateY(Sprite s, float y) {
        return Config.RESOLUTION / 2 + y / Config.VIEWPORT_SIZE * Config.RESOLUTION - s.getHeight() / 2;
    }
}
