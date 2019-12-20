package com.mygdx.airhockey.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PitchTest {

    private transient Pitch pitch;
    private transient Sprite sprite;
    private transient Body body;

    @BeforeEach
    void setUp() {
        sprite = Mockito.mock(Sprite.class);
        body = Mockito.mock(Body.class);
        pitch = new Pitch(sprite, body);
    }

    @Test
    void draw() {
        Batch batch = Mockito.mock(Batch.class);
        pitch.draw(batch);
        Mockito.verify(sprite, Mockito.times(1)).draw(batch);
    }
}