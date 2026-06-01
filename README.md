Rule Based Academic Recommendation and Course Allocation Engine


1. PROJECT TITLE
   Academic Recommendation System

---

2. PROJECT DESCRIPTION
   This project is a rule-based academic recommendation system developed using Java.
   It analyzes a student’s academic profile including CGPA, attendance, completed courses, failed courses, and subject-wise marks. Based on predefined logical rules, the system recommends suitable courses for the student.

The system ensures strict validation of inputs and provides recommendations only when all conditions are satisfied.

---

3. PURPOSE OF PROJECT

* To help students choose appropriate courses based on their performance
* To simulate an expert system using rule-based logic
* To demonstrate the use of conditions, data structures, and decision-making in Java
* To ensure recommendations are accurate, fair, and performance-based

---

4. FEATURES

* Rule-based recommendation engine
* GUI interface using Java Swing
* CSV file input support
* Validation for missing or incorrect inputs
* Dynamic recommendation generation
* Confidence-based sorting of results

---

5. STEPS TO RUN THE CODE

Step 1: Open the project in any Java IDE (VS Code / IntelliJ / Eclipse)

Step 2: Ensure all files are present:

* Main.java
* MainGUI.java
* RuleEngine.java
* Student.java
* Recommendation.java
* DataHandler.java
* students.csv

Step 3: Compile all Java files

Step 4: Run:

* Main.java → for terminal-based output
* MainGUI.java → for GUI-based interface

Step 5: Enter inputs in GUI OR use CSV file

---

6. REQUIRED INPUT FORMAT

A. Completed Courses
Example:
Python;Maths;Data Structures

B. Failed Courses
Example:
Physics

C. Marks Format (IMPORTANT)
Format:
Subject:Marks;Subject:Marks

Example:
Maths:90;Python:85

---

7. VALIDATION RULES

* Every completed course must have marks
* Marks cannot be added for subjects not in completed courses
* Invalid formats are rejected
* System shows error messages for incorrect inputs

---

8. OUTPUT

The system generates:

* List of recommended courses
* Confidence level for each recommendation
* Reason for recommendation

Example:
Data Structures (0.90)
Reason: Good Python base

---

9. TEST CASES

* Total test cases: 100
* Covers:
  • Basic scenarios
  • Edge cases
  • Validation errors
  • Advanced rule combinations

---

10. PROJECT STRUCTURE

Folders included:

* ALGORITHMS
* CODE
* DIAGRAMS
* PROMPT OUTPUTS
* REFERENCE DOCS
* REPORT
* TEST CASES
* README

---

11. INDIVIDUAL CONTRIBUTION

* Designed rule-based recommendation logic
* Implemented Java classes and GUI
* Developed validation system
* Created test cases and dataset
* Structured project files as per guidelines

---

12. CONCLUSION

This project successfully demonstrates a rule-based expert system that provides academic recommendations based on multiple parameters. It ensures logical clarity, correctness, and structured decision-making.

---
