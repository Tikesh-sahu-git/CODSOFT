// Import necessary classes from the Java standard library.
import java.io.FileWriter;   // Used to write character files, in this case, to save the results.
import java.io.IOException;    // Used to handle errors that may occur during file I/O operations.
import java.util.*;          // Imports all classes from the java.util package, including Scanner, List, and ArrayList.

// The main class that encapsulates the entire program.
public class StudentGradeCalculator {

    // A static nested class to act as a data model for a student.
    // 'static' means it can be accessed without creating an instance of the outer class.
    static class Student {
        // Fields to store student information.
        String name;           // Stores the student's name.
        String[] subjectNames; // An array to store the names of the subjects.
        int[] marks;           // An array to store the marks for each corresponding subject.
        int totalMarks;        // The sum of marks from all subjects.
        double average;        // The average percentage across all subjects.
        String grade;          // The calculated letter grade (e.g., "A+", "B").
        String performance;    // A descriptive comment based on the grade.

        // The constructor to initialize a new Student object with all its details.
        public Student(String name, String[] subjectNames, int[] marks, int totalMarks, double average,
                       String grade, String performance) {
            // 'this' keyword is used to differentiate instance variables from local parameters.
            this.name = name;
            this.subjectNames = subjectNames;
            this.marks = marks;
            this.totalMarks = totalMarks;
            this.average = average;
            this.grade = grade;
            this.performance = performance;
        }
    }

    // The main entry point of the program.
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user's console.
        Scanner scanner = new Scanner(System.in);
        // Create an ArrayList to store all the Student objects.
        List<Student> studentList = new ArrayList<>();

        // Print a welcome header to the console for a good user experience.
        System.out.println("═══════════════════════════════════");
        System.out.println("    ENHANCED STUDENT GRADE CALCULATOR");
        System.out.println("═══════════════════════════════════");

        // Prompt the user for the total number of students to process.
        System.out.print("Enter the number of students: ");
        int studentCount = scanner.nextInt(); // Read the integer value.
        scanner.nextLine(); // Consume the leftover newline character to prevent issues with future .nextLine() calls.

        // Loop through the process for each student based on the count provided.
        for (int s = 1; s <= studentCount; s++) {
            System.out.println("\n→ Entering details for Student " + s);

            // Prompt for and read the student's name.
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            int numSubjects = 0; // Initialize the number of subjects.
            // Start an input validation loop to get a valid number of subjects (1-10).
            while (true) {
                System.out.print("Enter the number of subjects (1–10): ");
                try {
                    // Try to read the integer input.
                    numSubjects = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline.

                    // Validate if the number is within the allowed range.
                    if (numSubjects < 1 || numSubjects > 10) {
                        System.out.println("Please enter between 1 and 10 subjects.");
                        continue; // If not valid, restart the loop.
                    }
                    break; // If valid, exit the loop.
                } catch (Exception e) {
                    // Catch non-numeric input and prompt the user again.
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input from the scanner buffer.
                }
            }

            // Initialize arrays to store subject names and marks based on the number of subjects.
            String[] subjectNames = new String[numSubjects];
            int[] marks = new int[numSubjects];
            int totalMarks = 0; // Initialize total marks to zero for the current student.

            // Loop to get the name and marks for each subject.
            for (int i = 0; i < numSubjects; i++) {
                // Prompt for and read the name of the subject.
                System.out.print("Enter name of subject " + (i + 1) + ": ");
                subjectNames[i] = scanner.nextLine();

                // Start an input validation loop to get valid marks (0-100).
                while (true) {
                    System.out.print("Enter marks for " + subjectNames[i] + " (0-100): ");
                    try {
                        // Try to read the integer input for marks.
                        marks[i] = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline.

                        // Validate if the marks are within the 0-100 range.
                        if (marks[i] < 0 || marks[i] > 100) {
                            System.out.println("Marks must be between 0 and 100!");
                            continue; // If invalid, restart the loop.
                        }
                        totalMarks += marks[i]; // Add the valid marks to the running total.
                        break; // Exit the loop on valid input.
                    } catch (Exception e) {
                        // Catch non-numeric input for marks.
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine(); // Clear the invalid input.
                    }
                }
            }

            // Calculate the average. Cast totalMarks to double to ensure floating-point division.
            double average = (double) totalMarks / numSubjects;
            // Call a helper method to determine the grade from the average.
            String grade = calculateGrade(average);
            // Call a helper method to get a performance comment based on the grade.
            String performance = getPerformanceComment(grade);

            // Create a new Student object with all the collected data and add it to the list.
            studentList.add(new Student(studentName, subjectNames, marks, totalMarks, average, grade, performance));
        }

        // Sort the list of students in descending order based on their average score.
        // It uses a lambda expression for the Comparator for concise code.
        studentList.sort((a, b) -> Double.compare(b.average, a.average));

        // After sorting, the top score is the average of the student at the first index (index 0).
        double topScore = studentList.get(0).average;

        // Use StringBuilder for efficient string concatenation, which is faster than using '+'.
        StringBuilder resultBuilder = new StringBuilder();
        // Append the header for the final ranked list.
        resultBuilder.append("═══════════════════════════════════\n");
        resultBuilder.append("           STUDENT RANK LIST\n");
        resultBuilder.append("═══════════════════════════════════\n");

        // Loop through the sorted list to display ranks and detailed results.
        for (int i = 0; i < studentList.size(); i++) {
            Student stu = studentList.get(i); // Get the student at the current rank.
            boolean isTop = stu.average == topScore; // Check if this student is a top scorer.
            // Format the rank line, adding a trophy emoji 🏆 for the top scorer(s).
            String rankLine = String.format("Rank %d - %s%s\n", i + 1, stu.name, isTop ? " 🏆" : "");
            resultBuilder.append(rankLine); // Add the rank line to the builder.
            // Call displayResults to get the detailed report and append it. This method also prints to the console.
            resultBuilder.append(displayResults(stu));
        }

        // Save the final compiled results string to a text file.
        saveToFile(resultBuilder.toString());
        System.out.println("\n📁 Results saved to 'student_results.txt'");
        
        // Close the scanner to release system resources, which is a best practice.
        scanner.close();
    }

    // A helper method to calculate the grade based on the average percentage.
    private static String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+ (Excellent)";
        if (percentage >= 80) return "A (Very Good)";
        if (percentage >= 70) return "B (Good)";
        if (percentage >= 60) return "C (Satisfactory)";
        if (percentage >= 50) return "D (Pass)";
        return "F (Fail)";
    }

    // A helper method to provide a qualitative performance comment based on the grade.
    private static String getPerformanceComment(String grade) {
        if (grade.startsWith("A+")) return "Outstanding performance!";
        if (grade.startsWith("A")) return "Excellent work!";
        if (grade.startsWith("B")) return "Good job! Keep improving.";
        if (grade.startsWith("C")) return "Satisfactory. Room for improvement.";
        if (grade.startsWith("D")) return "You passed, but need to work harder.";
        return "You need to focus more on your studies.";
    }

    // A helper method to format and display the results for a single student.
    private static String displayResults(Student stu) {
        StringBuilder res = new StringBuilder(); // Use StringBuilder for efficiency.
        // Build the result string with proper formatting for readability.
        res.append("═══════════════════════════════════\n");
        res.append("Student Name      : ").append(stu.name).append("\n");

        res.append("\nSubject-wise Marks:\n");
        // Loop through the subjects and marks to display them in an aligned format.
        for (int i = 0; i < stu.subjectNames.length; i++) {
            // String.format allows for aligned columns. %-15s means left-aligned string in a 15-char space.
            res.append(String.format("%-15s: %3d/100\n", stu.subjectNames[i], stu.marks[i]));
        }

        res.append("\nSummary:\n");
        res.append("Total Subjects    : ").append(stu.subjectNames.length).append("\n");
        res.append("Total Marks       : ").append(stu.totalMarks).append("/").append(stu.subjectNames.length * 100).append("\n");
        // Format the average to two decimal places for a clean look.
        res.append(String.format("Average Percent   : %.2f%%\n", stu.average));
        res.append("Grade             : ").append(stu.grade).append("\n");
        res.append("Performance       : ").append(stu.performance).append("\n");
        res.append("═══════════════════════════════════\n\n");

        // Also print the individual student's results to the console immediately.
        System.out.println(res);
        // Return the formatted string so it can be added to the final file content.
        return res.toString();
    }

    // A helper method to save the final results string to a file.
    private static void saveToFile(String data) {
        // Use a try-with-resources statement for FileWriter to ensure it's automatically closed.
        try (FileWriter writer = new FileWriter("student_results.txt")) {
            writer.write(data); // Write the complete string data to the file.
        } catch (IOException e) {
            // If an error occurs during file writing, print an informative error message.
            System.out.println("❌ Error saving results: " + e.getMessage());
        }
    }
}
