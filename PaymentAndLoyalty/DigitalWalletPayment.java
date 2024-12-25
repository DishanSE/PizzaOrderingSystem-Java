package PaymentAndLoyalty;

import OrderManagement.Order;

public class DigitalWalletPayment implements PaymentStrategy {
    @Override
    public void processPayment(Order order) {
        System.out.println("Processing digital wallet for order " + order.getOrderId());
    }
}
