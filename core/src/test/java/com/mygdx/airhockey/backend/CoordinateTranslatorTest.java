package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public final class CoordinateTranslatorTest {
    private transient Sprite sprite;
    private transient Config config = Config.getInstance();

    @BeforeEach
    void setUp() {
        sprite = Mockito.mock(Sprite.class);
        Mockito.when(sprite.getHeight()).thenReturn(config.resolution / 2);
        Mockito.when(sprite.getWidth()).thenReturn(config.resolution);
    }

    @Test
    void translateSize() {
        assertEquals(config.resolution,
                CoordinateTranslator.translateSize(config.viewportSize));
    }

    @Test
    void translatePosition() {
        assertEquals(new Vector2(500,500), CoordinateTranslator.translatePosition(new Vector2(0,0)));
    }
}
