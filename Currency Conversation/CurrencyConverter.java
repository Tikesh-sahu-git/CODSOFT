import java.text.DecimalFormat; // Imports the DecimalFormat class for formatting numerical output
import java.util.Scanner;        // Imports the Scanner class for reading user input

public class CurrencyConverter { // Declares the main class for the currency converter application
    
    // Exchange rates to Indian Rupee (INR) - These are declared as private static final
    // to make them constant and accessible throughout the class without object instantiation.
    private static final double USD_TO_INR = 85.50; // Exchange rate for US Dollar to Indian Rupee
    private static final double EUR_TO_INR = 88.25; // Exchange rate for Euro to Indian Rupee
    private static final double GBP_TO_INR = 102.10; // Exchange rate for British Pound to Indian Rupee
    private static final double JPY_TO_INR = 0.67;   // Exchange rate for Japanese Yen to Indian Rupee
    
    // Currency symbols and names - Arrays to store display information for currencies
    private static final String[] CURRENCY_SYMBOLS = {"$", "€", "£", "¥", "₹"}; // Array of currency symbols
    private static final String[] CURRENCY_NAMES = {"US Dollar", "Euro", "British Pound", "Japanese Yen", "Indian Rupee"}; // Array of full currency names
    private static final String[] CURRENCY_CODES = {"USD", "EUR", "GBP", "JPY", "INR"}; // Array of currency ISO codes
    
    public static void main(String[] args) { // Main method, the entry point of the program
        Scanner scanner = new Scanner(System.in); // Creates a Scanner object to read input from the console
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Creates a DecimalFormat object to format numbers with commas and two decimal places
        
        System.out.println("╔══════════════════════════════╗"); // Prints the top border of the welcome message
        System.out.println("║      CURRENCY CONVERTER      ║"); // Prints the title of the application
        System.out.println("╚══════════════════════════════╝"); // Prints the bottom border of the welcome message
        
        while (true) { // Starts an infinite loop for continuous currency conversions until the user chooses to exit
            // Display menu
            System.out.println("\nAvailable currencies:"); // Prints a header for the currency list
            for (int i = 0; i < CURRENCY_NAMES.length; i++) { // Loops through the currency names array to display each currency
                System.out.printf("%d. %s (%s) %s\n", // Formats and prints each currency's details
                    i+1, CURRENCY_NAMES[i], CURRENCY_CODES[i], CURRENCY_SYMBOLS[i]); // Displays index, name, code, and symbol
            }
            System.out.println("6. Exit"); // Adds an option to exit the program
            
            // Get source currency
            int sourceChoice = getMenuChoice(scanner, "Select source currency (1-6): ", 1, 6); // Prompts user to select source currency and validates input
            
            if (sourceChoice == 6) { // Checks if the user chose to exit
                System.out.println("\nThank you for using the Currency Converter. Goodbye!"); // Prints an exit message
                break; // Exits the while loop, terminating the program
            }
            
            // Get target currency
            int targetChoice = getMenuChoice(scanner, "Select target currency (1-5): ", 1, 5); // Prompts user to select target currency and validates input
            
            if (sourceChoice == targetChoice) { // Checks if source and target currencies are the same
                System.out.println("Source and target currencies cannot be the same. Please try again."); // Informs the user of the error
                continue; // Restarts the loop to allow the user to make new selections
            }
            
            // Get amount to convert
            double amount = getPositiveAmount(scanner, "Enter amount to convert: "); // Prompts user to enter amount and validates for positive value
            
            // Convert currency
            double convertedAmount = convertCurrency(amount, sourceChoice, targetChoice); // Calls the conversion method for the entered amount
            double oneUnitConversion = convertCurrency(1, sourceChoice, targetChoice); // Calls the conversion method for one unit to show the rate
            
            // Display conversion rate and result
            System.out.println("\n════════════ Conversion Details ════════════"); // Prints a header for conversion details
            System.out.printf("Conversion Rate: 1 %s = %s %s\n", // Formats and prints the conversion rate
                CURRENCY_CODES[sourceChoice-1], // Displays the source currency code
                df.format(oneUnitConversion),   // Displays the formatted one-unit conversion
                CURRENCY_CODES[targetChoice-1]); // Displays the target currency code
                
            System.out.println("\n════════════ Conversion Result ════════════"); // Prints a header for conversion result
            System.out.printf("%s %s (%s) = %s %s (%s)\n", // Formats and prints the detailed conversion result
                df.format(amount), // Displays the formatted original amount
                CURRENCY_CODES[sourceChoice-1], // Displays the source currency code
                CURRENCY_NAMES[sourceChoice-1], // Displays the source currency name
                df.format(convertedAmount), // Displays the formatted converted amount
                CURRENCY_CODES[targetChoice-1], // Displays the target currency code
                CURRENCY_NAMES[targetChoice-1]); // Displays the target currency name
                
            System.out.printf("\t %s%s = %s%s\n", // Formats and prints the conversion result using symbols
                CURRENCY_SYMBOLS[sourceChoice-1], df.format(amount), // Displays source symbol and formatted amount
                CURRENCY_SYMBOLS[targetChoice-1], df.format(convertedAmount)); // Displays target symbol and formatted converted amount
            System.out.println("══════════════════════════════════════════\n"); // Prints the bottom border for the result section
            
            // Ask to continue
            if (!askToContinue(scanner)) { // Calls a method to ask if the user wants to continue and checks the response
                System.out.println("\nThank you for using the Currency Converter. Goodbye!"); // Prints a final goodbye message
                break; // Exits the while loop, terminating the program
            }
        }
        
        scanner.close(); // Closes the scanner object to release system resources
    }
    
    private static double convertCurrency(double amount, int source, int target) { // Method to perform currency conversion
        // First convert to INR, then to target currency - This is a common approach for multiple currency conversions
        double amountInInr; // Declares a variable to hold the amount converted to INR
        
        // Convert source to INR
        switch (source) { // Uses a switch statement to handle conversion from source currency to INR
            case 1: amountInInr = amount * USD_TO_INR; break; // Converts USD to INR
            case 2: amountInInr = amount * EUR_TO_INR; break; // Converts EUR to INR
            case 3: amountInInr = amount * GBP_TO_INR; break; // Converts GBP to INR
            case 4: amountInInr = amount * JPY_TO_INR; break; // Converts JPY to INR
            case 5: amountInInr = amount; break;             // If source is INR, amount remains unchanged
            default: amountInInr = 0; // Default case, though input validation should prevent this
        }
        
        // Convert INR to target
        switch (target) { // Uses a switch statement to handle conversion from INR to target currency
            case 1: return amountInInr / USD_TO_INR; // Converts INR to USD
            case 2: return amountInInr / EUR_TO_INR; // Converts INR to EUR
            case 3: return amountInInr / GBP_TO_INR; // Converts INR to GBP
            case 4: return amountInInr / JPY_TO_INR; // Converts INR to JPY
            case 5: return amountInInr;              // If target is INR, amount remains unchanged
            default: return 0; // Default case, though input validation should prevent this
        }
    }
    
    private static int getMenuChoice(Scanner scanner, String prompt, int min, int max) { // Method to get a valid menu choice from the user
        while (true) { // Loops until a valid choice is entered
            System.out.print("\n" + prompt); // Prints the prompt message
            try { // Starts a try-catch block to handle potential NumberFormatException
                int choice = Integer.parseInt(scanner.nextLine()); // Reads the user's input and attempts to parse it as an integer
                if (choice >= min && choice <= max) { // Checks if the choice is within the valid range
                    return choice; // Returns the valid choice
                }
                System.out.printf("Please enter a number between %d and %d.\n", min, max); // Informs the user if the choice is out of range
            } catch (NumberFormatException e) { // Catches the exception if the input is not a valid number
                System.out.println("Invalid input. Please enter a number."); // Informs the user of invalid input
            }
        }
    }
    
    private static double getPositiveAmount(Scanner scanner, String prompt) { // Method to get a positive numeric amount from the user
        while (true) { // Loops until a valid positive amount is entered
            System.out.print("\n" + prompt); // Prints the prompt message
            try { // Starts a try-catch block to handle potential NumberFormatException
                double amount = Double.parseDouble(scanner.nextLine()); // Reads the user's input and attempts to parse it as a double
                if (amount > 0) { // Checks if the amount is positive
                    return amount; // Returns the valid positive amount
                }
                System.out.println("Amount must be positive. Please try again."); // Informs the user if the amount is not positive
            } catch (NumberFormatException e) { // Catches the exception if the input is not a valid number
                System.out.println("Invalid input. Please enter a numeric value."); // Informs the user of invalid input
            }
        }
    }
    
    private static boolean askToContinue(Scanner scanner) { // Method to ask the user if they want to continue
        while (true) { // Loops until a valid 'y' or 'n' response is entered
            System.out.print("\nWould you like to make another conversion? (y/n): "); // Prints the prompt
            String input = scanner.nextLine().trim().toLowerCase(); // Reads the user's input, trims whitespace, and converts to lowercase
            if (input.equals("y") || input.equals("yes")) { // Checks if the input is 'y' or 'yes'
                return true; // Returns true to continue
            } else if (input.equals("n") || input.equals("no")) { // Checks if the input is 'n' or 'no'
                return false; // Returns false to stop
            }
            System.out.println("Please enter 'y' or 'n'."); // Informs the user of invalid input
        }
    }
}
