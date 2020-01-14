package com.mygdx.airhockey.backend;

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
import com.mygdx.airhockey.movement.AiMovementController;
import com.mygdx.airhockey.movement.KeyboardController;
//import com.mygdx.airhockey.movement.MouseController;
import com.mygdx.airhockey.movement.MovementController;

/**
 * Class that handles the backend of the com.mygdx.airhockey.game.
 */
public class GameOperator {
    private static boolean MULTIPLAYER = false;
    private static Config config = Config.getInstance();
    Pitch pitch;
    Paddle redPaddle;
    Paddle bluePaddle;
    Puck puck;
    Goal goalLeft;
    Goal goalRight;
    int scoreLeft;
    int scoreRight;
    public transient boolean isGoalScored;

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
        this.puck = makePuck(world);
        this.pitch = makePitch(world);
        this.redPaddle = makePaddle(world,
                config.redPaddleX,
              new KeyboardController(config.redPaddleKeys));
        MovementController opponentController = new AiMovementController(puck);
        if (MULTIPLAYER) {
            opponentController = new KeyboardController(config.bluePaddleKeys);
        }
        this.bluePaddle = makePaddle(world,
                config.bluePaddleX, opponentController);
        this.goalLeft = new Goal(- config.wallWidth - config.goalDepth, -config.goalWidth);
        this.goalRight = new Goal(config.wallWidth + config.goalDepth - 1, -config.goalWidth);
        this.scoreLeft = 0;
        this.scoreRight = 0;
    }

    final Paddle makePaddle(World world,
                      float posX, MovementController movementController) {
        Body paddleBody = createBody(world, posX, 0);
        FixtureDef paddleFixtureDef = createFixtureDef(new CircleShape(), config.paddleRadius,
                config.paddleDensity, config.paddleFriction, config.paddleRestitution);
        paddleBody.createFixture(paddleFixtureDef);
        return new Paddle(paddleBody, movementController);
    }

    final Puck makePuck(World world) {
        Body puckBody = createBody(world, 0, 0);
        FixtureDef puckFixtureDef = createFixtureDef(new CircleShape(), config.puckRadius,
                config.puckDensity, config.puckFriction, config.puckRestitution);
        puckBody.createFixture(puckFixtureDef);
        return new Puck(puckBody);
    }

    /**
     * creates a pitch body.
     * @param world to create in.
     * @return created body.
     */
    final Pitch makePitch(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        Body pitchBody = world.createBody(bodyDef);

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
        return new Pitch(pitchBody);
    }

    /**
     * Updates physics of the game.
     */
    public void updatePhysics() {
        if (goalLeft.checkForGoal(puck)) {
            scoreRight++;
            resetPositions();
            isGoalScored = true;

        } else if (goalRight.checkForGoal(puck)) {
            scoreLeft++;
            resetPositions();
            isGoalScored = true;
        }

        bluePaddle.updateVelocity();
        redPaddle.updateVelocity();
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
     * Checks if the game is finished by
     * comparing scores.
     * @return if game is finished.
     */
    public boolean checkGameFinished() {
        if (scoreLeft >= 10 || scoreRight >= 10) {
            return true;
        }
        return false;
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
