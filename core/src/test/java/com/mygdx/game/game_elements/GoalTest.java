package com.mygdx.game.game_elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the goal game element class.
 */
public class GoalTest {
    static final int TEST_WIDTH = 20;
    static final int TEST_HEIGHT = 30;

    @Test
    void testConstructor() {
        Goal test = new Goal(TEST_WIDTH, TEST_HEIGHT);
        assertEquals(test.getCollisionBox().getX(), TEST_WIDTH);
        assertEquals(test.getCollisionBox().getY(), TEST_HEIGHT);
    }

    @Test
    void testScoreIncrement() {
        Goal test = new Goal(TEST_WIDTH, TEST_HEIGHT);
        assertEquals(test.getScore(), 0);
        test.incrementScore();
        assertEquals(test.getScore(), 1);
    }
}
