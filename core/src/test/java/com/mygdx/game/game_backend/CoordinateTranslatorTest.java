package com.mygdx.game.game_backend;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateTranslatorTest {
    private transient Sprite sprite;
    @BeforeEach
    void setUp() {
        sprite = Mockito.mock(Sprite.class);
        Mockito.when(sprite.getHeight()).thenReturn(Config.RESOLUTION/2);
        Mockito.when(sprite.getWidth()).thenReturn(Config.RESOLUTION);
    }

    @Test
    void translateX() {
        assertEquals(0, CoordinateTranslator.translateX(sprite, 0));
    }

    @Test
    void translateY() {
        assertEquals(250, CoordinateTranslator.translateY(sprite, 0));
    }

    @Test
    void translateSize() {
        assertEquals(Config.RESOLUTION, CoordinateTranslator.translateSize(Config.VIEWPORT_SIZE));
    }
}
