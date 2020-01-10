package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.GdxTestRunner;
import com.mygdx.airhockey.elements.Goal;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(GdxTestRunner.class)
public class HeadlessTest {

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
    public void createBody() {
        World world = new World(new Vector2(0,0), true);
        Gdx.gl = Mockito.mock(GL20.class);
        assertNotNull(gameOperator.createBody(world, 0,0));
    }

    @Test
    public void makePitchBody() {
        World world = new World(new Vector2(0,0), true);
        Gdx.gl = Mockito.mock(GL20.class);
        assertNotNull(gameOperator.getPitchBody(world));
    }
}