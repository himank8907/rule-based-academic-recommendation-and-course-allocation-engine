import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Student> students = DataHandler.loadStudents("students.csv");

        for (Student s : students) {

            System.out.println("\n==================================================");
            System.out.println("Student Name : " + s.name);
            System.out.println("CGPA         : " + s.cgpa);
            System.out.println("Attendance   : " + s.attendance);
            System.out.println("--------------------------------------------------");

            List<Recommendation> recs = RuleEngine.evaluate(s);

            // Remove duplicates
            Set<String> seen = new HashSet<>();
            List<Recommendation> unique = new ArrayList<>();

            for (Recommendation r : recs) {
                if (!seen.contains(r.course)) {
                    seen.add(r.course);
                    unique.add(r);
                }
            }

            // Sort by confidence descending
            unique.sort((a, b) -> Double.compare(b.confidence, a.confidence));

            for (Recommendation r : unique) {
                System.out.println("- " + r.course + " (Confidence: " + r.confidence + ")");
                System.out.println("  Reason: " + r.reason);
            }
        }
    }
}