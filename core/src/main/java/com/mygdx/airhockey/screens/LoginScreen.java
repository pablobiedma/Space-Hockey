package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.airhockey.auth.Authentication;
import com.mygdx.airhockey.database.DatabaseController;
import com.mygdx.airhockey.database.TuDbConnectionFactory;
import com.mygdx.airhockey.statistics.Player;


public class LoginScreen extends AuthScreen {
    transient TextureRegion backgroundTexture;

    /**
     * Constructor for login screen.
     *
     * @param g game of the login screen;
     */
    public LoginScreen(Game g, Sound sound) {
        super(g,sound);
        backgroundTexture = new TextureRegion(new Texture("background.gif"), 0, 0, 400, 400);

        createBtn("Log in", new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {

                btnLoginClicked();
                return true;
            }
        });

    }

    /**
     * Performs a check after clicking login button.
     */
    public void btnLoginClicked() {
        String username = txfUsername.getText();
        String password = txfPassword.getText();
        DatabaseController database = new DatabaseController(new TuDbConnectionFactory());
        Authentication auth = new Authentication(database);
        if (auth.signIn(username, password)) {
            Player player = new Player(username, database.getPersonalTopScore(username));
            game.setScreen(new PreGameScreen(game,sound, player));
            sound.stop();
        } else {
            txfUsername.setColor(Color.RED);
            txfPassword.setColor(Color.RED);
        }
    }

    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0,Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
    }
}
