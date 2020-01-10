package com.mygdx.airhockey.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.movement.MovementController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PaddleTest {
    private transient Paddle paddle;
    private transient Sprite sprite;
    private transient Body body;
    private transient MovementController movementController;

    @BeforeEach
    void setUp() {
        sprite = Mockito.mock(Sprite.class);
        body = Mockito.mock(Body.class);
        movementController = Mockito.mock(MovementController.class);
        paddle = new Paddle(sprite, body, movementController);
    }

    @Test
    void updateVelocity() {
        paddle.updateVelocity();
        Mockito.verify(movementController, Mockito.times(1)).updateVelocity(paddle.body);
    }

    @Test
    void getMovementController() {
        assertEquals(movementController, paddle.getMovementController());
    }

    @Test
    void setMovementController() {
        paddle.setMovementController(null);
        assertNull(paddle.getMovementController());
    }
}

