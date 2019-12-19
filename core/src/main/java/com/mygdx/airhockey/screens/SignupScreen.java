package com.mygdx.airhockey.screens;

        import com.badlogic.gdx.Game;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


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
}
