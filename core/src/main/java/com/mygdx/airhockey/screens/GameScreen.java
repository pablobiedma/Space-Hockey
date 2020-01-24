package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
public class GameScreen extends ScreenBase  {
    transient boolean multiplayer;
    transient ShapeRenderer shapeRenderer;
    transient ShapeDrawer shapeDrawer;
    transient GameOperator gameOperator;
    transient World world;
    transient Label score;
    transient Label timer;
    transient Label points;
    transient Label alert;
    transient int time = 0;
    transient boolean clear = true;
    transient Player player;
    transient Level level;

    /**
     * Constructor for game screen class.
     * Creates the game screen object.
     */
    public GameScreen(Game game, Player player, boolean multiplayer) {
        super(game, "music/bensound-funkyelement.mp3", "background.gif");
        this.game = game;
        this.multiplayer = multiplayer;
        this.player = player;
        this.level = new Level(player);
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        gameOperator = new GameOperator(world, player, multiplayer);
        shapeRenderer = new ShapeRenderer();
        shapeDrawer = new ShapeDrawer(shapeRenderer, batch);
        sound.loop();
        initializeUI();
    }

    /**
     * Initializes the UI.
     */
    private void initializeUI() {

        String skinPath = "Craftacular_UI_Skin/craftacular-ui.json";
        score = Utilities.initLabel(skinPath, "title", config.resolution / 2,
                3.2f * config.resolution / 4, 1, Color.WHITE);

        points = Utilities.initLabel(skinPath, "default", config.resolution / 2,
                0.8f * config.resolution / 4, 1, Color.WHITE);

        timer = Utilities.initLabel(skinPath, "default", config.resolution / 2,
                config.resolution / 4, 1, Color.GOLD);

        alert = Utilities.initLabel(skinPath, "title", config.resolution / 2,
                config.resolution / 2, 1.2f, Color.RED);
    }


    /**
     * Shows the view.
     */
    @Override
    public void show() {

    }

    /**
     * Renders the view.
     */
    @Override
    public void render(float delta) {
        renderHelper();
        world.step(1 / 60f, 6, 2);

        gameOperator.updatePhysics();

        shapeDrawer.drawPitch();
        shapeDrawer.drawElements(gameOperator);

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
            sound.stop();
            if (multiplayer) {
                game.setScreen(new MenuScreen(game, true));
            } else {
                game.setScreen(new EndGameScreen(
                        game, player,
                        "music/bensound-funkyelement.mp3", gameOperator.getLevel().getScore()));
            }

            sound.stop();
        }
    }

    /**
     * Loads the goal label.
     */
    private void loadGoalLabel() {
        if (gameOperator.getGoalManager().isGoalScored) {
            if (gameOperator.isFinished()) {
                alert.setText("GAME OVER!!!");
            } else {
                alert.setText("GOAL!!!");
            }

            gameOperator.getGoalManager().isGoalScored = false;
            clear = false;
        } else {
            if (!clear) {
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
        world.dispose();
    }
}
