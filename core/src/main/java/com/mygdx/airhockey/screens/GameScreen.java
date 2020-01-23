package com.mygdx.airhockey.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.backend.CoordinateTranslator;
import com.mygdx.airhockey.backend.GameOperator;
import com.mygdx.airhockey.statistics.Level;
import com.mygdx.airhockey.statistics.Player;

//music Music: www.bensound.com"
//Open Space by | e s c p | https://escp-music.bandcamp.com
//        Music promoted by https://www.free-stock-music.com
//        Creative Commons Attribution 3.0 Unported License
//        https://creativecommons.org/licenses/by/3.0/deed.en_US

/**
 * Game screen class - implements the game screen functionality.
 */
public class GameScreen extends ApplicationAdapter implements Screen {
    private static final Config config = Config.getInstance();
    transient boolean multiplayer;
    transient ShapeRenderer shapeRenderer;
    transient Game game;
    transient GameOperator gameOperator;
    transient SpriteBatch batch;
    transient World world;
    transient Box2DDebugRenderer debugRenderer;
    transient Camera camera;
    transient Stage stage;
    transient Label score;
    transient Label timer;
    transient Label points;
    transient Label alert;
    transient TextureRegion backgroundTexture;
    transient int time = 0;
    transient boolean clear = true;
    transient Player player;
    transient Level level;
    private static final Sprite leftPaddleSprite =
            new Sprite(new Texture(config.redPaddleTexturePath));
    private static final Sprite rightPaddleSprite =
            new Sprite(new Texture(config.bluePaddleTexturePath));
    private static final Sprite puckSprite = new Sprite(new Texture(config.puckTexturePath));
    private static final Sound sound = Gdx.audio.newSound(
            Gdx.files.internal("music/bensound-funkyelement.mp3"));

    /**
     * Constructor for game screen class.
     * Creates the game screen object.
     */
    public GameScreen(Game game, Player player, boolean multiplayer) {
        backgroundTexture = new TextureRegion(new Texture("background.gif"), 0, 0, 400, 400);
        this.game = game;
        this.multiplayer = multiplayer;
        this.player = player;
        this.level = new Level(player);
        Box2D.init();
        stage = new Stage();
        world = new World(new Vector2(0, 0), true);
        gameOperator = new GameOperator(world, player, multiplayer);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(config.viewportSize, config.viewportSize);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        sound.loop();
        initializeUI();
    }

    /**
     * Initializes the UI.
     */
    private void initializeUI() {
        int paddleWidth = (int) CoordinateTranslator.translateSize(2 * config.paddleRadius);
        leftPaddleSprite.setSize(paddleWidth, paddleWidth);
        rightPaddleSprite.setSize(paddleWidth, paddleWidth);

        int puckWidth = (int) CoordinateTranslator.translateSize(2 * config.puckRadius);
        puckSprite.setSize(puckWidth, puckWidth);

        Skin skin = new Skin(Gdx.files.internal("Craftacular_UI_Skin/craftacular-ui.json"));
        score = Utilities.initLabel(skin, "title", config.resolution / 2,
                3.2f * config.resolution / 4, 1, Color.WHITE);

        points = Utilities.initLabel(skin, "default", config.resolution / 2,
                0.8f * config.resolution / 4, 1, Color.WHITE);

        timer = Utilities.initLabel(skin, "default", config.resolution / 2,
                config.resolution / 4, 1, Color.GOLD);

        alert = Utilities.initLabel(skin, "title", config.resolution / 2,
                config.resolution / 2, 1.2f, Color.RED);
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
        Gdx.gl.glLineWidth(5);

        stage.clear();
        camera.update();
        world.step(1 / 60f, 6, 2);
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameOperator.updatePhysics();

        stage.getBatch().begin();
        stage.getBatch().draw(
                backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        drawPitch();
        drawElements();

        loadTimeLabel();
        loadGoalLabel();

        score.setText(gameOperator.getLevel().getLeftGoals()
                + "-" + gameOperator.getLevel().getRightGoals());
        points.setText("Points: " + gameOperator.getLevel().getScore());
        if (!multiplayer) {
            stage.addActor(points);
        }
        stage.addActor(score);
        stage.addActor(timer);
        stage.addActor(alert);

        stage.draw();

        if (gameOperator.isFinished() && clear) {
            if (multiplayer) {
                game.setScreen(new MenuScreen(game, true));
            } else {
                game.setScreen(new EndGameScreen(
                        game, player,sound, gameOperator.getLevel().getScore()));
            }

            sound.stop();
        }
    }

    /**
     * Draws the pitch.
     */
    private void drawPitch() {
        Vector2 bottomLeftPitch = CoordinateTranslator.translatePosition(
                new Vector2(-config.wallWidth, -config.wallHeight));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(bottomLeftPitch.x, bottomLeftPitch.y,
                2 * CoordinateTranslator.translateSize(config.wallWidth),
                2 * CoordinateTranslator.translateSize(config.wallHeight));
        Vector2 middleLineStart =
                CoordinateTranslator.translatePosition(new Vector2(0, config.wallHeight));
        Vector2 middleLineEnd =
                CoordinateTranslator.translatePosition(new Vector2(0, -config.wallHeight));
        shapeRenderer.line(middleLineStart.x, middleLineStart.y, middleLineEnd.x, middleLineEnd.y);
        shapeRenderer.circle(config.resolution / 2, config.resolution / 2,
                CoordinateTranslator.translateSize(config.wallHeight) / 4);

        shapeRenderer.setColor(Color.WHITE);
        Vector2 leftGoal = CoordinateTranslator.translatePosition(
                new Vector2(-config.wallWidth - config.goalDepth, -config.goalWidth));
        Vector2 rightGoal = CoordinateTranslator.translatePosition(
                new Vector2(config.wallWidth, -config.goalWidth));
        shapeRenderer.rect(leftGoal.x, leftGoal.y,
                CoordinateTranslator.translateSize(config.goalDepth),
                CoordinateTranslator.translateSize(2 * config.goalWidth));
        shapeRenderer.rect(rightGoal.x, rightGoal.y,
                CoordinateTranslator.translateSize(config.goalDepth),
                CoordinateTranslator.translateSize(2 * config.goalWidth));
        shapeRenderer.end();
    }

    /**
     * Loads the goal label.
     */
    private void loadGoalLabel() {
        if (gameOperator.isGoalScored) {
            if (gameOperator.isFinished()) {
                alert.setText("GAME OVER!!!");
            } else {
                alert.setText("GOAL!!!");
            }

            gameOperator.isGoalScored = false;
            clear = false;
        } else {
            if (!clear) {
                Sound cheer = Gdx.audio.newSound(Gdx.files.internal("music/cheer.mp3"));
                cheer.play(1.0f);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                alert.setText("");
                clear = true;
            }
        }
    }

    /**
     * Loads the time label.
     */
    private void loadTimeLabel() {
        time++;
        String formatted = String.format("%02d:%02d", time / 3600, (time / 60) % 60);
        timer.setText(formatted);
    }


    private void drawPlanet(Sprite sprite, float radius, Vector2 position) {
        int width = (int) CoordinateTranslator.translateSize(2 * radius);
        Vector2 spritePos = CoordinateTranslator.translatePosition(position);
        sprite.setPosition(spritePos.x - width / 2, spritePos.y - width / 2);
        sprite.draw(batch);
    }

    /**
     * Draws paddles.
     */
    private void drawElements() {
        batch.begin();
        drawPlanet(leftPaddleSprite, config.paddleRadius,
                gameOperator.getRedPaddle().getBody().getPosition());
        drawPlanet(rightPaddleSprite, config.paddleRadius,
                gameOperator.getBluePaddle().getBody().getPosition());
        drawPlanet(puckSprite, config.puckRadius, gameOperator.getPuck().getBody().getPosition());
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
