package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.airhockey.elements.Goal;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GameOperatorTest {
    private transient GameOperator gameOperator;
    private transient Pitch pitch;
    private transient Paddle redPaddle;
    private transient Paddle bluePaddle;
    private transient Puck puck;
    private transient Goal goalLeft;
    private transient Goal goalRight;

    @BeforeEach
    void setUp() {
        redPaddle = Mockito.mock(Paddle.class);
        bluePaddle = Mockito.mock(Paddle.class);
        puck = Mockito.mock(Puck.class);
        pitch = Mockito.mock(Pitch.class);
        goalLeft = Mockito.mock(Goal.class);
        goalRight = Mockito.mock(Goal.class);
        gameOperator = new GameOperator(pitch, redPaddle, bluePaddle, puck, goalLeft, goalRight);
    }

    @Test
    void updatePhysics() {
        gameOperator.updatePhysics();
        Mockito.verify(bluePaddle, Mockito.times(1)).updateVelocity();
        Mockito.verify(redPaddle, Mockito.times(1)).updateVelocity();
        Mockito.verify(goalLeft, Mockito.times(1)).checkForGoal(puck);
        Mockito.verify(goalRight, Mockito.times(1)).checkForGoal(puck);

        Mockito.when(goalLeft.checkForGoal(puck)).thenReturn(true);
        gameOperator.updatePhysics();
        assertEquals(1, gameOperator.getScoreRight());

        Mockito.when(goalLeft.checkForGoal(puck)).thenReturn(false);
        Mockito.when(goalRight.checkForGoal(puck)).thenReturn(true);
        gameOperator.updatePhysics();
        assertEquals(1, gameOperator.getScoreLeft());
    }

    @Test
    void createFixtureDef() {
        CircleShape circleShape = Mockito.mock(CircleShape.class);
        FixtureDef fixtureDef = gameOperator.createFixtureDef(circleShape,0,0,0,0);
        assertNotNull(fixtureDef);
    }

    @Test
    void createBody() {
    }

    @Test
    void getPitch() {
        assertEquals(pitch, gameOperator.getPitch());
    }

    @Test
    void setPitch() {
        gameOperator.setPitch(null);
        assertNull(gameOperator.getPitch());
    }

    @Test
    void getRedPaddle() {
        assertEquals(redPaddle, gameOperator.getRedPaddle());
    }

    @Test
    void setRedPaddle() {
        gameOperator.setRedPaddle(null);
        assertNull(gameOperator.getRedPaddle());
    }

    @Test
    void getBluePaddle() {
        assertEquals(bluePaddle, gameOperator.getBluePaddle());
    }

    @Test
    void setBluePaddle() {
        gameOperator.setBluePaddle(null);
        assertNull(gameOperator.getBluePaddle());
    }

    @Test
    void getPuck() {
        assertEquals(puck, gameOperator.getPuck());
    }

    @Test
    void setPuck() {
        gameOperator.setPuck(null);
        assertNull(gameOperator.getPuck());
    }


    @Test
    void getandsetScoreLeft() {
        gameOperator.setScoreLeft(1);
        assertEquals(1, gameOperator.getScoreLeft());
    }

    @Test
    void getandsetScoreRight() {
        gameOperator.setScoreRight(1);
        assertEquals(1, gameOperator.getScoreRight());
    }

    @Test
    void getGoalLeft() {
        assertEquals(goalLeft, gameOperator.getGoalLeft());
    }

    @Test
    void setGoalLeft() {
        gameOperator.setGoalLeft(null);
        assertNull(gameOperator.getGoalLeft());
    }

    @Test
    void getGoalRight() {
        assertEquals(gameOperator.getGoalRight(), goalRight);
    }

    @Test
    void setGoalRight() {
        gameOperator.setGoalRight(null);
        assertNull(gameOperator.getGoalRight());
    }

    @Test
    void resetTest() {
        gameOperator.resetPositions();
        Mockito.verify(puck, Mockito.times(1)).resetPosition(0, 0);
    }

}