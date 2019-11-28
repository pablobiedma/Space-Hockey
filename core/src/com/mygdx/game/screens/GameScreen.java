package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGame;

public class GameScreen implements Screen {
    private Stage stage;
    private Game game;

    public GameScreen(Game aGame) {
        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        game = aGame;
        stage = new Stage(new ScreenViewport());

        Texture boardTexture = new Texture("sprite/sprite_air_hockey_table.png");
        Image board = new Image(boardTexture);
        board.rotateBy(90);
        board.setPosition(540, 220);
        board.scaleBy(-.7f);
        stage.addActor(board);

        //Puck thingy
        Texture puckTexture = new Texture("sprite/sprite_puck.png");
        Image puck = new Image(puckTexture);
        puck.setPosition(Gdx.input.getX(), Gdx.input.getY());
        puck.scaleBy(-0.8f);
        stage.addActor(puck);



        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Button quitButton = new TextButton("Quit game", mySkin);
        quitButton.setSize(col_width*4,row_height);
        quitButton.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*8);
        quitButton.addListener(new InputListener(){
            //add listener here
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //stage = new Stage(new ScreenViewport());
                System.exit(0);
                return true;
            }
        });
        stage.addActor(quitButton);

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getActors().get(1).setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        stage.act();
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
        stage.dispose();
    }
}
