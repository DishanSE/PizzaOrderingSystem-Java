package StatePattern;

import OrderManagement.Order;
import OrderManagement.OrderStatus;

public class OutForDeliveryState extends OrderState {
    public OutForDeliveryState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.OUT_FOR_DELIVERY;
    }

    @Override
    public void prepare() {
        throw new IllegalStateException("Order is already out of delivery.");
    }

    @Override
    public void deliver() {
        order.setState(new DeliverdState(order));
        System.out.println("Order " + order.getOrderId() + " has been deliverd.");
    }
}
