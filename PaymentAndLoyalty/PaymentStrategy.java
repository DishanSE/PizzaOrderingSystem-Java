package PaymentAndLoyalty;

import OrderManagement.Order;

public interface PaymentStrategy {
    void processPayment(Order order);
}
