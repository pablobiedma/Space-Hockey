package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

public final class CoordinateTranslatorTest {
    private transient Config config = Config.getInstance();

    @Test
    void translateSize() {
        assertEquals(config.resolution,
                CoordinateTranslator.translateSize(config.viewportSize));
    }

    @Test
    void translatePosition() {
        assertEquals(new Vector2(500,500),
                CoordinateTranslator.translatePosition(new Vector2(0,0)));
    }
}
