import java.util.Scanner; // Imports the Scanner class to read user input from the console.
import java.util.Random; // Imports the Random class to generate random numbers.

public class NumberGame { // Declares a public class named NumberGame.
    public static void main(String[] args) { // The main method, the entry point of the Java application.
        // Scanner for reading user input
        Scanner scanner = new Scanner(System.in); // Creates a new Scanner object to get input from the standard input (keyboard).
        // Random object to generate the number to guess
        Random random = new Random(); // Creates a new Random object to generate pseudo-random numbers.
        
        // Variables to keep track of game stats
        int totalRounds = 0; // Initializes a variable to keep count of the total rounds played.
        int totalWins = 0; // Initializes a variable to keep count of the total rounds won by the user.
        boolean playAgain = true; // Initializes a boolean flag to control the main game loop, set to true to start the game.
        
        // Welcome message
        System.out.println("Welcome to the Number Guessing Game!"); // Prints a welcome message to the console.
        System.out.println("I'm thinking of a number between 1 and 100."); // Informs the user about the range of the number.
        
        // Main game loop
        while (playAgain) { // Starts the main game loop, continues as long as playAgain is true.
            totalRounds++; // Increments the totalRounds counter for each new round.
            int numberToGuess = random.nextInt(100) + 1; // Generates a random integer between 1 and 100 (inclusive).
            int attempts = 0; // Initializes the attempt counter for the current round to zero.
            int maxAttempts = 10; // Sets the maximum number of attempts allowed per round.
            boolean hasWon = false; // Initializes a boolean flag to track if the user has won the current round.
            
            System.out.println("\nRound " + totalRounds + " - You have " + maxAttempts + " attempts."); // Informs the user about the current round and attempts.
            
            // Loop for user guesses
            while (attempts < maxAttempts && !hasWon) { // Loop continues as long as attempts are less than maxAttempts and the user hasn't won.
                System.out.print("Enter your guess: "); // Prompts the user to enter their guess.
                int userGuess; // Declares an integer variable to store the user's guess.
                
                try { // Starts a try-catch block to handle potential input errors.
                    userGuess = scanner.nextInt(); // Reads the integer input from the user.
                } catch (Exception e) { // Catches any exception that occurs during input reading (e.g., non-integer input).
                    // Handle non-integer input
                    System.out.println("Please enter a valid number!"); // Informs the user about invalid input.
                    scanner.next(); // Clears the invalid input from the scanner's buffer to prevent an infinite loop.
                    continue; // Skips the rest of the current loop iteration and goes to the next attempt.
                }
                
                attempts++; // Increments the attempt counter after each valid guess.
                
                // Check if guess is correct
                if (userGuess == numberToGuess) { // Checks if the user's guess matches the number to guess.
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts!"); // Congratulates the user on guessing correctly.
                    hasWon = true; // Sets hasWon to true, exiting the inner guess loop.
                    totalWins++; // Increments the totalWins counter.
                } else if (userGuess < numberToGuess) { // Checks if the user's guess is too low.
                    System.out.println("Too low! Attempts left: " + (maxAttempts - attempts)); // Informs the user their guess is too low and remaining attempts.
                } else { // If the guess is not correct and not too low, it must be too high.
                    System.out.println("Too high! Attempts left: " + (maxAttempts - attempts)); // Informs the user their guess is too high and remaining attempts.
                }
            }
            
            // If player did not guess correctly in given attempts
            if (!hasWon) { // Checks if the user failed to guess the number within the allowed attempts.
                System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + "."); // Reveals the correct number if the user lost.
            }
            
            // Ask player if they want to play again
            System.out.print("\nWould you like to play again? (yes/no): "); // Asks the user if they want to play another round.
            String playAgainInput = scanner.next().toLowerCase(); // Reads the user's input and converts it to lowercase.
            playAgain = playAgainInput.equals("yes") || playAgainInput.equals("y"); // Sets playAgain based on user input ("yes" or "y").
        }
        
        // Game summary after exiting the loop
        System.out.println("\nGame Over!"); // Prints a "Game Over" message.
        System.out.println("Rounds played: " + totalRounds); // Displays the total number of rounds played.
        System.out.println("Rounds won: " + totalWins); // Displays the total number of rounds won.
        System.out.println("Win rate: " + ((double)totalWins / totalRounds * 100) + "%"); // Calculates and displays the win rate as a percentage.
        
        // Close the scanner
        scanner.close(); // Closes the scanner object to release system resources.
    }
}
