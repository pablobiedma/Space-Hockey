package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGame;

public class GameScreen extends ApplicationAdapter implements Screen, InputProcessor {
    SpriteBatch batch;
    Sprite controller;
    Body puckBody;
    private Stage stage;
    private Game game;
    World world;
    Box2DDebugRenderer debugRenderer;
    Camera camera;
    Matrix4 debugMatrix;

    boolean drawSprite = true;
    final float PIXELS_TO_METERS = 100f;
    float torque = 2f;
    public GameScreen(Game aGame) {
        Box2D.init();
        batch = new SpriteBatch();

        Texture puckTexture = new Texture("sprite/sprite_air_hockey_controller.png");
        Image puck = new Image(puckTexture);
        controller = new Sprite(puckTexture);
        controller.setPosition(Gdx.input.getX(), Gdx.input.getY());

        world = new World(new Vector2(0, 0), true);
        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //game = aGame;
        //stage = new Stage(new ScreenViewport());

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //puckDef.position.set(Gdx.input.getX(), Gdx.input.getY());
        bodyDef.position.set(controller.getX(), controller.getY());
        puckBody  = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimensions
        //as our sprite
        shape.setAsBox(controller.getWidth()/2, controller.getHeight()/2);

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the
        //body
        // you also define it's properties like density, restitution and others
        //we will see shortly
        // If you are wondering, density and area are used to calculate over all
        //mass
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;

        Fixture fixture = puckBody.createFixture(fixtureDef);

        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();

        Gdx.input.setInputProcessor(this);
        /*
        Texture boardTexture = new Texture("sprite/sprite_air_hockey_table.png");
        Image board = new Image(boardTexture);
        board.setPosition(300, 150);
        board.scaleBy(-.5f);
        board.setWidth(1500);
        //stage.addActor(board);
        */



        /*
        //Puck thingy
        Texture puckTexture = new Texture("sprite/sprite_air_hockey_controller.png");
        Image puck = new Image(puckTexture);
        puck.setPosition(Gdx.input.getX(), Gdx.input.getY());
        puck.scaleBy(-0.8f);
        stage.addActor(puck);
        */


        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
                getHeight());

        /*
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
                //System.exit(0);
                game.setScreen(new MenuScreen(game));
                return true;
            }
        });
        stage.addActor(quitButton);

         */

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        camera.update();
        world.step(1/60f, 6, 2);
        puckBody.applyTorque(torque, true);
        controller.setPosition((puckBody.getPosition().x)
                , puckBody.getPosition().y);
        //controller.setRotation((float)Math.toDegrees(puckBody.getAngle()));

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);
        batch.begin();


        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            puckBody.setLinearVelocity(100f, 0f);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            puckBody.setLinearVelocity(-100f, 0f);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            puckBody.setLinearVelocity(0, 100f);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            puckBody.setLinearVelocity(-0, -100f);
        batch.draw(controller, controller.getX(), controller.getY());
        batch.end();
        /*
        stage.getActors().get(1).setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        if(stage.getActors().get(1).getX() > 100 && stage.getActors().get(1).getY() > 10) {

        }

         */
        //stage.act();
        //stage.draw();
        //debugRenderer.render(world, Gdx.graphics.get);
        //debugRenderer.render(world, debugMatrix);
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
        //stage.dispose();
        world.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // On right or left arrow set the velocity at a fixed rate in that
        //direction
        if(keycode == Input.Keys.RIGHT)
            puckBody.setLinearVelocity(1f, 0f);
        if(keycode == Input.Keys.LEFT)
            puckBody.setLinearVelocity(-1f,0f);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
