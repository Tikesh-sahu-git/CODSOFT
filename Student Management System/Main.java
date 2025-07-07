import java.io.*; // Imports classes for input/output operations (e.g., File, FileReader, FileWriter, BufferedReader, PrintWriter)
import java.util.ArrayList; // Imports ArrayList for dynamic array implementation
import java.util.List; // Imports List interface for collection of students
import java.util.Scanner; // Imports Scanner for reading user input

// Student class represents a single student with their details
class Student {
    private String name; // Private field to store student's name
    private String rollNumber; // Private field to store student's roll number (unique identifier)
    private String grade; // Private field to store student's grade
    private String email; // Private field to store student's email address
    private String phone; // Private field to store student's phone number

    // Constructor to initialize a new Student object
    public Student(String name, String rollNumber, String grade, String email, String phone) {
        this.name = name; // Assigns the provided name to the 'name' field
        this.rollNumber = rollNumber; // Assigns the provided rollNumber to the 'rollNumber' field
        this.grade = grade; // Assigns the provided grade to the 'grade' field
        this.email = email; // Assigns the provided email to the 'email' field
        this.phone = phone; // Assigns the provided phone to the 'phone' field
    }

    // Getter methods to access private fields
    public String getName() { return name; } // Returns the student's name
    public String getRollNumber() { return rollNumber; } // Returns the student's roll number
    public String getGrade() { return grade; } // Returns the student's grade
    public String getEmail() { return email; } // Returns the student's email
    public String getPhone() { return phone; } // Returns the student's phone number

    // Setter methods to modify private fields (except rollNumber, which is usually immutable after creation)
    public void setName(String name) { this.name = name; } // Sets the student's name
    public void setGrade(String grade) { this.grade = grade; } // Sets the student's grade
    public void setEmail(String email) { this.email = email; } // Sets the student's email
    public void setPhone(String phone) { this.phone = phone; } // Sets the student's phone number

    // Overrides the toString method to provide a formatted string representation of a Student object
    @Override
    public String toString() {
        // Uses String.format for structured output, ensuring consistent column widths
        return String.format("| %-15s | %-12s | %-6s | %-25s | %-12s |",
                name, rollNumber, grade, email, phone);
    }
}

// Student Management System class handles operations related to managing students
class StudentManagementSystem {
    private List<Student> students; // A list to store Student objects
    private static final String FILE_NAME = "students.txt"; // Constant for the file name where student data is stored

    // Constructor for StudentManagementSystem
    public StudentManagementSystem() {
        students = new ArrayList<>(); // Initializes the ArrayList for students
        loadStudentsFromFile(); // Loads student data from the file when the system starts
    }

    // Adds a new student to the system
    public void addStudent(Student student) {
        students.add(student); // Adds the student object to the list
        saveStudentsToFile(); // Saves the updated list back to the file
    }

    // Removes a student from the system based on their roll number
    public boolean removeStudent(String rollNumber) {
        Student student = findStudent(rollNumber); // Tries to find the student first
        if (student != null) { // If the student is found
            students.remove(student); // Removes the student from the list
            saveStudentsToFile(); // Saves the updated list to the file
            return true; // Returns true indicating successful removal
        }
        return false; // Returns false if the student was not found
    }

    // Finds a student by their roll number
    public Student findStudent(String rollNumber) {
        for (Student student : students) { // Iterates through the list of students
            if (student.getRollNumber().equals(rollNumber)) { // Compares roll numbers
                return student; // Returns the student if a match is found
            }
        }
        return null; // Returns null if no student with the given roll number is found
    }

    // Displays all students currently in the system in a formatted table
    public void displayAllStudents() {
        if (students.isEmpty()) { // Checks if there are no students
            System.out.println("No students in the system.");
            return;
        }

        // Prints the table header
        System.out.println("\n+-----------------+--------------+--------+---------------------------+--------------+");
        System.out.println("| Name            | Roll Number  | Grade  | Email                     | Phone        |");
        System.out.println("+-----------------+--------------+--------+---------------------------+--------------+");
        for (Student student : students) { // Iterates through each student
            System.out.println(student); // Prints the formatted student details using the overridden toString method
        }
        // Prints the table footer and total count
        System.out.println("+-----------------+--------------+--------+---------------------------+--------------+");
        System.out.println("Total students: " + students.size());
    }

    // Saves the current list of students to a text file
    public void saveStudentsToFile() {
        // try-with-resources ensures the PrintWriter is closed automatically
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) { // Iterates through each student
                // Writes student data to the file, separated by commas (CSV format)
                writer.println(student.getName() + "," + student.getRollNumber() + "," +
                               student.getGrade() + "," + student.getEmail() + "," + student.getPhone());
            }
        } catch (IOException e) { // Catches potential I/O errors
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // Loads student data from a text file into the system
    private void loadStudentsFromFile() {
        File file = new File(FILE_NAME); // Creates a File object for the student data file
        if (!file.exists()) return; // If the file doesn't exist, there's nothing to load, so return

        // try-with-resources ensures the BufferedReader is closed automatically
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line; // Variable to hold each line read from the file
            while ((line = reader.readLine()) != null) { // Reads lines until the end of the file
                String[] data = line.split(","); // Splits the line by comma to get individual data fields
                if (data.length == 5) { // Ensures all 5 expected fields are present
                    // Creates a new Student object and adds it to the list
                    students.add(new Student(data[0], data[1], data[2], data[3], data[4]));
                }
            }
        } catch (IOException e) { // Catches potential I/O errors
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }
}

// Main class contains the main method to run the Student Management System application
public class Main {
    private static Scanner scanner = new Scanner(System.in); // Scanner object for global input
    private static StudentManagementSystem sms = new StudentManagementSystem(); // Instance of the SMS

    public static void main(String[] args) {
        // Displays a welcome banner for the application
        System.out.println("══════════════════════════════════════════════");
        System.out.println("      STUDENT MANAGEMENT SYSTEM");
        System.out.println("══════════════════════════════════════════════");

        boolean running = true; // Flag to control the main application loop
        while (running) { // Main loop continues until 'running' becomes false
            displayMenu(); // Displays the main menu options
            int choice = getIntInput("Enter your choice: "); // Gets the user's menu choice

            switch (choice) { // Uses a switch statement to handle different menu options
                case 1:
                    addStudent(); // Calls method to add a student
                    break;
                case 2:
                    removeStudent(); // Calls method to remove a student
                    break;
                case 3:
                    searchStudent(); // Calls method to search for a student
                    break;
                case 4:
                    editStudent(); // Calls method to edit student information
                    break;
                case 5:
                    sms.displayAllStudents(); // Calls SMS method to display all students
                    break;
                case 6:
                    running = false; // Sets running to false to exit the loop
                    System.out.println("Exiting system. Goodbye!"); // Exit message
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); // Handles invalid menu input
            }
        }
        scanner.close(); // Closes the scanner to release resources
    }

    // Displays the main menu options to the user
    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add New Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Search Student");
        System.out.println("4. Edit Student");
        System.out.println("5. Display All Students");
        System.out.println("6. Exit");
    }

    // Method to handle adding a new student
    private static void addStudent() {
        System.out.println("\nAdd New Student");
        System.out.println("----------------");

        String name = getStringInput("Enter student name: "); // Prompts for and gets student name
        String rollNumber = getRollNumberInput(); // Prompts for and validates unique roll number
        String grade = getStringInput("Enter grade: "); // Prompts for and gets student grade
        String email = getEmailInput(); // Prompts for and validates email format
        String phone = getPhoneInput(); // Prompts for and validates phone number format

        Student student = new Student(name, rollNumber, grade, email, phone); // Creates a new Student object
        sms.addStudent(student); // Adds the student to the system
        System.out.println("Student added successfully!"); // Confirmation message
    }

    // Method to handle removing a student
    private static void removeStudent() {
        System.out.println("\nRemove Student");
        System.out.println("----------------");
        String rollNumber = getStringInput("Enter roll number to remove: "); // Gets the roll number to remove

        if (sms.removeStudent(rollNumber)) { // Attempts to remove the student
            System.out.println("Student removed successfully!"); // Success message
        } else {
            System.out.println("Student not found with roll number: " + rollNumber); // Not found message
        }
    }

    // Method to handle searching for a student
    private static void searchStudent() {
        System.out.println("\nSearch Student");
        System.out.println("----------------");
        String rollNumber = getStringInput("Enter roll number to search: "); // Gets the roll number to search

        Student student = sms.findStudent(rollNumber); // Attempts to find the student
        if (student != null) { // If student is found
            System.out.println("\nStudent Found:");
            // Displays all details of the found student
            System.out.println("Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone: " + student.getPhone());
        } else {
            System.out.println("Student not found with roll number: " + rollNumber); // Not found message
        }
    }

    // Method to handle editing student information
    private static void editStudent() {
        System.out.println("\nEdit Student");
        System.out.println("----------------");
        String rollNumber = getStringInput("Enter roll number to edit: "); // Gets the roll number of the student to edit

        Student student = sms.findStudent(rollNumber); // Finds the student
        if (student != null) { // If student is found
            System.out.println("\nCurrent Information:");
            // Displays current editable information
            System.out.println("1. Name: " + student.getName());
            System.out.println("2. Grade: " + student.getGrade());
            System.out.println("3. Email: " + student.getEmail());
            System.out.println("4. Phone: " + student.getPhone());

            int choice = getIntInput("\nEnter field number to edit (1-4, 0 to cancel): "); // Gets which field to edit
            if (choice >= 1 && choice <= 4) { // Validates choice
                switch (choice) {
                    case 1:
                        student.setName(getStringInput("Enter new name: ")); // Edits name
                        break;
                    case 2:
                        student.setGrade(getStringInput("Enter new grade: ")); // Edits grade
                        break;
                    case 3:
                        student.setEmail(getEmailInput()); // Edits email with validation
                        break;
                    case 4:
                        student.setPhone(getPhoneInput()); // Edits phone with validation
                        break;
                }
                sms.saveStudentsToFile(); // Saves changes to file
                System.out.println("Student information updated successfully!"); // Confirmation
            } else if (choice == 0) {
                System.out.println("Edit cancelled.");
            } else {
                System.out.println("Invalid field number.");
            }
        } else {
            System.out.println("Student not found with roll number: " + rollNumber); // Not found message
        }
    }

    // Helper method to get non-empty string input from the user
    private static String getStringInput(String prompt) {
        System.out.print(prompt); // Prints the prompt
        String input = scanner.nextLine().trim(); // Reads the line and trims whitespace
        while (input.isEmpty()) { // Loops until a non-empty input is received
            System.out.println("This field cannot be empty!");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input; // Returns the valid input
    }

    // Helper method to get a unique roll number input from the user
    private static String getRollNumberInput() {
        while (true) { // Loops indefinitely until a unique roll number is provided
            String rollNumber = getStringInput("Enter roll number: "); // Gets roll number input
            if (sms.findStudent(rollNumber) == null) { // Checks if the roll number already exists
                return rollNumber; // Returns if unique
            }
            System.out.println("Roll number already exists. Please enter a unique roll number."); // Error message
        }
    }

    // Helper method to get and validate email input from the user
    private static String getEmailInput() {
        while (true) { // Loops indefinitely until a valid email format is provided
            String email = getStringInput("Enter email: "); // Gets email input
            if (email.contains("@") && email.contains(".")) { // Basic validation for '@' and '.'
                return email; // Returns if valid
            }
            System.out.println("Invalid email format. Please include '@' and '.'"); // Error message
        }
    }

    // Helper method to get and validate phone number input from the user
    private static String getPhoneInput() {
        while (true) { // Loops indefinitely until a valid phone number format is provided
            String phone = getStringInput("Enter phone number: "); // Gets phone number input
            // Validates if the phone number consists of 10 to 15 digits using regex
            if (phone.matches("\\d{10,15}")) {
                return phone; // Returns if valid
            }
            System.out.println("Invalid phone number. Please enter 10-15 digits."); // Error message
        }
    }

    // Helper method to get integer input from the user with error handling
    private static int getIntInput(String prompt) {
        while (true) { // Loops indefinitely until a valid integer is provided
            try {
                System.out.print(prompt); // Prints the prompt
                int input = Integer.parseInt(scanner.nextLine()); // Tries to parse the input as an integer
                return input; // Returns the integer if successful
            } catch (NumberFormatException e) { // Catches if input is not a valid number
                System.out.println("Invalid input. Please enter a number."); // Error message
            }
        }
    }
}
