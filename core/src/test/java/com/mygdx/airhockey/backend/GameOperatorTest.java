package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.airhockey.backend.generators.BodyGenerator;
import com.mygdx.airhockey.elements.Goal;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.statistics.Level;
import com.mygdx.airhockey.statistics.Player;
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
        Player player = Mockito.mock(Player.class);
        gameOperator.level = new Level(player);
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
        assertEquals(1, gameOperator.getLevel().getRightGoals());

        Mockito.when(goalLeft.checkForGoal(puck)).thenReturn(false);
        Mockito.when(goalRight.checkForGoal(puck)).thenReturn(true);
        gameOperator.updatePhysics();
        assertEquals(1, gameOperator.getLevel().getLeftGoals());
    }

    @Test
    void testGameFinished() {
        assertFalse(gameOperator.isFinished());
        gameOperator.getLevel().setFinished(true);
        assertTrue(gameOperator.isFinished());
    }

    @Test
    void createFixtureDef() {
        CircleShape circleShape = Mockito.mock(CircleShape.class);
        FixtureDef fixtureDef = BodyGenerator.createFixtureDef(circleShape,0,0,0,0);
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
        assertEquals(redPaddle, gameOperator.getControllerManager().getRedPaddle());
    }

    @Test
    void setRedPaddle() {
        gameOperator.getControllerManager().setRedPaddle(null);
        assertNull(gameOperator.getControllerManager().getRedPaddle());
    }

    @Test
    void getBluePaddle() {
        assertEquals(bluePaddle, gameOperator.getControllerManager().getBluePaddle());
    }

    @Test
    void setBluePaddle() {
        gameOperator.getControllerManager().setBluePaddle(null);
        assertNull(gameOperator.getControllerManager().getBluePaddle());
    }

    @Test
    void getPuck() {
        assertEquals(puck, gameOperator.getControllerManager().getPuck());
    }

    @Test
    void setPuck() {
        gameOperator.getControllerManager().setPuck(null);
        assertNull(gameOperator.getControllerManager().getPuck());
    }

    @Test
    void getGoalLeft() {
        assertEquals(goalLeft, gameOperator.getGoalManager().getGoalLeft());
    }

    @Test
    void setGoalLeft() {
        gameOperator.getGoalManager().setGoalLeft(null);
        assertNull(gameOperator.getGoalManager().getGoalLeft());
    }

    @Test
    void getGoalRight() {
        assertEquals(gameOperator.getGoalManager().getGoalRight(), goalRight);
    }

    @Test
    void setGoalRight() {
        gameOperator.getGoalManager().setGoalRight(null);
        assertNull(gameOperator.getGoalManager().getGoalRight());
    }

    @Test
    void resetTest() {
        gameOperator.getControllerManager().resetPositions();
        Mockito.verify(puck, Mockito.times(1)).resetPosition(0, 0);
    }

}