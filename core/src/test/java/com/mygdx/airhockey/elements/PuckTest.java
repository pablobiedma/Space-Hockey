package com.mygdx.airhockey.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PuckTest {
    transient Body body;
    transient Puck puck;

    @BeforeEach
    void setUp() {
        body = Mockito.mock(Body.class);
        Mockito.when(body.getPosition()).thenReturn(new Vector2(0,0));
        puck = new Puck(body);
    }

    @Test
    void getBody() {
        assertEquals(body, puck.getBody());
    }

    @Test
    void setBody() {
        puck.setBody(null);
        assertNull(puck.getBody());
    }
}