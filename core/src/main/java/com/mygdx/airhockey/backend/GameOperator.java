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
import com.mygdx.airhockey.elements.Goal;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.movement.KeyCodeSet;
import com.mygdx.airhockey.movement.MovementController;

/**
 * Class that handles the backend of the com.mygdx.airhockey.game.
 */
public class GameOperator {
    private static Config config = Config.getInstance();
    Pitch pitch;
    Paddle redPaddle;
    Paddle bluePaddle;
    Puck puck;
    Goal goalLeft;
    Goal goalRight;
    int scoreLeft;
    int scoreRight;

    /**
     * Constructor for game operator.
     *
     * @param pitch      pitch for the game.
     * @param redPaddle  in the game.
     * @param bluePaddle in the game.
     * @param puck       in the game.
     * @param goalLeft left goal.
     * @param goalRight right goal.
     */
    public GameOperator(Pitch pitch, Paddle redPaddle, Paddle bluePaddle, Puck puck,
                        Goal goalLeft, Goal goalRight) {
        this.pitch = pitch;
        this.redPaddle = redPaddle;
        this.bluePaddle = bluePaddle;
        this.puck = puck;
        this.goalLeft = goalLeft;
        this.goalRight = goalRight;
    }

    /**
     * Set's up a new game.
     */
    public GameOperator(World world) {
        this.redPaddle = makePaddle(world, new Texture(config.redPaddleTexturePath),
                config.redPaddleX, config.redPaddleKeys);
        this.bluePaddle = makePaddle(world, new Texture(config.bluePaddleTexturePath),
                config.bluePaddleX, config.bluePaddleKeys);
        this.puck = makePuck(world);
        this.pitch = makePitch(world, new Texture(config.pitchTexturePath));
        this.goalLeft = new Goal(- config.wallWidth - config.goalDepth, -config.goalWidth);
        this.goalRight = new Goal(config.wallWidth + config.goalDepth - 1, -config.goalWidth);
        this.scoreLeft = 0;
        this.scoreRight = 0;
    }

    Paddle makePaddle(World world, Texture texture, float posX, KeyCodeSet keyCodeSet) {
        Sprite paddleSprite = createSprite(texture,
                CoordinateTranslator.translateSize(2 * config.paddleRadius),
                CoordinateTranslator.translateSize(2 * config.paddleRadius));
        Body paddleBody = createBody(world, posX, 0);
        FixtureDef paddleFixtureDef = createFixtureDef(new CircleShape(), config.paddleRadius,
                config.paddleDensity, config.paddleFriction, config.paddleRestitution);
        paddleBody.createFixture(paddleFixtureDef);
        return new Paddle(paddleSprite, paddleBody, new MovementController(keyCodeSet));
    }

    private Puck makePuck(World world) {
        Sprite puckSprite = createSprite(new Texture(config.puckTexturePath),
                CoordinateTranslator.translateSize(2 * config.puckRadius),
                CoordinateTranslator.translateSize(2 * config.puckRadius));
        Body puckBody = createBody(world, 0, 0);
        FixtureDef puckFixtureDef = createFixtureDef(new CircleShape(), config.puckRadius,
                config.puckDensity, config.puckFriction, config.puckRestitution);
        puckBody.createFixture(puckFixtureDef);
        return new Puck(puckSprite, puckBody);
    }

    private Pitch makePitch(World world, Texture texture) {
        Sprite pitchSprite = createSprite(texture,
                CoordinateTranslator.translateSize(2 * config.wallWidth),
                CoordinateTranslator.translateSize(2 * config.wallHeight));

        pitchSprite.setPosition(CoordinateTranslator.translateX(pitchSprite, 0),
                CoordinateTranslator.translateY(pitchSprite, 0));
        Body pitchBody = getPitchBody(world);
        return new Pitch(pitchSprite, pitchBody);
    }

    /**
     * creates a pitch body.
     * @param world to create in.
     * @return created body.
     */
    Body getPitchBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        Body pitchBody = world.createBody(bodyDef);

        //pitchshape
        float[] shape = {
            -config.wallWidth, config.wallHeight,
            config.wallWidth, config.wallHeight,
            config.wallWidth, config.goalWidth,
            config.wallWidth + config.goalDepth, config.goalWidth,
            config.wallWidth + config.goalDepth, -config.goalWidth,
            config.wallWidth, -config.goalWidth,
            config.wallWidth, -config.wallHeight,
            -config.wallWidth, -config.wallHeight,
            -config.wallWidth, -config.goalWidth,
            -config.wallWidth - config.goalDepth, -config.goalWidth,
            -config.wallWidth - config.goalDepth, config.goalWidth,
            -config.wallWidth, config.goalWidth,
        };

        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(shape);
        pitchBody.createFixture(chainShape, 0);
        return pitchBody;
    }

    /**
     * Updates physics of the game.
     */
    public void updatePhysics() {
        if (goalLeft.checkForGoal(puck)) {
            scoreRight++;
            resetPositions();
        } else if (goalRight.checkForGoal(puck)) {
            scoreLeft++;
            resetPositions();
        }

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

    /**
     * Resets position of all elements to default.
     */
    public void resetPositions() {
        puck.resetPosition(0,0);
        bluePaddle.resetPosition(config.bluePaddleX, 0);
        redPaddle.resetPosition(config.redPaddleX, 0);
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

    public int getScoreLeft() {
        return scoreLeft;
    }

    public void setScoreLeft(int scoreLeft) {
        this.scoreLeft = scoreLeft;
    }

    public int getScoreRight() {
        return scoreRight;
    }

    public void setScoreRight(int scoreRight) {
        this.scoreRight = scoreRight;
    }

    public Goal getGoalLeft() {
        return goalLeft;
    }

    public void setGoalLeft(Goal goalLeft) {
        this.goalLeft = goalLeft;
    }

    public Goal getGoalRight() {
        return goalRight;
    }

    public void setGoalRight(Goal goalRight) {
        this.goalRight = goalRight;
    }
}
