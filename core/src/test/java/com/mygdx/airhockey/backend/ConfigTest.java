package com.mygdx.airhockey.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigTest {
    private transient Config config;

    @BeforeEach
    void setUp() {
        config = Config.getInstance();
    }

    @Test
    void changePath() {
        Config.updateConfigFileLocation("some.wrong.path");
        Config newConfig = Config.getInstance();
        Config.updateConfigFileLocation("./config.properties");
        //if wrong path, then empty fields
        assertNotNull(newConfig);
        assertEquals(0, newConfig.bluePaddleX);

    }
}
