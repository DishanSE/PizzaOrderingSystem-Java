package PaymentAndLoyalty;

import java.util.*;

import OrderManagement.Order;

public class PaymentProcessor {
    public Map<String, PaymentStrategy> paymentStrategies = new HashMap<>();

    public void addPaymentStrategy(String key, PaymentStrategy strategy) {
        paymentStrategies.put(key, strategy);
    }

    public void processPayment(Order order, PaymentStrategy strategy) {
        strategy.processPayment(order);
    }
}
