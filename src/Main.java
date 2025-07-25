import java.util.Random;
import java.util.Scanner;

public class Lab_1_GuessTheNumber {
    public static void main(String[]args){
        int ourGuess;
        int compNumber;
        int guessCount=0;
        Random random= new Random();
        boolean guessedNumber = false;
        Scanner keyboard= new Scanner(System.in);

        compNumber =random.nextInt(100)+1;
        while (!guessedNumber){
            System.out.println("Enter your integer guess");
            ourGuess=keyboard.nextInt();
            guessCount++;

            if (ourGuess>=1 && ourGuess<=100) {
                if (ourGuess == compNumber) {
                    System.out.println("Congrats! You guessed the number inn "+ guessCount +"guesses! Thanks for playing");
                    guessedNumber=true;
                }
                else if (ourGuess > compNumber) {
                    System.out.println("Your number was too high");
                }
                else {
                    System.out.println("Your number was too low");
                }

            }
            else {
                System.out.println("That was a wasted guess! You must pick a different number between 1 to 100, inclusive");
            }
        }//end while
    }// end main

}
