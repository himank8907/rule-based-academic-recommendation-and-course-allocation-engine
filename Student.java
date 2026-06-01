import java.util.*;

public class Student {

    String name;
    double cgpa;
    List<String> completedCourses;
    List<String> failedCourses;
    int attendance;
    Map<String, Integer> marks;

    public Student(String name, double cgpa,
                   List<String> completedCourses,
                   List<String> failedCourses,
                   int attendance,
                   Map<String, Integer> marks) {

        this.name = name;
        this.cgpa = cgpa;
        this.completedCourses = completedCourses;
        this.failedCourses = failedCourses;
        this.attendance = attendance;
        this.marks = marks;
    }
}