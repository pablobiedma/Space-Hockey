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
    transient Sprite sprite;
    transient Body body;
    transient Puck puck;

    @BeforeEach
    void setUp() {
        sprite = Mockito.mock(Sprite.class);
        body = Mockito.mock(Body.class);
        Mockito.when(body.getPosition()).thenReturn(new Vector2(0,0));
        puck = new Puck(sprite,body);
    }

    @Test
    void draw() {
        Batch batch = Mockito.mock(Batch.class);
        puck.draw(batch);
        Mockito.verify(sprite, Mockito.times(1)).draw(batch);
    }

    @Test
    void createFixtureDef() {
        CircleShape circleShape = Mockito.mock(CircleShape.class);
        FixtureDef fixtureDef = puck.createFixtureDef(circleShape,0,0,0,0);
        assertNotNull(fixtureDef);
    }

    @Test
    void createSprite() {
        Texture texture = Mockito.mock(Texture.class);
        Sprite s = puck.createSprite(texture, 0);
        assertNotNull(s);
    }

    @Test
    void createBody() {
    }

    @Test
    void getSprite() {
        assertEquals(sprite, puck.getSprite());
    }

    @Test
    void setSprite() {
        puck.setSprite(null);
        assertNull(puck.getSprite());
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