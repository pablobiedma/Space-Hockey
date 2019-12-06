package com.mygdx.game.movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyCodeSetTest {
    private transient KeyCodeSet keyCodeSet;
    @BeforeEach
    void setUp() {
        keyCodeSet = new KeyCodeSet(1,2,3,4);
    }

    @Test
    void getKeyCodeUp() {
        assertEquals(3, keyCodeSet.getKeyCodeUp());
    }

    @Test
    void getKeyCodeDown() {
        assertEquals(4, keyCodeSet.getKeyCodeDown());
    }

    @Test
    void getKeyCodeLeft() {
        assertEquals(1, keyCodeSet.getKeyCodeLeft());
    }

    @Test
    void getKeyCodeRight() {
        assertEquals(2, keyCodeSet.getKeyCodeRight());
    }
}