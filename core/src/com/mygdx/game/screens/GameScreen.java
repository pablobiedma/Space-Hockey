package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Config;
import com.mygdx.game.movement.MovementController;
import com.mygdx.game.physics.CollisionListener;
import com.mygdx.game.physics.CoordinateTranslator;

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

    Label score;
    Rectangle goal1;
    Rectangle goal2;
    int scoreA;
    int scoreB;
    public GameScreen() {
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        stage = new Stage();
        initializeSprites();
        initializeBodies(Config.VIEWPORT_SIZE);
        initializeFixtures();
        initializeWalls(Config.WALL_WIDTH, Config.WALL_HEIGHT, Config.WALL_THICKNESS);
        initializeMovementControllers();
        initializeGoals();
        initializeUI();

        scoreA = 0;
        scoreB = 0;


        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Config.VIEWPORT_SIZE, Config.VIEWPORT_SIZE);
    }

    /**
     * Initializes UI elements.
     */
    private void initializeUI() {
        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        score = new Label("5-5",mySkin);
        score.setSize(100,20);
        score.setPosition(450, 200);
        score.setFontScale(5);
        score.setColor(Color.RED);
        score.setAlignment(Align.center);
        stage.addActor(score);
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

    private void initializeGoals() {
         goal1 = new Rectangle(-Config.WALL_WIDTH,
                                        -Config.WALL_HEIGHT / 2,
                                        Config.WALL_THICKNESS,
                                        Config.VIEWPORT_SIZE);
         goal1.setWidth(.8f);
         goal1.setHeight(Config.WALL_HEIGHT - 0.5f);
         goal2 = new Rectangle(Config.WALL_WIDTH - 0.4f,
                -Config.WALL_HEIGHT / 2,
                Config.WALL_THICKNESS,
                Config.VIEWPORT_SIZE);
        goal2.setWidth(.9f);
        goal2.setHeight(Config.WALL_HEIGHT);
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
                CoordinateTranslator.translateX(pitchSprite, 0),
                CoordinateTranslator.translateY(pitchSprite, 0));
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
        stage.clear();
        camera.update();
        world.step(1/60f, 6, 2);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        score.setText(scoreA + "-" + scoreB);
        stage.addActor(score);
        bluePaddleController.updateVelocity();
        redPaddleController.updateVelocity();

        drawSprites();
        Circle collisionTestCirlce = new Circle();
        collisionTestCirlce.set(puckBody.getPosition().x
                , puckBody.getPosition().y
                , Config.PUCK_RADIUS);

        if(Intersector.overlaps(collisionTestCirlce, goal1) ||
                Intersector.overlaps(collisionTestCirlce, goal2)) {
            if (Intersector.overlaps(collisionTestCirlce, goal1))
                scoreB++;
            if (Intersector.overlaps(collisionTestCirlce, goal2))
                scoreA++;

            puckBody.setTransform(0, 0, puckBody.getAngle());
            puckBody.setLinearVelocity(0, 0);

            bluePaddleBody.setTransform(-Config.WALL_WIDTH + 1f, 0
                    , bluePaddleBody.getAngle());
            bluePaddleBody.setLinearVelocity(0,0);
            redPaddleBody.setTransform(Config.WALL_WIDTH - 10f, 0
                    , redPaddleBody.getAngle());
            redPaddleBody.setLinearVelocity(0,0);


        }

        debugRenderer.render(world, camera.combined);
        stage.draw();
    }

    private void drawSprites() {
        batch.begin();
        pitchSprite.draw(batch);

        puckSprite.setPosition(
                CoordinateTranslator.translateX(puckSprite, puckBody.getPosition().x),
                CoordinateTranslator.translateY(puckSprite, puckBody.getPosition().y));
        puckSprite.draw(batch);

        redPaddleSprite.setPosition(
                CoordinateTranslator.translateX(redPaddleSprite, redPaddleBody.getPosition().x),
                CoordinateTranslator.translateY(redPaddleSprite, redPaddleBody.getPosition().y));
        redPaddleSprite.draw(batch);

        bluePaddleSprite.setPosition(
                CoordinateTranslator.translateX(bluePaddleSprite, bluePaddleBody.getPosition().x),
                CoordinateTranslator.translateY(bluePaddleSprite, bluePaddleBody.getPosition().y));
        bluePaddleSprite.draw(batch);

        batch.end();
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
