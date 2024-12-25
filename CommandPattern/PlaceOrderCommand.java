package CommandPattern;

import OrderManagement.Order;
import OrderTracking.OrderTracker;

public class PlaceOrderCommand implements Command {
    private Order order;
    private OrderTracker orderTracker;
    
    public PlaceOrderCommand() {}

    public PlaceOrderCommand(Order order, OrderTracker orderTracker) {
        this.order = order;
        this.orderTracker = orderTracker;
    }
    
    @Override
    public void execute() {
        orderTracker.addOrder(order);
        System.out.println("Order " + order.getOrderId() + " placed successfully.");
    }
}
