package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.database.DatabaseController;
import com.mygdx.airhockey.database.TuDbConnectionFactory;
import com.mygdx.airhockey.statistics.Player;

import java.util.List;

public class PreGameScreen extends ScreenBase {
    private transient Player player;
    private transient Label[][] leaderboard = new Label[5][2];
    private transient List<Player> players;


    /**
     * Instantiates a menu screen.
     */
    public PreGameScreen(Game game, String soundPath, Player player) {
        super(game, soundPath, "background.gif");
        this.player = player;
        Skin skin = new Skin(Gdx.files.internal("Craftacular_UI_Skin/craftacular-ui.json"));

        Label logo = new Label("LEADERBOARD", skin, "title");
        logo.setFontScale(1);
        logo.setAlignment(Align.center);
        int rowHeight = Gdx.graphics.getWidth() / 12;
        logo.setPosition(Config.getInstance().resolution / 2
                - logo.getWidth() / 2, Gdx.graphics.getHeight() - rowHeight * 2);
        logo.setColor(Color.GOLD);
        stage.addActor(logo);

        int colWidth = Gdx.graphics.getWidth() / 12;

        DatabaseController databaseController = new DatabaseController(new TuDbConnectionFactory());
        players = databaseController.getTopNScores(5);
        for (int i = 0; i < 5; i++) {
            leaderboard[i][0] = new Label(players.get(i).getUsername(), skin);
            leaderboard[i][0].setPosition(colWidth * 3,
                    Gdx.graphics.getHeight() - rowHeight * (3 + i * 0.5f));
            stage.addActor(leaderboard[i][0]);
            leaderboard[i][1] = new Label(players.get(i).getPoints() + "", skin);
            leaderboard[i][1].setPosition(colWidth * 8,
                    Gdx.graphics.getHeight() - rowHeight * (3 + i * 0.5f));
            leaderboard[i][1].setColor(Color.GOLD);
            stage.addActor(leaderboard[i][1]);
        }

        Button singlePlayerGame = new TextButton("new singleplayer game", skin);
        singlePlayerGame.setSize(colWidth * 6, rowHeight);
        singlePlayerGame.setPosition(colWidth * 3f, Gdx.graphics.getHeight() - rowHeight * 7);
        singlePlayerGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(
                        new GameScreen(game, player, false));
                backgroundSound.stop();
                return true;
            }
        });
        stage.addActor(singlePlayerGame);

        Button multiPlayerGame = new TextButton("new multiplayer game", skin);
        multiPlayerGame.setSize(colWidth * 6, rowHeight);
        multiPlayerGame.setPosition(colWidth * 3f, Gdx.graphics.getHeight() - rowHeight * 8.5f);
        multiPlayerGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(
                        new GameScreen(game, player, true));
                backgroundSound.stop();
                return true;
            }
        });
        stage.addActor(multiPlayerGame);

        Button howToPlay = new TextButton("how to play", skin);
        howToPlay.setSize(colWidth * 6, rowHeight);
        howToPlay.setPosition(colWidth * 3f, Gdx.graphics.getHeight() - rowHeight * 10f);
        howToPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(
                        new HowToPlayScreen(game, player, "music/open-space.mp3"));
                return true;
            }
        });
        stage.addActor(howToPlay);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
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
