import client.Authentication;
import database.DatabaseControler;
import game.Level;
import game.Player;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Demo class for authentication.
 */
public class Application {
    private static DatabaseControler database = new DatabaseControler();
    private static Authentication authService = new Authentication(database);
    private static Player player;
    private static final int GOALFOR_CHOICE = 1;
    private static final int GOALAGAINST_CHOICE = 2;
    private static final int SIGN_IN_CHOICE = 1;
    private static final int SIGN_UP_CHOICE = 2;

    /**
     * Main method to demonstrate the work of the app backend.
     * @param args arguments for the method.
     */
    public static void main(String[] args) {


        System.out.println(
                "--- AIR HOCKEY MENU ---\n"
                        + "1. SIGN IN.\n2. SIGN UP.\n"
        );


        Scanner sc = new Scanner(System.in);
        try {
            boolean authenticated = false;

            while (!authenticated) {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == SIGN_IN_CHOICE) {
                    while (!authenticated) {
                        authenticated = authenticate(sc);
                    }
                } else if (choice == SIGN_UP_CHOICE) {
                    boolean signedUp = false;
                    while (!signedUp) {
                        signedUp = signUp(sc);
                    }

                    System.out.println("Now you can sign in!");
                    while (!authenticated) {
                        authenticated = authenticate(sc);
                    }
                } else {
                    System.out.println("Invalid choice. Choose again:");
                }
            }

            System.out.println("Game started!");
            Level level = new Level(player);
            level.start();

            while (!level.isFinished()) {
                printGameInfo(level);
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == GOALFOR_CHOICE) {
                    level.goalFor();
                } else if (choice == GOALAGAINST_CHOICE) {
                    level.goalAgainst();
                } else {
                    System.out.println("Invalid choice. Choose again:");
                }
            }

            System.out.println("Game finished. "
                    + "\nFinal score: " + level.getPlayerGoals() + " : " + level.getAiGoals() + "\n"
                    + "Total points earned: " + level.getScore());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void printGameInfo(Level level) {
        String menu = "--- GAME INFO----\n"
                + "Current score: "
                + level.getPlayerGoals() + " : " + level.getAiGoals() + '\n'
                + "Current points: " + level.getScore() + '\n'
                + "Choose: \n"
                + "1. Score a goal.\n"
                + "2. Concede a goal.\n";
        System.out.println(menu);
    }

    private static boolean authenticate(Scanner sc) throws SQLException, NoSuchAlgorithmException {
        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        System.out.println("And password:");
        String password = sc.nextLine();
        if (authService.signIn(username, password)) {
            player = new Player(username, database.getScore(username));
            return true;
        } else {
            return false;
        }
    }

    private static boolean signUp(Scanner sc) throws SQLException, NoSuchAlgorithmException {
        System.out.println("Please enter desired username:");
        String username = sc.nextLine();
        System.out.println("And password:");
        String password = sc.nextLine();
        return authService.signUp(username, password);
    }
}
