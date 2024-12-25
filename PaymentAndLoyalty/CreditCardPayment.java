package PaymentAndLoyalty;

import OrderManagement.Order;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public void processPayment(Order order) {
        System.out.println("Processing credit card payment for order " + order.getOrderId());
    }
}
