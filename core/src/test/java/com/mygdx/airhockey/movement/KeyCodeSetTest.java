package com.mygdx.airhockey.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KeyCodeSetTest {
    public static final int NEW_VALUE = 100;
    private transient KeyCodeSet keyCodeSet;

    @BeforeEach
    void setUp() {
        keyCodeSet = new KeyCodeSet(1,2,3,4);
    }

    @Test
    void getAndSetKeyCodeUp() {
        assertEquals(3, keyCodeSet.getKeyCodeUp());
        keyCodeSet.setKeyCodeUp(NEW_VALUE);
        assertEquals(keyCodeSet.getKeyCodeUp(), NEW_VALUE);
    }

    @Test
    void getAndSetKeyCodeDown() {
        assertEquals(4, keyCodeSet.getKeyCodeDown());
        keyCodeSet.setKeyCodeDown(NEW_VALUE);
        assertEquals(keyCodeSet.getKeyCodeDown(), NEW_VALUE);
    }

    @Test
    void getAndSetKeyCodeLeft() {
        assertEquals(1, keyCodeSet.getKeyCodeLeft());
        keyCodeSet.setKeyCodeLeft(NEW_VALUE);
        assertEquals(keyCodeSet.getKeyCodeLeft(), NEW_VALUE);
    }

    @Test
    void getAndSetKeyCodeRight() {
        assertEquals(2, keyCodeSet.getKeyCodeRight());
        keyCodeSet.setKeyCodeRight(NEW_VALUE);
        assertEquals(keyCodeSet.getKeyCodeRight(), NEW_VALUE);
    }
}