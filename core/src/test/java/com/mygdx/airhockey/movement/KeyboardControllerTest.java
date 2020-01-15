package com.mygdx.airhockey.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;

class KeyboardControllerTest {
    private transient KeyboardController movementController;
    private transient Body body;
    private transient Config config = Config.getInstance();
    private transient KeySet keySet;
    private transient Input input;

    @BeforeEach
    void setUp() {
        body = Mockito.mock(Body.class);
        keySet = new KeySet(1,2,3,4);
        input = Mockito.mock(Input.class);
        Mockito.when(body.getPosition()).thenReturn(new Vector2(10f,10f));
        Mockito.when(input.isKeyPressed(1)).thenReturn(false);
        Mockito.when(input.isKeyPressed(2)).thenReturn(false);
        Mockito.when(input.isKeyPressed(3)).thenReturn(false);
        Mockito.when(input.isKeyPressed(4)).thenReturn(false);
        movementController = new KeyboardController(keySet, input);
    }

    @Test
    void touchesMiddleLineTest() {
        //two cases of overlapping
        Mockito.when(body.getPosition()).thenReturn(new Vector2(0.5f,0));
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(config.paddleSpeed, 0);

        Mockito.when(body.getPosition()).thenReturn(new Vector2(-0.5f,0));
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(-config.paddleSpeed, 0);
    }

    @Test
    void getAndSetKeycodes() {
        Config config = Config.getInstance();
        assertEquals(keySet, movementController.getKeycodes());
        movementController.setKeycodes(config.redPaddleKeys);
        assertEquals(config.redPaddleKeys, movementController.getKeycodes());
    }

    @Test
    void moveLeft() {
        Mockito.when(input.isKeyPressed(1)).thenReturn(true);
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(-config.paddleSpeed, 0);
        Mockito.when(input.isKeyPressed(1)).thenReturn(false);
    }

    @Test
    void moveRight() {
        Mockito.when(input.isKeyPressed(2)).thenReturn(true);
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(config.paddleSpeed, 0);
        Mockito.when(input.isKeyPressed(2)).thenReturn(false);
    }

    @Test
    void moveUp() {
        Mockito.when(input.isKeyPressed(3)).thenReturn(true);
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(0, config.paddleSpeed);
        Mockito.when(input.isKeyPressed(3)).thenReturn(false);
    }

    @Test
    void moveDown() {
        Mockito.when(input.isKeyPressed(4)).thenReturn(true);
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(0, -config.paddleSpeed);
        Mockito.when(input.isKeyPressed(4)).thenReturn(false);
    }

    @Test
    void getAndSetInput() {
        assertEquals(input, movementController.getInput());
        movementController.setInput(null);
        assertNull(movementController.getInput());
    }
}