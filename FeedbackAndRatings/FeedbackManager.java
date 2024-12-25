package FeedbackAndRatings;

import java.util.HashMap;
import java.util.Map;

public class FeedbackManager {
    private Map<String, Feedback> feedbacks = new HashMap<>();

    public void addFeedback(Feedback feedback) {
        feedbacks.put(feedback.getOrderId(), feedback);
    }

    public Feedback getFeedback(String orderId) {
        return feedbacks.get(orderId);
    }
}
