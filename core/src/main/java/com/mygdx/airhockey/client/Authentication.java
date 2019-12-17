package com.mygdx.airhockey.client;

import com.mygdx.airhockey.database.DatabaseController;

public class Authentication {

    private transient PasswordService ps = new PasswordService("SHA-256");
    private transient DatabaseController database;

    /**
     * Constructor for authentication class.
     * @param database used for authentication.
     */

    public Authentication(DatabaseController database) {
        this.database = database;
    }

    /**
     * Attempts to sign in with given credentials.
     * @param username to log in.
     * @param password to log in.
     * @return true if signed in, else false.
     */
    public boolean signIn(String username, String password) {
        if (!database.userExists(username)) {
            System.out.println("Account with this username was not found!");
            return false;
        }

        if (!ps.checkPassword(password, database.getHashedPassword(username))) {
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
     */

    public boolean signUp(String username, String password) {
        if (database.userExists(username)) {
            System.out.println("Account with this username already exists!");
            return false;
        } else {
            database.createUser(username, ps.hashPassword(password));
            System.out.println("Account created successfully!");
            return true;
        }
    }
}
