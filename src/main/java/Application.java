import Game.Game;
import Game.Player;
import client.Authentication;
import Game.Level;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Demo class for authentication.
 */
public class Application {
    private static Player player;
    final static int GOALFOR_CHOICE = 1;
    final static int GOALAGAINST_CHOICE = 2;
    final static int SIGN_IN_CHOICE = 1;
    final static int SIGN_UP_CHOICE = 2;
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {


        System.out.println(
                "--- AIR HOCKEY MENU ---\n" +
                        "1. SIGN IN.\n2. SIGN UP.\n"
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
            Game game = new Game(player);
            Level level = game.getLevel();
            level.start();

            while (!level.isFinished()) {
                printGameInfo(game);
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == GOALFOR_CHOICE) {
                    level.goalFor();
                } else if (choice == GOALAGAINST_CHOICE) {
                    level.goalAgainst();
                } else {
                    System.out.println("Invalid choice. Choose again:");
                }
            }

            System.out.println("Game finished. " +
                    "\nFinal score: " + level.getPlayerGoals() + " : " + level.getAiGoals() + "\n" +
                    "Total points earned: " + level.getScore());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void printGameInfo(Game game) {
        String menu = "--- GAME INFO----\n"+
                "Current score: " +
                game.getLevel().getPlayerGoals() + " : " + game.getLevel().getAiGoals() + '\n' +
                "Current points: " + game.getLevel().getScore() + '\n' +
                "Choose: \n" +
                "1. Score a goal.\n" +
                "2. Concede a goal.\n";
        System.out.println(menu);
    }

    private static boolean authenticate(Scanner sc) throws SQLException, NoSuchAlgorithmException {
        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        System.out.println("And password:");
        String password = sc.nextLine();
        player = new Player(username, 0);
        return Authentication.signIn(username, password);
    }

    private static boolean signUp(Scanner sc) throws SQLException, NoSuchAlgorithmException {
        System.out.println("Please enter desired username:");
        String username = sc.nextLine();
        System.out.println("And password:");
        String password = sc.nextLine();
        return Authentication.signUp(username, password);
    }
}
