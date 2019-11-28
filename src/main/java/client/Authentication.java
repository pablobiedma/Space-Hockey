package client;

import database.DBController;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Authentication {

    /**
     * Attempts to sign in with given credentials.
     * @param username to log in.
     * @param password to log in.
     * @return true if signed in, else false.
     * @throws SQLException for wrong queries.
     * @throws NoSuchAlgorithmException for invalid hashing algorithm.
     */
    public static boolean signIn(String username, String password) throws SQLException, NoSuchAlgorithmException {
        if(!DBController.userExists(username)) {
            System.out.println("Account with this username was not found!");
            return false;
        }

        if(!PasswordService.checkPassword(password, DBController.getHashedPassword(username))){
            System.out.println("Password is incorrect!");
            return false;
        }

        System.out.println("Successfully logged in!");
        return true;
    }

    /**
     * Attempts to create an account with given credentials.
     * @param username to log in.
     * @param password to log in.
     * @return true if signed up, else false.
     * @throws SQLException for wrong queries.
     * @throws NoSuchAlgorithmException for invalid hashing algorithm.
     */

    public static boolean signUp(String username, String password) throws SQLException, NoSuchAlgorithmException {
        if(DBController.userExists(username)){
            System.out.println("Account with this username already exists!");
            return false;
        } else {
            DBController.createUser(username, PasswordService.hashPassword(password));
            System.out.println("Account created successfully!");
            return true;
        }
    }
}
