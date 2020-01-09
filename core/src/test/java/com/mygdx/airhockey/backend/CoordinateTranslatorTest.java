package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public final class CoordinateTranslatorTest {
    private transient Sprite sprite;

    @BeforeEach
    void setUp() {
        sprite = Mockito.mock(Sprite.class);
        Mockito.when(sprite.getHeight()).thenReturn(Config.getInstance().resolution / 2);
        Mockito.when(sprite.getWidth()).thenReturn(Config.getInstance().resolution);
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
        assertEquals(Config.getInstance().resolution,
                CoordinateTranslator.translateSize(Config.getInstance().viewportSize));
    }
}
