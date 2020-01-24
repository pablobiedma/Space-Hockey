package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.airhockey.database.DatabaseController;
import com.mygdx.airhockey.database.TuDbConnectionFactory;
import com.mygdx.airhockey.statistics.Player;


public class EndGameScreen extends ScreenBase {
    private transient float score;
    protected transient Game game;
    protected transient Stage stage;
    protected transient TextField nickname;
    transient int rowHeight = Gdx.graphics.getWidth() / 12;
    transient int colWidth = Gdx.graphics.getWidth() / 12;
    protected transient Sound sound;
    private transient Player player;

    /**
     * Constructor for login screen.
     * @param g game of the login screen;
     */
    public EndGameScreen(Game g, Player player, String soundPath, float score) {
        super(g, soundPath, "background.gif");
        this.score = score;
        this.player = player;
        game = g;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        String skinPath = "Craftacular_UI_Skin/craftacular-ui.json";
        Label points = Utilities.initLabel(skinPath, "title", Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() - rowHeight * 3.5f, 1.5f, Color.RED);
        points.setText("" + score);
        stage.addActor(points);

        Label text = Utilities.initLabel(skinPath, "default", Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() - rowHeight * 2.25f, 1, Color.GOLD);
        text.setText("Your score:");
        stage.addActor(text);

        Label text2 = Utilities.initLabel(skinPath, "default", Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() - rowHeight * 5, 1, Color.GOLD);
        text2.setText("Enter a nickname to\nsave the score:");
        stage.addActor(text2);

        Skin skin = new Skin(Gdx.files.internal(skinPath));
        nickname = new TextField(player.getUsername(), skin);
        nickname.setPosition(colWidth * 3, rowHeight * 5);
        nickname.setSize(colWidth * 6, rowHeight);
        stage.addActor(nickname);

        Button save = new TextButton("save and go back to menu", skin);
        save.setSize(6 * colWidth, rowHeight);
        save.setPosition(3 * colWidth, 3.5f * rowHeight);
        save.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (nickname.getText().matches("[A-Za-z0-9_]{5,20}")) {
                    DatabaseController databaseController =
                            new DatabaseController(new TuDbConnectionFactory());
                    databaseController.addScore(player.getUsername(), score, nickname.getText());
                    game.setScreen(
                            new PreGameScreen(game, null, player));
                } else {
                    nickname.setColor(Color.RED);
                }
                return true;
            }
        });
        stage.addActor(save);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Renders the screen.
     *
     * @param delta frequency.
     */
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

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }


}
