package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.airhockey.auth.Authentication;
import com.mygdx.airhockey.database.ConnectionFactory;
import com.mygdx.airhockey.database.DatabaseController;


public class SignupScreen extends AuthScreen {

    /**
     * Constructor for signup screen.
     *
     * @param g game of the login screen;
     */
    public SignupScreen(Game g) {
        super(g);
        createBtn("Sign up", new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {

                btnSignupClicked();
                return true;
            }
        });
    }

    /**
     * Performs a check after clicking signup button.
     */
    public void btnSignupClicked() {
        String username = txfUsername.getText();
        String password = txfPassword.getText();
        DatabaseController database = new DatabaseController(new ConnectionFactory());
        Authentication auth = new Authentication(database);
        if (auth.signUp(username, password)) {
            game.setScreen(new GameScreen(game));
        } else {
            System.out.println("Try again");
        }
    }
}
