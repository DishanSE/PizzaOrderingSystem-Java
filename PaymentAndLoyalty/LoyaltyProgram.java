package PaymentAndLoyalty;

import java.util.HashMap;
import java.util.Map;

import Models.User;

public class LoyaltyProgram {
    private Map<User, Integer> loyaltyPoints = new HashMap<>();

    public void awardPoints(User user, int points) {
        loyaltyPoints.put(user, loyaltyPoints.getOrDefault(user, 0) + points);
    }

    public int getPoints(User user) {
        return loyaltyPoints.getOrDefault(user, 0);
    }

    public void redeemPoints(User user, int points) {
        int currentPoints = loyaltyPoints.get(user);
        if (currentPoints >= points) {
            loyaltyPoints.put(user, currentPoints - points);
        } else {
            throw new IllegalArgumentException("Not enough loyalty points.");
        }
    }
}
