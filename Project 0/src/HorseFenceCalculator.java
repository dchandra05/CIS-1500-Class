import java.util.Scanner;

public class HorseFenceCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask for length and width of the fenced area
            System.out.print("Enter the length of the fenced area (in feet): ");
            double length = scanner.nextDouble();

            System.out.print("Enter the width of the fenced area (in feet): ");
            double width = scanner.nextDouble();

            // Ask how far apart they want their posts to be
            System.out.print("Enter the distance between posts (in feet): ");
            double postDistance = scanner.nextDouble();

            // Validate if the post distance divides the perimeter evenly
            double perimeter = 2 * (length + width);
            if (perimeter % postDistance != 0) {
                System.out.println("Error: The distance between posts does not divide the perimeter evenly. Please run the program again.");
                return;
            }

            // Calculate number of posts
            int numberOfPosts = (int) (perimeter / postDistance);

            // Ask for the board length
            System.out.print("Enter the length of each board (in feet): ");
            double boardLength = scanner.nextDouble();

            // Validate if the board length is greater than the post distance
            if (boardLength < postDistance) {
                System.out.println("Error: The board length cannot be shorter than the distance between posts. Please run the program again.");
                return;
            }

            // Calculate number of boards needed for one row
            int boardsPerRow = (int) Math.ceil(perimeter / boardLength);

            // Ask how many boards they want across each post
            System.out.print("Enter the number of boards to run across each post: ");
            int boardsAcross = scanner.nextInt();

            // Ask for cost of each post and board
            System.out.print("Enter the cost of each post: ");
            double postCost = scanner.nextDouble();

            System.out.print("Enter the cost of each board: ");
            double boardCost = scanner.nextDouble();

            // Calculate the total number of boards needed
            int totalBoards = boardsPerRow * boardsAcross;

            // Calculate total costs
            double totalPostCost = numberOfPosts * postCost;
            double totalBoardCost = totalBoards * boardCost;
            double grandTotal = totalPostCost + totalBoardCost;

            // Output the results
            System.out.println("\n--- Fence Cost Breakdown ---");
            System.out.println("Total number of posts needed: " + numberOfPosts);
            System.out.println("Total number of boards needed: " + totalBoards);
            System.out.println("Total cost for posts: $" + totalPostCost);
            System.out.println("Total cost for boards: $" + totalBoardCost);
            System.out.println("Grand total cost for the project: $" + grandTotal);
        } finally {
            scanner.close();
        }
    }
}
