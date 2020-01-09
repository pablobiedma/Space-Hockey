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

    @BeforeEach
    void setUp() {
        body = Mockito.mock(Body.class);
        KeyCodeSet keyCodeSet = Config.getInstance().bluePaddleKeys;
        movementController = new MovementController(keyCodeSet);
    }

    @Test
    void touchesMiddleLineTest() {
        //two cases of overlapping
        Mockito.when(body.getPosition()).thenReturn(new Vector2(0.5f,0));
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(Config.getInstance().paddleSpeed, 0);

        Mockito.when(body.getPosition()).thenReturn(new Vector2(-0.5f,0));
        movementController.updateVelocity(body);
        Mockito.verify(body, times(1))
                .setLinearVelocity(-Config.getInstance().paddleSpeed, 0);

        //        //and one case of not overlapping
        //        //PROBLEM WITH THE GDX.INPUT -> THROWS NULL POINTER EXCEPTION
        //        Mockito.when(body.getPosition()).thenReturn(new Vector2(-5f,0));
        //        movementController.updateVelocity();
        //        Mockito.verify(body, times(1)).setLinearVelocity(0, 0);
    }

    @Test
    void getAndSetKeycodes() {
        Config config = Config.getInstance();
        assertEquals(config.bluePaddleKeys, movementController.getKeycodes());
        movementController.setKeycodes(config.redPaddleKeys);
        assertEquals(config.redPaddleKeys, movementController.getKeycodes());
    }
}