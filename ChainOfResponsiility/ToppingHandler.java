package ChainOfResponsiility;

import OrderManagement.Order;

public class ToppingHandler extends OrderCustomizationHandler {
    @Override
    protected void processRequest(Order order) {
        System.out.println("Processing topping customization for order " + order.getOrderId());
        handleRequest(order);
    }
}