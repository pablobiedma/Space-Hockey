package com.mygdx.airhockey.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.movement.KeyboardController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PaddleTest {
    private transient Paddle paddle;
    private transient Body body;
    private transient KeyboardController keyboardMovementController;

    @BeforeEach
    void setUp() {
        body = Mockito.mock(Body.class);
        keyboardMovementController = Mockito.mock(KeyboardController.class);
        paddle = new Paddle(body, keyboardMovementController);
    }

    @Test
    void updateVelocity() {
        paddle.updateVelocity();
        Mockito.verify(keyboardMovementController, Mockito.times(1)).updateVelocity(paddle.body);
    }

    @Test
    void getMovementController() {
        assertEquals(keyboardMovementController, paddle.getMovementController());
    }

    @Test
    void setMovementController() {
        paddle.setMovementController(null);
        assertNull(paddle.getMovementController());
    }
}

