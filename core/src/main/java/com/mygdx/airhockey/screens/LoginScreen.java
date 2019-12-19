package com.mygdx.airhockey.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class LoginScreen extends AuthScreen {

    /**
     * Constructor for login screen.
     *
     * @param g game of the login screen;
     */
    public LoginScreen(Game g) {
        super(g);
        createBtn("Log in", new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button) {

                btnLoginClicked();
                return true;
            }
        });

    }
}
