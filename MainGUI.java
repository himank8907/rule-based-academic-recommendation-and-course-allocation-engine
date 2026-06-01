import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

public class MainGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Academic Recommendation System");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 2));

        JTextField nameField = new JTextField();
        JTextField cgpaField = new JTextField();
        JTextField completedField = new JTextField();
        JTextField failedField = new JTextField();
        JTextField attendanceField = new JTextField();
        JTextField marksField = new JTextField();

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton btn = new JButton("Get Recommendations");

        frame.add(new JLabel("Name:"));
        frame.add(nameField);

        frame.add(new JLabel("CGPA:"));
        frame.add(cgpaField);

        frame.add(new JLabel("Completed Courses (; separated):"));
        frame.add(completedField);

        frame.add(new JLabel("Failed Courses (; separated):"));
        frame.add(failedField);

        frame.add(new JLabel("Attendance:"));
        frame.add(attendanceField);

        frame.add(new JLabel("Marks (Subject:Marks;Subject:Marks):"));
        frame.add(marksField);

        frame.add(btn);
        frame.add(new JLabel(""));

        frame.add(new JLabel("Output:"));
        frame.add(new JScrollPane(outputArea));

        btn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                double cgpa = Double.parseDouble(cgpaField.getText().trim());
                int attendance = Integer.parseInt(attendanceField.getText().trim());

                // Completed
                List<String> completed = Arrays.stream(completedField.getText().split("[;,]"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();

                // Failed
                List<String> failed = Arrays.stream(failedField.getText().split("[;,]"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();

                // Marks parsing (FIXED)
                Map<String, Integer> marks = new HashMap<>();
                String marksInput = marksField.getText().trim();

                if (marksInput.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Enter marks!");
                    return;
                }

                String[] pairs = marksInput.split(";");

                for (String p : pairs) {

                    if (!p.contains(":")) {
                        JOptionPane.showMessageDialog(frame, "Use format Subject:Marks");
                        return;
                    }

                    String[] kv = p.split(":");

                    if (kv.length != 2) {
                        JOptionPane.showMessageDialog(frame, "Invalid entry: " + p);
                        return;
                    }

                    String subject = kv[0].trim();
                    int mark = Integer.parseInt(kv[1].trim());

                    marks.put(subject, mark);
                }

                // VALIDATION: marks without completed course ❗
                for (String subject : marks.keySet()) {
                    if (!completed.contains(subject)) {
                        JOptionPane.showMessageDialog(frame,
                                "You added marks for '" + subject + "' but not in completed courses");
                        return;
                    }
                }

                Student s = new Student(name, cgpa, completed, failed, attendance, marks);

                List<Recommendation> recs = RuleEngine.evaluate(s);

                recs.sort((a, b) -> Double.compare(b.confidence, a.confidence));

                outputArea.setText("");

                for (Recommendation r : recs) {
                    outputArea.append(r.course + " (" + r.confidence + ")\n");
                    outputArea.append("Reason: " + r.reason + "\n\n");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        frame.setVisible(true);
    }
}