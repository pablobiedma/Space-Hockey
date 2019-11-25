package client;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordServiceTest {
    private String password = "password";
    private String hash = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=";
    @Test
    void hashTest() throws NoSuchAlgorithmException {
        assertEquals(hash, PasswordService.hashPassword(password));
    }

    @Test
    void checkPassword() throws NoSuchAlgorithmException {
        assertTrue(PasswordService.checkPassword(password, hash));
        assertFalse(PasswordService.checkPassword("hello", hash));
    }
}
