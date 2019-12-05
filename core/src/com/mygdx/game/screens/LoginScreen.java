package com.mygdx.game.screens;

//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class LoginScreen implements Screen {
    public static final int INPUT_BOX_X = 200;
    private Game game;
    private Stage stage;
    private TextField txfUsername;
    private TextField txfPassword;

    public LoginScreen(Game g){
            game = g;
            stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        TextButton btnLogin = new TextButton("Login", skin);
        btnLogin.setPosition(INPUT_BOX_X, 100);
        btnLogin.setSize(300, 60);
        btnLogin.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button){
            btnLoginClicked();
            return true;
            }


        });
        txfUsername = new TextField("", skin);
        txfUsername.setPosition(INPUT_BOX_X, 250);
        txfUsername.setSize(300, 40);
        stage.addActor(txfUsername);
        txfPassword = new TextField("", skin);
        txfPassword.setPosition(INPUT_BOX_X, 200);
        txfPassword.setSize(300, 40);
        txfPassword.setPasswordCharacter('*');
        txfPassword.setPasswordMode(true);
//        char ch = new Character('*');
//
        stage.addActor(txfPassword);
        stage.addActor(btnLogin);

    }
    public void btnLoginClicked() {
        String username = txfUsername.getText();
        String password = txfPassword.getText();
        if(username.equals("admin") && password.equals("pass")){
            game.setScreen(new GameScreen(game));
        }else{

            txfPassword.clear();
            txfPassword.clearSelection();
            System.out.println("Try again");

           //txfPassword.setStyle();
        }
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
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
