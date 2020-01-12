package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



class GoalTest {
    private transient Goal goal;
    private transient Puck puck;
    private transient Sprite sprite;
    private transient Body body;

    @BeforeEach
    void setUp() {
        goal = new Goal(0,0);
        body = Mockito.mock(Body.class);
        sprite = Mockito.mock(Sprite.class);
        Mockito.when(body.getPosition()).thenReturn(new Vector2(0,0));
        puck = new Puck(sprite, body);
    }

    @Test
    void checkForGoal() {
        Assertions.assertTrue(goal.checkForGoal(puck));

        body = Mockito.mock(Body.class);
        sprite = Mockito.mock(Sprite.class);
        Mockito.when(body.getPosition()).thenReturn(new Vector2(10,10));
        puck = new Puck(sprite, body);
        Assertions.assertFalse(goal.checkForGoal(puck));
    }

    @Test
    void getCollider() {
        Assertions.assertNotNull(goal.getCollider());
    }

    @Test
    void setCollider() {
        goal.setCollider(null);
        Assertions.assertNull(goal.getCollider());
    }
}