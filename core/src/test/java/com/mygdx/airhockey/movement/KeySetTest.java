package com.mygdx.airhockey.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KeySetTest {
    public static final int NEW_VALUE = 100;
    private transient KeySet keySet;

    @BeforeEach
    void setUp() {
        keySet = new KeySet(1,2,3,4);
    }

    @Test
    void getAndSetKeyCodeUp() {
        assertEquals(3, keySet.getKeyCodeUp());
        keySet.setKeyCodeUp(NEW_VALUE);
        assertEquals(keySet.getKeyCodeUp(), NEW_VALUE);
    }

    @Test
    void getAndSetKeyCodeDown() {
        assertEquals(4, keySet.getKeyCodeDown());
        keySet.setKeyCodeDown(NEW_VALUE);
        assertEquals(keySet.getKeyCodeDown(), NEW_VALUE);
    }

    @Test
    void getAndSetKeyCodeLeft() {
        assertEquals(1, keySet.getKeyCodeLeft());
        keySet.setKeyCodeLeft(NEW_VALUE);
        assertEquals(keySet.getKeyCodeLeft(), NEW_VALUE);
    }

    @Test
    void getAndSetKeyCodeRight() {
        assertEquals(2, keySet.getKeyCodeRight());
        keySet.setKeyCodeRight(NEW_VALUE);
        assertEquals(keySet.getKeyCodeRight(), NEW_VALUE);
    }
}