import java.io.*;
import java.util.*;

public class DataHandler {

    public static List<Student> loadStudents(String fileName) {

        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

    if (line.trim().isEmpty()) continue;  // ✅ skip empty lines

                String[] data = line.split(",");

                String name = data[0];
                double cgpa = Double.parseDouble(data[1]);

                List<String> completed = parseList(data[2]);
                List<String> failed = parseList(data[3]);

                int attendance = Integer.parseInt(data[4]);

                Map<String, Integer> marks = parseMarks(data[5]);

                students.add(new Student(name, cgpa, completed, failed, attendance, marks));
            }

        } catch (Exception e) {
        }

        return students;
    }

    // Convert "A;B;C" → List
    private static List<String> parseList(String value) {
        List<String> list = new ArrayList<>();

        if (value == null || value.isEmpty()) return list;

        String[] items = value.split(";");
        for (String item : items) {
            list.add(item.trim());
        }

        return list;
    }

    // Convert "Maths:91;Physics:45" → Map
    private static Map<String, Integer> parseMarks(String value) {

        Map<String, Integer> map = new HashMap<>();

        if (value == null || value.isEmpty()) return map;

        String[] pairs = value.split(";");

        for (String pair : pairs) {
            String[] parts = pair.split(":");
            String subject = parts[0].trim();
            int mark = Integer.parseInt(parts[1].trim());

            map.put(subject, mark);
        }

        return map;
    }
}