package FeedbackAndRatings;

public class Feedback {
    private String orderId;
    private String feedback;
    private int rating;

    public Feedback() {}

    public Feedback(String orderId, String feedback, int rating) {
        this.orderId = orderId;
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
