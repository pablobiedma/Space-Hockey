package client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordServiceTest {
    private String password = "password";
    private String hash = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
    @Test
    void hashTest() {
        assertEquals(hash, PasswordService.hashPassword(password));
    }

    @Test
    void checkPassword() {
        assertTrue(PasswordService.checkPassword(password, hash));
        assertFalse(PasswordService.checkPassword("hello", hash));
    }
}
