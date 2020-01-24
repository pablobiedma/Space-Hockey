package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.statistics.Player;

public class HowToPlayScreen extends ScreenBase {
    private static final TextureRegion backgroundTexture = new TextureRegion(
            new Texture("background.gif"), 0, 0, 400, 400);
    private transient BitmapFont font;
    private transient Skin skin;
    private transient Label controls;
    private transient Label scoring;
    private transient Config config = Config.getInstance();


    /**
     * Instantiates a menu screen.
     */
    public HowToPlayScreen(Game game, Player player, String soundPath) {
        super(game, soundPath, "background.gif");
        skin = new Skin(Gdx.files.internal("Craftacular_UI_Skin/craftacular-ui.json"));
        font = new BitmapFont(Gdx.files.internal("Craftacular_UI_Skin/text.fnt"), false);
        font.setColor(Color.WHITE);

        String skinPath = "Craftacular_UI_Skin/craftacular-ui.json";
        controls = Utilities.initLabel(skinPath, "title", config.resolution / 2,
                9 * config.resolution / 10, 1f, Color.GOLD);
        controls.setText("CONTROLS");

        scoring = Utilities.initLabel(skinPath, "title", config.resolution / 2,
                7 * config.resolution / 10, 1f, Color.GOLD);
        scoring.setText("SCORING");

        Button back = new TextButton("back to menu", skin);
        back.setSize(5 * config.resolution / 10, config.resolution / 12);
        back.setPosition(2.5f * config.resolution / 10, 2 * config.resolution / 12);
        back.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(
                        new PreGameScreen(game, "music/bensound-funkyelement.mp3", player));
                return true;
            }
        });
        stage.addActor(back);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        //stage.getBatch().draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth(),
        //        Gdx.graphics.getHeight());

        font.draw(stage.getBatch(),
                "Left player uses WASD to move his paddle."
                        + "\nRight paddle is steered by the AI in single player"
                        + "\nand by arrow keys in local multiplayer mode.",
                1.5f * config.resolution / 10, 8.5f * config.resolution / 10);

        font.draw(stage.getBatch(),
                "Game ends when one of the players scores 7 goals."
                        + "\nThe first who does that is the winner. "
                        + "\n\nPoints for the game are awarded following rules:"
                        + "\n+200 points for each goal scored"
                        + "\n-100 for each goal conceded"
                        + "\n+500 points for winning the game"
                        + "\n-300 for losing game"
                        + "\n\nPoints are only awarded for single player games.",
                1.5f * config.resolution / 10, 6.5f * config.resolution / 10);

        stage.addActor(scoring);
        stage.addActor(controls);
        stage.getBatch().end();
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

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
