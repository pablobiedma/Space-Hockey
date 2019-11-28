package client;
import Game.Player;
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
        ps = new PasswordService();
    }

    @Test
    void hashTest() throws NoSuchAlgorithmException {
        assertEquals(hash, ps.hashPassword(password));
        assertNotEquals(hash, ps.hashPassword("some_other_password"));
    }

    @Test
    void checkPassword() throws NoSuchAlgorithmException {
        assertTrue(ps.checkPassword(password, hash));
        assertFalse(ps.checkPassword("hello", hash));
    }
}
