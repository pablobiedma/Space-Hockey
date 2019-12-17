package com.mygdx.airhockey.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordServiceTest {
    private transient String password = "password";
    private transient String hash = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=";
    private transient PasswordService ps;

    @BeforeEach
    void setUp() {
        ps = new PasswordService("SHA-256");
    }

    @Test
    void hashTest() {
        assertEquals(hash, ps.hashPassword(password));
        assertNotEquals(hash, ps.hashPassword("some_other_password"));
    }

    @Test
    void checkPassword() {
        assertTrue(ps.checkPassword(password, hash));
        assertFalse(ps.checkPassword("hello", hash));
    }

    @Test
    void wrongAlgorithmTest() {
        assertDoesNotThrow(() -> {
            new PasswordService("inexistent_algorithm");
        });
    }
}
