import java.io.*; // Imports classes for input/output operations (e.g., File, FileReader, FileWriter, BufferedReader, PrintWriter)
import java.text.DecimalFormat; // Imports DecimalFormat for formatting currency
import java.text.SimpleDateFormat; // Imports SimpleDateFormat for formatting dates and times
import java.util.*; // Imports utility classes like Scanner, List, ArrayList, Map, HashMap, Date

/**
 * BankAccount class with PIN, transaction history, and file saving/loading.
 */
class BankAccount {
    private double balance; // Stores the current balance of the account
    private String accountNumber; // Unique identifier for the bank account
    private String pin; // Personal Identification Number for account access
    private List<String> transactionHistory; // List to store a chronological record of transactions
    private DecimalFormat currencyFormat = new DecimalFormat("₹###,##0.00"); // Formats balance and amounts as Indian Rupees

    // Constructor for creating a new bank account with an initial balance
    public BankAccount(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber; // Sets the account number
        this.pin = pin; // Sets the PIN
        this.balance = initialBalance; // Sets the initial balance
        this.transactionHistory = new ArrayList<>(); // Initializes an empty list for transaction history
        addTransaction("Account opened with initial balance: " + currencyFormat.format(initialBalance)); // Records the account opening transaction
    }

    // Constructor for loading an existing bank account from stored data (e.g., from a file)
    public BankAccount(String accountNumber, String pin, double balance, List<String> history) {
        this.accountNumber = accountNumber; // Sets the account number
        this.pin = pin; // Sets the PIN
        this.balance = balance; // Sets the balance
        this.transactionHistory = history; // Sets the transaction history
    }

    // Verifies if the entered PIN matches the account's PIN
    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin); // Compares the entered PIN with the stored PIN
    }

    // Changes the account's PIN to a new one
    public void changePin(String newPin) {
        this.pin = newPin; // Updates the PIN
        addTransaction("PIN changed"); // Records the PIN change in transaction history
    }

    // Returns the current balance of the account
    public double getBalance() {
        return balance;
    }

    // Returns the account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Returns the list of transaction history
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    // Processes a deposit transaction
    public void deposit(double amount) {
        if (amount > 0) { // Checks if the deposit amount is positive
            balance += amount; // Adds the amount to the balance
            addTransaction("Deposit: " + currencyFormat.format(amount)); // Records the deposit transaction
            System.out.println("\n✅ Deposit successful. New balance: " + currencyFormat.format(balance)); // Confirms success
        } else {
            System.out.println("\n❌ Error: Deposit amount must be positive"); // Error message for invalid amount
        }
    }

    // Processes a withdrawal transaction
    public boolean withdraw(double amount) {
        if (amount <= 0) { // Checks if the withdrawal amount is positive
            System.out.println("\n❌ Error: Withdrawal amount must be positive"); // Error message for invalid amount
            return false; // Returns false if withdrawal failed
        }

        if (amount > balance) { // Checks if there are sufficient funds
            System.out.println("\n❌ Error: Insufficient funds. Available balance: " + currencyFormat.format(balance)); // Error message for insufficient funds
            return false; // Returns false if withdrawal failed
        }

        balance -= amount; // Deducts the amount from the balance
        addTransaction("Withdrawal: " + currencyFormat.format(amount)); // Records the withdrawal transaction
        System.out.println("\n✅ Withdrawal successful. New balance: " + currencyFormat.format(balance)); // Confirms success
        return true; // Returns true if withdrawal was successful
    }

    // Displays the current account balance
    public void displayBalance() {
        System.out.println("\n💰 Current Account Balance: " + currencyFormat.format(balance)); // Prints the formatted balance
    }

    // Displays the entire transaction history
    public void displayTransactionHistory() {
        System.out.println("\n═══════════════════════════════════");
        System.out.println("      TRANSACTION HISTORY");
        System.out.println("═══════════════════════════════════");

        if (transactionHistory.isEmpty()) { // Checks if there are any transactions
            System.out.println("No transactions yet."); // Message if history is empty
        } else {
            for (String transaction : transactionHistory) { // Iterates through and prints each transaction
                System.out.println(transaction);
            }
        }
    }

    // Adds a new transaction entry with a timestamp to the history
    private void addTransaction(String description) {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()); // Gets current date and time
        transactionHistory.add(timestamp + " - " + description); // Adds the formatted transaction to the list
    }

    // Saves the account's data (account number, PIN, balance, history) to a file
    public void saveToFile() {
        // Uses try-with-resources to ensure the PrintWriter is closed automatically
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(accountNumber + ".txt"), "UTF-8"))) {
            writer.println("Account Number: " + accountNumber); // Writes account number
            writer.println("PIN: " + pin); // Writes PIN
            writer.println("Balance: " + balance); // Writes balance
            writer.println("\nTransaction History:"); // Header for transaction history
            for (String transaction : transactionHistory) { // Writes each transaction
                writer.println(transaction);
            }
            // System.out.println("Account " + accountNumber + " saved successfully."); // Optional: Confirmation message
        } catch (IOException e) { // Catches potential I/O errors
            System.out.println("❌ Error saving account data: " + e.getMessage()); // Prints error message
        }
    }

    // Static method to load a BankAccount object from a file
    public static BankAccount loadFromFile(String accountNumber) {
        File file = new File(accountNumber + ".txt"); // Creates a File object for the account file
        if (!file.exists()) { // Checks if the file exists
            return null; // Returns null if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            String loadedAccountNumber = null;
            String loadedPin = null;
            double loadedBalance = 0.0;
            List<String> loadedHistory = new ArrayList<>();
            boolean readingHistory = false; // Flag to indicate if currently reading transaction history

            while ((line = reader.readLine()) != null) { // Reads the file line by line
                if (line.startsWith("Account Number: ")) {
                    loadedAccountNumber = line.substring("Account Number: ".length()); // Extracts account number
                } else if (line.startsWith("PIN: ")) {
                    loadedPin = line.substring("PIN: ".length()); // Extracts PIN
                } else if (line.startsWith("Balance: ")) {
                    loadedBalance = Double.parseDouble(line.substring("Balance: ".length())); // Extracts balance and parses to double
                } else if (line.equals("\nTransaction History:") || line.equals("Transaction History:")) { // Check for history header
                    readingHistory = true; // Set flag to true
                } else if (readingHistory && !line.trim().isEmpty()) { // If reading history and line is not empty
                    loadedHistory.add(line); // Add the line to history
                }
            }
            // Create and return a new BankAccount object with loaded data
            return new BankAccount(loadedAccountNumber, loadedPin, loadedBalance, loadedHistory);
        } catch (IOException | NumberFormatException e) { // Catches I/O errors or number format errors
            System.out.println("❌ Error loading account data from " + accountNumber + ".txt: " + e.getMessage()); // Prints error message
            return null; // Returns null if loading failed
        }
    }
}

/**
 * ATM class simulates an Automated Teller Machine, handling user interaction,
 * account login, and various banking operations.
 */
class ATM {
    private BankAccount currentAccount; // Stores the currently logged-in bank account
    private Scanner scanner; // Scanner object for reading user input
    private Map<String, BankAccount> accounts; // Map to store all bank accounts, with account number as key

    // Constructor for the ATM class
    public ATM() {
        this.scanner = new Scanner(System.in); // Initializes the scanner
        this.accounts = new HashMap<>(); // Initializes the HashMap for accounts
        loadAllAccounts(); // Loads all existing accounts from files at startup
        initializeSampleAccounts(); // Ensures sample accounts exist if not loaded
    }

    // This method ensures sample accounts are created ONLY IF they don't already exist
    // (i.e., not loaded from file). This prevents overwriting existing data.
    private void initializeSampleAccounts() {
        // Example of creating new accounts if they don't exist, for first run setup
        if (!accounts.containsKey("123456789")) {
            BankAccount acc1 = new BankAccount("123456789", "1234", 10000.00);
            accounts.put("123456789", acc1);
            acc1.saveToFile(); // Save this new account immediately
            System.out.println("Created new sample account: " + acc1.getAccountNumber());
        }
        if (!accounts.containsKey("987654321")) {
            BankAccount acc2 = new BankAccount("987654321", "4321", 5000.00);
            accounts.put("987654321", acc2);
            acc2.saveToFile(); // Save this new account immediately
            System.out.println("Created new sample account: " + acc2.getAccountNumber());
        }
    }

    // Main run method for the ATM application loop
    public void run() {
        System.out.println("\n═══════════════════════════════════");
        System.out.println("      WELCOME TO INDIAN BANK ATM");
        System.out.println("═══════════════════════════════════");

        while (true) { // Loop for main ATM menu (Login/Exit)
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            int choice = getIntInput("Enter choice: "); // Gets user's choice

            switch (choice) {
                case 1:
                    if (login()) { // Attempts to log in
                        showMainMenu(); // If login successful, show main account menu
                    }
                    break;
                case 2:
                    System.out.println("\n🙏 Thank you for using our ATM. Goodbye!"); // Exit message
                    saveAllAccounts(); // Saves all accounts before exiting
                    return; // Exits the application
                default:
                    System.out.println("❌ Invalid choice"); // Error for invalid input
            }
        }
    }

    // Handles the login process for a bank account
    private boolean login() {
        System.out.println("\n═══════════════════════════════════");
        System.out.println("            LOGIN");
        System.out.println("═══════════════════════════════════");

        String accountNumber = getStringInput("Enter account number: "); // Gets account number from user
        String pin = getStringInput("Enter PIN: "); // Gets PIN from user

        BankAccount account = accounts.get(accountNumber); // Retrieves the account from the map
        // Checks if the account exists and if the PIN is correct
        if (account != null && account.verifyPin(pin)) {
            currentAccount = account; // Sets the current logged-in account
            System.out.println("\n✅ Login successful!"); // Success message
            return true; // Returns true for successful login
        }

        System.out.println("\n❌ Invalid account number or PIN"); // Error message for failed login
        return false; // Returns false for failed login
    }

    // Displays the main menu for a logged-in user and handles their choices
    private void showMainMenu() {
        while (currentAccount != null) { // Loop continues as long as a user is logged in
            System.out.println("\n═══════════════════════════════════");
            System.out.println("            MAIN MENU");
            System.out.println("═══════════════════════════════════");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout");

            int choice = getIntInput("Enter choice: "); // Gets user's choice

            switch (choice) {
                case 1:
                    currentAccount.displayBalance(); // Displays current account balance
                    break;
                case 2:
                    doDeposit(); // Initiates deposit process
                    break;
                case 3:
                    doWithdraw(); // Initiates withdrawal process
                    break;
                case 4:
                    currentAccount.displayTransactionHistory(); // Displays transaction history
                    break;
                case 5:
                    changePin(); // Initiates PIN change process
                    break;
                case 6:
                    currentAccount.saveToFile(); // Saves the current account's data before logging out
                    currentAccount = null; // Logs out the current account
                    System.out.println("✅ Logged out successfully."); // Logout confirmation
                    break;
                default:
                    System.out.println("❌ Invalid choice"); // Error for invalid input
            }
        }
    }

    // Handles the deposit operation
    private void doDeposit() {
        System.out.println("\n═══════════════════════════════════");
        System.out.println("            DEPOSIT");
        System.out.println("═══════════════════════════════════");

        double amount = getDoubleInput("Enter amount (minimum ₹100): "); // Gets deposit amount from user
        if (amount >= 100) { // Validates minimum deposit amount
            currentAccount.deposit(amount); // Calls deposit method on the current account
        } else {
            System.out.println("\n❌ Error: Minimum deposit is ₹100"); // Error message for low amount
        }
    }

    // Handles the withdrawal operation
    private void doWithdraw() {
        System.out.println("\n═══════════════════════════════════");
        System.out.println("            WITHDRAW");
        System.out.println("═══════════════════════════════════");

        double amount = getDoubleInput("Enter amount (multiples of ₹100): "); // Gets withdrawal amount from user
        if (amount < 100) { // Validates minimum withdrawal amount
            System.out.println("\n❌ Error: Minimum withdrawal is ₹100"); // Error message for low amount
        } else if (amount % 100 != 0) { // Validates if amount is a multiple of 100
            System.out.println("\n❌ Error: Amount must be in multiples of ₹100"); // Error message for non-multiple
        } else {
            currentAccount.withdraw(amount); // Calls withdrawal method on the current account
        }
    }

    // Handles the PIN change operation
    private void changePin() {
        System.out.println("\n═══════════════════════════════════");
        System.out.println("            CHANGE PIN");
        System.out.println("═══════════════════════════════════");

        String currentPin = getStringInput("Enter current PIN: "); // Gets current PIN from user
        if (!currentAccount.verifyPin(currentPin)) { // Verifies the current PIN
            System.out.println("\n❌ Incorrect current PIN"); // Error message for incorrect PIN
            return; // Exits if PIN is incorrect
        }

        String newPin = getStringInput("Enter new PIN (4 digits): "); // Gets new PIN from user
        // Validates if the new PIN is 4 digits and contains only digits
        if (newPin.length() != 4 || !newPin.matches("\\d+")) {
            System.out.println("\n❌ PIN must be 4 digits"); // Error message for invalid new PIN format
            return; // Exits if new PIN is invalid
        }

        currentAccount.changePin(newPin); // Changes the PIN on the current account
        System.out.println("\n✅ PIN changed successfully"); // Confirmation message
    }

    // Saves data for all accounts in the system to individual files
    private void saveAllAccounts() {
        System.out.println("\nSaving all account data...");
        for (BankAccount account : accounts.values()) { // Iterates through all accounts in the map
            account.saveToFile(); // Calls saveToFile method for each account
        }
        System.out.println("All accounts saved.");
    }

    // Loads all existing accounts from files into the ATM system
    private void loadAllAccounts() {
        System.out.println("\nLoading existing accounts...");
        File currentDir = new File("."); // Gets the current directory
        // Filters for .txt files that are not named "log.txt" (assuming account files are named after account numbers)
        File[] files = currentDir.listFiles((dir, name) -> name.endsWith(".txt") && !name.equals("log.txt"));

        if (files != null) { // Checks if any files were found
            for (File file : files) { // Iterates through each found file
                String fileName = file.getName();
                String accountNumber = fileName.substring(0, fileName.lastIndexOf(".txt")); // Extracts account number from file name

                BankAccount loadedAccount = BankAccount.loadFromFile(accountNumber); // Attempts to load the account from its file
                if (loadedAccount != null) { // If account was successfully loaded
                    accounts.put(loadedAccount.getAccountNumber(), loadedAccount); // Adds the loaded account to the map
                    System.out.println("Loaded account: " + loadedAccount.getAccountNumber()); // Confirms loading
                }
            }
        }
        System.out.println("Account loading complete. Total accounts loaded: " + accounts.size());
    }

    // Helper method to get string input from the user
    private String getStringInput(String prompt) {
        System.out.print(prompt); // Prints the prompt
        return scanner.nextLine().trim(); // Reads the line, trims whitespace, and returns it
    }

    // Helper method to get integer input from the user with error handling
    private int getIntInput(String prompt) {
        while (true) { // Loops until valid integer input is received
            try {
                System.out.print(prompt); // Prints the prompt
                return Integer.parseInt(scanner.nextLine().trim()); // Parses input to integer
            } catch (NumberFormatException e) { // Catches if input is not a valid number
                System.out.println("❌ Invalid input. Please enter a number."); // Error message
            }
        }
    }

    // Helper method to get double input from the user with error handling
    private double getDoubleInput(String prompt) {
        while (true) { // Loops until valid double input is received
            try {
                System.out.print(prompt); // Prints the prompt
                return Double.parseDouble(scanner.nextLine().trim()); // Parses input to double
            } catch (NumberFormatException e) { // Catches if input is not a valid number
                System.out.println("❌ Invalid input. Please enter a number."); // Error message
            }
        }
    }
}

// Main class to run the ATM application
public class IndianATM {
    public static void main(String[] args) {
        // Corrected line: Create an instance of the ATM class, not IndianATM
        ATM atm = new ATM();
        atm.run(); // Starts the ATM application
    }
}
