package com.mygdx.airhockey.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
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
import com.badlogic.gdx.utils.Align;
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
    transient Label goalScored;
    transient TextureRegion backgroundTexture;
    transient int time = 0;
    transient boolean clear = true;
    transient Player player;
    transient Level level;

    /**
     * Constructor for game screen class.
     * Creates the game screen object.
     */
    public GameScreen(Game game, Player player) {
        backgroundTexture = new TextureRegion(new Texture("background.gif"), 0, 0, 400, 400);
        this.game = game;
        this.player = player;
        this.level = new Level(player);
        Box2D.init();
        stage = new Stage();
        world = new World(new Vector2(0, 0), true);
        gameOperator = new GameOperator(world);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(config.viewportSize, config.viewportSize);
        shapeRenderer = new ShapeRenderer();
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("music/bensound-funkyelement.mp3"));
        sound.play();
        initializeUI();
    }

    private void initializeUI() {
        Skin mySkin = new Skin(Gdx.files.internal("Craftacular_UI_Skin/craftacular-ui.json"));
        score = new Label("5-5", mySkin);
        score.setSize(100, 20);
        score.setPosition(config.resolution / 2 - 45, 3 * config.resolution / 4);
        score.setFontScale(2);
        score.setColor(Color.WHITE);
        score.setAlignment(Align.center);

        timer = new Label("00:00", mySkin);
        timer.setSize(100, 20);
        timer.setPosition(config.resolution / 2 - 44, config.resolution / 4);
        timer.setFontScale(1);
        timer.setColor(Color.GOLD);
        timer.setAlignment(Align.center);

        goalScored = new Label("", mySkin);
        goalScored.setSize(100, 20);
        goalScored.setPosition(config.resolution / 2 - 44, config.resolution / 2);
        goalScored.setFontScale(2);
        goalScored.setColor(Color.RED);
        goalScored.setAlignment(Align.center);

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
        //gameOperator.drawSprites(batch);
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
        drawPuck();
        drawPaddles();

        loadTimeLabel();
        loadGoalLabel();
        score.setText(gameOperator.getScoreLeft() + "-" + gameOperator.getScoreRight());
        stage.addActor(score);
        stage.addActor(timer);
        stage.addActor(goalScored);

        stage.draw();
    }

    private void drawPitch() {
        Vector2 bottomLeftPitch = CoordinateTranslator.translatePosition(
                new Vector2(-config.wallWidth, -config.wallHeight));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(bottomLeftPitch.x, bottomLeftPitch.y,
                2 * CoordinateTranslator.translateSize(config.wallWidth),
                2 * CoordinateTranslator.translateSize(config.wallHeight));
        Vector2 middleLineStart = CoordinateTranslator.translatePosition(new Vector2(0, config.wallHeight));
        Vector2 middleLineEnd = CoordinateTranslator.translatePosition(new Vector2(0, -config.wallHeight));
        shapeRenderer.line(middleLineStart.x, middleLineStart.y, middleLineEnd.x, middleLineEnd.y);
        shapeRenderer.circle(config.resolution / 2, config.resolution / 2, CoordinateTranslator.translateSize(config.wallHeight) / 4);

        shapeRenderer.setColor(Color.WHITE);
        Vector2 leftGoal = CoordinateTranslator.translatePosition(new Vector2(-config.wallWidth - config.goalDepth, -config.goalWidth));
        Vector2 rightGoal = CoordinateTranslator.translatePosition(new Vector2(config.wallWidth, -config.goalWidth));
        shapeRenderer.rect(leftGoal.x, leftGoal.y, CoordinateTranslator.translateSize(config.goalDepth), CoordinateTranslator.translateSize(2 * config.goalWidth));
        shapeRenderer.rect(rightGoal.x, leftGoal.y, CoordinateTranslator.translateSize(config.goalDepth), CoordinateTranslator.translateSize(2 * config.goalWidth));
        shapeRenderer.end();
    }

    private void loadGoalLabel() {
        if (gameOperator.isGoalScored) {
            goalScored.setText("GOAL!!!");
            gameOperator.isGoalScored = false;
            clear = false;
        } else {

            if (!clear) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("music/cheer.mp3"));
                sound.play(1.0f);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goalScored.setText("");
                clear = true;
            }
        }
    }

    private void loadTimeLabel() {
        time++;
        String formatted = String.format("%02d:%02d", time / 3600, (time / 60) % 60);
        timer.setText(formatted);
    }

    private void drawPaddles() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        Vector2 redPaddlePosition = CoordinateTranslator.translatePosition(gameOperator.getRedPaddle().getBody().getPosition());
        Vector2 bluePaddlePosition = CoordinateTranslator.translatePosition(gameOperator.getBluePaddle().getBody().getPosition());
        shapeRenderer.circle(redPaddlePosition.x, redPaddlePosition.y, CoordinateTranslator.translateSize(config.paddleRadius));
        shapeRenderer.circle(bluePaddlePosition.x, bluePaddlePosition.y, CoordinateTranslator.translateSize(config.paddleRadius));
        shapeRenderer.end();
    }

    private void drawPuck() {
        Vector2 puckPosition = CoordinateTranslator.translatePosition(gameOperator.getPuck().getBody().getPosition());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.circle(puckPosition.x, puckPosition.y, CoordinateTranslator.translateSize(config.puckRadius), 64);
        shapeRenderer.end();

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(puckPosition.x, puckPosition.y, CoordinateTranslator.translateSize(config.puckRadius), 64);
        shapeRenderer.end();
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
