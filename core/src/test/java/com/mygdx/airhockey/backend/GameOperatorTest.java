package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.elements.Walls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GameOperatorTest {
    private transient GameOperator gameOperator;
    private transient Pitch pitch;
    private transient Paddle redPaddle;
    private transient Paddle bluePaddle;
    private transient Puck puck;
    private transient Walls walls;

    @BeforeEach
    void setUp() {
        pitch = Mockito.mock(Pitch.class);
        redPaddle = Mockito.mock(Paddle.class);
        bluePaddle = Mockito.mock(Paddle.class);
        puck = Mockito.mock(Puck.class);
        walls = Mockito.mock(Walls.class);
        gameOperator = new GameOperator(pitch, redPaddle, bluePaddle, puck, walls);
    }

    @Test
    void updatePhysics() {
        gameOperator.updatePhysics();
        Mockito.verify(bluePaddle, Mockito.times(1)).updateVelocity();
        Mockito.verify(redPaddle, Mockito.times(1)).updateVelocity();
    }

    @Test
    void drawSprites() {
        Batch batch = Mockito.mock(Batch.class);
        gameOperator.drawSprites(batch);
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
    void getWalls() {
        assertEquals(walls,gameOperator.getWalls());
    }

    @Test
    void setWalls() {
        gameOperator.setWalls(null);
        assertNull(gameOperator.getWalls());
    }
}