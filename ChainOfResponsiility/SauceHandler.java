package ChainOfResponsiility;

import OrderManagement.Order;

public class SauceHandler extends OrderCustomizationHandler {
    @Override
    protected void processRequest(Order order) {
        System.out.println("Processing sauce customization for order " + order.getOrderId());
        handleRequest(order);
    }
}
