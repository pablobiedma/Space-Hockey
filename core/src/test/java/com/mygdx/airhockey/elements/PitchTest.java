package com.mygdx.airhockey.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PitchTest {
    private transient Pitch pitch;
    private transient Sprite sprite;

    @BeforeEach
    void setUp() {
        sprite = Mockito.mock(Sprite.class);
        pitch = new Pitch(sprite);
    }

    @Test
    void draw() {
        Batch batch = Mockito.mock(Batch.class);
        pitch.draw(batch);
        Mockito.verify(sprite, times(1)).draw(batch);
    }

    @Test
    void getSprite() {
        assertEquals(sprite, pitch.getSprite());
    }

    @Test
    void setSprite() {
        pitch.setSprite(null);
        assertNull(pitch.getSprite());
    }
}