import java.util.Random;
import java.util.Scanner;

public class Project1LunarLander {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            // Initialize the starting values
            int distance = 10;
            int xTilt = random.nextInt(21) - 10; // Random value between -10 and 10
            int yTilt = random.nextInt(21) - 10; // Random value between -10 and 10
            boolean selfDestructActive = false;
            String cancellationCode = "1234"; // Set the cancellation code for self-destruct

            System.out.println("Welcome to the Lunar Lander Simulator!");
            System.out.println("Initial Distance: " + distance + " units from surface.");
            System.out.println("Initial X-Axis Tilt: " + xTilt);
            System.out.println("Initial Y-Axis Tilt: " + yTilt);

            // Main game loop
            while (distance > 0) {
                if (!selfDestructActive) {
                    System.out.println("\nEnter a command: ");
                    System.out.println("Valid commands: 'x+', 'x-', 'y+', 'y-', 'thruster', 'do nothing', 'self-destruct'");

                    String command = scanner.nextLine();

                    switch (command) {
                        case "x+":
                            xTilt++;
                            distance--;
                            break;
                        case "x-":
                            xTilt--;
                            distance--;
                            break;
                        case "y+":
                            yTilt++;
                            distance--;
                            break;
                        case "y-":
                            yTilt--;
                            distance--;
                            break;
                        case "thruster":
                            distance += 2;
                            break;
                        case "do nothing":
                            distance--;
                            break;
                        case "self-destruct":
                            selfDestructActive = true;
                            System.out.println("Self-destruct sequence activated. Enter cancellation code to stop:");
                            break;
                        default:
                            System.out.println("Invalid command. Please enter one of the following commands: 'x+', 'x-', 'y+', 'y-', 'thruster', 'do nothing', 'self-destruct'");
                    }
                } else {
                    System.out.println("Self-destruct active. Enter cancellation code:");
                    String enteredCode = scanner.nextLine();
                    if (enteredCode.equals(cancellationCode)) {
                        selfDestructActive = false;
                        System.out.println("Self-destruct sequence canceled.");
                    } else {
                        System.out.println("Incorrect code. You cannot perform other commands until you cancel self-destruct.");
                    }
                }

                // Display current state
                if (distance > 0) {
                    System.out.println("Distance to surface: " + distance);
                    System.out.println("X-Axis Tilt: " + xTilt);
                    System.out.println("Y-Axis Tilt: " + yTilt);
                }
            }

            // Check for landing conditions
            if (xTilt == 0 && yTilt == 0) {
                System.out.println("Congratulations! You have successfully landed the lunar lander.");
            } else {
                System.out.println("You have crashed! X or Y tilt was not zero.");
            }

            // Ask if the player wants to play again
            System.out.println("\nWould you like to play again? (yes/no)");
            String playAgainInput = scanner.nextLine();
            playAgain = playAgainInput.equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing the Lunar Lander Simulator!");
        scanner.close();
    }
}
