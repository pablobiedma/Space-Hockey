package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.airhockey.auth.Authentication;
import com.mygdx.airhockey.database.ConnectionFactory;
import com.mygdx.airhockey.database.DatabaseController;


public class LoginScreen extends AuthScreen {

    /**
     * Constructor for login screen.
     *
     * @param g game of the login screen;
     */
    public LoginScreen(Game g) {
        super(g);
        createBtn("Login", new ClickListener(){
            @Override
           public boolean touchDown(InputEvent e, float x, float y, int point, int button){

                    btnLoginClicked();
                    return true;
                }
        });

    }
    /**
     //     * Performs a check after clicking login button.
     //     */
    public void btnLoginClicked() {
        String username = txfUsername.getText();
        String password = txfPassword.getText();
        DatabaseController database = new DatabaseController(new ConnectionFactory());
        Authentication auth = new Authentication(database);
        if (auth.signIn(username, password)) {
            game.setScreen(new GameScreen(game));
        } else {
            System.out.println("Try again");
        }
    }
}
