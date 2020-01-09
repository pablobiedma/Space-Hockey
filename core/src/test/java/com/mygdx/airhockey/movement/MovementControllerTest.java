package com.mygdx.airhockey.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MovementControllerTest {
    private transient MovementController movementController;
    private transient Body body;
    private transient Config config = Config.getInstance();

    @BeforeEach
    void setUp() {
        body = Mockito.mock(Body.class);
        KeyCodeSet keyCodeSet = config.bluePaddleKeys;
        movementController = new MovementController(keyCodeSet);
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
        assertEquals(config.bluePaddleKeys, movementController.getKeycodes());
        movementController.setKeycodes(config.redPaddleKeys);
        assertEquals(config.redPaddleKeys, movementController.getKeycodes());
    }
}