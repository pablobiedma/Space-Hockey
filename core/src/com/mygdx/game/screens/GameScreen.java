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
import com.mygdx.game.movement.KeyCodeSet;
import com.mygdx.game.movement.MovementController;

/**
 * Game screen class - implements the game screen functionality.
 */
public class GameScreen extends ApplicationAdapter implements Screen {

    /**
     * Constants section:
     */
    public static final float VIEWPORT_SIZE = 50;

    public static final float PADDLE_RADIUS = 1;
    public static final float PADDLE_DENSITY = 0.5f;
    public static final float PADDLE_FRICTION = 0.1f;
    public static final float PADDLE_RESTITUTION = 0.1f;

    public static final float PUCK_RADIUS = 0.75f;
    public static final float PUCK_DENSITY = 0.5f;
    public static final float PUCK_FRICTION = 0.05f;
    public static final float PUCK_RESTITUTION = 0.9f;

    public static final float WALL_HEIGHT = 10;
    public static final float WALL_WIDTH = 20;
    public static final float WALL_THICKNESS = 0.2f;

    public static final KeyCodeSet BLUE_PADDLE_KEYS = new KeyCodeSet(Input.Keys.DPAD_LEFT, Input.Keys.DPAD_RIGHT, Input.Keys.DPAD_UP, Input.Keys.DPAD_DOWN);
    public static final KeyCodeSet RED_PADDLE_KEYS = new KeyCodeSet(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S);
    public static final int RESOLUTION = 1000;

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
        initializeBodies();
        initializeFixtures();
        initializeWalls();
        initializeMovementControllers();

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(VIEWPORT_SIZE,VIEWPORT_SIZE);
    }

    private void initializeMovementControllers() {
        bluePaddleController = new MovementController(bluePaddleBody, BLUE_PADDLE_KEYS, false);
        redPaddleController = new MovementController(redPaddleBody, RED_PADDLE_KEYS, true);
    }

    private void initializeFixtures() {

        //initialize puck and paddles
        FixtureDef paddleFixtureDef = getFixtureDef(new CircleShape(), PADDLE_RADIUS, PADDLE_DENSITY, PADDLE_FRICTION, PADDLE_RESTITUTION);
        FixtureDef puckFixtureDef = getFixtureDef(new CircleShape(), PUCK_RADIUS, PUCK_DENSITY, PUCK_FRICTION, PUCK_RESTITUTION);

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

    private void initializeWalls() {
        initializeWall(0, -WALL_HEIGHT, WALL_WIDTH, WALL_THICKNESS);
        initializeWall(0, WALL_HEIGHT, WALL_WIDTH, WALL_THICKNESS);
        initializeWall(WALL_WIDTH, 0, WALL_THICKNESS, WALL_HEIGHT);
        initializeWall(-WALL_WIDTH, 0, WALL_THICKNESS, WALL_HEIGHT);
    }

    private void initializeWall(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        Body wallBody  = world.createBody(bodyDef);

        PolygonShape wall = new PolygonShape();
        wall.setAsBox(width, height);
        wallBody.createFixture(wall, 0);
    }

    private void initializeBodies() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(0, 0);
        puckBody  = world.createBody(bodyDef);

        bodyDef.position.set(VIEWPORT_SIZE/4, 0);
        bluePaddleBody  = world.createBody(bodyDef);

        bodyDef.position.set(-VIEWPORT_SIZE/4, 0);
        redPaddleBody = world.createBody(bodyDef);
    }

    /**
     * Initializes the sprites.
     */
    private void initializeSprites() {
        Texture bluePaddleTexture = new Texture("sprite/blue-paddle.png");
        bluePaddleSprite = new Sprite(bluePaddleTexture);
        bluePaddleSprite.setSize(2* PADDLE_RADIUS / VIEWPORT_SIZE * RESOLUTION, 2 * PADDLE_RADIUS / VIEWPORT_SIZE * RESOLUTION);

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
                getHeight());

        /*
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Button quitButton = new TextButton("Quit game", mySkin);
        quitButton.setSize(col_width*4,row_height);
        quitButton.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*8);
        quitButton.addListener(new InputListener(){
            //add listener here
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //stage = new Stage(new ScreenViewport());
                //System.exit(0);
                game.setScreen(new MenuScreen(game));
                return true;
            }
        });
        stage.addActor(quitButton);

         */
        Texture redPaddleTexture = new Texture("sprite/red-paddle.png");
        redPaddleSprite = new Sprite(redPaddleTexture);
        redPaddleSprite.setSize(2* PADDLE_RADIUS / VIEWPORT_SIZE * RESOLUTION, 2 * PADDLE_RADIUS / VIEWPORT_SIZE * RESOLUTION);

        Texture puckTexture = new Texture("sprite/puck.png");
        puckSprite = new Sprite(puckTexture);
        puckSprite.setSize(2 * PUCK_RADIUS / VIEWPORT_SIZE * RESOLUTION, 2 * PUCK_RADIUS / VIEWPORT_SIZE * RESOLUTION);

        Texture pitchTexture = new Texture("sprite/pitch.png");
        pitchSprite = new Sprite(pitchTexture);
        pitchSprite.setSize(2 * WALL_WIDTH/VIEWPORT_SIZE * RESOLUTION, 2 * WALL_HEIGHT/VIEWPORT_SIZE * RESOLUTION);
        pitchSprite.setPosition(translateCoordinateX(pitchSprite, 0), translateCoordinateY(pitchSprite, 0));
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


//        FOR SOME REASON THE TEXTURES WON'T WORK, TO DO
        batch.begin();
        pitchSprite.draw(batch);

        puckSprite.setPosition(translateCoordinateX(puckSprite, puckBody.getPosition().x), translateCoordinateY(puckSprite, puckBody.getPosition().y));
        puckSprite.draw(batch);

        redPaddleSprite.setPosition(translateCoordinateX(redPaddleSprite, redPaddleBody.getPosition().x),
                translateCoordinateY(redPaddleSprite, redPaddleBody.getPosition().y));
        redPaddleSprite.draw(batch);

        bluePaddleSprite.setPosition(translateCoordinateY(bluePaddleSprite, bluePaddleBody.getPosition().x),
                translateCoordinateX(bluePaddleSprite, bluePaddleBody.getPosition().y));
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
        return RESOLUTION/2 + x / VIEWPORT_SIZE * RESOLUTION - s.getWidth() / 2;
    }

    private float translateCoordinateY(Sprite s, float y) {
        return RESOLUTION/2 + y / VIEWPORT_SIZE * RESOLUTION - s.getHeight() / 2;
    }
}
