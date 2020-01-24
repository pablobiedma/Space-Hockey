package com.mygdx.airhockey.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.elements.Puck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



class AiMovementControllerTest {
    private transient Body body;
    private transient Puck puck;
    private static Config config = Config.getInstance();
    private transient AiMovementController aiMovementController;

    @BeforeEach
    void setUp() {
        body = Mockito.mock(Body.class, Mockito.RETURNS_DEEP_STUBS);
        puck = Mockito.mock(Puck.class, Mockito.RETURNS_DEEP_STUBS);
        aiMovementController = new AiMovementController(puck);
    }

    @Test
    void updateVelocity() {
        Mockito.when(puck.getBody().getPosition()).thenReturn(new Vector2(3f,5f));
        aiMovementController.updateVelocity(body);
        System.out.println(MovementController.touchesMiddleLine(body));
        Mockito.verify(puck, Mockito.times(6)).getBody();
        Mockito.verify(body,Mockito.times(8)).getPosition();
        System.out.println(body);
    }

    @Test
    void bodyXSmaller() {
        Mockito.when(body.getPosition()).thenReturn(new Vector2(config.wallWidth
                - config.bluePaddleX / 4,0f));
        Mockito.when(puck.getBody().getPosition()).thenReturn(new Vector2(-5,0));
        //        Mockito.when(towardsDefault.len()).thenReturn(0.4f);
        System.out.println(MovementController.touchesMiddleLine(body));
        aiMovementController.updateVelocity(body);
        Mockito.verify(body,Mockito.times(1)).setLinearVelocity(0f, 0f);
    }

    @Test
    void bodyXGreater() {
        Mockito.when(body.getPosition()).thenReturn(new Vector2(config.wallWidth
                - config.bluePaddleX / 4,1f));
        Mockito.when(puck.getBody().getPosition()).thenReturn(new Vector2(-5,0));
        System.out.println(MovementController.touchesMiddleLine(body));
        System.out.println(puck.getBody().getPosition().x < 0);
        aiMovementController.updateVelocity(body);
        float x = config.wallWidth - config.bluePaddleX / 4
                - body.getPosition().x;
        float y = body.getPosition().y;
        Vector2 towardsDefault = new Vector2(x, -y);
        towardsDefault.setLength(config.paddleSpeed);
        System.out.println(config.paddleSpeed);
        Mockito.verify(body, Mockito.times(1)).setLinearVelocity(towardsDefault);
    }

    @Test
    void puckBehindAI() {
        Mockito.when(body.getPosition()).thenReturn(new Vector2(1f,0f));
        Mockito.when(puck.getBody().getPosition())
                .thenReturn(new Vector2(config.bluePaddleX + 1,0));
        aiMovementController.updateVelocity(body);
        float x = puck.getBody().getPosition().x - body.getPosition().x;
        float y = puck.getBody().getPosition().y - body.getPosition().y;
        Vector2 towardsPuck = new Vector2(x, y);
        towardsPuck.setLength(config.paddleSpeed);
        System.out.println(x);
        Mockito.when(puck.getBody().getLinearVelocity()).thenReturn(new Vector2(1.5f,0f));
        aiMovementController.updateVelocity(body);
        Mockito.verify(body,Mockito.times(2)).setLinearVelocity(towardsPuck);
    }

    @Test
    void towardGoal() {
        Mockito.when(body.getPosition()).thenReturn(new Vector2(19f,0f));
        Mockito.when(puck.getBody().getPosition())
                .thenReturn(new Vector2(config.bluePaddleX + 1,0));
        Mockito.when(puck.getBody().getLinearVelocity()).thenReturn(new Vector2(3f,0f));
        System.out.println(config.wallWidth);
        aiMovementController.updateVelocity(body);
        Mockito.verify(body,Mockito.times(1)).setLinearVelocity(0f,0f);
    }

    @Test
    void towardGoalElse() {
        Mockito.when(body.getPosition()).thenReturn(new Vector2(5f,0f));
        Mockito.when(puck.getBody().getPosition())
                .thenReturn(new Vector2(config.bluePaddleX + 1,0));
        Mockito.when(puck.getBody().getLinearVelocity()).thenReturn(new Vector2(3f,0f));
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        Vector2 towardsOwnGoal = new Vector2(config.wallWidth - x, -y);
        towardsOwnGoal.setLength(config.paddleSpeed);
        aiMovementController.updateVelocity(body);
        Mockito.verify(body,Mockito.times(1)).setLinearVelocity(towardsOwnGoal);
    }

}