# Student Grade Calculator 🎓

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![License](https://img.shields.io/badge/License-MIT-green)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/yourusername/student-grade-calculator/pulls)

A Java application that calculates student grades, generates performance reports, and ranks students based on their academic performance.

## Features ✨

- 📝 Input student details and subject marks
- 📊 Calculate total marks, average percentage, and letter grades
- 🏆 Rank students based on performance
- 📂 Save results to a text file
- 📊 Subject-wise performance breakdown
- 🛠️ Input validation for marks (0-100) and subject count (1-10)

## How It Works ⚙️

1. Enter the number of students to process
2. For each student:
   - Enter name and number of subjects
   - Input subject names and marks
3. The system calculates:
   - Total marks
   - Average percentage
   - Letter grade (A+ to F)
   - Performance comment
4. Students are ranked by average score
5.  Results are displayed and 📁 saved to `student_results.txt`

# 🧮 Sample Input – Student Grade Calculator

```text
═══════════════════════════════════
     STUDENT GRADE CALCULATOR
═══════════════════════════════════
Enter the number of students: 2

→ Entering details for Student 1
Enter student name: Yash
Enter the number of subjects (1–10): 3
Enter name of subject 1: Math
Enter marks for Math (0-100): 95
Enter name of subject 2: Science
Enter marks for Science (0-100): 90
Enter name of subject 3: English
Enter marks for English (0-100): 92

→ Entering details for Student 2
Enter student name: Raj
Enter the number of subjects (1–10): 3
Enter name of subject 1: Math
Enter marks for Math (0-100): 85
Enter name of subject 2: Science
Enter marks for Science (0-100): 87
Enter name of subject 3: English
Enter marks for English (0-100): 83


```
# 🧮 Output – Student Grade Calculator
```
═══════════════════════════════════
STUDENT RANK LIST
═══════════════════════════════════
Rank 1 - Yash 🏆
═══════════════════════════════════
Student Name : Yash

Subject-wise Marks:
Maths : 92/100
Science : 88/100
English : 95/100

Summary:
Total Subjects : 3
Total Marks : 275/300
Average Percent : 91.67%
Grade : A+ (Excellent)
Performance : Outstanding performance!

```

## Requirements 📋

- Java 17 or higher
- Basic terminal/command prompt knowledge

## Installation & Usage 🚀

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/student-grade-calculator.git

2. Navigate to the project directory:
   cd student-grade-calculator

3. Compile the Java file:
   javac StudentGradeCalculator.java

4. Run the program:
java StudentGradeCalculator


License 📄
This project is licensed under the MIT License.

Contributing 🤝
Contributions, issues, and feature requests are welcome!
Feel free to open a pull request.


