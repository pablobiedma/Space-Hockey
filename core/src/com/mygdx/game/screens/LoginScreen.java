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
    public static final int WIDTH = 300;
    public static final int HEIGHT = 40;
    private Game game;
    private Stage stage;
    private TextField txfUsername;
    private TextField txfPassword;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;

    public LoginScreen(Game g){
            game = g;
            stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        TextButton btnLogin = new TextButton("Login", skin);
        TextButton goBack = new TextButton("Back", skin);
        goBack.setPosition(col_width*5,Gdx.graphics.getHeight()-row_height*8);
        goBack.setSize(col_width*2,row_height-20);
        btnLogin.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*6);
        btnLogin.setSize(col_width*4,row_height);
        btnLogin.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button){
            btnLoginClicked();
            return true;
            }


        });
        goBack.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent e, float x, float y, int point, int button){
                game.setScreen(new MenuScreen(game));
                return true;
            }


        });
        txfUsername = new TextField("", skin);
        txfUsername.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*3);
        txfUsername.setSize(col_width*4, HEIGHT);
        stage.addActor(txfUsername);
        txfPassword = new TextField("", skin);
        txfPassword.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*4);
        txfPassword.setSize(col_width*4, HEIGHT);
        txfPassword.setPasswordCharacter('*');
        txfPassword.setPasswordMode(true);
//        char ch = new Character('*');
//
        stage.addActor(txfPassword);
        stage.addActor(btnLogin);
        stage.addActor(goBack);

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
