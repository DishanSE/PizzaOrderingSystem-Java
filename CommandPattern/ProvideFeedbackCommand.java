package CommandPattern;

import FeedbackAndRatings.Feedback;
import FeedbackAndRatings.FeedbackManager;

public class ProvideFeedbackCommand implements Command {
    private Feedback feedback;
    private FeedbackManager feedbackManager;

    public ProvideFeedbackCommand() {}

    public ProvideFeedbackCommand(Feedback feedback, FeedbackManager feedbackManager) {
        this.feedback = feedback;
        this.feedbackManager = feedbackManager;
    }

    @Override
    public void execute() {
        feedbackManager.addFeedback(feedback);
        System.out.println("Feedback for order " + feedback.getOrderId() + " added successfully.");
    }
}
