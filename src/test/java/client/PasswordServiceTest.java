package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

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
//for some reason doesnt work???
    @Test
    void wrongAlgorithmTest() {
        assertDoesNotThrow(() -> {
            new PasswordService("inexistent_algorithm");
        });
    }
}
