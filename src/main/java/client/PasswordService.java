package client;

import java.security.MessageDigest;

public class PasswordService {
    /**
     * Creates a hash of a plain-text password.
     * @param pwd password to hash.
     * @return hash of the password.
     */
    public static String hashPassword(String pwd) {
        return "";
    }

    /**
     * Checks if password is correct by comparing with its hash.
     * @param pwd password to check.
     * @param hash hash of the correct password.
     * @return true if correct, else false.
     */
    public static boolean checkPassword(String pwd, String hash) {
        return true;
    }
}
