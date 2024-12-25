package StatePattern;

import OrderManagement.Order;
import OrderManagement.OrderStatus;

public class PlacedState extends OrderState {
    public PlacedState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.PLACED;
    }

    @Override
    public void prepare() {
        order.setState(new InPreparationState(order));
        System.out.println("Order " + order.getOrderId() + " is now in preparation.");
    }

    @Override
    public void deliver() {
        throw new IllegalStateException("Order is not ready for delivery yet.");
    }
}
