import java.util.*;

public class RuleEngine {

    // ================= CONFIG =================
    static final double MIN_CGPA_BASIC = 7.0;
    static final double MIN_CGPA_ADVANCED = 8.0;
    static final double MIN_CGPA_EXPERT = 8.5;

    static final int MIN_ATTENDANCE_LOW = 60;
    static final int MIN_ATTENDANCE_MEDIUM = 70;
    static final int MIN_ATTENDANCE_HIGH = 75;
    static final int MIN_ATTENDANCE_EXCELLENT = 90;

    static final int MIN_MARKS_PASS = 60;
    static final int MIN_MARKS_AVERAGE = 65;
    static final int MIN_MARKS_GOOD = 70;
    static final int MIN_MARKS_STRONG = 80;
    static final int MIN_MARKS_EXCELLENT = 90;

    public static List<Recommendation> evaluate(Student s) {

        List<Recommendation> recs = new ArrayList<>();

        // ================= VALIDATION =================

        // 1️⃣ Completed → must have marks
        for (String course : s.completedCourses) {
            if (!s.marks.containsKey(course)) {
                return List.of(new Recommendation(
                        "Missing Marks",
                        0.0,
                        "Please add marks for completed subject: " + course
                ));
            }
        }

        // 2️⃣ Marks → must have completed
        for (String subject : s.marks.keySet()) {
            if (!s.completedCourses.contains(subject)) {
                return List.of(new Recommendation(
                        "Invalid Input",
                        0.0,
                        "You added marks for '" + subject + "' but did not add it in completed courses"
                ));
            }
        }

        // ================= RULES =================

        // 🔴 Remedial Programming
        if (s.cgpa < MIN_CGPA_BASIC &&
            s.attendance >= MIN_ATTENDANCE_LOW &&
            s.completedCourses.contains("Python") &&
            s.marks.get("Python") < MIN_MARKS_PASS) {

            recs.add(new Recommendation("Remedial Programming", 0.80,
                    "Low CGPA + weak Python"));
        }

        // 🟢 Data Structures
        if (s.cgpa >= MIN_CGPA_BASIC &&
            s.attendance >= MIN_ATTENDANCE_MEDIUM &&
            s.completedCourses.contains("Python") &&
            s.marks.get("Python") >= MIN_MARKS_AVERAGE) {

            recs.add(new Recommendation("Data Structures", 0.90,
                    "Good Python base"));
        }

        // 🔥 Advanced Algorithms
        if (s.cgpa >= MIN_CGPA_ADVANCED &&
            s.attendance >= MIN_ATTENDANCE_HIGH &&
            s.completedCourses.contains("Python") &&
            s.marks.get("Python") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Advanced Algorithms", 0.85,
                    "Strong academic profile"));
        }

        // 🤖 Machine Learning
        if (s.cgpa >= MIN_CGPA_ADVANCED &&
            s.attendance >= MIN_ATTENDANCE_MEDIUM &&
            s.completedCourses.contains("Python") &&
            s.completedCourses.contains("Maths") &&
            s.marks.get("Maths") >= MIN_MARKS_STRONG &&
            s.marks.get("Python") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Machine Learning Basics", 0.88,
                    "Strong maths + programming"));
        }

        // 🧠 Artificial Intelligence
        if (s.cgpa >= MIN_CGPA_EXPERT &&
            s.attendance >= MIN_ATTENDANCE_HIGH &&
            s.completedCourses.contains("Python") &&
            s.completedCourses.contains("Maths") &&
            s.marks.get("Python") >= MIN_MARKS_STRONG &&
            s.marks.get("Maths") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Artificial Intelligence", 0.88,
                    "High CGPA + strong fundamentals"));
        }

        // 📊 Data Science
        if (s.cgpa >= MIN_CGPA_ADVANCED &&
            s.attendance >= MIN_ATTENDANCE_HIGH &&
            s.completedCourses.contains("Python") &&
            s.completedCourses.contains("Maths") &&
            s.marks.get("Maths") >= MIN_MARKS_EXCELLENT &&
            s.marks.get("Python") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Data Science", 0.89,
                    "Excellent maths + coding"));
        }

        // 🗄️ DBMS
        if (s.cgpa >= MIN_CGPA_BASIC &&
            s.attendance >= MIN_ATTENDANCE_MEDIUM &&
            s.completedCourses.contains("Python") &&
            s.completedCourses.contains("Data Structures") &&
            s.marks.get("Python") >= MIN_MARKS_GOOD &&
            s.marks.get("Data Structures") >= MIN_MARKS_GOOD) {

            recs.add(new Recommendation("Database Management Systems", 0.82,
                    "Strong foundation"));
        }

        // 💻 Operating Systems
        if (s.cgpa >= MIN_CGPA_BASIC &&
            s.attendance >= MIN_ATTENDANCE_MEDIUM &&
            s.completedCourses.contains("Data Structures") &&
            s.marks.get("Data Structures") >= MIN_MARKS_GOOD) {

            recs.add(new Recommendation("Operating Systems", 0.75,
                    "Good DS background"));
        }

        // 📘 Theory of Computation
        if (s.cgpa >= MIN_CGPA_ADVANCED &&
            s.attendance >= MIN_ATTENDANCE_HIGH &&
            s.completedCourses.contains("Python") &&
            s.completedCourses.contains("Maths") &&
            s.marks.get("Maths") >= MIN_MARKS_STRONG &&
            s.marks.get("Python") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Theory of Computation", 0.84,
                    "Strong maths + programming"));
        }

        // 📐 Discrete Maths
        if (s.cgpa >= MIN_CGPA_BASIC &&
            s.attendance >= MIN_ATTENDANCE_MEDIUM &&
            s.completedCourses.contains("Maths") &&
            s.marks.get("Maths") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Discrete Mathematics", 0.83,
                    "Strong maths"));
        }

        // 💻 OOP
        if (s.cgpa >= MIN_CGPA_BASIC &&
            s.attendance >= MIN_ATTENDANCE_MEDIUM &&
            s.completedCourses.contains("Python") &&
            s.marks.get("Python") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Object Oriented Programming", 0.86,
                    "Strong coding"));
        }

        // 🎯 Research (FIXED PROPERLY)
        if (s.cgpa >= MIN_CGPA_EXPERT &&
            s.attendance >= MIN_ATTENDANCE_EXCELLENT &&
            s.completedCourses.contains("Maths") &&
            s.completedCourses.contains("Python") &&
            s.marks.get("Maths") >= MIN_MARKS_EXCELLENT &&
            s.marks.get("Python") >= MIN_MARKS_STRONG) {

            recs.add(new Recommendation("Research Methodology", 0.90,
                    "Excellent profile with strong maths + programming"));
        }

        // 🚨 Counseling
        if (s.failedCourses.size() >= 2) {
            recs.add(new Recommendation("Academic Counseling", 0.78,
                    "Multiple failed subjects"));
        }

        // ❌ Final fallback
        if (recs.isEmpty()) {
            recs.add(new Recommendation("No Course Eligible", 0.0,
                    "You are not eligible based on current profile"));
        }

        // ================= ADDITIONAL ADVANCED RULES =================

// 📘 Software Engineering
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Python") &&
    s.completedCourses.contains("Data Structures") &&
    s.marks.get("Python") >= MIN_MARKS_GOOD &&
    s.marks.get("Data Structures") >= MIN_MARKS_GOOD) {

    recs.add(new Recommendation("Software Engineering", 0.82,
            "Good coding + DS foundation"));
}

// 🌐 Computer Networks
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Data Structures") &&
    s.marks.get("Data Structures") >= MIN_MARKS_GOOD) {

    recs.add(new Recommendation("Computer Networks", 0.75,
            "Strong DS base"));
}

// 🔐 Cyber Security
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Python") &&
    s.marks.get("Python") >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Cyber Security", 0.86,
            "Strong programming skills"));
}

// ☁️ Cloud Computing
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Operating Systems") &&
    s.marks.getOrDefault("Operating Systems",0) >= MIN_MARKS_GOOD) {

    recs.add(new Recommendation("Cloud Computing", 0.80,
            "OS knowledge required"));
}

// 🧠 Deep Learning
if (s.cgpa >= MIN_CGPA_EXPERT &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Machine Learning Basics") &&
    s.marks.getOrDefault("Maths",0) >= MIN_MARKS_EXCELLENT) {

    recs.add(new Recommendation("Deep Learning", 0.91,
            "Advanced ML + strong maths"));
}

// 📊 Big Data Analytics
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Data Science") &&
    s.marks.get("Maths") >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Big Data Analytics", 0.88,
            "Data science background"));
}

// ⚙️ Compiler Design
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Theory of Computation") &&
    s.marks.get("Maths") >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Compiler Design", 0.85,
            "TOC required"));
}

// 🔄 Parallel Computing
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Operating Systems") &&
    s.marks.getOrDefault("Operating Systems",0) >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Parallel Computing", 0.84,
            "OS + performance understanding"));
}

// 🤝 Human Computer Interaction
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Python")) {

    recs.add(new Recommendation("Human Computer Interaction", 0.70,
            "Basic programming needed"));
}

// 🧩 Design Patterns
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Object Oriented Programming") &&
    s.marks.getOrDefault("Python",0) >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Design Patterns", 0.86,
            "Strong OOP skills"));
}

// 📈 Business Analytics
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Maths") &&
    s.marks.get("Maths") >= MIN_MARKS_GOOD) {

    recs.add(new Recommendation("Business Analytics", 0.78,
            "Maths based analysis"));
}

// 🔍 Information Retrieval
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Data Structures") &&
    s.marks.get("Data Structures") >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Information Retrieval", 0.83,
            "Strong DS required"));
}

// 🧮 Numerical Methods
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Maths") &&
    s.marks.get("Maths") >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Numerical Methods", 0.80,
            "Maths intensive"));
}

// 📡 Distributed Systems
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Operating Systems") &&
    s.marks.getOrDefault("Operating Systems",0) >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Distributed Systems", 0.87,
            "Strong OS knowledge"));
}

// 🧠 Natural Language Processing
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Python") &&
    s.completedCourses.contains("Machine Learning Basics")) {

    recs.add(new Recommendation("NLP", 0.89,
            "ML + Python required"));
}

// 📷 Computer Vision
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Machine Learning Basics") &&
    s.marks.get("Maths") >= MIN_MARKS_STRONG) {

    recs.add(new Recommendation("Computer Vision", 0.89,
            "Maths + ML"));
}

// ⚡ Embedded Systems
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    !s.failedCourses.contains("Physics")) {

    recs.add(new Recommendation("Embedded Systems", 0.70,
            "Physics background"));
}

// 📊 Linear Algebra (Advanced Maths)
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Maths") &&
    s.marks.get("Maths") >= MIN_MARKS_EXCELLENT) {

    recs.add(new Recommendation("Linear Algebra", 0.88,
            "Excellent maths"));
}

// 🔧 DevOps
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Operating Systems")) {

    recs.add(new Recommendation("DevOps", 0.78,
            "OS knowledge"));
}

// 🎮 Game Development
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Python")) {

    recs.add(new Recommendation("Game Development", 0.75,
            "Programming skills"));
}

// 🧪 Software Testing
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_MEDIUM &&
    s.completedCourses.contains("Software Engineering")) {

    recs.add(new Recommendation("Software Testing", 0.76,
            "SE background"));
}

// 📦 Data Warehousing
if (s.cgpa >= MIN_CGPA_ADVANCED &&
    s.attendance >= MIN_ATTENDANCE_HIGH &&
    s.completedCourses.contains("Database Management Systems")) {

    recs.add(new Recommendation("Data Warehousing", 0.85,
            "DBMS knowledge"));
}

// 🛰️ IoT
if (s.cgpa >= MIN_CGPA_BASIC &&
    s.attendance >= MIN_ATTENDANCE_LOW &&
    !s.failedCourses.contains("Physics")) {

    recs.add(new Recommendation("Internet of Things", 0.72,
            "Physics + basics"));
}
        return recs;
    }
}