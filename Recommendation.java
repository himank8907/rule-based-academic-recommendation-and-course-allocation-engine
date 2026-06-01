public class Recommendation {
    String course;
    double confidence;
    String reason;

    public Recommendation(String course, double confidence, String reason) {
        this.course = course;
        this.confidence = confidence;
        this.reason = reason;
    }
}