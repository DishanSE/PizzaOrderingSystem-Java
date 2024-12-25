package ChainOfResponsiility;

import OrderManagement.Order;

public class CrustHandler extends OrderCustomizationHandler {
    @Override
    protected void processRequest(Order order) {
        System.out.println("Processing crust customization for order " + order.getOrderId());
        handleRequest(order);
    }
}
