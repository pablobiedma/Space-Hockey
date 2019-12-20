package com.mygdx.airhockey.backend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.movement.KeyCodeSet;
import com.mygdx.airhockey.movement.MovementController;

/**
 * Class that handles the backend of the com.mygdx.airhockey.game.
 */
public class GameOperator {
    Pitch pitch;
    Paddle redPaddle;
    Paddle bluePaddle;
    Puck puck;

    /**
     * Constructor for game operator.
     *
     * @param pitch      pitch for the game.
     * @param redPaddle  in the game.
     * @param bluePaddle in the game.
     * @param puck       in the game.
     */
    public GameOperator(Pitch pitch, Paddle redPaddle, Paddle bluePaddle, Puck puck) {
        this.pitch = pitch;
        this.redPaddle = redPaddle;
        this.bluePaddle = bluePaddle;
        this.puck = puck;
    }

    /**
     * Set's up a new game.
     */
    public GameOperator(World world) {
        this.redPaddle = makePaddle(world, new Texture(Config.RED_PADDLE_TEXTURE_PATH), Config.RED_PADDLE_X, Config.RED_PADDLE_KEYS);
        this.bluePaddle = makePaddle(world, new Texture(Config.BLUE_PADDLE_TEXTURE_PATH), Config.BLUE_PADDLE_X, Config.BLUE_PADDLE_KEYS);
        this.puck = makePuck(world);
        this.pitch = makePitch(world);
    }

    private Paddle makePaddle(World world, Texture texture, float posX, KeyCodeSet keyCodeSet) {
        Sprite paddleSprite = createSprite(texture,
                CoordinateTranslator.translateSize(2 * Config.PADDLE_RADIUS),
                CoordinateTranslator.translateSize(2 * Config.PADDLE_RADIUS));
        Body paddleBody = createBody(world, posX, 0);
        FixtureDef paddleFixtureDef = createFixtureDef(new CircleShape(), Config.PADDLE_RADIUS,
                Config.PADDLE_DENSITY, Config.PADDLE_FRICTION, Config.PADDLE_RESTITUTION);
        paddleBody.createFixture(paddleFixtureDef);
        return new Paddle(paddleSprite, paddleBody, new MovementController(keyCodeSet));
    }

    private Puck makePuck(World world) {
        Sprite puckSprite = createSprite(new Texture(Config.PUCK_TEXTURE_PATH),
                CoordinateTranslator.translateSize(2 * Config.PUCK_RADIUS),
                CoordinateTranslator.translateSize(2 * Config.PUCK_RADIUS));
        Body puckBody = createBody(world, 0, 0);
        FixtureDef puckFixtureDef = createFixtureDef(new CircleShape(), Config.PUCK_RADIUS,
                Config.PUCK_DENSITY, Config.PUCK_FRICTION, Config.PUCK_RESTITUTION);
        puckBody.createFixture(puckFixtureDef);
        return new Puck(puckSprite, puckBody);
    }

    private Pitch makePitch(World world) {
        Sprite pitchSprite = createSprite(new Texture(Config.PITCH_TEXTURE_PATH),
                CoordinateTranslator.translateSize(2 * Config.WALL_WIDTH),
                CoordinateTranslator.translateSize(2 * Config.WALL_HEIGHT));

        pitchSprite.setPosition(CoordinateTranslator.translateX(pitchSprite,0), CoordinateTranslator.translateY(pitchSprite, 0));
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        Body pitchBody = world.createBody(bodyDef);

        float[] shape = {
                -Config.WALL_WIDTH, Config.WALL_HEIGHT, Config.WALL_WIDTH, Config.WALL_HEIGHT,
                Config.WALL_WIDTH, -Config.WALL_HEIGHT, -Config.WALL_WIDTH, -Config.WALL_HEIGHT
        };

        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(shape);
        pitchBody.createFixture(chainShape, 0);
        return new Pitch(pitchSprite, pitchBody);
    }

    /**
     * Updates physics of the game.
     */
    public void updatePhysics() {
        bluePaddle.updateVelocity();
        redPaddle.updateVelocity();
    }

    /**
     * Draws sprites on a batch.
     *
     * @param batch to draw on.
     */
    public void drawSprites(Batch batch) {
        batch.begin();

        pitch.draw(batch);
        puck.draw(batch);
        redPaddle.draw(batch);
        bluePaddle.draw(batch);

        batch.end();
    }

    /**
     * Creates a sprite.
     *
     * @param texture for the sprite.
     * @param width   of the desired sprite.
     * @param height  of the desired sprite.
     * @return Sprite object.
     */
    public Sprite createSprite(Texture texture, float width, float height) {
        Sprite sprite = new Sprite(texture);
        sprite.setSize(width, height);
        return sprite;
    }

    /**
     * Creates a circular body.
     *
     * @param world to create in.
     * @param x     position of the body.
     * @param y     position of the body.
     * @return created body.
     */
    public Body createBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        return world.createBody(bodyDef);
    }

    /**
     * Creates a fixture definition.
     *
     * @param shape       of the fixture.
     * @param radius      of the fixture.
     * @param density     of the fixture.
     * @param friction    of the fixture.
     * @param restitution of the fixture.
     * @return created fixture definition.
     */
    public FixtureDef createFixtureDef(
            CircleShape shape, float radius, float density, float friction, float restitution) {
        shape.setRadius(radius);
        FixtureDef res = new FixtureDef();
        res.shape = shape;
        res.density = density;
        res.friction = friction;
        res.restitution = restitution;
        shape.dispose();
        return res;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Paddle getRedPaddle() {
        return redPaddle;
    }

    public void setRedPaddle(Paddle redPaddle) {
        this.redPaddle = redPaddle;
    }

    public Paddle getBluePaddle() {
        return bluePaddle;
    }

    public void setBluePaddle(Paddle bluePaddle) {
        this.bluePaddle = bluePaddle;
    }

    public Puck getPuck() {
        return puck;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    public Pitch getWalls() {
        return pitch;
    }

    public void setWalls(Pitch pitch) {
        this.pitch = pitch;
    }
}
