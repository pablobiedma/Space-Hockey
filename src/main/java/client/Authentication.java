package client;

import database.DBController;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Authentication {

    private transient PasswordService ps = new PasswordService();

    /**
     * Attempts to sign in with given credentials.
     * @param database used for authentication.
     * @param username to log in.
     * @param password to log in.
     * @return true if signed in, else false.
     * @throws SQLException for wrong queries.
     * @throws NoSuchAlgorithmException for invalid hashing algorithm.
     */
    public boolean signIn(DBController database, String username, String password) throws SQLException, NoSuchAlgorithmException {
        if(!database.userExists(username)) {
            System.out.println("Account with this username was not found!");
            return false;
        }

        if(ps.checkPassword(password, database.getHashedPassword(username))){
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

    public boolean signUp(DBController database, String username, String password) throws SQLException, NoSuchAlgorithmException {
        if(database.userExists(username)){
            System.out.println("Account with this username already exists!");
            return false;
        } else {
            database.createUser(username, ps.hashPassword(password));
            System.out.println("Account created successfully!");
            return true;
        }
    }
}
